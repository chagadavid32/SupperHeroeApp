package com.example.superheroeapp.interfaces

import com.example.superheroeapp.data.series.HeroSeriesData

interface HeroSeriesSelectionListener {

    fun onHeroSeriesSelected( heroesSeriesData: HeroSeriesData )
}