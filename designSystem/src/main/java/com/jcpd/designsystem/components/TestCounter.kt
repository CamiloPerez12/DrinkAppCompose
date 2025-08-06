package com.jcpd.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TestCounter(
    value: Int,
    updateValue: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Counter value: $value",
                fontSize = 24.sp
            )
            Row {
                IconButton(
                    onClick = {
                        updateValue(value - 1)
                    },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "-",
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                }
                IconButton(
                    onClick = {
                        updateValue(value + 1)
                    },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "+",
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun TestCounterPreview() {
    TestCounter(value = 0)
}