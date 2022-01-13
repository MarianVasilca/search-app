package com.argyle.searchapp.data.network.response

import com.google.gson.annotations.SerializedName

enum class LinkItemKind {

    @SerializedName("employer")
    EMPLOYER,

    @SerializedName("gig")
    GIG,

    @SerializedName("platform")
    PLATFORM

}