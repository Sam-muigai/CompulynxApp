package com.app.compulynx.features.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.app.compulynx.features.authentication.navigation.Login

data object Home : NavKey

fun EntryProviderScope<NavKey>.homeEntry(backStack: NavBackStack<NavKey>) {
    entry<Home> {

    }
}