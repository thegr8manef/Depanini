package com.dev0jk.depanin.view.settings.edit

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityEditProfileBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.utils.getUser
import com.dev0jk.depanin.utils.setUser
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.view.settings.client.ClientSettingsFragment
import com.dev0jk.depanin.view.settings.worker.WorkerSettingsFragment
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class EditProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityEditProfileBinding
    private  var uriImage : Uri? = null
    private lateinit var userVM : UserVM
    private lateinit var workerVM: WorkerVM
    private lateinit var progressDialog: ProgressDialog
    lateinit var workersettingsFragment: WorkerSettingsFragment
    lateinit var clientsettingsFragment: ClientSettingsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userVM = UserVM()
        workerVM = WorkerVM()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle(getString(R.string.please_wait))
        progressDialog.setCanceledOnTouchOutside(false)
        workersettingsFragment = WorkerSettingsFragment()
        clientsettingsFragment = ClientSettingsFragment()
        val intent1 = Intent(this, HomeActivity::class.java)

        binding.updateAccount.setOnClickListener {

            binding.labelUsername.error = null
            binding.labelNewPassword.error = null
            binding.labelConfirmPassword.error = null

            if (binding.username.text?.isEmpty() == false && binding.newPassword.text?.isEmpty() == false && binding.confirmPassword.text?.isEmpty() == false) {
                if (binding.newPassword.text?.toString()!! == binding.confirmPassword.text?.toString()!!) {
                    if (getUser(this).cin != "null") {
                        progressDialog.setMessage(getString(R.string.updating_user))
                        progressDialog.show()
                        MainScope().launch {
                            workerVM.updateWorker(
                                getUser(this@EditProfileActivity).id!!.toLong(),
                                binding.username.text?.toString(),
                                binding.confirmPassword.text?.toString()
                            )
                        }
                        setUser(User(getUser(this@EditProfileActivity).id!!.toLong(),binding.username.text.toString(),binding.confirmPassword.text.toString(),getUser(this@EditProfileActivity).address_gov,getUser(this@EditProfileActivity).address_municipale,getUser(this@EditProfileActivity).image,getUser(this@EditProfileActivity).phone,getUser(this@EditProfileActivity).cin,getUser(this@EditProfileActivity).speciality,true),this)
                        startActivity(intent1)
                    } else {
                        progressDialog.setMessage(getString(R.string.updating_user))
                        progressDialog.show()
                        MainScope().launch {
                            userVM.updateUser(
                                getUser(this@EditProfileActivity).id!!.toLong(),
                                binding.username.text?.toString(),
                                binding.confirmPassword.text?.toString()
                            )
                        }
                        setUser(User(getUser(this@EditProfileActivity).id!!.toLong(),binding.username.text.toString(),binding.confirmPassword.text.toString(),getUser(this@EditProfileActivity).address_gov,getUser(this@EditProfileActivity).address_municipale,getUser(this@EditProfileActivity).image,getUser(this@EditProfileActivity).phone,getUser(this@EditProfileActivity).cin,getUser(this@EditProfileActivity).speciality,true),this)
                        startActivity(intent1)
                    }
                } else {
                    binding.labelConfirmPassword.error = getString(R.string.password_not_matching)
                    progressDialog.dismiss()
                }
            } else {

                binding.labelUsername.error = getString(R.string.champs_empty)
                binding.labelNewPassword.error = getString(R.string.champs_empty)
                binding.labelConfirmPassword.error = getString(R.string.champs_empty)
            }

        }

        binding.backBtn.setOnClickListener {
            onBackPressed()

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