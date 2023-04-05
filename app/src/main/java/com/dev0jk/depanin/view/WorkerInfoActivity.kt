package com.dev0jk.depanin.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityWorkerInfoBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.utils.setUser
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.WorkerVM

class WorkerInfoActivity : AppCompatActivity() {
    lateinit var binding : ActivityWorkerInfoBinding
    lateinit var workerVM: WorkerVM
    lateinit var user: User
    private  var uriImage : Uri? = null
    var adapterItems: ArrayAdapter<String>? = null
    private var selected = false
    var speciality = String()
    private var stausList = arrayListOf<String>("Electricity","Plumbing","Painting","Gardener","Security","Masonry","Carpenter")
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        workerVM = WorkerVM()

        var phone = intent.getStringExtra("phone").toString()
        var username = intent.getStringExtra("username").toString()
        var password = intent.getStringExtra("password").toString()
        var ChosenCity = intent.getStringExtra("ChosenCity").toString()
        var ChosenGouv = intent.getStringExtra("ChosenGouv").toString()
        var image = intent.getStringExtra("image").toString()
         speciality = intent.getStringExtra("speciality").toString()

        dropDownList()

        binding.createAccount.setOnClickListener {
            if(!binding.checkBox.isChecked){

                binding.checkBox.setTextColor(Color.parseColor("#ff3333"))

            } else

                if (binding.cin.text?.isEmpty() == true || !selected) {
                    binding.checkBox.setTextColor(Color.parseColor("#000000"))

                    binding.labelCin.error = null
                    binding.labelSpeciality.error = null
                    if (binding.cin.text?.isEmpty() == true) {
                        binding.labelCin.error = "CIN is required"

                    }
                    if (!selected) {
                        binding.labelCin.error = "Speciality is required"

                    }

                } else {
                    binding.checkBox.setTextColor(Color.parseColor("#000000"))
                    //  val loadingAlert = LoadingAlert(this)
                    //  loadingAlert.startLoadingAlert()
                    workerVM.signUpWorker(
                        username,
                        password,
                        ChosenGouv,
                        ChosenCity,
                        image,
                        phone.toInt(),
                        binding.cin.text.toString(),
                        speciality
                    ).observe(this){
                        if (it.isLogin==true){
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

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

                }

        }

    }
    private fun next(){
        val intent1 = Intent(this, HomeActivity::class.java)
        startActivity(intent1)
    }
    fun showAlertIfAddingFailed() {
        val builder = AlertDialog.Builder(this@WorkerInfoActivity)
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
    private fun dropDownList() {
        adapterItems = ArrayAdapter(this, R.layout.list_item, stausList)
        binding.speciality.setAdapter(adapterItems)

        binding.speciality.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                 speciality = parent.getItemAtPosition(position).toString()
                speciality.dropLast(1)
                selected = true
                Toast.makeText(applicationContext, "Item: $speciality", Toast.LENGTH_SHORT).show()
                val editor = getSharedPreferences("my_prefs", Context.MODE_PRIVATE).edit()
                editor.putString("speciality", speciality)
                editor.apply()



            }


    }


}