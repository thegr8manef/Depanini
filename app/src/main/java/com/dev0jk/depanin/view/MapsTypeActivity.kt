package com.dev0jk.depanin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev0jk.depanin.databinding.ActivityMapsTypeBinding

class MapsTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.getStarted.isClickable =false
        binding.getStarted.isEnabled = false

        var sm =0
        var GPS = 0
        binding.SimpleMapCardView.setOnClickListener {
            //binding.provcardview.setBackgroundResource(R.drawable.background_user_type)
            binding.SimpleMapCardView.animate().translationZ(50F).start()
            //binding.cardView.setBackgroundResource(R.drawable.background_user_type_white)
            binding.cardViewGPS.animate().translationZ(0F).start()
            sm = 1
            GPS = 0
            if (sm == 1){
                binding.getStarted.isClickable =true
                binding.getStarted.isEnabled = true
                binding.getStarted.setOnClickListener {
                        val intent1 = Intent(this, MapsActivity::class.java)
                        startActivity(intent1)


                }
            }

        }

        binding.cardViewGPS.setOnClickListener {
            // binding.cardView.setBackgroundResource(R.drawable.background_user_type)
            binding.cardViewGPS.animate().translationZ(50F).start()
            // binding.provcardview.setBackgroundResource(R.drawable.background_user_type_white)
            binding.SimpleMapCardView.animate().translationZ(0F).start()
            sm = 0
            GPS = 1
            if (GPS==1){
                binding.getStarted.isClickable =true
                binding.getStarted.isEnabled = true
                binding.getStarted.setOnClickListener {

                        val intent1 = Intent(this, MapsGpsActivity::class.java)
                        startActivity(intent1)


                }
            }

        }


    }
}