package com.dev0jk.depanin.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityHomeBinding
import com.dev0jk.depanin.utils.getUser
import com.dev0jk.depanin.view.search.SearchFragment
import com.dev0jk.depanin.view.settings.client.ClientSettingsFragment
import com.dev0jk.depanin.view.settings.worker.WorkerSettingsFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding
    lateinit var homeFragment : HomeFragment
    lateinit var searchFragment: SearchFragment
    lateinit var workersettingsFragment: WorkerSettingsFragment
    lateinit var clientsettingsFragment: ClientSettingsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        val chosenCity = intent.getStringExtra("ChosenCity")
        val chosenGouv = intent.getStringExtra("ChosenGouv")
        Log.v("================>ChosenCity","$chosenCity")
        Log.v("================>ChosenGouv","$chosenGouv")
        if (getUser(this).cin !="null"){
            binding.bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_send))
            binding.bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_home))
            binding.bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_round_search))
            binding.bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_settings))

            homeFragment = HomeFragment()
            searchFragment= SearchFragment()
            workersettingsFragment = WorkerSettingsFragment()



            binding.bottomNavigation.show(2)

            binding.bottomNavigation.setOnShowListener {
                changeFragmentWorker(it.id)

            }
        }else{
            binding.bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_send))
            binding.bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_home))
            binding.bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_round_search))
            binding.bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_settings))

            homeFragment = HomeFragment()
            searchFragment= SearchFragment()
            clientsettingsFragment = ClientSettingsFragment()



            binding.bottomNavigation.show(2)

            binding.bottomNavigation.setOnShowListener {
                changeFragmentClient(it.id)

            }
        }




    }
    fun changeFragmentWorker(id :Int){


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
            4-> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.home_fragment, workersettingsFragment)
                    commit()

                }
            }




        }
    }

    fun changeFragmentClient(id :Int){


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
            4-> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.home_fragment, clientsettingsFragment)
                    commit()

                }
            }




        }

    }


}