package com.example.superheroeapp.views.details.pages.events

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroeapp.data.events.HeroEventsData
import com.example.superheroeapp.databinding.HeroeDataBinding
import com.example.superheroeapp.interfaces.HeroEventsSelectionListener

class HeroEventsAdapter (
    private val context: Context,
    private val onClickListener: HeroEventsSelectionListener
) : RecyclerView.Adapter<HeroEventsAdapter.ViewHolderHeroEvents>() {

    private lateinit var binding: HeroeDataBinding
    private lateinit var heroEventsData: List<HeroEventsData>

    fun setHeroEventsData(heroEventsData: List<HeroEventsData>){
        this.heroEventsData = heroEventsData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHeroEvents {
        binding = HeroeDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderHeroEvents(context, binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolderHeroEvents, position: Int) {
        val heroEventData = heroEventsData[position]
        holder.bind(heroEventData)
    }

    override fun getItemCount(): Int = heroEventsData.size

    class ViewHolderHeroEvents (
        private val context: Context,
        private val binding: HeroeDataBinding,
        private val onClickListener: HeroEventsSelectionListener
    ): RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(heroEventsData: HeroEventsData){
            with(binding){

                heroeDataName.text = heroEventsData.title

                Glide.with(context).load(
                    "${heroEventsData.thumbnail.path}.${heroEventsData.thumbnail.extension}"
                ).into(heroeDataThumbnail)

                heroDetailRoot.setOnClickListener {
                    onClickListener.onHeroEventClickListener(heroEventsData)
                }

            }
        }

    }

}