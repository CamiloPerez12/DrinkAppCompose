package com.jcpd.drinkapp.navigation


object LoginScreenRoute {
    const val route = "login_screen"
}

object HomeScreenRoute {
    const val route = "home_screen"
}

object RegisterScreenRoute {
    const val route = "register_screen"
}

// If you had routes with arguments, they might look like:
// data class ProfileScreenRoute(val userId: String) {
//    companion object {
//        const val baseRoute = "profile_screen"
//        fun destination(userId: String) = "$baseRoute/$userId"
//    }
// }
