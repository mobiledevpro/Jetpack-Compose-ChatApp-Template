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
package com.mobiledevpro.user.profile.view

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mobiledevpro.ui.component.LabeledDarkModeSwitch
import com.mobiledevpro.ui.component.ProfileContent
import com.mobiledevpro.ui.component.ProfilePicture
import com.mobiledevpro.ui.component.ProfilePictureSize
import com.mobiledevpro.ui.component.ScreenBackground
import com.mobiledevpro.ui.component.SettingsButton
import com.mobiledevpro.ui.theme.AppTheme
import com.mobiledevpro.ui.theme._darkModeState
import com.mobiledevpro.ui.theme.darkModeState
import kotlinx.coroutines.flow.update

@Composable
fun ProfileScreen(
    onNavigateToSubscription: () -> Unit
) {
    Log.d("navigation", "ProfileScreen:")

    val backgroundBoxTopOffset = remember { mutableStateOf(0) }
    val darkModeOn = remember { mutableStateOf(darkModeState.value) }


    ScreenBackground(
        modifier = Modifier
            .fillMaxSize()
    ) {

        //Background with rounded top-corners
        Box(
            modifier = Modifier
                .offset { IntOffset(0, backgroundBoxTopOffset.value) }
                .clip(RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp))
                .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.Top
        ) {

            ProfilePicture(
                photoUri = Uri.EMPTY,
                onlineStatus = true,
                size = ProfilePictureSize.LARGE,
                modifier = Modifier
                    .padding(paddingValues = PaddingValues(16.dp, 16.dp, 16.dp, 16.dp))
                    .align(Alignment.CenterHorizontally)
                    .onGloballyPositioned {
                        val rect = it.boundsInRoot()
                        backgroundBoxTopOffset.value =
                            rect.topCenter.y.toInt() + (rect.bottomCenter.y - rect.topCenter.y).toInt() / 2
                    }
            )

            ProfileContent(
                userName = "Your Name",
                subName = "@nickname",
                isOnline = true,
                alignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )





            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.Bottom
            ) {

                LabeledDarkModeSwitch(
                    label = "Dark mode",
                    checked = darkModeOn.value,
                    onCheckedChanged = { isDark ->
                        Log.d("main", "ProfileScreen: dark $isDark")
                        darkModeOn.value = isDark
                        _darkModeState.update {
                            isDark
                        }
                    })

                Divider()

                SettingsButton(
                    label = "Log Out",
                    icon = Icons.Rounded.ExitToApp,
                    onClick = {

                    }
                )
            }
        }


    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    AppTheme(darkTheme = true) {
        ProfileScreen({})
    }
}