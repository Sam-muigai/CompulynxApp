package com.app.compulynx.features.authentication.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.compulynx.features.authentication.login.LoginScreen
import com.app.compulynx.features.home.navigation.Home
import kotlinx.serialization.Serializable

@Serializable
data object Login : NavKey

fun EntryProviderScope<NavKey>.authenticationEntry(backStack: NavBackStack<NavKey>) {
    entry<Login> {
        LoginScreen(
            navigateToHome = {
                backStack.apply {
                    add(Home)
                    removeAll(listOf(Login))
                }
            },
            onSignUp = {
            },
            onBackClick = {
                backStack.removeLastOrNull()
            },
            onForgotPassword = {
            },
        )
    }
}
