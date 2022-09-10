package com.dev0jk.depanin.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityHomeBinding
import com.dev0jk.depanin.model.entity.Category

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val arrayOfCategories = arrayListOf<Category>(
            Category("All", R.drawable.img_all),
            Category("Electricity", R.drawable.img_electricity),
            Category("Plumbing", R.drawable.img_plumbing),
            Category("Painting", R.drawable.img_painting),
            Category("Gardener", R.drawable.img_gardener),
            Category("Security", R.drawable.img_camera),
            Category("Masonry", R.drawable.img_masonry),
            Category("Carpenter", R.drawable.img_carpenter),)

        val categoryAdapter = CategoriesAdapter(this, arrayOfCategories)
        binding.categories.layoutManager = LinearLayoutManager(this)
        binding.categories.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.categories.adapter = categoryAdapter
    }
}