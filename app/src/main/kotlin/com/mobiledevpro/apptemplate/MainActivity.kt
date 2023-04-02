package com.mobiledevpro.apptemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mobiledevpro.apptemplate.ui.MainApp
import com.mobiledevpro.ui.theme.AppTheme
import com.mobiledevpro.ui.theme.darkModeState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(true) }

            //TODO: move it to ViewModel instead
            LaunchedEffect(Unit) {
                    darkModeState.collect {
                        isDarkTheme = it
                    }
            }

            AppTheme(darkTheme = isDarkTheme) {
                MainApp()
            }
        }
    }
}