package com.example.equal.activity_login

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.equal.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class LoginActivity:AppCompatActivity(R.layout.activity_login) {
    private lateinit var logo:ImageView
    private lateinit var bg:ImageView
    private lateinit var inputEmail:TextInputEditText
    private lateinit var inputPass:TextInputEditText
    private lateinit var loginBtn:Button
    private lateinit var googleBtn:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setBackground()
        setLogo()
    }
    fun init(){
        logo = findViewById(R.id.login_activity_logo_iv)
        bg = findViewById(R.id.login_activity_bg)
        inputEmail = findViewById(R.id.login_activity_emilet)
        inputPass = findViewById(R.id.login_activity_passwordet)
        loginBtn = findViewById(R.id.login_activity_loginBtn)
        googleBtn = findViewById(R.id.login_activity_googlefab)
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

    }
}