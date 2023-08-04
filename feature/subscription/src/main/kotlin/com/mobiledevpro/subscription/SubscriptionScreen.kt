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
package com.mobiledevpro.subscription

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobiledevpro.ui.component.ScreenBackground
import com.mobiledevpro.ui.theme.AppTheme

@Composable
fun SubscriptionScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current

    val showToast: (message: String) -> Unit = { message ->
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
    ) { paddingValues ->
        ScreenBackground(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = "Be like a Pro",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(16.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Subscribe on",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge

                    )

                    SubsButton(
                        text = "1 Month - 0.9\$",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        showToast("Subscribing to 1 month...")
                        onNavigateBack()
                    }

                    SubsButton(
                        text = "6 Months - 3.99\$",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        showToast("Subscribing to 6 months...")
                        onNavigateBack()
                    }

                    SubsButton(
                        text = "1 Year - 6.99\$",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        showToast("Subscribing to 1 year...")
                        onNavigateBack()
                    }

                }
            }

            /*

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    .height(IntrinsicSize.Max)
            ) {

                Header()

                //If you remove Items, Footer will be centered at the screen as you could see on attached screenshots
                Item(text = "Item 1")
                Item(text = "Item 2")
                Item(text = "Item 3")

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        //.background(color = Color.Blue)
                ) {
                    Footer(modifier = Modifier.align(Alignment.Center))
                }
            }

             */

        }
    }


}
/*
@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.Yellow)
    )
}

@Composable
fun Item(text: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.White)

    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.Gray)

    ) {
        Text(
            text = "Optional block",
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

 */

@Composable
fun SubsButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
            .defaultMinSize(minWidth = 192.dp, minHeight = 48.dp)


    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun SubscriptionScreenPreview() {
    AppTheme {
        SubscriptionScreen {}
    }
}