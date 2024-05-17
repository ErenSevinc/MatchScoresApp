package com.example.matchscoresapp.core.base

import com.google.gson.annotations.SerializedName

class BaseApiResponse<T>(
    @SerializedName("success")
    val success: Boolean? = false,
    @SerializedName("error")
    val error: List<List<String>>? = null,
    @SerializedName("data")
    val data: T? = null
)
