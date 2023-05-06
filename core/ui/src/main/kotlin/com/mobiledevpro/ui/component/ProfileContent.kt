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

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * For People list
 *
 * Created on Feb 05, 2023.
 *
 */
@Composable
fun ProfileContent(
    userName: String,
    subName: String? = null,
    isOnline: Boolean,
    alignment: Alignment.Horizontal,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(
            LocalContentColor provides LocalContentColor.current.copy(alpha = if (isOnline) 1f else 0.4f)
        ) {

            Text(
                text = userName,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        CompositionLocalProvider(LocalContentColor provides LocalContentColor.current.copy(alpha = 0.4F)) {
            Text(
                text = subName ?: if (isOnline) "Active now" else "Offline",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}