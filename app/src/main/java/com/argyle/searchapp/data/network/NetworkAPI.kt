package com.argyle.searchapp.data.network

import com.argyle.searchapp.data.network.response.LinkItem
import com.argyle.searchapp.data.network.response.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {

    @GET("search/link-items")
    suspend fun searchLinkItems(
        @Query("limit") limit: Int = LINK_ITEMS_DEFAULT_LIMIT,
        @Query("q") search: String
    ): ResponseWrapper<LinkItem>

    companion object {
        const val LINK_ITEMS_DEFAULT_LIMIT = 15
    }

}