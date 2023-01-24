/*
 * Copyright 2022 | Dmitri Chernysh | https://mobile-dev.pro
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mobiledevpro.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

const val navigationRouteOnBoarding = "on_boarding"
const val navigationRouteHome = "home"
const val navigationRouteChatList = "chat_list"
const val navigationRoutePeopleList = "people_list"
const val navigationRouteProfile = "profile"
const val navigationRouteSubscription = "subscription"

sealed class Screen(
    val route: String,
    var clearBackStack: Boolean = false,
    val title: String? = null,
    val icon: ImageVector? = null
) {

    fun withClearBackStack() = apply { clearBackStack = true }

    object OnBoarding : Screen(navigationRouteOnBoarding)
    object Home : Screen(navigationRouteHome)
    object ChatList :
        Screen(route = navigationRouteChatList, title = "Chats", icon = Icons.Rounded.Home)

    object PeopleList :
        Screen(route = navigationRoutePeopleList, title = "People", icon = Icons.Rounded.Person)

    object Profile :
        Screen(route = navigationRouteProfile, title = "Profile", icon = Icons.Rounded.Settings)

    object Subscription : Screen(navigationRouteSubscription)
}