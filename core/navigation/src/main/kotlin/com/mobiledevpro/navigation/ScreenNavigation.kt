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

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobiledevpro.chatlist.di.featureChatListModule
import com.mobiledevpro.chatlist.view.ChatListScreen
import com.mobiledevpro.chatlist.view.ChatListViewModel
import com.mobiledevpro.di.rememberViewModel
import com.mobiledevpro.home.view.HomeScreen
import com.mobiledevpro.home.view.HomeViewModel
import com.mobiledevpro.navigation.ext.navigateTo
import com.mobiledevpro.navigation.graph.HomeNavGraph
import com.mobiledevpro.navigation.graph.OnBoardingNavGraph
import com.mobiledevpro.navigation.graph.PeopleNavGraph
import com.mobiledevpro.onboarding.view.OnBoardingFirstScreen
import com.mobiledevpro.onboarding.view.OnBoardingScreen
import com.mobiledevpro.onboarding.view.OnBoardingSecondScreen
import com.mobiledevpro.onboarding.view.OnBoardingThirdScreen
import com.mobiledevpro.people.profile.view.PeopleProfileScreen
import com.mobiledevpro.people.profile.view.PeopleProfileViewModel
import com.mobiledevpro.people.profile.view.args.PeopleProfileArgs
import com.mobiledevpro.people.view.PeopleScreen
import com.mobiledevpro.peoplelist.di.featurePeopleListModule
import com.mobiledevpro.peoplelist.view.PeopleListScreen
import com.mobiledevpro.peoplelist.view.PeopleListViewModel
import com.mobiledevpro.subscription.SubscriptionScreen
import com.mobiledevpro.user.profile.di.featureUserProfileModule
import com.mobiledevpro.user.profile.view.ProfileScreen
import com.mobiledevpro.user.profile.view.vm.ProfileViewModel


fun NavGraphBuilder.homeNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.Home.route
    ) {
        Log.d("navigation", "------homeNavGraph:START------------")

        //NavController for nested graph
        //It will not work for root graph
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val bottomBar: @Composable () -> Unit = {
            Log.d("navigation", "homeNavGraph:bottomBar")
            HomeBottomNavigation(
                screens = listOf(
                    Screen.ChatList,
                    Screen.People,
                    Screen.Profile
                ), onNavigateTo = navController::navigateTo,
                currentDestination = navBackStackEntry?.destination
            )
        }

        val nestedNavGraph: @Composable () -> Unit = {
            Log.d("navigation", "homeNavGraph:nestedNavGraph")
            HomeNavGraph(
                navController = navController,
                onNavigateToRoot = onNavigateToRoot
            )
        }

        val viewModel: HomeViewModel = viewModel()

        HomeScreen(
            bottomBar = bottomBar,
            nestedNavGraph = nestedNavGraph
        )

        Log.d("navigation", "------homeNavGraph:END------------")
    }

}

fun NavGraphBuilder.onBoardingNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.OnBoarding.route
    ) {

        val navController = rememberNavController()

        val nestedNavGraph: @Composable () -> Unit = {
            OnBoardingNavGraph(
                navController = navController
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

fun NavGraphBuilder.peopleNavGraph() {
    composable(
        route = Screen.People.route
    ) {
        val navController = rememberNavController()

        val nestedNavGraph: @Composable () -> Unit = {
            PeopleNavGraph(
                navController = navController
            )
        }

        PeopleScreen(nestedNavGraph)
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

        val viewModel = rememberViewModel<ChatListViewModel>(
            modules = { listOf(featureChatListModule) }
        )

        ChatListScreen(
            state = viewModel.uiState,
            onClick = { chat ->
                //TODO: open chat screen
            }
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.peopleListScreen(
    transitionScope: SharedTransitionScope,
    onNavigateTo: (Screen) -> Unit
) {
    composable(
        route = Screen.PeopleList.route
    ) {

        val viewModel = rememberViewModel<PeopleListViewModel>(
            modules = { listOf(featurePeopleListModule) }
        )

        transitionScope.PeopleListScreen(
            viewModel.uiState,
            animatedVisibilityScope = this,
            onNavigateToProfile = { profileId: Int ->
                Screen.PeopleProfile.routeWith(profileId.toString())
                    .also(onNavigateTo)
            }
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.peopleProfileScreen(
    transitionScope: SharedTransitionScope,
    onNavigateBack: () -> Unit,
    onNavigateTo: (Screen) -> Unit
) {
    composable(
        route = Screen.PeopleProfile.route,
        arguments = listOf(
            navArgument(PeopleProfileArgs.PEOPLE_PROFILE_ID_ARG) { type = NavType.IntType }
        )
    ) {

        val viewModel: PeopleProfileViewModel = viewModel()
        val peopleProfile = remember { viewModel.getProfile() }

        peopleProfile ?: return@composable

        val context = LocalContext.current

        val openWebLink: (Uri) -> Unit = { uri ->
            Intent(Intent.ACTION_VIEW).apply {
                data = uri
            }.also { intent ->
                context.startActivity(intent)
            }
        }

        transitionScope.PeopleProfileScreen(
            peopleProfile,
            animatedVisibilityScope = this,
            onBackPressed = onNavigateBack,
            onOpenChatWith = {},
            onOpenSocialLink = openWebLink
        )
    }
}


fun NavGraphBuilder.profileScreen(onNavigateTo: (Screen) -> Unit) {
    composable(
        route = Screen.Profile.route
    ) {

        val viewModel = rememberViewModel<ProfileViewModel>(
            modules = {
                listOf(featureUserProfileModule)
            }
        )

        ProfileScreen(
            state = viewModel.uiState,
            onNavigateToSubscription = {
                onNavigateTo(Screen.Subscription)
            }
        )
    }
}

