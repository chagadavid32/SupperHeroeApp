package com.example.superheroeapp.views.heroes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroeapp.R
import com.example.superheroeapp.data.heroes.HeroesData
import com.example.superheroeapp.databinding.FragmentHeroesListBinding
import com.example.superheroeapp.interfaces.HeroeSelectionListener

class HeroesFragmentList : Fragment() , HeroeSelectionListener{

    private val TAG = "HeroesListFragment"

    private lateinit var binding: FragmentHeroesListBinding

    private lateinit var heroesListViewModel: HeroesListViewModel

    private lateinit var adapter: HeroesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_heroes_list,
            container,
            false
        )

        Log.i(TAG, "Fragment launched")

        heroesListViewModel = ViewModelProvider(this).get(HeroesListViewModel::class.java)

        val displayMetrics = requireContext().resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        val columns: Int = (dpWidth / 220).toInt()

        binding.heroesList.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.heroesList.setHasFixedSize(true)

        heroesListViewModel.heroesData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            adapter = HeroesRecyclerViewAdapter( this, requireContext())
            adapter.setHeroesData(it.data.results)
            binding.heroesList.adapter = adapter
        })

        return binding.root
    }


    //Click listener
    override fun onSelectedHeroe(heroeSelected: HeroesData) {
        //NavController
        val action = HeroesFragmentListDirections.actionListToDetails()
        action.hero = heroeSelected
        findNavController().navigate(action)
    }

}