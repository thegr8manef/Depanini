package com.dev0jk.depanin

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dev0jk.depanin.databinding.ActivityUserInfoBinding
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.view.UserVM

class UserInfoActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserInfoBinding
    lateinit var userVM : UserVM
    private  var uriImage : Uri? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userVM = UserVM()

        binding.createAccount.setOnClickListener {
            if (binding.firstname.text?.isEmpty() == true || binding.lastname.text?.isEmpty() == true) {

                binding.labelFirstname.error = null
                binding.labelLastname.error = null
                if (binding.firstname.text?.isEmpty() == true) {
                    binding.labelFirstname.error = "Firstname is required"


                }
                if (binding.lastname.text?.isEmpty() == true) {
                    binding.labelLastname.error = "Lastname is required"
                }

            } else {
                val loadingAlert = LoadingAlert(this)
                loadingAlert.startLoadingAlert()
                userVM.addUser(
                    User(
                        "NULL",
                        binding.firstname.text.toString(),
                        binding.lastname.text.toString(),
                        "28556554",
                            "NULL"
                    ),
                    uriImage
                ).observe(this, Observer {
                    loadingAlert.dismissDialog()
                    if (it.statu) {
                        Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show()
                    } else {
                        if (it.message.isEmpty()){
                            Toast.makeText(this, "Something Was Wrong", Toast.LENGTH_LONG).show()

                        }else
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                })
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