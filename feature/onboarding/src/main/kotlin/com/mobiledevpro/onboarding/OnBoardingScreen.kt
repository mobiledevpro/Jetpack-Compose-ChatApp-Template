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
package com.mobiledevpro.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.trace
import com.mobiledevpro.ui.component.ScreenBackground
import com.mobiledevpro.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(
    onNext: () -> Unit
) = trace("OnBoardingScreen") {
    Scaffold { paddingValues ->
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
                    text = "OnBoarding Screen",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge

                )

                Button(
                    onClick = onNext,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .defaultMinSize(minWidth = 144.dp, minHeight = 48.dp)

                ) {
                    Text(text = "Next")
                }

            }
        }
    }


}

@Preview
@Composable
fun OnBoardingPreview() {
    AppTheme {
        OnBoardingScreen {

        }
    }
}