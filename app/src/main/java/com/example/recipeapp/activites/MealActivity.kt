package com.example.recipeapp.activites

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityMealBinding
import com.example.recipeapp.fragments.HomeFragment
import com.example.recipeapp.pojo.Meal
import com.example.recipeapp.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealMvvm: MealViewModel
    private lateinit var ytlink : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]

        getMealInformationFromIntent()
        setInformationInViews()

        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observMealDetailsLiveData()

        onYoutubeImgClick()
    }

    private fun onYoutubeImgClick() {
        binding.imgYoutube.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ytlink))
            startActivity(intent)
        }
    }

    private fun observMealDetailsLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this
        ) { value: Meal? ->
            onResponseCase()

            binding.tvMealCategory.text = "Category : ${value!!.strCategory}"
            binding.tvMealLocation.text = "Location : ${value.strArea}"
            binding.instractionText.text = value.strInstructions

            ytlink = value.strYoutube
        }
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingtoolbar.title = mealName
        binding.collapsingtoolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingtoolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddToFavorite.visibility = View.INVISIBLE
        binding.instractionText.visibility = View.INVISIBLE
        binding.tvMealCategory.visibility = View.INVISIBLE
        binding.tvMealLocation.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddToFavorite.visibility = View.VISIBLE
        binding.instractionText.visibility = View.VISIBLE
        binding.tvMealCategory.visibility = View.VISIBLE
        binding.tvMealLocation.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
}