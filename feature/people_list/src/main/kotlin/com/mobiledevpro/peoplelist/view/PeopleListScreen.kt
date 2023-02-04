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
package com.mobiledevpro.peoplelist.view

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.mobiledevpro.people.profile.domain.model.PeopleProfile
import com.mobiledevpro.ui.component.ScreenBackground
import com.mobiledevpro.ui.theme.AppTheme
import com.mobiledevpro.ui.theme.lightGreen
import com.mobiledevpro.ui.theme.red


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PeopleListScreen(onNavigateToProfile: (profileId: Int) -> Unit) {
    val viewModel: PeopleListViewModel = viewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ScreenBackground(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when (uiState) {
            is PeopleProfileUIState.Loading -> Loading()
            is PeopleProfileUIState.Empty -> NoPeopleFound()
            is PeopleProfileUIState.Success ->
                PeopleList(
                    list = (uiState as PeopleProfileUIState.Success).profileList,
                    onProfileClick = onNavigateToProfile
                )

            is PeopleProfileUIState.Fail -> {
                NoPeopleFound()
                LaunchedEffect(Unit) {
                    Toast.makeText(
                        context,
                        (uiState as PeopleProfileUIState.Fail).throwable.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }

    }
}

@Composable
private fun NoPeopleFound() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "No people found",
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
private fun PeopleList(list: List<PeopleProfile>, onProfileClick: (profileId : Int) -> Unit) {
    LazyColumn {
        items(list) { profile ->
            ProfileCard(item = profile, onClick = { onProfileClick(profile.id) })
        }
    }
}

@Composable
private fun ProfileCard(item: PeopleProfile, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { onClick.invoke() },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(item.photoUrl, item.status, 72.dp)
            ProfileContent(item.name, item.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(photoUrl: String, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus) MaterialTheme.colorScheme.lightGreen else MaterialTheme.colorScheme.red
        ),
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoUrl)
                    .size(Size.ORIGINAL) // Set the target size to load the image at.
                    .transformations(CircleCropTransformation())
                    .build()
            ),
            contentDescription = "Profile image",
            modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(
            LocalContentColor provides LocalContentColor.current.copy(alpha = if (onlineStatus) 1f else 0.4f)
        ) {

            Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        CompositionLocalProvider(LocalContentColor provides LocalContentColor.current.copy(alpha = 0.4F)) {
            Text(
                text = if (onlineStatus) "Active now" else "Offline",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Preview
@Composable
fun PeopleListPreview() {
    AppTheme {
        PeopleListScreen(
            onNavigateToProfile = { }
        )
    }
}