package com.argyle.searchapp.data.network.response

import com.google.gson.annotations.SerializedName

data class LinkItem(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("logo_url") val logoURL: String?,
    @SerializedName("kind") val kind: LinkItemKind?,
)
