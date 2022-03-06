package com.example.equal.onboarding

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bccintern3.invisiblefunction.LoadActivity
import com.example.equal.R
import com.example.equal.login.LoginActivity

class OnboardingActivity:AppCompatActivity(R.layout.onboarding_activity) {
    private lateinit var banner:ImageView
    private lateinit var registerBtn:Button
    private lateinit var loginBtn:Button
    private lateinit var loadAct:LoadActivity

    fun init(){
        banner=findViewById(R.id.onboardactivity_banneriv)
        registerBtn=findViewById(R.id.onboardactivity_registerbtn)
        loginBtn=findViewById(R.id.onboardactivity_loginbtn)
        loadAct= LoadActivity()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        runClickListener()
    }
    fun runClickListener(){
        registerBtn.setOnClickListener {

        }
        loginBtn.setOnClickListener {
            loadAct.loadActivityDisposable(this,LoginActivity::class.java,this,true)
        }
    }
}