package com.dev0jk.depanin.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityUserTypeBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.utils.setUser
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM

class UserTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserTypeBinding
     var type : String =""
     lateinit var user: User
    lateinit var userVM : UserVM
    lateinit var workerVM: WorkerVM
    var username = String()
    var phone = String()
    var password = String()
    var image = String()
    var ChosenCity = String()
    var ChosenGouv = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userVM = UserVM()
        workerVM = WorkerVM()

         phone = intent.getStringExtra("phone").toString()
/*          var image : Uri? = try {
            Uri.parse(intent.getStringExtra("image").toString())
        } catch (e : java.lang.NullPointerException){
            null
        }*/
        image = intent.getStringExtra("image").toString()
         username = intent.getStringExtra("username").toString()
         password = intent.getStringExtra("password").toString()
         ChosenCity = intent.getStringExtra("ChosenCity").toString()
         ChosenGouv = intent.getStringExtra("ChosenGouv").toString()








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

            if (type.isNullOrEmpty()) {
                Toast.makeText(this, "Please Select your Type", Toast.LENGTH_LONG).show()
            }else{
                if (type.equals("provider")){
                    userVM.signUpClient(
                        username,
                        password,
                        ChosenGouv,
                        ChosenCity,
                        image.toString(),
                        phone.toInt()
                    ).observe(this){
                        if (it.isLogin==true) {
                            setUser(User(
                                it.id,
                                it.username,
                                it.password,
                                it.address_gov,
                                it.address_municipale,
                                it.image.toString(),
                                it.phone!!.toInt(),
                                it.cin,
                                it.speciality,
                                true),
                                this)
                            next()

                        }else{
                            showAlertIfAddingFailed()

                        }
                    }

/*                    if (::user.isInitialized) {
                        setUser(user, this@UserTypeActivity)
                    }*/
            }else{
                    val intent = Intent(this, WorkerInfoActivity::class.java)

                    intent.putExtra("username",username)
                    intent.putExtra("password",password)
                    intent.putExtra("ChosenCity",ChosenCity)
                    intent.putExtra("ChosenGouv",ChosenGouv)
                    intent.putExtra("image",image)
                    Log.println(Log.ASSERT,"image_2",image.toString())
                    intent.putExtra("phone",phone)


                    startActivity(intent)
                }

        }
/*            if (!type.isNullOrEmpty()) {


                val loadingAlert = LoadingAlert(this)
                loadingAlert.startLoadingAlert()
                userVM.addUser(
                    User(
                        "NULL",
                        username,
                        password,
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
            }*/
        }
    }
    fun showAlertIfAddingFailed() {
        val builder = AlertDialog.Builder(this@UserTypeActivity)
        builder.setTitle("Info")
        builder.setMessage("Sign Up Failed .")
        builder.setCancelable(true)
        builder.setPositiveButton("Try again") { dialogInterface, i ->
            finish()
            startActivity(intent)

        }
        builder.setNegativeButton("OK") { dialogInterface, i ->
            onResume()
        }
        builder.show()
    }
    private fun next(){
        //eaiToast.makeText(applicationContext, "register: ", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, HomeActivity::class.java).apply {

/*            putExtra("speciality",speciality)
            putExtra("role",role)
            putExtra("username",username)
            putExtra("email",email)*/
        }
        startActivity(intent)
    }

}