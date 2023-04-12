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
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityUserInfoBinding
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.utils.LoadingAlert
import com.dev0jk.depanin.vm.UserVM
import java.util.regex.Pattern

class UserInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserInfoBinding
    lateinit var userVM: UserVM
    private var uriImage: Uri? = null
    private var phone: String? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userVM = UserVM()

        phone = intent.getStringExtra("phone").toString()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnSignin.setOnClickListener {
            val intent: Intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.createAccount.setOnClickListener {
            if (!binding.checkBox.isChecked) {

                binding.checkBox.setTextColor(Color.parseColor("#ff3333"))

            } else

                if (binding.username.text?.isEmpty() == true || binding.password.text?.isEmpty() == true) {
                    binding.checkBox.setTextColor(Color.parseColor("#000000"))

                    binding.labelUsername.error = null
                    binding.labelPassword.error = null
                    if (binding.username.text?.isEmpty() == true) {
                        binding.labelUsername.error = getString(R.string.username_is_required)


                    }
                    if (binding.password.text?.isEmpty() == true) {
                        binding.labelPassword.error = getString(R.string.password_is_required)
                    }

                } else {
                    binding.checkBox.setTextColor(Color.parseColor("#000000"))
                    if (passwordValidate(binding.password.text.toString())) {


                        //  val loadingAlert = LoadingAlert(this)
                        //  loadingAlert.startLoadingAlert()
                        val intent: Intent =
                            Intent(this, SelectLocationActivity::class.java).apply {

                                if (uriImage != null) {
                                    putExtra("image", uriImage.toString())
                                    Log.println(Log.ASSERT, "image_", uriImage.toString())

                                }

                                putExtra("phone", phone)
                                putExtra("username", binding.username.text.toString())
                                putExtra("password", binding.password.text.toString())

                            }
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

                    }
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
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            uriImage = data.data
            Glide.with(this /* context */)
                .load(uriImage)
                .into(binding.userImage)
            binding.userImage.setPadding(0, 0, 0, 0)

        }
    }

    private fun passwordValidate(password1: String): Boolean {
        when {
            password1.length < 9 -> {
                binding.labelPassword.error = getString(R.string.min_9_characters)
                return false
            }
            !password1.matches(".*[A-Z].*".toRegex()) -> {
                binding.labelPassword.error = getString(R.string.min_1_upper_case)
                return false
            }
            !password1.matches(".*[a-z].*".toRegex()) -> {
                binding.labelPassword.error = getString(R.string.min_1_lower_case)
                return false
            }
            !password1.matches(".*[1-9].*".toRegex()) -> {
                binding.labelPassword.error = getString(R.string.min_1_number)
                return false
            }
            !password1.matches(".*[!@#$%^&*+=/?.,].*".toRegex()) -> {
                binding.labelPassword.error = getString(R.string.min_1_symbol)
                return false
            }
            else -> return true

        }
    }
}