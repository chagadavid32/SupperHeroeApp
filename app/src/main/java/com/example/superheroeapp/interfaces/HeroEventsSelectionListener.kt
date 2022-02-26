package com.example.superheroeapp.interfaces

import com.example.superheroeapp.data.events.HeroEventsData

interface HeroEventsSelectionListener {

    fun onHeroEventClickListener( heroEventsData: HeroEventsData )

}