package com.dev0jk.depanin.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityUserTypeBinding
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.UserVM

class UserTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserTypeBinding
     var type : String =""
    lateinit var userVM : UserVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userVM = UserVM()
        var phone = intent.getStringExtra("phone").toString()
        var image : Uri? = try {
            Uri.parse(intent.getStringExtra("image").toString())
        } catch (e : java.lang.NullPointerException){
            null
        }

        var firstName = intent.getStringExtra("firstname").toString()
        var lastName = intent.getStringExtra("lastname").toString()
        var ChosenCity = intent.getStringExtra("ChosenCity").toString()
        var ChosenGouv = intent.getStringExtra("ChosenGouv").toString()








        binding.provcardview.setOnClickListener{
            binding.provcardview.setBackgroundResource(R.drawable.background_user_type)
            binding.provcardview.animate().translationZ(50F).start()
            binding.cardView.setBackgroundResource(R.drawable.background_user_type_white)
            binding.cardView.animate().translationZ(0F).start()

            type = "provider"

        }
        binding.cardView.setOnClickListener{
            binding.cardView.setBackgroundResource(R.drawable.background_user_type)
            binding.cardView.animate().translationZ(50F).start()
            binding.provcardview.setBackgroundResource(R.drawable.background_user_type_white)
            binding.provcardview.animate().translationZ(0F).start()

            type ="worker"

        }


        binding.getStarted.setOnClickListener {
            if (!type.isNullOrEmpty()) {


                val loadingAlert = LoadingAlert(this)
                loadingAlert.startLoadingAlert()
                userVM.addUser(
                    User(
                        "NULL",
                        firstName,
                        lastName,
                        phone,
                        image.toString(),
                        type,
                        Location(ChosenGouv,ChosenCity)

                    ),
                    image
                ).observe(this, Observer {
                    loadingAlert.dismissDialog()
                    if (it.statu) {
                        Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("id",it.message)
                        startActivity(intent)
                        this.finish()
                    } else {
                        if (it.message.isEmpty()){
                            Toast.makeText(this, "Something Was Wrong", Toast.LENGTH_LONG).show()

                        }else
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                })
            }

            else{
                Toast.makeText(this, "Please Select your Type", Toast.LENGTH_LONG).show()
            }
        }
    }
}