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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobiledevpro.chatlist.view.ChatListScreen
import com.mobiledevpro.home.view.HomeScreen
import com.mobiledevpro.navigation.ext.navigateTo
import com.mobiledevpro.navigation.graph.HomeNavGraph
import com.mobiledevpro.navigation.graph.OnBoardingNavGraph
import com.mobiledevpro.onboarding.view.OnBoardingFirstScreen
import com.mobiledevpro.onboarding.view.OnBoardingScreen
import com.mobiledevpro.onboarding.view.OnBoardingSecondScreen
import com.mobiledevpro.onboarding.view.OnBoardingThirdScreen
import com.mobiledevpro.peoplelist.view.PeopleListScreen
import com.mobiledevpro.profile.view.ProfileScreen
import com.mobiledevpro.subscription.SubscriptionScreen


fun NavGraphBuilder.homeNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.Home.route
    ) {

        //NavController for nested graph
        //It doesn't work for root graph
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
                modifier = Modifier.safeContentPadding(),
                onNavigateToRoot = onNavigateToRoot
            )
        }

        HomeScreen(
            bottomBar = bottomBar,
            nestedNavGraph = nestedNavGraph
        )
    }

}

fun NavGraphBuilder.onBoardingNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.OnBoarding.route
    ) {

        val navController = rememberNavController()

        val nestedNavGraph: @Composable () -> Unit = {
            OnBoardingNavGraph(
                navController = navController,
                modifier = Modifier.safeContentPadding()
            )
        }

        OnBoardingScreen(
            nestedNavGraph,
            onNext = {
                when (navController.currentDestination?.route) {
                    Screen.OnBoardingFirst.route -> navController.navigateTo(Screen.OnBoardingSecond)
                    Screen.OnBoardingSecond.route -> navController.navigateTo(Screen.OnBoardingThird)
                    Screen.OnBoardingThird.route -> Screen.Home.withClearBackStack()
                        .also(onNavigateToRoot)

                    else -> {}
                }
            }
        )
    }
}

fun NavGraphBuilder.onBoardingFirstScreen() {
    composable(
        route = Screen.OnBoardingFirst.route
    ) {
        OnBoardingFirstScreen()
    }
}

fun NavGraphBuilder.onBoardingSecondScreen() {
    composable(
        route = Screen.OnBoardingSecond.route
    ) {
        OnBoardingSecondScreen()
    }
}

fun NavGraphBuilder.onBoardingThirdScreen() {
    composable(
        route = Screen.OnBoardingThird.route
    ) {
        OnBoardingThirdScreen()
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

fun NavGraphBuilder.profileScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Profile.route
    ) {
        ProfileScreen(
            onNavigateToSubscription = {
                onNavigateTo(Screen.Subscription)
            }
        )
    }
}

