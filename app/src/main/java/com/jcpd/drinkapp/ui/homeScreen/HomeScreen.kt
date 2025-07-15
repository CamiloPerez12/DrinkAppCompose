package com.jcpd.drinkapp.ui.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jcpd.drinkapp.R
import com.jcpd.drinkapp.ui.theme.BgLoginColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BgLoginColor)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BgLoginColor,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Drinks App", color = Color.Yellow, fontSize = 24.sp)
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.coctail_svgrepo_com),
                            tint = Color.Yellow,
                            contentDescription = "Back"
                        )
                    }
                },
            )
            Text(text = "Cocktail of the Day", color = Color.Yellow, fontSize = 18.sp)
            Image(
                painter = painterResource(id = R.drawable.drink_app_logo),
                contentDescription = "Cocktail of the Day"
            )
            Text(text = "Coctail Name", color = Color.Yellow, fontSize = 18.sp)
            Column (
                modifier = modifier
                    .fillMaxSize()
                    .background(BgLoginColor)
                    .padding(horizontal = 24.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Card {
                        Image(
                            painter = painterResource(id = R.drawable.coctail_svgrepo_com),
                            contentDescription = "Cocktail n"
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Card {
                        Image(
                            painter = painterResource(id = R.drawable.coctail_svgrepo_com),
                            contentDescription = "Cocktail n"
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Card {
                        Image(
                            painter = painterResource(id = R.drawable.coctail_svgrepo_com),
                            contentDescription = "Cocktail n"
                        )
                    }
                }
            }
        }
    }
}