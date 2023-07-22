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
package com.mobiledevpro.user.profile.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.mobiledevpro.user.profile.data.local.ImplUserProfileLocalSource
import com.mobiledevpro.user.profile.data.local.UserProfileLocalSource
import com.mobiledevpro.user.profile.data.repository.ImplUserProfileRepository
import com.mobiledevpro.user.profile.data.repository.UserProfileRepository
import com.mobiledevpro.user.profile.domain.interactor.ImplUserProfileInteractor
import com.mobiledevpro.user.profile.domain.interactor.UserProfileInteractor
import com.mobiledevpro.user.profile.view.vm.ProfileViewModel
import org.koin.androidx.compose.defaultExtras
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.androidx.viewmodel.resolveViewModel
import org.koin.compose.LocalKoinScope
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.ext.getFullName
import org.koin.java.KoinJavaComponent

/**
 * User Profile screen module
 *
 * Created on Jul 22, 2023.
 *
 */

val featureUserProfileModule = module {

    scope<ProfileViewModel> {
        viewModelOf(::ProfileViewModel)

        scoped<UserProfileInteractor>{
            ImplUserProfileInteractor(
                repository = get()
            )
        }

        scoped<UserProfileRepository> {
            ImplUserProfileRepository(
                localSource = get()
            )
        }

        scoped<UserProfileLocalSource> {
            ImplUserProfileLocalSource(

            )
        }
    }
}


inline fun <reified T : Any> koinScope(): Scope {

    val scopeId = T::class.getFullName() + "@" + T::class.hashCode()
    val qualifier = TypeQualifier(T::class)

    return KoinJavaComponent.getKoin().getOrCreateScope(scopeId, qualifier)
}



@OptIn(KoinInternalApi::class)
@Composable
inline fun <reified T : ViewModel> koinViewModel(
    qualifier: Qualifier? = null,
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null,
    extras: CreationExtras = defaultExtras(viewModelStoreOwner),
    scope: Scope = LocalKoinScope.current,
    noinline parameters: ParametersDefinition? = null,
): T {
    return resolveViewModel(
        T::class, viewModelStoreOwner.viewModelStore, key, extras, qualifier, scope, parameters
    )
}