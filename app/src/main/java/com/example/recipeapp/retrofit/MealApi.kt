package com.example.recipeapp.retrofit

import com.example.recipeapp.pojo.CategoryList
import com.example.recipeapp.pojo.CategoryMeals
import com.example.recipeapp.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String): Call<MealList>

    @GET("filter.php")
    fun getPopularItems(@Query("c") categoryName: String): Call<CategoryList>
}