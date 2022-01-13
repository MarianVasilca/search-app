package com.argyle.searchapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.argyle.searchapp.R
import com.argyle.searchapp.data.network.response.LinkItem
import com.argyle.searchapp.databinding.ItemLinkBinding
import com.argyle.searchapp.utilities.loadImage

class LinkItemAdapter : ListAdapter<LinkItem, LinkItemViewHolder>(
    ITEM_COMPARATOR
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LinkItemViewHolder {
        return LinkItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: LinkItemViewHolder,
        position: Int
    ) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<LinkItem>() {
            override fun areItemsTheSame(
                oldItem: LinkItem,
                newItem: LinkItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LinkItem,
                newItem: LinkItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class LinkItemViewHolder(
    private val binding: ItemLinkBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LinkItem) {
        binding.ivHeader.loadImage(item.logoURL, R.drawable.ic_logo_placeholder)
        binding.tvName.text = item.name
        binding.tvKind.text = item.kind?.name
    }

    companion object {
        fun create(
            parent: ViewGroup,
        ): LinkItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
                ItemLinkBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            return LinkItemViewHolder(binding)
        }
    }

}