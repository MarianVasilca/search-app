package com.argyle.searchapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.argyle.searchapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    val isLoading = MutableLiveData(false)
    val isError = SingleLiveEvent<Unit>()
}