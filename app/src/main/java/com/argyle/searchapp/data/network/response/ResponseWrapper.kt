package com.argyle.searchapp.data.network.response

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    @SerializedName("results") val results: List<T>
)
