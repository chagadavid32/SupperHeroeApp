package com.example.superheroeapp.views.details.pages.series

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroeapp.data.series.HeroSeriesData
import com.example.superheroeapp.databinding.HeroeDataBinding
import com.example.superheroeapp.interfaces.HeroSeriesSelectionListener

class HeroSeriesAdapter (
    private val onClickListener: HeroSeriesSelectionListener,
    private val context: Context
) : RecyclerView.Adapter<HeroSeriesAdapter.ViewHolderHeroSeries>(){

    private lateinit var binding: HeroeDataBinding
    private lateinit var heroSeriesData: List<HeroSeriesData>

    fun setHeroSeriesData( heroSeriesData: List<HeroSeriesData>){
        this.heroSeriesData = heroSeriesData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHeroSeries {
        binding = HeroeDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderHeroSeries(binding, onClickListener, context)
    }

    override fun onBindViewHolder(holder: ViewHolderHeroSeries, position: Int) {
        val heroSeriesData = heroSeriesData[position]
        holder.bind(heroSeriesData)
    }

    override fun getItemCount(): Int = heroSeriesData.size

    class ViewHolderHeroSeries(
        private val binding: HeroeDataBinding,
        private val onClickListener: HeroSeriesSelectionListener,
        private val context: Context
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(heroSeries: HeroSeriesData){
            with(binding){

                heroeDataName.text = heroSeries.title

                Glide.with(context).load(
                    "${heroSeries.thumbnail.path}.${heroSeries.thumbnail.extension}"
                ).into(heroeDataThumbnail)

                heroDetailRoot.setOnClickListener {
                    onClickListener.onHeroSeriesSelected(heroSeries)
                }


            }

        }

    }

}