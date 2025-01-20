package com.example.retrofiteweatherapi.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.retrofiteweatherapi.R
import com.example.retrofiteweatherapi.SecondActivity
import com.example.retrofiteweatherapi.modelsViewPager.ViewPager

class ViewPagerFragment : Fragment() {

    private var check = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerTV:TextView = view.findViewById(R.id.titleViewPagerTV)
        val viewPagerIV:ImageView = view.findViewById(R.id.imageViewPagerIV)
        val beginViewPagerBTN: Button = view.findViewById(R.id.beginViewPagerBTN)

        val viewPagerItem = arguments?.getSerializable("vp") as ViewPager
        check = viewPagerItem.checkButton
        viewPagerTV.text = viewPagerItem.title
        viewPagerIV.setImageResource(viewPagerItem.image)

        if(!check){
            beginViewPagerBTN.visibility = View.VISIBLE
            beginViewPagerBTN.setOnClickListener {
                startActivity(Intent(activity,SecondActivity::class.java))
            }
        }
    }
}