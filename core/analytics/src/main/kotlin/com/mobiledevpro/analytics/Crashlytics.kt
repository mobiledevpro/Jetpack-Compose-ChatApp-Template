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
package com.mobiledevpro.analytics

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics


object Crashlytics {
    fun log(message: String) {
        Log.d("Crashlytics", message)
        FirebaseCrashlytics.getInstance().log(message)
    }

    fun log(logLevel: Int, logTag: String, message: String) {
        val log: String = when (logLevel) {
            Log.ERROR -> {
                Log.e(logTag, message)
                "E/$logTag: "
            }

            Log.INFO -> {
                Log.i(logTag, message)
                "I/$logTag: "
            }

            else -> {
                Log.d(logTag, message)
                "D/$logTag: "
            }
        }

        FirebaseCrashlytics.getInstance().log(log + message)
    }

    fun logException(throwable: Throwable) {
        Log.e("Crashlytics", throwable.localizedMessage, throwable)
        FirebaseCrashlytics.getInstance().recordException(throwable)
    }

    fun setCustomKey(key: String, value: String) {
        FirebaseCrashlytics.getInstance()
            .setCustomKey(key, value)
    }
}