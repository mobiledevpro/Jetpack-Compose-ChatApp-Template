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
package com.mobiledevpro.chatlist.view

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mobiledevpro.chatlist.view.component.ChatCard
import com.mobiledevpro.domain.model.fakeChatList
import com.mobiledevpro.ui.component.ScreenBackground
import com.mobiledevpro.ui.theme.AppTheme


@Composable
fun ChatListScreen() {

    Log.d("navigation", "ChatListScreen: ")

    ScreenBackground(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn{
            items(fakeChatList) {chat->
               ChatCard(items = chat.peopleList, onClick = { /*TODO: open chat screen*/})
            }
        }
    }
}

@Composable
@Preview
fun ChatListScreenPreview() {
    AppTheme {
        ChatListScreen()
    }
}