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
package com.mobiledevpro.people.profile.view

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mobiledevpro.domain.model.PeopleProfile
import com.mobiledevpro.domain.model.fakePeopleProfileList
import com.mobiledevpro.people.profile.R
import com.mobiledevpro.ui.component.ProfileContent
import com.mobiledevpro.ui.component.ProfilePicture
import com.mobiledevpro.ui.component.ProfilePictureSize
import com.mobiledevpro.ui.component.ScreenBackground
import com.mobiledevpro.ui.theme.AppTheme
import com.mobiledevpro.ui.R as RApp

/**
 * Profile screen for selected person from People list
 *
 * Created on Feb 03, 2023.
 *
 */

@Composable
fun PeopleProfileScreen(
    profile: PeopleProfile,
    onBackPressed: () -> Unit,
    onOpenChatWith: (profile: PeopleProfile) -> Unit
) {

    val backgroundBoxTopOffset = remember { mutableIntStateOf(0) }

    ScreenBackground(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        //Background with rounded top-corners
        Box(
            modifier = Modifier
                .offset { IntOffset(0, backgroundBoxTopOffset.value) }
                .clip(RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp))
                .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp))
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            IconButton(
                onClick = { onBackPressed() },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = RApp.drawable.ic_arrow_back_white_24dp),
                    contentDescription = ""
                )
            }

            ProfilePicture(
                photoUri = profile.photo ?: Uri.EMPTY,
                onlineStatus = profile.status,
                size = ProfilePictureSize.LARGE,
                modifier = Modifier
                    .padding(paddingValues = PaddingValues(16.dp, 16.dp, 16.dp, 16.dp))
                    .align(Alignment.CenterHorizontally)
                    .onGloballyPositioned {
                        val rect = it.boundsInParent()
                        backgroundBoxTopOffset.value =
                            rect.topCenter.y.toInt() + (rect.bottomCenter.y - rect.topCenter.y).toInt() / 2
                    }
            )

            ProfileContent(
                userName = profile.name,
                isOnline = profile.status,
                alignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            ProfileSocialIcons(modifier = Modifier.align(Alignment.CenterHorizontally))

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {
                        profile.also(onOpenChatWith)
                    },
                    modifier = Modifier
                        .padding(bottom = 48.dp, top = 16.dp, start = 16.dp, end = 16.dp)
                        .defaultMinSize(minHeight = 48.dp, minWidth = 144.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                ) {
                    Text(text = "Say Hi \uD83D\uDC4B")
                }
            }
        }

    }
}

@Composable
fun ProfileSocialIcons(modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        IconButton(
            onClick = {

            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_instagram_white_48dp),
                contentDescription = "",
                modifier = Modifier.padding(4.dp),
            )
        }

        IconButton(
            onClick = {

            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_linkedin_white_48dp),
                contentDescription = "",
                modifier = Modifier.padding(4.dp)
            )
        }

        IconButton(
            onClick = {

            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_youtube_white_48dp),
                contentDescription = "",
                modifier = Modifier.padding(4.dp)
            )
        }

        IconButton(
            onClick = {

            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter_white_48dp),
                contentDescription = "",
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}


@Preview
@Composable
fun PeopleProfilePreview() {
    AppTheme(darkTheme = true) {
        fakePeopleProfileList.find { it.id == 2 }?.let {
            PeopleProfileScreen(
                it,
                onBackPressed = {},
                onOpenChatWith = {}
            )
        }
    }
}