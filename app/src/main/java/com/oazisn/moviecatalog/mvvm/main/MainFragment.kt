package com.oazisn.moviecatalog.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.databinding.FragmentMainBinding
import com.oazisn.moviecatalog.mvvm.main.ui.MOVIES_INDEX
import com.oazisn.moviecatalog.mvvm.main.ui.SectionsPagerAdapter
import com.oazisn.moviecatalog.mvvm.main.ui.TVSHOWS_INDEX

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionPagerAdapter = SectionsPagerAdapter(this)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            toolbar.inflateMenu(R.menu.main)
            toolbar.setOnMenuItemClickListener {
                return@setOnMenuItemClickListener when (it.itemId) {
                    R.id.favorite -> {
                        val direction =
                            MainFragmentDirections.actionMainFragmentToFavoriteFragment()
                        findNavController().navigate(direction)
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MOVIES_INDEX -> getString(R.string.tab_text_1)
            TVSHOWS_INDEX -> getString(R.string.tab_text_2)
            else -> null
        }
    }

}