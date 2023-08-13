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

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobiledevpro.chatlist.view.component.ChatCard
import com.mobiledevpro.domain.model.Chat
import com.mobiledevpro.domain.model.fakeChatList
import com.mobiledevpro.ui.component.ScreenBackground
import com.mobiledevpro.ui.ext.dp
import com.mobiledevpro.ui.ext.statusBarHeight
import com.mobiledevpro.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun ChatListScreen(
    state: StateFlow<ChatListUIState>,
    onClick: (Chat) -> Unit
) {

    val uiState by state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ScreenBackground(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when (uiState) {
            is ChatListUIState.Loading -> Loading()
            is ChatListUIState.Empty -> NoChatFound()
            is ChatListUIState.Success ->
                ChatList(
                    chatList = (uiState as ChatListUIState.Success).profileList,
                    onClick = onClick
                )

            is ChatListUIState.Fail -> {
                NoChatFound()
                LaunchedEffect(Unit) {
                    Toast.makeText(
                        context,
                        (uiState as ChatListUIState.Fail).throwable.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }


    }
}

@Composable
private fun ChatList(chatList: List<Chat>, onClick: (Chat) -> Unit) {

    //Cause content is drawn edge-to-edge, let's set the top-padding for the first element in the list
    val statusBarHeight: Dp = WindowInsets.statusBarHeight().dp()

    LazyColumn {
        itemsIndexed(chatList) { index, chat ->
            ChatCard(
                modifier = Modifier.then(
                    if (index == 0)
                        Modifier.padding(top = statusBarHeight)
                    else
                        Modifier
                ),
                chat = chat,
                onClick = { onClick(chat) }
            )
        }
    }
}

@Composable
private fun NoChatFound() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "No chat found",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Loading...", modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
@Preview
fun ChatListScreenPreview() {
    AppTheme {
        ChatListScreen(
            state = MutableStateFlow(ChatListUIState.Success(fakeChatList)),
            onClick = {}
        )
    }
}