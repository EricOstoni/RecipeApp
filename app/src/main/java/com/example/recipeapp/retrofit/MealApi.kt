package com.example.recipeapp.retrofit

import com.example.recipeapp.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET( "random.php" )
    fun getRandomMeal():Call<MealList>
}