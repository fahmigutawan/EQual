package com.example.equal.activity_onboard

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bccintern3.invisiblefunction.LoadActivity
import com.example.equal.R
import com.example.equal.activity_login.LoginActivity
import com.example.equal.activity_register.RegisterActivity
import com.squareup.picasso.Picasso

class OnboardingActivity:AppCompatActivity(R.layout.onboard_activity) {
    private lateinit var screenBg:ImageView
    private lateinit var screenFg:ImageView
    private lateinit var registerBtn:Button
    private lateinit var loginBtn:Button
    private lateinit var logoIv:ImageView
    private lateinit var loadAct:LoadActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setBackground()
        setForeground()
        setLogo()
        runClickListener()
    }
    fun init(){
        screenBg = findViewById(R.id.onboard_activity_bg)
        screenFg = findViewById(R.id.onboard_activity_fg)
        registerBtn = findViewById(R.id.onboard_activity_registerbtn)
        loginBtn = findViewById(R.id.onboard_activity_loginbtn)
        logoIv = findViewById(R.id.onboard_activity_logoiv)
        loadAct = LoadActivity()
    }
    fun setBackground(){
        screenBg.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                screenBg.viewTreeObserver.removeOnGlobalLayoutListener(this)

                Picasso
                    .get()
                    .load(R.drawable.universal_screen_background)
                    .resize(screenBg.width,screenBg.height)
                    .centerCrop()
                    .into(screenBg)
            }
        })
    }
    fun setForeground(){
        screenFg.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                screenFg.viewTreeObserver.removeOnGlobalLayoutListener(this)

                Picasso
                    .get()
                    .load(R.drawable.activity_onboard_foreground)
                    .resize(screenFg.width,screenFg.height)
                    .centerCrop()
                    .into(screenFg)
            }
        })
    }
    fun setLogo(){
        logoIv.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                logoIv.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val size = logoIv.width/2

                Picasso
                    .get()
                    .load(R.drawable.universal_logo)
                    .resize(size,size)
                    .onlyScaleDown()
                    .into(logoIv)
            }
        })
    }
    fun runClickListener(){
        loginBtn.setOnClickListener {
            loadAct.loadActivityDelayable(this, LoginActivity::class.java,1000)
        }
        registerBtn.setOnClickListener {
            loadAct.loadActivityDelayable(this, RegisterActivity::class.java,1000)
        }
    }
}