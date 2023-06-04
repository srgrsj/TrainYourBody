package com.srgrsj.tyb.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.srgrsj.tyb.presentation.theme.AppTheme

@Composable
fun Contact(avatar: Int, name: String, lastMessage: String) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(300.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .width(75.dp)
                    .fillMaxHeight()
            ) {

                ChatAvatar(avatar = avatar)
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()

            ) {

                Spacer(modifier = Modifier.height(10.dp))

                Row() {
                    Text(
                        text = name,
                        style = AppTheme.typography.name
                    )
                }

                Row() {
                    Text(
                        text = lastMessage,
                        style = AppTheme.typography.text16sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}