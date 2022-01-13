package com.argyle.searchapp.ui.search

import androidx.lifecycle.*
import com.argyle.searchapp.data.model.SearchLinkItemsError
import com.argyle.searchapp.data.network.NetworkAPI
import com.argyle.searchapp.data.network.response.LinkItem
import com.argyle.searchapp.data.repository.SearchRepository
import com.argyle.searchapp.utilities.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val searchQuery = MutableLiveData<String?>()
    private val debouncedSearchQuery = searchQuery
        .asFlow()
        .map { it?.trim() }
        .filter { !it.isNullOrBlank() && it.length >= SEARCH_TEXT_MIN_LENGTH }
        .debounce(SEARCH_TEXT_DEBOUNCE_TIME)

    val isLoading = MutableLiveData(false)
    val isError = SingleLiveEvent<Unit>()
    val linkItems = MutableLiveData<List<LinkItem>>()
    val areLinkItemsEmpty = linkItems
        .map { it.isEmpty() }

    init {
        viewModelScope.launch {
            debouncedSearchQuery
                .collectLatest { searchLinkItems(it ?: "") }
        }
    }

    fun onQueryTextChanged(text: String?) {
        searchQuery.value = text
    }

    private suspend fun searchLinkItems(query: String) {
        try {
            isLoading.value = true
            linkItems.value = searchRepository.searchLinkItems(
                NetworkAPI.LINK_ITEMS_DEFAULT_LIMIT, query
            )
        } catch (e: SearchLinkItemsError) {
            isError.call()
        } catch (e: Throwable) {
            isError.call()
        } finally {
            isLoading.value = false
        }
    }

    companion object {
        private const val SEARCH_TEXT_DEBOUNCE_TIME = 500L // in millis
        private const val SEARCH_TEXT_MIN_LENGTH = 3
    }
}