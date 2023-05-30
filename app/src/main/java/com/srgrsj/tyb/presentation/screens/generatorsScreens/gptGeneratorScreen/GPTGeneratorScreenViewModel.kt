package com.srgrsj.tyb.presentation.screens.generatorsScreens.gptGeneratorScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.*
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import com.srgrsj.tyb.domain.exercise.usecase.ExerciseUseCase
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import com.srgrsj.tyb.presentation.screens.generatorsScreens.GeneratorsScreenViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class GPTGeneratorScreenViewModel @Inject constructor(
    workoutUseCase: WorkoutUseCase
) : GeneratorsScreenViewModel(workoutUseCase) {

    var gptQuery by mutableStateOf("")

    fun generateGptQuery(
        muscleGroups: String,
        desiredDurationOfTraining: String,
        additionalWorkoutRequirements: String
    ) {
        gptQuery = """
        Сгенерируй тренировку на эти группы мышц:
        $muscleGroups
        На полное выполнение которой, потребоавлось бы следующее время:
        $desiredDurationOfTraining
        Дополнительные требования к тренировку (могут отсутствовать):
        $additionalWorkoutRequirements
        
        
        Ответ должен содержать только json экземпляр следующих data классов:
        
        data class Workout(
            var title: String? = "",
            var description: String? = "",
            var duration: Long,
            var exerciseList: List<Exercise> = listOf(),
        )
        
        data class Exercise(
            var title: String? = "",
            var description: String? = "",
            var numberOfRepetitions: Int,
            var numberOfCircles: Int,
            var durationOfOneCircle: Long,
            var durationOfRest: Long,
            var exerciseType: ExerciseType,
        )

        enum class ExerciseType {
            TIME, REPETITION
        }
        
        Пояснения к полям классов:
        
        Workout:
            title - название тренировки
            description - описание тренировки
            duration - примерная продолжительность тренировки
        
        Exercise:
            title - название упражнения
            description - описание упражнения
            numberOfRepetitions - количество повторений за один круг
            numberOfCircles - количество кругов
            durationOfOneCircle - продолжительность одного круга, в миллисекундах
            durationOfRest - продолжительность отдыха между кругами
        
        экземпляр класса Exercise может быть двух типов:
        TIME - Упраженения, которые нужно выполять на время
        REPETITIONS - Упражнения, которые нужно выполнять на повторения
        
        в description, для каждого Exercise, подробно опиши процесс выполнения этого упражнения
        
        Время нужно указывать в миллискундах, а так же время одного круга на упражнение не должно быть не меньше 30 секунд
         
    """.trimIndent()
    }

    var gptResponse by mutableStateOf("")
    var gson = Gson()


    private val _generatedWorkout = MutableLiveData<Workout>()
    val generatedWorkout: LiveData<Workout> = _generatedWorkout

    private fun updateGeneratedWorkout(newValue: Workout) {
        _generatedWorkout.value = newValue
        println(_generatedWorkout.value)
        setIsWorkoutGenerateTrue()
    }

    @OptIn(BetaOpenAI::class)
    fun getGPTResponse() {

        println(gptQuery)

        viewModelScope.launch {
            val openAI = OpenAI(
                OpenAIConfig(
                    token = CHAT_GPT_API_KEY,
                    timeout = Timeout(socket = 5.minutes)
                )
            )

            try {
                val chatCompletionRequest = ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"),
                    messages = listOf(
                        ChatMessage(
                            role = ChatRole.User,
                            content = gptQuery
                        )
                    )
                )

                val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)

                val response = completion.choices.first().message?.content

                gptResponse = response ?: ""

                println(
                    "-------------- \n" +
                            "$response \n" +
                            "_______________"
                )

                gson.fromJson(response, Workout::class.java)?.let {
                    updateGeneratedWorkout(it)
                }
            } catch (e: Exception) {
                gptResponse = "ERROR: ${e.message ?: ""}"
            }
        }
    }

    companion object {
        const val CHAT_GPT_API_KEY = ""
    }


    private val _isWorkoutGenerate = MutableStateFlow(false)
    val isWorkoutGenerate: StateFlow<Boolean> =
        _isWorkoutGenerate.asStateFlow()

    fun setIsWorkoutGenerateTrue() {
        _isWorkoutGenerate.value = true
    }

    fun setIsWorkoutGenerateFalse() {
        _isWorkoutGenerate.value = false
    }

}
