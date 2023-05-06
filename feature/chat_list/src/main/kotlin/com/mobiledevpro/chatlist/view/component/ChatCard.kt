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
package com.mobiledevpro.chatlist.view.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobiledevpro.domain.model.PeopleProfile
import com.mobiledevpro.domain.model.fakePeopleProfileList
import com.mobiledevpro.domain.model.toChatName
import com.mobiledevpro.ui.component.CardItem
import com.mobiledevpro.ui.component.ProfilePicture
import com.mobiledevpro.ui.component.ProfilePictureSize
import com.mobiledevpro.ui.theme.AppTheme

/**
 * Fo chat list screen
 *
 * Created on May 06, 2023.
 *
 */
@Composable
internal fun ChatCard(chatName : String, peopleList: List<PeopleProfile>, onClick: () -> Unit) {

    CardItem(
        modifier = Modifier
            .clickable { onClick.invoke() }
    ) {

        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

            ChatPicture(
                profileList = peopleList
            )

            Text(
                text = chatName,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun ChatPicture(profileList: List<PeopleProfile>, modifier: Modifier = Modifier) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = modifier) {
            profileList.take(VISIBLE_PROFILES_COUNT).forEachIndexed { index, profile ->

                Log.d("ChatPicture", "ChatPicture: ${profile.name}")
                ProfilePicture(
                    profile.photo,
                    profile.status,
                    size = ProfilePictureSize.SMALL,
                    modifier = Modifier.padding(start = 16.dp * index)
                )

            }
        }
        if (profileList.size > VISIBLE_PROFILES_COUNT)
            Text(text = "+${profileList.size - 3}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(4.dp))
    }
}

@Composable
@Preview
fun ChatCardPreview() {
    val peopleList = fakePeopleProfileList.take(5).sortedByDescending { it.status }
    AppTheme {
        ChatCard(chatName = peopleList.toChatName(), peopleList = peopleList, onClick = {})
    }
}

const val VISIBLE_PROFILES_COUNT = 3