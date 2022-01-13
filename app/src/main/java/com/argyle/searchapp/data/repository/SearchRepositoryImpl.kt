package com.argyle.searchapp.data.repository

import com.argyle.searchapp.data.model.SearchLinkItemsError
import com.argyle.searchapp.data.network.NetworkAPI
import com.argyle.searchapp.data.network.response.LinkItem
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val networkAPI: NetworkAPI
) : SearchRepository {

    override suspend fun searchLinkItems(
        limit: Int,
        search: String
    ): List<LinkItem> {
        try {
            return networkAPI.searchLinkItems(limit, search).results
        } catch (e: Throwable) {
            throw SearchLinkItemsError("Unable to search link items for term $search", e)
        }
    }

}