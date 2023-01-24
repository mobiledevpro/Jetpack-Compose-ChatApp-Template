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
package com.mobiledevpro.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mobiledevpro.navigation.Screen
import com.mobiledevpro.navigation.homeNavGraph
import com.mobiledevpro.navigation.navigateTo
import com.mobiledevpro.navigation.onBoardingScreen
import com.mobiledevpro.navigation.subscriptionScreen

/**
 * Top-level navigation host in the app
 *
 * Created on Jan 07, 2023.
 *
 */

@Composable
fun RootNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        route = "root_host",
        startDestination = startDestination.route,
        modifier = modifier,
    ) {

        val navigateBack: () -> Unit = {
            navController.navigateUp()
        }

        onBoardingScreen(onNavigateTo = navController::navigateTo)
        subscriptionScreen(onNavigateBack = navigateBack)

        //Home screen contains nested navigation with a bottom navigation
        homeNavGraph()

        //Nested Navigation Graph example
        /*navigation(
            route = Screen.Home.route,
            startDestination = Screen.ChatList.route
        ) {

            chatListScreen()
            peopleListScreen()
            profileScreen()
        }

         */

    }
}