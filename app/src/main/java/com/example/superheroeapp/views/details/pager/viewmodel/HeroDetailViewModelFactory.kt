package com.example.superheroeapp.views.details.pager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.superheroeapp.data.heroes.HeroesData
import java.lang.IllegalArgumentException

class HeroDetailViewModelFactory (private val heroDetails: HeroesData) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HeroDetailsViewModel::class.java)){
            return HeroDetailsViewModel(heroDetails) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}