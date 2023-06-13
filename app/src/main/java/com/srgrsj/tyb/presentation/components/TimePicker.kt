package com.srgrsj.tyb.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.commandiron.wheel_picker_compose.core.WheelTextPicker
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.WheelPicker

@Composable
fun TimePicker(
    value: MutableState<Long>
) {
    val minList = generateListOfNumbers(0, 59)
    val secList = generateListOfNumbers(1, 59)

    var min by remember { mutableStateOf(0) }

    var sec by remember { mutableStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        WheelTextPicker(
            texts = minList,
            rowCount = 1,
            size = DpSize(128.dp, 50.dp),
            style = AppTheme.typography.textFieldStyle,
            color = AlphaWhiteColor,
            onScrollFinished = { snappedIndex ->
                min = minList[snappedIndex].toInt()
                value.value = convertToMilliseconds(min, sec)
                null
            },
            selectorProperties = WheelPickerDefaults.selectorProperties(
                color = WheelPicker,
                border = BorderStroke(1.dp, Color.Black)
            )
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 5.dp),
            text = ":",
            style = AppTheme.typography.text20sp,
            color = AlphaWhiteColor
        )

        WheelTextPicker(
            texts = secList,
            rowCount = 1,
            size = DpSize(128.dp, 50.dp),
            style = AppTheme.typography.textFieldStyle,
            color = AlphaWhiteColor,
            onScrollFinished = { snappedIndex ->
                sec = secList[snappedIndex].toInt()
                value.value = convertToMilliseconds(min, sec)
                null
            },
            selectorProperties = WheelPickerDefaults.selectorProperties(
                color = WheelPicker,
                border = BorderStroke(1.dp, Color.Black)
            )
        )
    }
}

fun generateListOfNumbers(from: Int, to: Int): List<String> {
    val listOfNumbers = mutableListOf<String>()
    for (i in from..to) {
        listOfNumbers.add(i.toString())
    }
    return listOfNumbers
}

fun convertToMilliseconds(min: Int, sec: Int): Long {
    val totalSeconds = min * 60 + sec
    return totalSeconds * 1000L
}
