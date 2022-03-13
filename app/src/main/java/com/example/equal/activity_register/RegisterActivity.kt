package com.example.equal.activity_register

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bccintern3.invisiblefunction.LoadActivity
import com.example.equal.R
import com.example.equal.activity_login.LoginActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class RegisterActivity:AppCompatActivity(R.layout.activity_register) {
    private lateinit var logo:ImageView
    private lateinit var bg:ImageView
    private lateinit var inputEmail:TextInputEditText
    private lateinit var inputPass:TextInputEditText
    private lateinit var inputConfirmPass:TextInputEditText
    private lateinit var registerBtn:Button
    private lateinit var loginTv:TextView
    private lateinit var fbAuth:FirebaseAuth
    private lateinit var loadAct:LoadActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setBackground()
        setLogo()
        runCLickListener()
    }
    fun init(){
        logo = findViewById(R.id.register_activity_logo_iv)
        bg = findViewById(R.id.register_activity_bg)
        inputEmail = findViewById(R.id.register_activity_emilet)
        inputPass = findViewById(R.id.register_activity_passwordet)
        inputConfirmPass = findViewById(R.id.register_activity_confirmpasswordet)
        registerBtn = findViewById(R.id.register_activity_registerBtn)
        loginTv = findViewById(R.id.register_activity_loginBtn)
        fbAuth = FirebaseAuth.getInstance()
        loadAct = LoadActivity()
    }
    fun setBackground(){
        bg.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                bg.viewTreeObserver.removeOnGlobalLayoutListener(this)

                Picasso
                    .get()
                    .load(R.drawable.universal_screen_background_blurred)
                    .resize(bg.width,bg.height)
                    .centerCrop()
                    .into(bg)
            }
        })
    }
    fun setLogo(){
        logo.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                logo.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val size = logo.width/3
                Picasso
                    .get()
                    .load(R.drawable.universal_logo)
                    .resize(size,size)
                    .into(logo)
            }

        })
    }
    fun runCLickListener(){
        loginTv.setOnClickListener {
            loadLoginActivity()
        }
        registerBtn.setOnClickListener {
            signUp()
        }
    }
    private fun loadLoginActivity(){
        loadAct.loadActivityComplete(this,LoginActivity::class.java,this,true,1000)
    }
    private fun signUp(){
        if(inputEmail.text.toString()!="" && inputPass.text.toString()!="" && inputConfirmPass.text.toString()!=""){
            if(inputPass.text.toString()!=inputConfirmPass.text.toString()){
                Toast.makeText(this,"Pastikan input password dengan benar",Toast.LENGTH_SHORT).show()
            }
            else{
                fbAuth.createUserWithEmailAndPassword(
                    inputEmail.text.toString(),
                    inputPass.text.toString()
                ).addOnSuccessListener {
                    Toast.makeText(this,"Registrasi berhasil\nakan menuju ke halaman login",Toast.LENGTH_SHORT).show()
                    loadAct.loadActivityComplete(this,LoginActivity::class.java,this,true,1000)
                }.addOnFailureListener {
                    Toast.makeText(this,"Registrasi gagal\ncoba lagi nanti",Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            Toast.makeText(this,"Harap isi semua data dengan benar",Toast.LENGTH_SHORT).show()
        }
    }
}