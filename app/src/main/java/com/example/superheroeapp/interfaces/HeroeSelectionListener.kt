package com.example.superheroeapp.interfaces

import com.example.superheroeapp.data.heroes.HeroesData

interface HeroeSelectionListener {

    fun onSelectedHeroe(heroeSelected: HeroesData)

}