package com.ajantha.starwarsplanets.presentation.util

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers

@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Crop,
    width: Int,
    height: Int,
    @DrawableRes
    placeholderResId: Int
) {
    val context = LocalContext.current
    var loading by remember(imageUrl) { mutableStateOf(true) }

    val listener = object : ImageRequest.Listener {
        override fun onError(
            request: ImageRequest,
            result: ErrorResult
        ) {
            loading = false
        }

        override fun onSuccess(
            request: ImageRequest,
            result: SuccessResult
        ) {
            loading = false
        }
    }

    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .listener(listener)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .error(placeholderResId)
        .fallback(placeholderResId)
        .size(
            width,
            height
        )
        .build()

    Box {
        AsyncImage(
            model = imageRequest,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
        if (loading) {
            Box(
                modifier = modifier.shimmerEffect()
            )
        }
    }

}