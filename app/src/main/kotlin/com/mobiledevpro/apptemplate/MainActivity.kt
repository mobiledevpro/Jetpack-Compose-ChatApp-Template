package com.mobiledevpro.apptemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobiledevpro.apptemplate.ui.MainApp
import com.mobiledevpro.ui.theme.AppTheme
import com.mobiledevpro.ui.theme.darkModeState

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkModeState = darkModeState.collectAsStateWithLifecycle()

            AppTheme(darkTheme = darkModeState.value) {
                MainApp()
            }
        }
    }
}