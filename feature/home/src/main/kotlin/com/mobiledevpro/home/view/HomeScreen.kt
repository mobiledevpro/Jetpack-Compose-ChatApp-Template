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
package com.mobiledevpro.home.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobiledevpro.ui.component.ScreenBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    nestedNavGraph: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit
) {

    val viewModel: HomeViewModel = viewModel()

    Scaffold(bottomBar = bottomBar) { paddingValues ->
        ScreenBackground(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            nestedNavGraph.invoke()
        }
    }

    /*

    Scaffold(
        bottomBar = bottomBar
    ) { paddingValues ->
        ScreenBackground(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Home Screen",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge

                )

                Button(
                    onClick = onOpenSubscriptionScreen,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .defaultMinSize(minWidth = 144.dp, minHeight = 48.dp)
                ) {
                    Text(
                        text = "Be like a Pro"
                    )
                }

            }
        }


    }
*/

}

@Preview
@Composable
fun HomeScreenPreview() {
    /* AppTheme {
         HomeScreen(
             bottomBar = {}
         ) {

         }
     }

     */
}