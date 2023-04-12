package com.dev0jk.depanin.view

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityUserTypeBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.model.entity.UserImageFb
import com.dev0jk.depanin.utils.setUser
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File

class UserTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserTypeBinding
     var type : String =""
     lateinit var user: User
     lateinit var userImageFb: UserImageFb
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
        var image : Uri? = try {
            intent.getStringExtra("image")?.toUri()

        } catch (e : java.lang.NullPointerException){
            null
        }
        //image = intent.getStringExtra("image").toString()
         username = intent.getStringExtra("username").toString()
         password = intent.getStringExtra("password").toString()
         ChosenCity = intent.getStringExtra("ChosenCity").toString()
         ChosenGouv = intent.getStringExtra("ChosenGouv").toString()

        Log.println(Log.ASSERT,"image UserType",image.toString())

        //sendImage(image.toString())
            binding.btnBack.setOnClickListener {
                onBackPressed()
            }



        binding.provcardview.setOnClickListener{
            binding.provcardview.setBackgroundResource(R.drawable.background_user_type)
            binding.provcardview.animate().translationZ(50F).start()
            binding.cardView.setBackgroundResource(R.drawable.background_user_type_white)
            binding.cardView.animate().translationZ(0F).start()

            type = getString(R.string.provider)

        }
        binding.cardView.setOnClickListener{
            binding.cardView.setBackgroundResource(R.drawable.background_user_type)
            binding.cardView.animate().translationZ(50F).start()
            binding.provcardview.setBackgroundResource(R.drawable.background_user_type_white)
            binding.provcardview.animate().translationZ(0F).start()

            type =getString(R.string.worker)

        }


        binding.getStarted.setOnClickListener {

            if (type.isNullOrEmpty()) {
                Toast.makeText(this, getString(R.string.selection_type), Toast.LENGTH_LONG).show()
            }else{
                if (type.equals(getString(R.string.provider))){
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
/*                            user = User(it.id,
                                username,
                                password,
                                ChosenGouv,
                                ChosenCity,
                                image.toString(),
                                phone.toInt(),
                            null,
                            null,
                                true)
                            userVM.addUser(user,image.toUri())
                            userImageFb =UserImageFb(it.id,image)*/
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
                    intent.putExtra("image",image.toString())
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
        builder.setTitle(getString(R.string.info))
        builder.setMessage(getString(R.string.sign_up_failed))
        builder.setCancelable(true)
        builder.setPositiveButton(getString(R.string.try_again)) { dialogInterface, i ->
            finish()
            startActivity(intent)

        }
        builder.setNegativeButton(getString(R.string.ok)) { dialogInterface, i ->
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
    fun sendImage(image : String){
        val client = OkHttpClient()

        val file = File(image)
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", file.name, RequestBody.create("application/octet-stream".toMediaTypeOrNull(), file))
            .build()

        val request = Request.Builder()
            .url("http://localhost:8080/client/signup")
            .post(requestBody)
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            // handle error
        }
    }

}