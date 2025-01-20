package com.example.retrofiteweatherapi.utils

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofiteweatherapi.fragments.ViewPagerFragment
import com.example.retrofiteweatherapi.modelsViewPager.ViewPager

class ViewPagerAdapter(fragment: FragmentActivity,private val viewPagerList: MutableList<ViewPager>)
    :FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return viewPagerList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerFragment()
        fragment.arguments = bundleOf("vp" to viewPagerList[position])
        return fragment
    }
}
