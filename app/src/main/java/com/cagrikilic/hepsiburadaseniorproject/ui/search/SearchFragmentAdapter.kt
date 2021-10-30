package com.cagrikilic.hepsiburadaseniorproject.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cagrikilic.hepsiburadaseniorproject.databinding.ViewListItemBinding
import com.cagrikilic.hepsiburadaseniorproject.network.model.Media

class SearchFragmentAdapter(private val onClickListener: OnClickListener) : PagingDataAdapter<Media,SearchFragmentAdapter.SearchViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
       return SearchViewHolder(ViewListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener{
            if (movie != null) {
                onClickListener.onClick(movie)
            }
        }
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class SearchViewHolder(private var binding:
                           ViewListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(media: Media) {
            binding.movie = media
            binding.executePendingBindings()
        }
    }
    class OnClickListener(val clickListener: (media: Media) -> Unit) {
        fun onClick(media:Media) = clickListener(media)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Media>() {
        override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem.trackId == newItem.trackId
        }
    }
}