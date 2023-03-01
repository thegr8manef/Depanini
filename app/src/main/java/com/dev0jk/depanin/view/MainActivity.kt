package com.dev0jk.depanin.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityHomeBinding
import com.dev0jk.depanin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener {

            Log.v("test_login","login")
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, VerificationActivity::class.java)
            startActivity(intent)
            Log.v("test_signup","signup")
        }


    }

}