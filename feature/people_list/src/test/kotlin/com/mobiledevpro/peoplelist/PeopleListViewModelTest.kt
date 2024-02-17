/*
 * Copyright 2023 | Dmitri Chernysh | https://mobile-dev.pro
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
package com.mobiledevpro.peoplelist

import app.cash.turbine.test
import com.mobiledevpro.peoplelist.domain.usecase.GetPeopleListUseCase
import com.mobiledevpro.peoplelist.view.PeopleListViewModel
import com.mobiledevpro.peoplelist.view.PeopleProfileUIState
import com.mobiledevpro.ui.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class PeopleListViewModelTest {

    private lateinit var vm: PeopleListViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())

        val useCase = GetPeopleListUseCase()
        vm = PeopleListViewModel(getPeopleListUseCase = useCase)
        assertTrue(
            "Initial state is incorrect: ${vm.uiState.value}",
            (vm.uiState.value as UIState) == PeopleProfileUIState.Loading
        )
    }

    @Test
    fun stateTest() = runTest {

        vm.uiState.test {
            // assertEquals("State is not Loading", PeopleProfileUIState.Loading, awaitItem())

            assertTrue(
                "People list is empty",
                (awaitItem() as PeopleProfileUIState.Success).profileList.isNotEmpty()
            )
        }
    }

    @After
    fun finish() = Dispatchers.resetMain()
}