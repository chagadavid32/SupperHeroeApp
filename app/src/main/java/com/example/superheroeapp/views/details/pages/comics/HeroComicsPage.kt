package com.example.superheroeapp.views.details.pages.comics

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
import com.example.superheroeapp.R
import com.example.superheroeapp.constants.HeroDetailsContentConstants.HERO_COMICS
import com.example.superheroeapp.constants.HeroDetailsContentConstants.HERO_DATA
import com.example.superheroeapp.constants.HeroDetailsContentConstants.HERO_EVENTS
import com.example.superheroeapp.constants.HeroDetailsContentConstants.HERO_SERIES
import com.example.superheroeapp.data.comics.HeroesComicData
import com.example.superheroeapp.databinding.FragmentHeroComicsPageBinding
import com.example.superheroeapp.interfaces.HeroComicSelectionListener
import com.example.superheroeapp.views.details.pager.viewmodel.HeroDetailsViewModel

class HeroComicsPage ( ): Fragment() , HeroComicSelectionListener{

    private val TAG = "TAG"

    private lateinit var binding: FragmentHeroComicsPageBinding

    private lateinit var heroDetailsViewModel: HeroDetailsViewModel

    private var adapter: HeroComicsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_hero_comics_page,
            container,
            false
        )

        Log.i(TAG, "Created!")

        heroDetailsViewModel = arguments?.getParcelable("ViewModel")!!

        binding.lifecycleOwner = viewLifecycleOwner

        val displayMetrics = requireContext().resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        val columns: Int = (dpWidth / 220).toInt()

        binding.heroesDataList.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.heroesDataList.setHasFixedSize(true)

        heroDetailsViewModel.heroComics.observe(viewLifecycleOwner, Observer {
            binding.progressBar2.visibility = View.GONE
            adapter = HeroComicsAdapter(this, requireContext())
            adapter?.setHeroComicsData(it.data.results)
            binding.heroesDataList.adapter = adapter
        })

        Log.i(TAG, heroDetailsViewModel.heroeDetail.value?.name.toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf {
            it.containsKey(HERO_DATA)
        }?.apply {
            Log.i(TAG, getString(HERO_DATA)!!)
            when(getString(HERO_DATA)){

                HERO_EVENTS -> { heroDetailsViewModel.getEvents() }
                HERO_COMICS -> { heroDetailsViewModel.getComics() }
                HERO_SERIES -> { heroDetailsViewModel.getSeries() }

            }
        }


    }


    //Interface function
    override fun onComicsHeroSelected(heroesComicData: HeroesComicData) {
        val uri = Uri.parse(heroesComicData.urls[0].url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}