package com.oazisn.moviecatalog.mvvm.main.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.core.base.BaseViewModel
import com.oazisn.moviecatalog.core.utils.EspressoIdlingResource
import com.oazisn.moviecatalog.core.utils.isConnected
import com.oazisn.moviecatalog.core.utils.observeInLifecycle
import com.oazisn.moviecatalog.core.utils.showSnackbar
import com.oazisn.moviecatalog.databinding.FragmentListBinding
import com.oazisn.moviecatalog.mvvm.main.MainFragmentDirections
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A placeholder fragment containing a simple view.
 */
class MoviesFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModel()
    private lateinit var binding: FragmentListBinding
    private val adapter: MoviesAdapter = MoviesAdapter { _view, id ->
        val direction = MainFragmentDirections.actionMainFragmentToDetailMovieFragment(id)
        _view.findNavController().navigate(direction)
    }
    private var loadJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.adapter = adapter

            swipeRefresh.setOnRefreshListener {
                loadData()
            }
            swipeRefresh.setColorSchemeResources(R.color.green_500)

        }
        moviesViewModel.apply {
            eventsFlow.onEach {
                when (it) {
                    is BaseViewModel.Event.Refresh -> {
                        binding.swipeRefresh.isRefreshing = it.bool
                    }
                    is BaseViewModel.Event.ShowSnackbar -> {
                        if (BaseViewModel.MESSAGE_NO_INTERNET == it.message) {
                            binding.recyclerView.showSnackbar(R.string.error_no_internet)
                        }
                    }
                }
            }.observeInLifecycle(viewLifecycleOwner)
        }
        loadData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        retainInstance = true
    }

    private fun loadData() {
        EspressoIdlingResource.increment()
        moviesViewModel.isConnected = requireContext().isConnected()
        moviesViewModel.getData()
        loadJob?.cancel()
        loadJob = viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.getMovies().collectLatest {
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
                adapter.submitData(it)
            }
        }

    }

    companion object {
        /**
         * Returns a new instance of this fragment.
         */
        @JvmStatic
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}