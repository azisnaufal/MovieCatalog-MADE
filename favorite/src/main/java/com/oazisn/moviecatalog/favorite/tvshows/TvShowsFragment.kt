package com.oazisn.moviecatalog.favorite.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.core.utils.EspressoIdlingResource
import com.oazisn.moviecatalog.core.utils.showSnackbar
import com.oazisn.moviecatalog.databinding.FragmentListBinding
import com.oazisn.moviecatalog.favorite.FavoriteFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A placeholder fragment containing a simple view.
 */
class TvShowsFragment : Fragment(R.layout.fragment_list) {

    private val tvShowsViewModel: TvShowsViewModel by viewModel()
    private val binding: FragmentListBinding by viewBinding()
    private lateinit var adapter: TvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EspressoIdlingResource.increment()
        adapter = TvAdapter { _view, id ->
            val direction =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailTvShowFragment(id)
            _view.findNavController().navigate(direction)
        }
        binding.apply {
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.adapter = adapter

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false
            }
            swipeRefresh.setColorSchemeResources(R.color.green_500)

        }
        tvShowsViewModel.apply {
            tvShows.observe(viewLifecycleOwner, {
                if (it.isEmpty()) {
                    binding.root.showSnackbar(R.string.error_no_data)
                }
                adapter.setData(it)
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            })
        }
    }

    override fun onPause() {
        binding.swipeRefresh.isEnabled = false
        super.onPause()
    }

    override fun onResume() {
        binding.swipeRefresh.isEnabled = true
        super.onResume()
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    companion object {
        /**
         * Returns a new instance of this fragment.
         */
        @JvmStatic
        fun newInstance(): TvShowsFragment {
            return TvShowsFragment()
        }
    }
}