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

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mobiledevpro.domain.model.PeopleProfile
import com.mobiledevpro.domain.model.fakePeopleProfileList
import com.mobiledevpro.people.profile.view.args.PeopleProfileArgs

/**
 * Profile screen for selected person from People list
 *
 * Created on Feb 04, 2023.
 *
 */
class PeopleProfileViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val profileId : Int =  PeopleProfileArgs(savedStateHandle).peopleProfileId



    init {
        Log.d("navigation", "PeopleProfileViewModel: args = $profileId")
    }

    fun getProfile() : PeopleProfile? = fakePeopleProfileList.find { it.id == profileId }
}