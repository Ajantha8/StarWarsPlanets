package com.ajantha.starwarsplanets.ui.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

inline fun <reified T : Parcelable> parcelableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun get(
        bundle: Bundle,
        key: String
    ): T? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        bundle.getParcelable(
            key,
            T::class.java
        )
    } else {
        bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        val decodedJson = URLDecoder.decode(
            value,
            "UTF-8"
        ) // Decode JSON
        return json.decodeFromString(decodedJson)
    }

    override fun serializeAsValue(value: T): String {
        val jsonString = json.encodeToString(value)
        return URLEncoder.encode(
            jsonString,
            "UTF-8"
        ) // Encode JSON
    }

    override fun put(
        bundle: Bundle,
        key: String,
        value: T
    ) {
        bundle.putParcelable(
            key,
            value
        )
    }

}