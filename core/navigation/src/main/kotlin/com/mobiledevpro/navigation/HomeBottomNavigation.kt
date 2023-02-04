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
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mobiledevpro.ui.component.AppBottomBar
import com.mobiledevpro.ui.component.AppBottomBarItem


@Composable
fun HomeBottomNavigation(
    screens: List<Screen>,
    onNavigateTo: (Screen) -> Unit,
    currentDestination: NavDestination?
) {

    Log.d("navigation", "HomeBottomNavigation")

    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it }),
    ) {


        AppBottomBar {

            screens.forEach { screen ->
                Log.d("navigation", "HomeBottomNavigation: hierarchy = $currentDestination")

                val selected: Boolean =
                    currentDestination?.hierarchy?.any { it.route == screen.route } ?: false

                AppBottomBarItem(
                    selected = selected,
                    onClick = { onNavigateTo(screen) },
                    icon = {
                        Icon(
                            imageVector = screen.icon ?: Icons.Default.Warning,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = screen.title ?: "") }
                )
            }
        }
    }
}