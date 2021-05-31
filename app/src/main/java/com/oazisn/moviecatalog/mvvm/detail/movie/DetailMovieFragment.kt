package com.oazisn.moviecatalog.mvvm.detail.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.core.base.BaseViewModel
import com.oazisn.moviecatalog.core.utils.EspressoIdlingResource
import com.oazisn.moviecatalog.core.utils.isConnected
import com.oazisn.moviecatalog.core.utils.observeInLifecycle
import com.oazisn.moviecatalog.core.utils.showSnackbar
import com.oazisn.moviecatalog.databinding.DetailFragmentBinding
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment(R.layout.detail_fragment) {

    private val detailViewModel: DetailMovieViewModel by viewModel()
    private val binding: DetailFragmentBinding by viewBinding()
    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                loadData()
            }
            swipeRefresh.setColorSchemeResources(R.color.green_500)
            fabFavorite.setOnClickListener {
                detailViewModel.setFav()
            }
            toolbar.apply {
                title = getString(R.string.detail_movie)

                setNavigationOnClickListener { view ->
                    (activity as AppCompatActivity?)?.supportActionBar?.show()
                    view.findNavController().navigateUp()
                }
            }
            nestedScrollView.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                    if (scrollY > oldScrollY) {
                        fabFavorite.hide()
                    } else {
                        fabFavorite.show()
                    }
                }
            )
        }
        detailViewModel.apply {
            eventsFlow.onEach {
                when (it) {
                    is BaseViewModel.Event.Refresh -> {
                        binding.swipeRefresh.isRefreshing = it.bool
                        if (!it.bool && !EspressoIdlingResource.idlingResource.isIdleNow) {
                            EspressoIdlingResource.decrement()
                        }
                    }
                    is BaseViewModel.Event.ShowSnackbar -> {
                        if (BaseViewModel.MESSAGE_NO_INTERNET == it.message) {
                            binding.root.showSnackbar(R.string.error_no_internet)
                        }
                    }
                }
            }.observeInLifecycle(viewLifecycleOwner)

            isFavorite.observe(viewLifecycleOwner, {
                if (it) {
                    binding.fabFavorite.apply {
                        setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_baseline_favorite_24
                            )
                        )

                        contentDescription = getString(R.string.rem_from_fav_movie)
                    }

                    if (!binding.swipeRefresh.isRefreshing)
                        binding.root.showSnackbar(R.string.add_as_fav)

                } else {
                    binding.fabFavorite.apply {
                        setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_baseline_favorite_border_24
                            )
                        )

                        contentDescription = getString(R.string.add_as_fav_movie)
                    }

                    if (!binding.swipeRefresh.isRefreshing)
                        binding.root.showSnackbar(R.string.rem_from_fav)
                }
            })

            movie.observe(viewLifecycleOwner, { data ->
                binding.apply {
                    fabFavorite.visibility = View.VISIBLE

                    Glide.with(requireContext())
                        .load(data.poster)
                        .into(ivPoster)
                    ivPoster.contentDescription = getString(R.string.poster_of, data.title)

                    tvTitle.text = data.title
                    tvRate.text = "${data.rating}"
                    tvSubtitle.text = getString(
                        R.string.subtitle,
                        if (data.rated.isEmpty()) "-" else data.rated,
                        if (data.duration.isEmpty()) "-" else data.duration,
                        if (data.genre.isEmpty()) "-" else data.genre,
                        if (data.releaseDate.isEmpty()) "-" else data.releaseDate
                    )
                    tvDesc.text = data.desc

                    if (data.director.isEmpty()) {
                        tvDirector.text = getString(R.string.director, "-")
                    } else {
                        tvDirector.text = getString(R.string.director, data.director)
                    }

                    if (data.writers.isEmpty()) {
                        tvWriter.text = getString(R.string.writers, "-")
                    } else {
                        tvWriter.text = getString(R.string.writers, data.writers)
                    }

                    if (data.stars.isEmpty()) {
                        tvStars.text = getString(R.string.stars, "-")
                    } else {
                        tvStars.text = getString(R.string.stars, data.stars)
                    }

                    if (data.creator.isEmpty()) {
                        tvCreator.text = getString(R.string.creator, "-")
                    } else {
                        tvCreator.text = getString(R.string.creator, data.creator)
                    }
                }
            })
        }
        detailViewModel.contentId = args.movieId
        loadData()
    }

    private fun loadData() {
        EspressoIdlingResource.increment()
        detailViewModel.isConnected = requireContext().isConnected()
        detailViewModel.getData()
    }

}