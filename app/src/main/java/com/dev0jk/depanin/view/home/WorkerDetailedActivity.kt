package com.dev0jk.depanin.view.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity

import com.dev0jk.depanin.databinding.ActivityWorkerDetailedBinding
var REQUEST_PHONE_CALL= 1
class WorkerDetailedActivity : AppCompatActivity() {
    var username = String()
    var address_gov = String()
    var address_municipale = String()
    var image = String()
    var phone = String()
    var speciality = String()
    lateinit var binding : ActivityWorkerDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerDetailedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        val intent1 = Intent(this, HomeActivity::class.java)

        username = intent.getStringExtra("username_worker").toString()
        address_gov = intent.getStringExtra("address_gov_worker").toString()
        address_municipale = intent.getStringExtra("address_municipale_worker").toString()
        image = intent.getStringExtra("image_worker").toString()
        phone = intent.getIntExtra("phone_worker",0).toString()
        Log.println(Log.ASSERT,"worker phone2",phone)
        speciality = intent.getStringExtra("speciality_worker").toString()

        binding.workerUsername.text = username
        binding.workerLocation.text = "$address_gov,$address_municipale"
        binding.workerSpeciality.text = speciality

        binding.backBtn.setOnClickListener {
            onBackPressed()

        }
        binding.workerPhone.setOnClickListener {
            // cheacking permission
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                REQUEST_PHONE_CALL
            )
            else {
                makeCall()
            }
        }
    }


        private fun makeCall(){
            val intent1= Intent(Intent.ACTION_CALL)
            Log.println(Log.ASSERT,"worker phone3",phone)
            intent1.data = Uri.parse("tel:${phone.toInt()}")
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show()
                return
            }
            startActivity(intent1)
        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == REQUEST_PHONE_CALL){
                makeCall()
            }

        }
}

