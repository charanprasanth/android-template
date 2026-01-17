package com.xlorit.template.navigation

sealed class Route(val path: String) {
    object Login : Route("login")
    object Home : Route("home")
}