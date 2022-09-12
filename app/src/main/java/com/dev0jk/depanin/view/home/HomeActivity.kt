package com.dev0jk.depanin.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityHomeBinding
import com.dev0jk.depanin.model.entity.Category
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

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



        binding.bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_send))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_home))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_round_search))



        binding.bottomNavigation.show(2)
    }
}