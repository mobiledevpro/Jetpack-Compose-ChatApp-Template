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

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobiledevpro.home.HomeScreen
import com.mobiledevpro.onboarding.OnBoardingScreen
import com.mobiledevpro.subscription.SubscriptionScreen


fun NavController.navigateTo(
    screen: Screen,
    // navOptions: NavOptions? = null,
    clearBackStack: Boolean? = false
) {

    val currentRoute: String? = this.currentBackStackEntry?.destination?.route

    navigate(screen.route) {

        //Clearing back stack if needed
        if (clearBackStack == true && !currentRoute.isNullOrEmpty())
            popUpTo(currentRoute) {
                inclusive = true
            }

        //Animating transition between screens
        /*
                anim {
                   // enter = android.R.anim.slide_in_left
                    enter = android.R.anim.slide_in_left
                }

         */
    }
}

fun NavGraphBuilder.homeScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Home.route
    ) {
        HomeScreen(
            onOpenSubscriptionScreen = { onNavigateTo(Screen.Subscription) }
        )
    }
}

fun NavGraphBuilder.onBoardingScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.OnBoarding.route
    ) {
        OnBoardingScreen(
            onNext = { onNavigateTo(Screen.Home) }
        )
    }
}

fun NavGraphBuilder.subscriptionScreen(onNavigateBack : () -> Unit) {
    composable(
        route = Screen.Subscription.route
    ) {
        SubscriptionScreen(onNavigateBack)
    }
}