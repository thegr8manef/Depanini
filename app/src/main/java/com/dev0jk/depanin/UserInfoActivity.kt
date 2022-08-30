package com.dev0jk.depanin

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dev0jk.depanin.databinding.ActivityUserInfoBinding
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.view.UserVM

class UserInfoActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserInfoBinding
    lateinit var userVM : UserVM

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userVM = UserVM()

        binding.createAccount.setOnClickListener {
            if(binding.firstname.text?.isEmpty() == true || binding.lastname.text?.isEmpty() == true){

                binding.labelFirstname.error = null
                binding.labelLastname.error = null
                if(binding.firstname.text?.isEmpty() == true){
                    binding.labelFirstname.error ="Is required"


                }
                if(binding.lastname.text?.isEmpty() == true){
                    binding.labelLastname.error ="Is required"
                }

            }
            else{
                val loadingAlert = LoadingAlert(this)
                loadingAlert.startLoadingAlert()
                userVM.addUser(User("NULL",binding.firstname.text.toString(), binding.lastname.text.toString(), "28556554")).observe(this, Observer {
                    loadingAlert.dismissDialog()
                    if (it.statu){
                        Toast.makeText(this, "yemchy", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                })
            }

        }
    }
}