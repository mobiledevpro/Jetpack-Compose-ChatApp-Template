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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobiledevpro.people.profile.R
import com.mobiledevpro.people.profile.domain.model.PeopleProfile
import com.mobiledevpro.people.profile.view.components.ProfileContent
import com.mobiledevpro.people.profile.view.components.ProfilePicture
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
    onBackPressed: () -> Unit,
    onOpenChatWith: (profile: PeopleProfile) -> Unit
) {
    val viewModel: PeopleProfileViewModel = viewModel()

    val profile = remember { viewModel.getProfile() } ?: return

    ScreenBackground(
        modifier = Modifier
            .fillMaxSize()
    ) {

        ConstraintLayout {
            val (profilePicture, profileContent, boxTopBorder, contentBox, socialIcons, buttonHi) = createRefs()

            IconButton(
                onClick = { onBackPressed() },
                modifier = Modifier.padding(16.dp)) {
                Icon(
                    painter = painterResource(id = RApp.drawable.ic_arrow_back_white_24dp),
                    contentDescription = ""
                )
            }

            //Line to link the top side of the Box to
            Divider(
                thickness = 1.dp,
                color = Color.Transparent,
                modifier = Modifier.constrainAs(boxTopBorder) {
                    top.linkTo(profilePicture.top, margin = 48.dp)
                    bottom.linkTo(profilePicture.bottom)
                }
            )

            //Background with rounded top-corners
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp))
                    .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp))
                    .constrainAs(contentBox) {
                        top.linkTo(boxTopBorder.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            ProfilePicture(
                photoUrl = profile.photoUrl,
                onlineStatus = profile.status,
                imageSize = 144.dp,
                modifier = Modifier
                    .constrainAs(profilePicture) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.padding(paddingValues = PaddingValues(16.dp, 64.dp, 16.dp, 16.dp))
            )

            ProfileContent(
                userName = profile.name,
                onlineStatus = profile.status,
                alignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(profileContent) {
                        top.linkTo(profilePicture.bottom)
                        start.linkTo(profilePicture.start)
                        end.linkTo(profilePicture.end)
                    }
            )

            ProfileSocialIcons(
                modifier = Modifier.constrainAs(socialIcons) {
                    top.linkTo(profileContent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Button(
                onClick = {
                    profile.also(onOpenChatWith)
                },
                modifier = Modifier
                    .padding(bottom = 48.dp, top = 16.dp, start = 16.dp, end = 16.dp)
                    .defaultMinSize(minHeight = 48.dp, minWidth = 144.dp)
                    .constrainAs(buttonHi) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Text(text = "Say Hi \uD83D\uDC4B")
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
    }
}


@Preview
@Composable
fun PeopleProfilePreview() {
    AppTheme(darkTheme = true) {
        PeopleProfileScreen(
            onBackPressed = {},
            onOpenChatWith = {}
        )
    }
}