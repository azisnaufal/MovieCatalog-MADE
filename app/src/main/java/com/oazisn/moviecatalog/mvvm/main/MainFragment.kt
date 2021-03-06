package com.oazisn.moviecatalog.mvvm.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.databinding.FragmentMainBinding
import com.oazisn.moviecatalog.mvvm.main.ui.MOVIES_INDEX
import com.oazisn.moviecatalog.mvvm.main.ui.SectionsPagerAdapter
import com.oazisn.moviecatalog.mvvm.main.ui.TVSHOWS_INDEX

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionPagerAdapter = SectionsPagerAdapter(childFragmentManager,
            viewLifecycleOwner.lifecycle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            viewPager.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View?) {
                }

                override fun onViewDetachedFromWindow(v: View?) {
                    viewPager.adapter = null
                }

            })
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

    override fun onDestroyView() {
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.detach()
        super.onDestroyView()
    }

}