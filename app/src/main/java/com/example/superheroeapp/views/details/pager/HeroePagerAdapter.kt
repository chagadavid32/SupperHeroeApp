package com.example.superheroeapp.views.details.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HeroePagerAdapter ( fragmentActivity: FragmentActivity ) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentContentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()


    override fun getItemCount(): Int {
        return fragmentContentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentContentList[position]
    }

    fun getPageTitle(position: Int): String {
        return fragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String){
        fragmentContentList.add(fragment)
        fragmentTitleList.add(title)
    }

}