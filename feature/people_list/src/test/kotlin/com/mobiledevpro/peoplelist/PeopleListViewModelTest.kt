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
import com.mobiledevpro.peoplelist.view.PeopleListViewModel
import com.mobiledevpro.peoplelist.view.PeopleProfileUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class PeopleListViewModelTest {

    private lateinit var vm: PeopleListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        /*
            val mocked = mockk<PeopleProfile>()
            every { mocked.photo } returns Uri.EMPTY

         */
        vm = PeopleListViewModel()
        assertTrue(
            "Initial state is incorrect: ${vm.uiState.value}",
            vm.uiState.value == PeopleProfileUIState.Empty
        )
    }

    @Test
    fun stateTest() = runTest {

        vm.uiState.test {
            assertEquals("State is not Loading", PeopleProfileUIState.Loading, awaitItem())

            assertTrue(
                "People list is empty",
                (awaitItem() as PeopleProfileUIState.Success).profileList.isNotEmpty()
            )
        }
    }

    @After
    fun finish() = Dispatchers.resetMain()
}