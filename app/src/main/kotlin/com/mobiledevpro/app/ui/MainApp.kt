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
package com.mobiledevpro.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.mobiledevpro.navigation.Screen
import com.mobiledevpro.navigation.graph.RootNavGraph


@Composable
fun MainApp() {
    val navController = rememberNavController()

    RootNavGraph(
        navController = navController,
        startDestination = Screen.Home
    )

}