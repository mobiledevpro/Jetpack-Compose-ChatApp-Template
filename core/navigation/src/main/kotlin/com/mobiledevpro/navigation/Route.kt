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

const val navigationRouteOnBoarding = "on_boarding"
const val navigationRouteHome = "home"
const val navigationRouteChatList = "chat_list"
const val navigationRoutePeopleList = "people_list"
const val navigationRouteProfile = "profile"
const val navigationRouteSubscription = "subscription"

sealed class Screen(val route: String) {
    internal var clearBackStack : Boolean = false

    object OnBoarding : Screen(navigationRouteOnBoarding)
    object Home : Screen(navigationRouteHome)
    object ChatList : Screen(navigationRouteChatList)
    object PeopleList : Screen(navigationRoutePeopleList)
    object Profile : Screen(navigationRouteProfile)
    object Subscription : Screen(navigationRouteSubscription)
}