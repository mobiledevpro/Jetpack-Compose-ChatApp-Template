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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiledevpro.domain.model.fakePeopleProfileList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PeopleListViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<PeopleProfileUIState> =
        MutableStateFlow(PeopleProfileUIState.Empty)
    val uiState: StateFlow<PeopleProfileUIState> = _uiState.asStateFlow()

    init {
        observePeopleList()
    }

    private fun observePeopleList() {
        viewModelScope.launch {

            _uiState.update { PeopleProfileUIState.Loading }

             delay(2000)

            // _uiState.update { PeopleProfileUIState.Empty }
            //   _uiState.update { PeopleProfileUIState.Fail(Throwable("Test error")) }

            _uiState.update {
                PeopleProfileUIState.Success(fakePeopleProfileList)
            }
        }
    }

}