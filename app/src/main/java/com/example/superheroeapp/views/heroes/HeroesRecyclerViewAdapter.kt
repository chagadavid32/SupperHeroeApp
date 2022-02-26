package com.example.superheroeapp.views.heroes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.superheroeapp.data.heroes.HeroesData
import com.example.superheroeapp.data.heroes.Json
import com.example.superheroeapp.databinding.HeroeItemBinding
import com.example.superheroeapp.interfaces.HeroeSelectionListener

class HeroesRecyclerViewAdapter(
    var onClickListener: HeroeSelectionListener,
    var context : Context
) : RecyclerView.Adapter<HeroesRecyclerViewAdapter.ViewHolderHeroes>(){

    private lateinit var heroesData: List<HeroesData>
    private lateinit var binding: HeroeItemBinding

    fun setHeroesData(heroesData: List<HeroesData>){
        this.heroesData = heroesData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHeroes {
        binding = HeroeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderHeroes(binding, onClickListener, context)
    }

    override fun onBindViewHolder(holder: ViewHolderHeroes, position: Int) {
        val hero = heroesData[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int = heroesData.size

    class ViewHolderHeroes(
        private val binding: HeroeItemBinding,
        private val onClickListener: HeroeSelectionListener,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(heroe: HeroesData){

            with(binding){

                heroeName.text = heroe.name

                Glide.with(context)
                    .load(heroe.thumbnail.path + "." +heroe.thumbnail.extension)
                    .into(binding.heroeThumbnail)

                heroeItem.setOnClickListener {
                    onClickListener.onSelectedHeroe(heroe)
                }


            }
        }
    }
}