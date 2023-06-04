package com.srgrsj.tyb.domain.exercise.readyExercisesData

import android.app.Application
import android.content.Context
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.exercise.model.Exercise
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.coroutines.coroutineContext

enum class ReadyExercises(val exercise: Exercise) {
    X_UPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/x-апы.mp4?alt=media&token=95d0eaed-bd58-433f-af7f-58f8578afb99&_gl=1*1qcgkkf*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQwMjEuMC4wLjA."
        )
    ),
    BUTTERFLY_MACHINE(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/бабочка.mp4?alt=media&token=6a550a01-33d4-4a51-a0c9-5e76cf0d3071&_gl=1*3c4h1f*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQwMzcuMC4wLjA."
        )
    ),
    BURPEE(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/берпи.mp4?alt=media&token=8f32dc88-5382-4b5f-ae79-28befa0e2fdb&_gl=1*z1w9y0*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQwNDYuMC4wLjA."
        )
    ),
    DIPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/брусья.mp4?alt=media&token=41bb1a5c-729e-497b-981a-783900ad14bd&_gl=1*1gcc8eu*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQwNTkuMC4wLjA."
        )
    ),
    LUNGES(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/выпады.mp4?alt=media&token=7e6163e6-ce8e-4b1f-8eb4-577ddbd9091d&_gl=1*6jwtdi*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQwNzQuMC4wLjA."
        )
    ),
    HYPEREXTENSION(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/гиперэкстензия.mp4?alt=media&token=63f9692b-dd42-4652-ada3-57fe36d454db&_gl=1*18shvlq*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQwODAuMC4wLjA."
        )
    ),
    DEVILS_PRESS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/дьявольский%20жим.mp4?alt=media&token=aab6c0f5-2a4e-476a-a7f4-74caed217c30&_gl=1*1r18dyh*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQwODguMC4wLjA."
        )
    ),
    OVERHEAD_DUMBBELL_PRESS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/жим%20гантелей%20вверх.mp4?alt=media&token=3955a9e6-50fb-4d50-9862-a92efd8acbaa&_gl=1*gm4tex*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQwOTYuMC4wLjA."
        )
    ),
    DUMBBELL_FLYES(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/жим%20гантелей%20со%20сведением.mp4?alt=media&token=1a30085f-9390-46d0-846e-27cf4685c0f1&_gl=1*v3pvyy*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxMDMuMC4wLjA."
        )
    ),
    DUMBBELL_PRESS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/жим%20гантелей.mp4?alt=media&token=5962a934-5c46-47e5-8307-d498854c2801&_gl=1*b7k2rn*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxMDguMC4wLjA."
        )
    ),
    KETTLEBELL_SWINGS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/махи%20гири.mp4?alt=media&token=e6aa324d-6c30-4e25-b438-a444016e2b40&_gl=1*143rrvb*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxMjUuMC4wLjA."
        )
    ),
    REVERSE_PUSH_UPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/обратные%20отжимания.mp4?alt=media&token=1f025b77-9eab-4370-b073-0afe61a48734&_gl=1*13ae7u*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxMzIuMC4wLjA."
        )
    ),
    BEHIND_THE_NECK_DUMBBELL_PRESS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/опускание%20гантели%20за%20спину.mp4?alt=media&token=260f8ce2-d83d-4461-b749-36fdd0fa8b85&_gl=1*f1wzib*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxMzguMC4wLjA."
        )
    ),
    CLAP_PUSH_UPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/отжимания%20с%20хлопком.mp4?alt=media&token=39dd14f3-fd7e-4c2d-91d7-f2b6dbbea760&_gl=1*q4t7py*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxNDQuMC4wLjA."
        )
    ),
    PUSH_UPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/отжимания.mp4?alt=media&token=19fbdb75-1aff-4ab6-af83-a702d7ec4bd2&_gl=1*1ns8wad*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxNTAuMC4wLjA."
        )
    ),
    PLANK_WITH_SHOULDER_TAPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/планка%20с%20касанием%20плеча.mp4?alt=media&token=6b3cf124-1351-4bb5-a5a4-f6826ddb1b40&_gl=1*17iyi6w*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxNTcuMC4wLjA."
        )
    ),
    PLANK_WITH_HIP_ROTATIONS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/планка%20с%20переходами.mp4?alt=media&token=fdc915de-f7d9-44ec-9f9d-5d604e6bbd59&_gl=1*1evc8o1*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxNjMuMC4wLjA."
        )
    ),
    PLANK(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/планка.mp4?alt=media&token=c0783900-3b5d-46b0-8a45-f9339ee27eea&_gl=1*1w011me*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxNjkuMC4wLjA."
        )
    ),
    PULL_UPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/подтягивания.mp4?alt=media&token=b7c93fac-8704-485a-b10e-6514e7419e39&_gl=1*pht9kt*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxNzkuMC4wLjA."
        )
    ),
    DUMBBELL_BICEP_CURLS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/подьемы%20гантелей%20на%20бицепс.mp4?alt=media&token=963ebd1e-bfe8-4ce7-9665-3f46cd8c36e0&_gl=1*rctgg8*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQxOTUuMC4wLjA."
        )
    ),
    KETTLEBELL_CHIN_UPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/подьемы%20гири%20к%20подбороку.mp4?alt=media&token=8d6652ae-7570-4232-af9e-657c1a490e71&_gl=1*prkt41*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyMDMuMC4wLjA."
        )
    ),
    LEG_RAISES(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/подьемы%20ног.mp4?alt=media&token=cc6e7d73-c296-4854-b2fd-416547e1e700&_gl=1*1lmuzt8*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyMTQuMC4wLjA."
        )
    ),
    ALTERNATING_DUMBBELL_FRONT_RAISES(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/поочередные%20подьемы%20гантелей%20перед%20собой.mp4?alt=media&token=0a43ef9e-72fc-462f-a6e9-aca25c4fe812&_gl=1*11sl7zk*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyMjMuMC4wLjA."
        )
    ),
    FLOOR_PRESS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/пресс%20на%20полу.mp4?alt=media&token=582f1cdb-b932-48cb-968f-274216c439c6&_gl=1*2d4ctl*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyMzYuMC4wLjA."
        )
    ),
    REGULAR_CRUNCH(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/пресс%20обычный.mp4?alt=media&token=d43cce34-e6c5-4b8f-9a12-3c58beccaf4b&_gl=1*c03es1*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyNDMuMC4wLjA."
        )
    ),
    ALTERNATING_LEG_RAISE_CRUNCH(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/пресс%20с%20поочередным%20подьемом%20ног.mp4?alt=media&token=a7c35dc4-9f44-4bba-8927-cfe7eda2fc68&_gl=1*189isic*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyNTAuMC4wLjA."
        )
    ),
    JUMP_SQUATS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/приседания%20с%20прыжком.mp4?alt=media&token=6ebf42a5-815c-4c85-9fee-15a776dbec75&_gl=1*11t6kq6*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyNjEuMC4wLjA."
        )
    ),
    SQUATS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/приседания.mp4?alt=media&token=e16db9fc-b274-4a21-b8ba-bbd6e11bf9b5&_gl=1*1id25c3*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyNjguMC4wLjA."
        )
    ),
    TUCK_JUMPS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/прыжки%20с%20подьемом%20рук.mp4?alt=media&token=3e1f69f6-7b21-43cd-b519-6b383f4f3e01&_gl=1*s08qia*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyNzcuMC4wLjA."
        )
    ),
    DUMBBELL_LATERAL_RAISES(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/разведения.mp4?alt=media&token=de97cd56-829c-414f-a84a-3751229e6f62&_gl=1*1xj44hi*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyODYuMC4wLjA."
        )
    ),
    THRUSTERS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/трастеры.mp4?alt=media&token=7de243f0-cdbf-46d4-9aa7-b074a04ccb62&_gl=1*xwc7an*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQyOTMuMC4wLjA."
        )
    ),
    LAT_PULLDOWN(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/тяга%20верхнего%20блока.mp4?alt=media&token=0cc766f9-565a-4d94-99da-dfff2e0a8a29&_gl=1*15w4ebl*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQzMDEuMC4wLjA."
        )
    ),
    BENT_OVER_DUMBBELL_ROWS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/тяга%20гантелей%20в%20наклоне.mp4?alt=media&token=da8e9848-b181-4fea-abb3-c194cfc4d27c&_gl=1*1jc44bm*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQzMDkuMC4wLjA."
        )
    ),
    KETTLEBELL_ROW(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/тяга%20гири.mp4?alt=media&token=310f2e37-95df-4adc-b61b-265daa9f2d3d&_gl=1*w35von*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQzMTguMC4wLjA."
        )
    ),
    CABLE_PULL_THROUGH(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/тяга%20нижнего%20блока.mp4?alt=media&token=1fab50b6-c03d-485f-bbd0-7428c36f20f8&_gl=1*1k14z4m*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQzMjUuMC4wLjA."
        )
    ),
    SINGLE_ARM_DUMBBELL_ROW(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/тяга%20одной%20ганетли.mp4?alt=media&token=29488964-2d5c-4cf2-b267-654740717360&_gl=1*hxvu2d*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQzMzEuMC4wLjA."
        )
    ),
    CLOSE_GRIP_DUMBBELL_PRESS(
        Exercise(
            demonstration = "https://firebasestorage.googleapis.com/v0/b/sportapp-74f15.appspot.com/o/узкий%20жим%20гантелей.mp4?alt=media&token=57db87f8-97a1-4663-89e0-28a6af4f0366&_gl=1*t47s2*_ga*MTk4NDA3NzQ0OC4xNjg0NjY4NjU0*_ga_CW55HF8NVT*MTY4NTU1Mjg1NS4xNC4xLjE2ODU1NTQzMzcuMC4wLjA."
        )
    );

    fun getLocalizedExercise(context: Context): Exercise {
        val exerciseTitle = when (this) {
            X_UPS -> context.getString(R.string.x_ups)
            BUTTERFLY_MACHINE -> context.getString(R.string.butterfly_machine)
            BURPEE -> context.getString(R.string.burpee)
            DIPS -> context.getString(R.string.parallel_bars_dip_bars)
            LUNGES -> context.getString(R.string.lunges)
            HYPEREXTENSION -> context.getString(R.string.hyperextension)
            DEVILS_PRESS -> context.getString(R.string.devils_press)
            OVERHEAD_DUMBBELL_PRESS -> context.getString(R.string.overhead_dumbbell_press)
            DUMBBELL_FLYES -> context.getString(R.string.dumbbell_flyes)
            DUMBBELL_PRESS -> context.getString(R.string.dumbbell_press)
            KETTLEBELL_SWINGS -> context.getString(R.string.kettlebell_swings)
            REVERSE_PUSH_UPS -> context.getString(R.string.reverse_push_ups)
            BEHIND_THE_NECK_DUMBBELL_PRESS -> context.getString(R.string.behind_the_neck_dumbbell_press)
            CLAP_PUSH_UPS -> context.getString(R.string.clap_push_ups)
            PUSH_UPS -> context.getString(R.string.push_ups)
            PLANK_WITH_SHOULDER_TAPS -> context.getString(R.string.plank_with_shoulder_taps)
            PLANK_WITH_HIP_ROTATIONS -> context.getString(R.string.plank_with_hip_rotations)
            PLANK -> context.getString(R.string.plank)
            PULL_UPS -> context.getString(R.string.pull_ups)
            DUMBBELL_BICEP_CURLS -> context.getString(R.string.dumbbell_bicep_curls)
            KETTLEBELL_CHIN_UPS -> context.getString(R.string.kettlebell_chin_ups)
            LEG_RAISES -> context.getString(R.string.leg_raises)
            ALTERNATING_DUMBBELL_FRONT_RAISES -> context.getString(R.string.alternating_dumbbell_front_raises)
            FLOOR_PRESS -> context.getString(R.string.floor_press)
            REGULAR_CRUNCH -> context.getString(R.string.regular_crunch)
            ALTERNATING_LEG_RAISE_CRUNCH -> context.getString(R.string.alternating_leg_raise_crunch)
            JUMP_SQUATS -> context.getString(R.string.jump_squats)
            SQUATS -> context.getString(R.string.squats)
            TUCK_JUMPS -> context.getString(R.string.tuck_jumps)
            DUMBBELL_LATERAL_RAISES -> context.getString(R.string.dumbbell_lateral_raises)
            THRUSTERS -> context.getString(R.string.thrusters)
            LAT_PULLDOWN -> context.getString(R.string.lat_pulldown)
            BENT_OVER_DUMBBELL_ROWS -> context.getString(R.string.bent_over_dumbbell_rows)
            KETTLEBELL_ROW -> context.getString(R.string.kettlebell_row)
            CABLE_PULL_THROUGH -> context.getString(R.string.cable_pull_through)
            SINGLE_ARM_DUMBBELL_ROW -> context.getString(R.string.single_arm_dumbbell_row)
            CLOSE_GRIP_DUMBBELL_PRESS -> context.getString(R.string.close_grip_dumbbell_press)
        }

        return this.exercise.copy(title = exerciseTitle)
    }
}