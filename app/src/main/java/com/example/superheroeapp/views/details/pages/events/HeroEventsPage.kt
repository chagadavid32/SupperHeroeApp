package com.example.superheroeapp.views.details.pages.events

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.superheroeapp.R
import com.example.superheroeapp.constants.HeroDetailsContentConstants
import com.example.superheroeapp.data.events.HeroEventsData
import com.example.superheroeapp.databinding.FragmentHeroEventsPageBinding
import com.example.superheroeapp.interfaces.HeroEventsSelectionListener
import com.example.superheroeapp.views.details.pager.viewmodel.HeroDetailsViewModel

class HeroEventsPage : Fragment(), HeroEventsSelectionListener{

    private val TAG = "HeroEventsPage"

    private lateinit var binding: FragmentHeroEventsPageBinding

    private lateinit var heroDetailsViewModel: HeroDetailsViewModel

    private lateinit var adapter: HeroEventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_hero_events_page,
            container,
            false
        )

        heroDetailsViewModel = arguments?.getParcelable("ViewModel")!!

        val displayMetrics = requireContext().resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        val columns: Int = (dpWidth / 220).toInt()

        //binding.heroesComicList.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.heroEventsList.layoutManager = StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL)
        binding.heroEventsList.setHasFixedSize(true)

        heroDetailsViewModel.heroEvents.observe(viewLifecycleOwner, Observer {
            binding.heroEventsProgress.visibility = View.GONE
            adapter = HeroEventsAdapter(requireContext(), this)
            adapter.setHeroEventsData(it.data.results)
            binding.heroEventsList.adapter = adapter
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf {
            it.containsKey(HeroDetailsContentConstants.HERO_DATA)
        }?.apply {
            Log.i(TAG, getString(HeroDetailsContentConstants.HERO_DATA)!!)
            when(getString(HeroDetailsContentConstants.HERO_DATA)){

                HeroDetailsContentConstants.HERO_EVENTS -> { heroDetailsViewModel.getEvents() }
                HeroDetailsContentConstants.HERO_COMICS -> { heroDetailsViewModel.getComics() }
                HeroDetailsContentConstants.HERO_SERIES -> { heroDetailsViewModel.getSeries() }

            }
        }



    }

    override fun onHeroEventClickListener(heroEventsData: HeroEventsData) {
        val uri = Uri.parse(heroEventsData.urls[0].url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}