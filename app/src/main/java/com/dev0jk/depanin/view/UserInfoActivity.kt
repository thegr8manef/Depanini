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
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.vm.UserVM

class UserInfoActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserInfoBinding
    lateinit var userVM : UserVM
    private  var uriImage : Uri? = null
    private  var phone : String = "+21628556554"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userVM = UserVM()

       // phone = intent.getStringExtra("phone").toString()


        binding.createAccount.setOnClickListener {
            if(!binding.checkBox.isChecked){

                binding.checkBox.setTextColor(Color.parseColor("#ff3333"))

            } else

            if (binding.firstname.text?.isEmpty() == true || binding.lastname.text?.isEmpty() == true) {
                binding.checkBox.setTextColor(Color.parseColor("#000000"))

                binding.labelFirstname.error = null
                binding.labelLastname.error = null
                if (binding.firstname.text?.isEmpty() == true) {
                    binding.labelFirstname.error = "Firstname is required"



                }
                if (binding.lastname.text?.isEmpty() == true) {
                    binding.labelLastname.error = "Lastname is required"
                }

            } else {
                binding.checkBox.setTextColor(Color.parseColor("#000000"))

              //  val loadingAlert = LoadingAlert(this)
              //  loadingAlert.startLoadingAlert()
                val intent: Intent = Intent(this, UserTypeActivity::class.java).apply {

                    if (uriImage != null){
                        putExtra("image", uriImage.toString())
                    }

                    putExtra("phone",phone)
                    putExtra("firstname",binding.firstname.text.toString())
                    putExtra("lastname",binding.lastname.text.toString())

                }
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

            }

        }

        binding.userImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            this.startActivityForResult(intent, 2)

            val callbacks = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    finish()
                }

            }
            this.onBackPressedDispatcher.addCallback(callbacks)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            uriImage = data.data
            Glide.with(this /* context */)
                .load(uriImage)
                .into(binding.userImage)
            binding.userImage.setPadding(0,0,0,0)

        }
    }

}