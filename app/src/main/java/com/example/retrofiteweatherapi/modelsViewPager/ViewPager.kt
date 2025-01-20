package com.example.retrofiteweatherapi.modelsViewPager

import com.example.retrofiteweatherapi.R
import java.io.Serializable

class ViewPager(val title:String, val image:Int,val checkButton:Boolean):Serializable {

    companion object{
        val viewPagerList = mutableListOf(
            ViewPager("Говорят после дождя...",R.drawable.a,true),
            ViewPager("...на небе радуга!",R.drawable.s,true),
            ViewPager("Но чтобы не попасть под дождь,\n узнай прогноз заранее!)"
                ,R.drawable.d,false)
        )
    }
}