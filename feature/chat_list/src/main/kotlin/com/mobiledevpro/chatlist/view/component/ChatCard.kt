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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobiledevpro.domain.model.PeopleProfile
import com.mobiledevpro.domain.model.fakePeopleProfileList
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
internal fun ChatCard(items: List<PeopleProfile>, onClick: () -> Unit) {

    val nextProfilePictureOffset = remember { mutableStateOf(0.dp) }

    val chatName: String =
        items.mapTo(ArrayList<String>()) { profile -> profile.name }.let { names ->
            val stringBuilder = StringBuilder()
            names.onEachIndexed { index, s ->
                if (index > 0)
                    stringBuilder.append(", ")
                stringBuilder.append(s)
            }
            stringBuilder.toString()
        }


    CardItem(
        modifier = Modifier
            .clickable { onClick.invoke() }
    ) {

        Row(modifier = Modifier.padding(16.dp)) {

            ProfilePicture(
                items[0].photoUrl,
                items[0].status,
                size = ProfilePictureSize.SMALL
            )
            /*
                        Box {

                            items.forEachIndexed { index, item ->


                                ProfilePicture(
                                    item.photoUrl,
                                    item.status,
                                    size = ProfilePictureSize.SMALL,
                                    modifier = Modifier.offset(x = nextProfilePictureOffset.value)
                                )


                                nextProfilePictureOffset.value += 16.dp
                            }
                        }

             */

            Text(
                text = chatName,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 8.dp)
            )
        }


        /*ProfilePicture(
            item.photoUrl,
            item.status,
            size = ProfilePictureSize.MEDIUM,
            modifier = Modifier.padding(16.dp)
        )
        ProfileContent(
            userName = item.name,
            subName = null,
            isOnline = item.status,
            alignment = Alignment.Start,
            modifier = Modifier.padding(8.dp)
        )*/
    }
}

@Composable
@Preview
fun ChatCardPreview() {
    AppTheme {
        ChatCard(items = fakePeopleProfileList.take(3), onClick = {})
    }
}