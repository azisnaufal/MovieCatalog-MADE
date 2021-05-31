package com.oazisn.moviecatalog.favorite.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oazisn.moviecatalog.favorite.movies.MoviesFragment
import com.oazisn.moviecatalog.favorite.tvshows.TvShowsFragment

const val MOVIES_INDEX = 0
const val TVSHOWS_INDEX = 1

class SectionsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MOVIES_INDEX to { MoviesFragment.newInstance() },
        TVSHOWS_INDEX to { TvShowsFragment.newInstance() },
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment =
        tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()

}