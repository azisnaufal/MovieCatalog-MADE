package com.oazisn.moviecatalog.mvvm.main.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oazisn.moviecatalog.mvvm.main.movies.MoviesFragment
import com.oazisn.moviecatalog.mvvm.main.tvshows.TvShowsFragment

const val MOVIES_INDEX = 0
const val TVSHOWS_INDEX = 1

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MOVIES_INDEX to { MoviesFragment.newInstance() },
        TVSHOWS_INDEX to { TvShowsFragment.newInstance() },
    )

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()

}