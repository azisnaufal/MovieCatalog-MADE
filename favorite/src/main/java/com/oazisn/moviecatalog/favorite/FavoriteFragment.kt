package com.oazisn.moviecatalog.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.oazisn.moviecatalog.R
import com.oazisn.moviecatalog.core.utils.getDrawableFromAttribute
import com.oazisn.moviecatalog.databinding.FragmentMainBinding
import com.oazisn.moviecatalog.favorite.di.favoriteModule
import com.oazisn.moviecatalog.favorite.ui.MOVIES_INDEX
import com.oazisn.moviecatalog.favorite.ui.SectionsPagerAdapter
import com.oazisn.moviecatalog.favorite.ui.TVSHOWS_INDEX
import org.koin.core.context.loadKoinModules


class FavoriteFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        val sectionPagerAdapter = SectionsPagerAdapter(this)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            toolbar.title = getString(R.string.app_favorite_name)
            toolbar.setNavigationIconTint(ContextCompat.getColor(requireContext(), R.color.white))
            toolbar.navigationIcon =
                requireContext().getDrawableFromAttribute(R.attr.homeAsUpIndicator)
            toolbar.setNavigationOnClickListener {
                (activity as AppCompatActivity?)?.supportActionBar?.show()
                findNavController().navigateUp()
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
        binding.viewPager.adapter = null
        super.onDestroyView()
    }

}