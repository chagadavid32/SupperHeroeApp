package com.example.superheroeapp.views.details.pages.comics

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroeapp.data.comics.HeroesComicData
import com.example.superheroeapp.databinding.HeroeDataBinding
import com.example.superheroeapp.interfaces.HeroComicSelectionListener

class HeroComicsAdapter(
    private val onClickListener: HeroComicSelectionListener,
    private val context: Context
) : RecyclerView.Adapter<HeroComicsAdapter.ViewHolderHeroDetails>() {

    private lateinit var binding: HeroeDataBinding
    private lateinit var heroComicsData: List<HeroesComicData>


    fun setHeroComicsData(heroComicsData: List<HeroesComicData>) {
        this.heroComicsData = heroComicsData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHeroDetails {
        binding = HeroeDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderHeroDetails(binding, onClickListener, context)
    }

    override fun onBindViewHolder(holder: ViewHolderHeroDetails, position: Int) {
        val heroComics = heroComicsData[position]
        holder.bindComics(heroComics)
    }

    override fun getItemCount(): Int = heroComicsData.size


    class ViewHolderHeroDetails(
        private val binding: HeroeDataBinding,
        private val onClickListener: HeroComicSelectionListener,
        private val context: Context

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindComics(heroeComics: HeroesComicData) {
            with(binding) {

                heroeDataName.text = heroeComics.title

                Glide.with(context).load(
                    "${heroeComics.thumbnail.path}.${heroeComics.thumbnail.extension}")
                    .into(heroeDataThumbnail)

                heroDetailRoot.setOnClickListener {
                    onClickListener.onComicsHeroSelected(heroeComics)
                }

            }
        }

    }


}