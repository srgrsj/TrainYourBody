package com.srgrsj.tyb.presentation.screens.generatorsScreens.gptGeneratorScreen

import android.content.Context
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
import com.google.gson.Gson
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import com.srgrsj.tyb.presentation.screens.generatorsScreens.GeneratorsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

@HiltViewModel
class GPTGeneratorScreenViewModel @Inject constructor(
    workoutUseCase: WorkoutUseCase,
) : GeneratorsScreenViewModel(workoutUseCase) {

    private var gptQuery by mutableStateOf("")
    private var apiKey: String = ""


    fun generateGptQuery(
        context: Context,
        muscleGroups: String,
        desiredDurationOfTraining: String,
        additionalWorkoutRequirements: String
    ) {
        gptQuery = context.getString(
            R.string.generate_gpt_query_template,
            muscleGroups,
            desiredDurationOfTraining,
            additionalWorkoutRequirements
        )
    }

    private var gptResponse by mutableStateOf("")
    private var gson = Gson()


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
                    token = apiKey,
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

    private val _isWorkoutGenerate = MutableStateFlow(false)
    val isWorkoutGenerate: StateFlow<Boolean> =
        _isWorkoutGenerate.asStateFlow()

    private fun setIsWorkoutGenerateTrue() {
        _isWorkoutGenerate.value = true
    }

    fun setIsWorkoutGenerateFalse() {
        _isWorkoutGenerate.value = false
    }
}
