package com.example.superheroeapp.views.details.pages.series

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.superheroeapp.R
import com.example.superheroeapp.constants.HeroDetailsContentConstants
import com.example.superheroeapp.data.series.HeroSeriesData
import com.example.superheroeapp.databinding.FragmentHeroSeriesPageBinding
import com.example.superheroeapp.interfaces.HeroSeriesSelectionListener
import com.example.superheroeapp.views.details.pager.viewmodel.HeroDetailsViewModel


class HeroSeriesPage : Fragment(), HeroSeriesSelectionListener {

    private val TAG = "HeroSeriesPage"

    private lateinit var binding: FragmentHeroSeriesPageBinding

    private lateinit var heroDetailsViewModel: HeroDetailsViewModel

    private lateinit var adapter: HeroSeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_hero_series_page,
            container,
            false
        )

        Log.i(TAG, "Created!")

        heroDetailsViewModel = arguments?.getParcelable("ViewModel")!!

        val displayMetrics = requireContext().resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        val columns: Int = (dpWidth / 220).toInt()

        //binding.heroesComicList.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.heroesComicList.layoutManager = StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL)
        binding.heroesComicList.setHasFixedSize(true)

        heroDetailsViewModel.heroSeries.observe(viewLifecycleOwner, Observer {
            binding.heroesComicProgress.visibility = View.GONE
            adapter = HeroSeriesAdapter(this, requireContext())
            adapter.setHeroSeriesData(it.data.results)
            binding.heroesComicList.adapter = adapter
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

    override fun onHeroSeriesSelected(heroesSeriesData: HeroSeriesData) {
        val uri = Uri.parse(heroesSeriesData.urls[0].url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }


}