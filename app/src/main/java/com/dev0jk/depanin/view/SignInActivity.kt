package com.dev0jk.depanin.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivitySignInBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.utils.setUser
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.UserVM

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    lateinit var userVM : UserVM
    lateinit var user: User
    private lateinit var progressDialog: ProgressDialog

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        userVM = UserVM()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle(getString(R.string.please_wait))
        progressDialog.setCanceledOnTouchOutside(false)
            binding.btnBack.setOnClickListener {
                onBackPressed()
            }
        binding.btnSignup.setOnClickListener {
            val intent2 = Intent(this, VerificationActivity::class.java)
            startActivity(intent2)
        }
            binding.createAccount.setOnClickListener {

                    if (binding.username.text?.isEmpty() == true || binding.password.text?.isEmpty() == true) {
                        binding.labelUsername.error = null
                        binding.labelPassword.error = null
                        if (binding.username.text?.isEmpty() == true) {
                            binding.labelUsername.error = getString(R.string.username_is_required)



                        }
                        if (binding.password.text?.isEmpty() == true) {
                            binding.labelPassword.error = getString(R.string.password_is_required)
                        }

                    } else {
                if (!isNetworkConnected()){
                    showAlertIfNoConnection()
                }
                        else {

                    progressDialog.setMessage(getString(R.string.verifying_user))
                    progressDialog.show()
                    userVM.authentifacteUsers(
                        binding.username.text.toString(),
                        binding.password.text.toString()
                    ).observe(this) {
                        Log.println(Log.ASSERT, "it.message", it.username)
                        Log.println(Log.ASSERT, "it.response", it.password.toString())

                        var itm = it
                        if (it.isLogin==true) {
                            user = User(it.id,it.username,it.password,it.address_gov,it.address_municipale,it.image,it.phone,it.cin,it.speciality,it.isLogin)
                            Log.println(Log.ASSERT, "it.response",user.toString())
                            val intent1 = Intent(this, HomeActivity::class.java).apply {
                                setUser(user,this@SignInActivity)
                            }
                            startActivity(intent1)

                        } else {
                            progressDialog.dismiss()
                            binding.labelUsername.error = getString(R.string.your_username_is_false)
                            binding.labelPassword.error = getString(R.string.your_password_is_false)
                        }
                    }
                }
                    }

            }

        binding.tvForgetPassword.setOnClickListener{
            showAlertSendingEmail()

        }

        }
    fun isNetworkConnected(): Boolean {
        val cm: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
        fun showAlertIfNoConnection() {
        val builder = AlertDialog.Builder(this@SignInActivity)
        builder.setTitle(getString(R.string.info))
        builder.setMessage(getString(R.string.cheking_internet))
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

    fun showAlertSendingEmail() {
        val builder = AlertDialog.Builder(this@SignInActivity)
        builder.setTitle(getString(R.string.forgot_password))
        builder.setMessage(getString(R.string.sending_email))
        builder.setCancelable(true)
        builder.setNegativeButton(getString(R.string.ok)) { dialogInterface, i ->
            onResume()
        }
        builder.show()
    }
}