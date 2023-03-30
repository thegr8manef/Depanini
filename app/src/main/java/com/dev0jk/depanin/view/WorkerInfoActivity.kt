package com.dev0jk.depanin.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityUserInfoBinding
import com.dev0jk.depanin.databinding.ActivityWorkerInfoBinding
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.view.home.HomeFragment
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM

class WorkerInfoActivity : AppCompatActivity() {
    lateinit var binding : ActivityWorkerInfoBinding
    lateinit var workerVM: WorkerVM
    private  var uriImage : Uri? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        workerVM = WorkerVM()

        var phone = intent.getStringExtra("phone").toString()
        var username = intent.getStringExtra("username").toString()
        var password = intent.getStringExtra("password").toString()
        var ChosenCity = intent.getStringExtra("ChosenCity").toString()
        var ChosenGouv = intent.getStringExtra("ChosenGouv").toString()


        binding.createAccount.setOnClickListener {
            if(!binding.checkBox.isChecked){

                binding.checkBox.setTextColor(Color.parseColor("#ff3333"))

            } else

                if (binding.cin.text?.isEmpty() == true || binding.niveau.text?.isEmpty() == true) {
                    binding.checkBox.setTextColor(Color.parseColor("#000000"))

                    binding.labelCin.error = null
                    binding.labelNiveau.error = null
                    if (binding.cin.text?.isEmpty() == true) {
                        binding.labelCin.error = "CIN is required"



                    }
                    if (binding.niveau.text?.isEmpty() == true) {
                        binding.labelNiveau.error = "Niveau is required"
                    }

                } else {
                    binding.checkBox.setTextColor(Color.parseColor("#000000"))

                    //  val loadingAlert = LoadingAlert(this)
                    //  loadingAlert.startLoadingAlert()
                    workerVM.signUpWorker(
                        username,
                        password,
                        ChosenGouv,
                        ChosenCity,
                        phone.toInt(),
                        binding.cin.text.toString().toInt(),
                        binding.niveau.text.toString()
                    )
                    val intent1 = Intent(this, HomeActivity::class.java)
                    startActivity(intent1)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

                }

        }

    }

}