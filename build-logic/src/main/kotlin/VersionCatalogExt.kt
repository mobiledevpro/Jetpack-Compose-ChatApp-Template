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

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import kotlin.jvm.optionals.getOrNull

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal val VersionCatalog.plugins: List<String>
    get() = pluginAliases

internal fun VersionCatalog.versionStr(alias: String): String =
    this.findVersion(alias).getOrNull()?.toString() ?: ""

internal fun VersionCatalog.versionInt(alias: String): Int =
    versionStr(alias).toInt()

internal fun VersionCatalog.library(alias: String) =
    findLibrary(alias).get()

internal fun VersionCatalog.bundle(alias: String) =
    findBundle(alias).get()

