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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobiledevpro.people.profile.domain.model.PeopleProfile
import com.mobiledevpro.peoplelist.view.component.ProfileCard
import com.mobiledevpro.ui.component.ScreenBackground
import com.mobiledevpro.ui.theme.AppTheme


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


@Preview
@Composable
fun PeopleListPreview() {
    AppTheme {
        PeopleListScreen(
            onNavigateToProfile = { }
        )
    }
}