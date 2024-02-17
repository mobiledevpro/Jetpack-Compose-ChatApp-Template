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
package com.mobiledevpro.user.profile.view.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mobiledevpro.ui.vm.BaseViewModel
import com.mobiledevpro.user.profile.domain.usecase.GetUserProfileUseCase
import com.mobiledevpro.user.profile.view.state.UserProfileUIState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : BaseViewModel<UserProfileUIState>() {

    override fun initUIState(): UserProfileUIState = UserProfileUIState.Empty

    init {
        Log.d("UI", "ProfileViewModel init")
        observeUserProfile()
    }

    private fun observeUserProfile() {
        getUserProfileUseCase.execute()
            .onEach { result ->
                result.onSuccess { profile ->
                    UserProfileUIState.Success(profile)
                        .also { _uiState.value = it }
                }.onFailure {
                    //TODO: show the error
                }

            }.launchIn(viewModelScope)
    }
}