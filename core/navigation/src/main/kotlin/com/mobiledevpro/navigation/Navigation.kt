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

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobiledevpro.chatlist.view.ChatListScreen
import com.mobiledevpro.home.view.HomeScreen
import com.mobiledevpro.navigation.graph.HomeNavGraph
import com.mobiledevpro.onboarding.OnBoardingScreen
import com.mobiledevpro.peoplelist.view.PeopleListScreen
import com.mobiledevpro.profile.view.ProfileScreen
import com.mobiledevpro.subscription.SubscriptionScreen


fun NavController.navigateTo(
    screen: Screen
) {

    val currentRoute: String? = this.currentBackStackEntry?.destination?.route

    navigate(screen.route) {

        //Clearing back stack if needed
        if (screen.clearBackStack && !currentRoute.isNullOrEmpty())
            popUpTo(currentRoute) {
                inclusive = true
            }
    }
}

fun NavGraphBuilder.homeNavGraph() {
    composable(
        route = Screen.Home.route
    ) {

        val navController = rememberNavController()

        val bottomBar: @Composable () -> Unit = {
            HomeBottomNavigation(
                screens = listOf(
                    Screen.ChatList,
                    Screen.PeopleList,
                    Screen.Profile
                ), onNavigateTo = navController::navigateTo,
                currentDestination = null
            )
        }

        val nestedNavGraph: @Composable () -> Unit = {
            HomeNavGraph(
                navController = navController,
                modifier = Modifier.safeContentPadding()
            )
        }

        HomeScreen(
            bottomBar = bottomBar,
            nestedNavGraph = nestedNavGraph
        )
    }

}

fun NavGraphBuilder.onBoardingScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.OnBoarding.route
    ) {
        OnBoardingScreen(
            onNext = { onNavigateTo(Screen.Home.withClearBackStack()) }
        )
    }
}

fun NavGraphBuilder.subscriptionScreen(onNavigateBack: () -> Unit) {
    composable(
        route = Screen.Subscription.route
    ) {
        SubscriptionScreen(onNavigateBack)
    }
}

fun NavGraphBuilder.chatListScreen() {
    composable(
        route = Screen.ChatList.route
    ) {
        ChatListScreen()
    }
}

fun NavGraphBuilder.peopleListScreen() {
    composable(
        route = Screen.PeopleList.route
    ) {
        PeopleListScreen()
    }
}

fun NavGraphBuilder.profileScreen() {
    composable(
        route = Screen.Profile.route
    ) {
        ProfileScreen()
    }
}

