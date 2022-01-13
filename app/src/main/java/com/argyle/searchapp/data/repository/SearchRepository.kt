package com.argyle.searchapp.data.repository

import com.argyle.searchapp.data.network.response.LinkItem

interface SearchRepository {

    suspend fun searchLinkItems(
        limit: Int,
        search: String
    ): List<LinkItem>

}