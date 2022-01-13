package com.argyle.searchapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.argyle.searchapp.R
import com.argyle.searchapp.databinding.FragmentSearchBinding
import com.argyle.searchapp.utilities.extension.visibleIf
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var optionalBinding: FragmentSearchBinding? = null

    // can be used only between [onCreateView] and [onDestroyView]
    private val binding get() = optionalBinding!!

    private val viewModel: SearchViewModel by viewModels()

    private val adapter by lazy { LinkItemAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createBinding(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupSubscribers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        optionalBinding = null
    }

    private fun createBinding(inflater: LayoutInflater) {
        optionalBinding = FragmentSearchBinding.inflate(inflater)
    }

    private fun setupUI() {
        binding.rvItems.adapter = adapter
    }

    private fun setupSubscribers() {
        subscribeToViewModelEvents()
        subscribeToUiEvents()
    }

    private fun subscribeToViewModelEvents() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::setIsLoadingVisibility)
        viewModel.isError.observe(viewLifecycleOwner) { showMessage(R.string.error_results_text) }
        viewModel.areLinkItemsEmpty.observe(viewLifecycleOwner, ::setEmptyListMessageVisibility)
        viewModel.linkItems.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    private fun setIsLoadingVisibility(isLoading: Boolean) {
        binding.loadingProgressBar.visibleIf(isLoading)
    }

    private fun setEmptyListMessageVisibility(isLoading: Boolean) {
        binding.tvEmptyList.visibleIf(isLoading)
    }

    private fun showMessage(@StringRes resID: Int) {
        Snackbar.make(requireView(), resID, Snackbar.LENGTH_LONG).show()
    }

    private fun subscribeToUiEvents() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean = false
            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.onQueryTextChanged(text)
                return true
            }
        })
    }

}