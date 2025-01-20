package com.example.retrofiteweatherapi

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.retrofiteweatherapi.modelsViewPager.ViewPager
import com.example.retrofiteweatherapi.utils.RetrofitInstance
import com.example.retrofiteweatherapi.utils.ViewPagerAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager:ViewPager2
    private lateinit var adapter:ViewPagerAdapter

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        adapter = ViewPagerAdapter(this,ViewPager.viewPagerList)
        viewPager.adapter = adapter

    }
}