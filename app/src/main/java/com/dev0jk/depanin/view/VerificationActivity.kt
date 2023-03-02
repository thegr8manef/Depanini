package com.dev0jk.depanin.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dev0jk.depanin.databinding.ActivityVerificationBinding
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding

    private var textEmail = ""
    private var textPassword = ""
lateinit var workerVM: WorkerVM
    private lateinit var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        workerVM = WorkerVM()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        val intentDataUser = intent

        textEmail = intentDataUser.getStringExtra("email").toString()
        textPassword = intentDataUser.getStringExtra("password").toString()

/*        mCallBack = object  : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){


            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                signWithPhoneAuthCredential(phoneAuthCredential)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                progressDialog.dismiss()
                Toast.makeText(this@VerificationActivity,"${e.message}",Toast.LENGTH_LONG).show()

            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {

                Log.d(TAG,"onCodeSent:$verificationId")
                mVerificationId = verificationId
                forceResendingToken = token
                progressDialog.dismiss()


                Toast.makeText(this@VerificationActivity,"Verification code sent...",Toast.LENGTH_LONG).show()
                binding.relativeLayoutGetOTP.visibility = View.GONE
                binding.relativeLayoutVerifyOTP.visibility = View.VISIBLE

            }


        }*/
        binding.btnNext.setOnClickListener {

            val phone = binding.numberphone.text.toString().trim()
            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(
                    this@VerificationActivity,
                    "Please enter phone number",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                progressDialog.setMessage("Verifing Phone Number...")
                progressDialog.show()
                workerVM.getData(phone.toInt()).observe(this) {

                    var itm = it
                    if (it.statu) {
                        val intent1 = Intent(this, UserInfoActivity::class.java)
                        intent1.putExtra("phone",phone)
                        startActivity(intent1)

                    }
                    else{
                        progressDialog.dismiss()
                        binding.idEdtPhoneNumber.error = "This Phone Number Exists"
                    }
                }

                //progressDialog.dismiss()
            }
        }

/*        binding.resendtx.setOnClickListener{

            val phone = binding.numberphone.text.toString().trim()
            if (TextUtils.isEmpty(phone)){
                Toast.makeText(this@VerificationActivity,"Please enter phone number",Toast.LENGTH_LONG).show()

            }else{
                resendVerificationCode(phone, forceResendingToken!!)
            }

        }*/

/* Assign the TextViews in the array in the order in which you want to shift focus */

/* Assign the TextViews in the array in the order in which you want to shift focus */
/*        val otp1 = binding.idEdtOtp1
        val otp2 = binding.idEdtOtp2
        val otp3 = binding.idEdtOtp3
        val otp4 = binding.idEdtOtp4
        val otp5 = binding.idEdtOtp5
        val otp6 = binding.idEdtOtp6

        val otpTextViews = arrayOf(
            otp1,
            otp2,
            otp3,
            otp4,
            otp5,
            otp6
        )

        for (currTextView in otpTextViews) {
            currTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    nextTextView().requestFocus()
                }

                override fun afterTextChanged(s: Editable) {}
                fun nextTextView(): TextView {
                    var i: Int = 0
                    while (i < otpTextViews.size - 1) {
                        if (otpTextViews[i] === currTextView) return otpTextViews[i + 1]
                        i++
                    }
                    return otpTextViews[i]
                }

            })
        }*/
/*        binding.idBtnVerify.setOnClickListener{
            val otp11 = binding.idEdtOtp1.text
            val otp22 = binding.idEdtOtp2.text
            val otp33 = binding.idEdtOtp3.text
            val otp44 = binding.idEdtOtp4.text
            val otp55 = binding.idEdtOtp5.text
            val otp66 = binding.idEdtOtp6.text
            val total = "$otp11$otp22$otp33$otp44$otp55$otp66"
            val code = total.trim()
            if (TextUtils.isEmpty(code)){
                Toast.makeText(this@VerificationActivity,"Please enter phone number",Toast.LENGTH_LONG).show()

            }else{
                verifyPhoneWithCode(mVerificationId,code)
                //Toast.makeText(this@VerificationActivity, "Welcome$code",Toast.LENGTH_LONG).show()
            }
        }*/





    }
/*    private fun startPhoneNumberVerification(phone :String){
        progressDialog.setMessage("Verifing Phone Number...")
        progressDialog.show()

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBack!!)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)

    }*/

/*    private fun resendVerificationCode(phone: String,token : PhoneAuthProvider.ForceResendingToken){
        progressDialog.setMessage("Resend Code...")
        progressDialog.show()

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBack!!)
            .setForceResendingToken(token)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)


    }*/

/*    private fun verifyPhoneWithCode(verificationId : String?,code:String){
        progressDialog.setMessage("Verifing code...")
        progressDialog.show()

        val credential = PhoneAuthProvider.getCredential(verificationId.toString(),code)
        signWithPhoneAuthCredential(credential)
    }*/

/*    private fun signWithPhoneAuthCredential(credential: PhoneAuthCredential){
        progressDialog.setMessage("Logging IN")

        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val phone = auth.currentUser?.phoneNumber
                //SignUp(textEmail,textPassword)
                Toast.makeText(this,"Logged In as $phone",Toast.LENGTH_LONG).show()
                val intent1 = Intent(this, UserInfoActivity::class.java)
                intent1.putExtra("phone",phone)
                startActivity(intent1)
                this.finish()

            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this,"${e.message}",Toast.LENGTH_LONG).show()

            }
    }*/

    }


