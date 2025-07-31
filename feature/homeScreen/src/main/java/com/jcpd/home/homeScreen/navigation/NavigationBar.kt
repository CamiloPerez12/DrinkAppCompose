package com.jcpd.home.homeScreen.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jcpd.designsystem.theme.BgLoginColor
import com.jcpd.home.R
import com.jcpd.home.homeScreen.HOME_ROUTE

data class Destination(
    val name: String,
    val route: String,
    @DrawableRes val icon: Int,
    val contentDescription: String
)

object NavigationBarDestinations {
    val destinations = listOf(
        Destination(
            name = "Home",
            route = HOME_ROUTE,
            icon = R.drawable.coctail_svgrepo_com,
            contentDescription = "Home"
        ),
        Destination(
            name = "Profile",
            route = "",
            icon = R.drawable.coctail_svgrepo_com,
            contentDescription = "Profile"
        ),
        Destination(
            name = "Settings",
            route = "",
            icon = R.drawable.coctail_svgrepo_com,
            contentDescription = "Settings"
        )
    )
}

@Composable
fun GetNavigationBar() {
    NavigationBar(
        windowInsets = NavigationBarDefaults.windowInsets,
        containerColor = BgLoginColor,
    ) {
        NavigationBarDestinations.destinations.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = true,
                onClick = {
                },
                icon = {
                    Icon(
                        modifier = Modifier.requiredSize(24.dp),
                        painter = painterResource(id = destination.icon),
                        contentDescription = destination.contentDescription,
                        tint = Color.Yellow,
                    )
                },
                label = { Text(color = Color.Yellow, text = destination.name) }
            )
        }
    }
}