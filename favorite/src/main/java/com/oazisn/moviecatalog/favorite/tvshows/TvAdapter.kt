package com.oazisn.moviecatalog.favorite.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.core.domain.model.TvShowDomain
import com.oazisn.moviecatalog.databinding.ItemMovieTvshowBinding

class TvAdapter(val onClick: (view: View, index: Int) -> Unit) :
    ListAdapter<TvShowDomain, TvAdapter.ViewHolder>(
        TvDiffCallback()
    ) {
    private val data = ArrayList<TvShowDomain>()
    fun setData(value: List<TvShowDomain>?) {
        data.clear()
        value?.forEach {
            data.add(it)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShowDomain) {
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
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

private class TvDiffCallback : DiffUtil.ItemCallback<TvShowDomain>() {
    override fun areItemsTheSame(oldItem: TvShowDomain, newItem: TvShowDomain): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TvShowDomain, newItem: TvShowDomain): Boolean =
        oldItem == newItem

}