package com.oazisn.moviecatalog.favorite.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.core.domain.model.MovieDomain
import com.oazisn.moviecatalog.databinding.ItemMovieTvshowBinding

class MoviesAdapter(val onClick: (view: View, index: Int) -> Unit) :
    ListAdapter<MovieDomain, MoviesAdapter.ViewHolder>(
        MovieDiffCallback()
    ) {
    private val data = ArrayList<MovieDomain>()
    fun setData(value: List<MovieDomain>?) {
        data.clear()
        value?.forEach {
            data.add(it)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieDomain) {
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

private class MovieDiffCallback : DiffUtil.ItemCallback<MovieDomain>() {
    override fun areItemsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean =
        oldItem == newItem

}