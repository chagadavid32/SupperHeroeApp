package com.example.superheroeapp.views.details.pager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.superheroeapp.R
import com.example.superheroeapp.constants.HeroDetailsContentConstants.HERO_COMICS
import com.example.superheroeapp.constants.HeroDetailsContentConstants.HERO_DATA
import com.example.superheroeapp.constants.HeroDetailsContentConstants.HERO_EVENTS
import com.example.superheroeapp.constants.HeroDetailsContentConstants.HERO_SERIES
import com.example.superheroeapp.databinding.FragmentHeroDetailsCarouselBinding
import com.example.superheroeapp.views.details.pages.comics.HeroComicsPage
import com.example.superheroeapp.views.details.pager.viewmodel.HeroDetailViewModelFactory
import com.example.superheroeapp.views.details.pager.viewmodel.HeroDetailsViewModel
import com.example.superheroeapp.views.details.pages.events.HeroEventsPage
import com.example.superheroeapp.views.details.pages.series.HeroSeriesPage
import com.google.android.material.tabs.TabLayoutMediator


class HeroDetailsCarousel : Fragment() {

    private val TAG = "HERODETAILS"

    private lateinit var binding: FragmentHeroDetailsCarouselBinding

    //ViewModel
    private lateinit var heroDetailViewModelFactory: HeroDetailViewModelFactory
    private lateinit var heroDetailsViewModel: HeroDetailsViewModel

    //Fragments
    private lateinit var fragmentHeroComics: Fragment
    private lateinit var fragmentHeroSeries: Fragment
    private lateinit var fragmentHeroEvents: Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i(TAG, "onCreateView")

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_hero_details_carousel,
            container,
            false
        )

        heroDetailViewModelFactory = HeroDetailViewModelFactory(
            HeroDetailsCarouselArgs.fromBundle(requireArguments()).hero!!
        )
        heroDetailsViewModel = ViewModelProvider(this, heroDetailViewModelFactory)
            .get(HeroDetailsViewModel::class.java)

        val pagerAdapter = HeroePagerAdapter(requireActivity())
        initFragments()

        pagerAdapter.addFragment(fragmentHeroComics, "COMICS")
        pagerAdapter.addFragment(fragmentHeroSeries, "SERIES")
        pagerAdapter.addFragment(fragmentHeroEvents, "EVENTS")

        binding.pagerHeroeData.adapter = pagerAdapter

        TabLayoutMediator(binding.tabHeroeData, binding.pagerHeroeData){ tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()

        return binding.root
    }

    private fun initFragments() {
        fragmentHeroComics = HeroComicsPage(/*"HERO_COMICS"*/).apply {
            arguments = Bundle().apply {
                putString(HERO_DATA, HERO_COMICS)
                putParcelable("ViewModel", heroDetailsViewModel)
            }
        }

        fragmentHeroSeries = HeroSeriesPage(/*"HERO_SERIES"*/).apply {
            arguments = Bundle().apply {
                putString(HERO_DATA, HERO_SERIES)
                putParcelable("ViewModel", heroDetailsViewModel)
            }
        }

        fragmentHeroEvents = HeroEventsPage(/*"HERO_EVENTS"*/).apply {
            arguments = Bundle().apply {
                putString(HERO_DATA, HERO_EVENTS)
                putParcelable("ViewModel", heroDetailsViewModel)

            }
        }
    }

}