package com.oazisn.moviecatalog.mvvm.main.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.core.domain.model.TvShowPagingDomain
import com.oazisn.moviecatalog.databinding.ItemMovieTvshowBinding

class TvAdapter(val onClick: (view: View, index: Int) -> Unit) :
    PagingDataAdapter<TvShowPagingDomain, TvAdapter.ViewHolder>(diffCallback) {
    inner class ViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShowPagingDomain) {
            Glide.with(binding.root.context)
                .load(data.poster)
                .into(binding.ivPoster)
            binding.ivPoster.contentDescription =
                binding.root.context.getString(R.string.poster_of, data.title)

            binding.tvTitle.text = data.title
            binding.tvRate.text = data.rating.toString()
            binding.cardView.setOnClickListener {
                onClick(it, data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = 
            ItemMovieTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TvShowPagingDomain>() {
            override fun areItemsTheSame(
                oldItem: TvShowPagingDomain,
                newItem: TvShowPagingDomain
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TvShowPagingDomain,
                newItem: TvShowPagingDomain
            ): Boolean =
                oldItem == newItem

        }
    }
}
