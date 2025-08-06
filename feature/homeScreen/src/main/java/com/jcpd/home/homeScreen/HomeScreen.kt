package com.jcpd.home.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.jcpd.designsystem.components.TestCounter
import com.jcpd.features.composables.DACard
import com.jcpd.home.R
import com.jcpd.home.homeScreen.navigation.GetNavigationBar

const val HOME_ROUTE = "home_route"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val protoState by viewModel.preferencesStateFlow.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Drinks App", color = Color.Yellow, fontSize = 24.sp)
                },
                actions = {
                    IconButton(onClick = { navController.navigate("login_screen") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.coctail_svgrepo_com),
                            tint = Color.Yellow,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
        bottomBar = {
            GetNavigationBar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .background(Color.White),
        ) {
            item {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Hi ${protoState?.name}",
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    Checkbox(
                        checked = protoState?.counterFlag == true,
                        onCheckedChange = {
                            viewModel.toggleCounter()
                        },
                    )
                }
                AnimatedVisibility(protoState?.counterFlag == true) {
                    TestCounter(
                        value = protoState?.counter ?: 0,
                        updateValue = {
                            viewModel.updateCounterValue(it)
                        }
                    )
                }
            }
            item {
                Text(text = "Cocktail of the Day", color = Color.Yellow, fontSize = 18.sp)
                AsyncImage(
                    model = uiState.data?.listDrink[0]?.strDrinkThumb,
                    contentDescription = uiState.data?.listDrink[0]?.strDrink,
                )
                Text(text = "Coctail Name", color = Color.Yellow, fontSize = 18.sp)
                LazyRow(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.Yellow)
                        .padding(vertical = 24.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            AsyncImage(
                                model = uiState.data?.listDrink[0]?.strDrinkThumb,
                                contentDescription = uiState.data?.listDrink[0]?.strDrink,
                            )
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            AsyncImage(
                                model = uiState.data?.listDrink[0]?.strDrinkThumb,
                                contentDescription = uiState.data?.listDrink[0]?.strDrink,
                            )
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            AsyncImage(
                                model = uiState.data?.listDrink[0]?.strDrinkThumb,
                                contentDescription = uiState.data?.listDrink[0]?.strDrink,
                            )
                        }
                    }
                    item {
                        DACard(text = "DACard")
                    }
                }
            }
        }
    }
}
