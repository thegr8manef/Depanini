package com.dev0jk.depanin.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityHomeBinding
import com.dev0jk.depanin.model.entity.Category
import com.dev0jk.depanin.view.search.SearchFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding
    lateinit var homeFragment : HomeFragment
    lateinit var searchFragment: SearchFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        binding.bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_send))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_home))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_round_search))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_settings))

         homeFragment = HomeFragment()
        searchFragment= SearchFragment()



        binding.bottomNavigation.show(2)

        binding.bottomNavigation.setOnShowListener {
            changeFragment(it.id)

        }

    }


    fun changeFragment(id :Int){


        when (id) {



            2-> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.home_fragment, homeFragment)
                    commit()

                }
            }
            3-> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.home_fragment, searchFragment)
                    commit()

                }
            }



        }
    }
}