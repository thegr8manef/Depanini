package com.dev0jk.depanin.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivitySignInBinding
import com.dev0jk.depanin.databinding.ActivityWorkerInfoBinding
import com.dev0jk.depanin.model.entity.Location
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    lateinit var userVM : UserVM
    private lateinit var progressDialog: ProgressDialog

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)
        userVM = UserVM()

            binding.createAccount.setOnClickListener {
                if(!binding.checkBox.isChecked){

                    binding.checkBox.setTextColor(Color.parseColor("#ff3333"))

                } else

                    if (binding.username.text?.isEmpty() == true || binding.password.text?.isEmpty() == true) {
                        binding.checkBox.setTextColor(Color.parseColor("#000000"))

                        binding.labelUsername.error = null
                        binding.labelPassword.error = null
                        if (binding.username.text?.isEmpty() == true) {
                            binding.labelUsername.error = "CIN is required"



                        }
                        if (binding.password.text?.isEmpty() == true) {
                            binding.labelPassword.error = "Niveau is required"
                        }

                    } else {
                        binding.checkBox.setTextColor(Color.parseColor("#000000"))
                if (!isNetworkConnected()){
                    showAlertIfNoConnection()
                }
                        else {

                    progressDialog.setMessage("Verifing User...")
                    progressDialog.show()
                    userVM.authentifacteUsers(
                        binding.username.text.toString(),
                        binding.password.text.toString()
                    ).observe(this) {
                        Log.println(Log.ASSERT, "it.message", it.message)
                        Log.println(Log.ASSERT, "it.response", it.reponse.toString())

                        var itm = it
                        if (it.reponse) {
                            val intent1 = Intent(this, HomeActivity::class.java)
                            startActivity(intent1)

                        } else {
                            progressDialog.dismiss()
                            binding.labelUsername.error = "Your Username is false"
                            binding.labelPassword.error = "Your Password is false"
                        }
                    }
                }
                    }

            }

        }
    fun isNetworkConnected(): Boolean {
        val cm: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
        fun showAlertIfNoConnection() {
        val builder = AlertDialog.Builder(this@SignInActivity)
        builder.setTitle("Info")
        builder.setMessage("You are not Connected plz check you connection network .")
        builder.setCancelable(true)
        builder.setPositiveButton("Try again") { dialogInterface, i ->
            finish()
            startActivity(intent)

        }
        builder.setNegativeButton("OK") { dialogInterface, i ->
            onStart()
            startActivity(intent)
        }
        builder.show()
    }
}