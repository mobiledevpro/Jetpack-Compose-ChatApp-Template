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
package com.mobiledevpro.people.profile.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.mobiledevpro.ui.theme.lightGreen
import com.mobiledevpro.ui.theme.red

/**
 * For People list
 *
 * Created on Feb 05, 2023.
 *
 */

@Composable
fun ProfilePicture(photoUrl: String, onlineStatus: Boolean, imageSize: Dp, modifier: Modifier = Modifier) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus) MaterialTheme.colorScheme.lightGreen else MaterialTheme.colorScheme.red
        ),
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoUrl)
                    .size(Size.ORIGINAL) // Set the target size to load the image at.
                    .transformations(CircleCropTransformation())
                    .build()
            ),
            contentDescription = "Profile image",
            modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop
        )
    }

}