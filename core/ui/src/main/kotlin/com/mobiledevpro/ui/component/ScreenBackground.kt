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
package com.mobiledevpro.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Common background for all screens
 *
 * Created on Jan 10, 2023.
 *
 */

@Composable
fun ScreenBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val color = MaterialTheme.colorScheme.background

    Surface(
        color = if (color == Color.Unspecified) Color.Transparent else color,
        modifier = modifier.fillMaxSize(),
    ) {
        CompositionLocalProvider(
            LocalAbsoluteTonalElevation provides 0.dp
        ) {
            content()
        }
    }
}
