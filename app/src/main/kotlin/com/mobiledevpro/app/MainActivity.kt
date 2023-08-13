package com.mobiledevpro.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobiledevpro.app.ui.MainApp
import com.mobiledevpro.ui.theme.AppTheme
import com.mobiledevpro.ui.theme.darkModeState

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //False - allows to drawing the content "edge-to-edge"
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val darkModeState by darkModeState.collectAsStateWithLifecycle()

            AppTheme(darkTheme = darkModeState) {
                MainApp()
            }
        }
    }
}