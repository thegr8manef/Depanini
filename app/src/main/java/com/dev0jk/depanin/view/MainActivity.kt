package com.dev0jk.depanin.view

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityHomeBinding
import com.dev0jk.depanin.databinding.ActivityMainBinding
import com.dev0jk.depanin.utils.getUser
import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var imageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        imageUri = Uri.EMPTY
        binding.btnLogin.setOnClickListener {
           // uploadImage()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            Log.v("test_login","login")
        }

        binding.btnSignup.setOnClickListener {
//            selectImage()
            val intent = Intent(this, VerificationActivity::class.java)
            startActivity(intent)
            Log.v("test_signup", "signup")
        }

    }
}
/*
private fun uploadImage(){
    val progressDialog = ProgressDialog(this)
    progressDialog.setMessage("Uploding file ...")
    progressDialog.setCancelable(false)
    progressDialog.show()


    //val formatter = getUser(this).id
    val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
    val now = Date()
    val fileName= formatter.format(now)
    val storageReference = FirebaseStorage.getInstance().getReference().child("profiles").child("image/+$fileName")
    storageReference.putFile(imageUri).
            addOnSuccessListener {
                Toast.makeText(this,"image uploaded successfully",Toast.LENGTH_LONG).show()
                if (progressDialog.isShowing)progressDialog.dismiss()


            }.addOnFailureListener{
        Toast.makeText(this,"image failed",Toast.LENGTH_LONG).show()
        if (progressDialog.isShowing)progressDialog.dismiss()
    }
}
    private fun selectImage(){
        val intent = Intent()
        intent.type ="image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==-1){

            imageUri=data?.data!!
            binding.OtpImg.setImageURI(imageUri)
        }
    }*/