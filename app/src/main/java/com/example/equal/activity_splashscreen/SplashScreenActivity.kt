package com.example.equal.activity_splashscreen

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bccintern3.invisiblefunction.LoadActivity
import com.example.equal.R
import com.example.equal.activity_onboard.OnboardingActivity
import com.squareup.picasso.Picasso

class SplashScreenActivity:AppCompatActivity(R.layout.activity_splashscreen) {
    private lateinit var bg:ImageView
    private lateinit var logo:ImageView
    private lateinit var loadAct:LoadActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setBackground()
        setLogo()
    }
    fun init(){
        bg = findViewById(R.id.splashscreen_activity_backgroundiv)
        logo = findViewById(R.id.splashscreen_activity_logoiv)
        loadAct = LoadActivity()
        loadNextActivity()
    }
    fun setBackground(){
        bg.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                bg.viewTreeObserver.removeOnGlobalLayoutListener(this)

                Picasso
                    .get()
                    .load(R.drawable.activity_splashscreen_bg)
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

                val width = logo.width
                val size = width/2

                Picasso
                    .get()
                    .load(R.drawable.universal_logo)
                    .resize(size,size)
                    .centerCrop()
                    .into(logo)
            }
        })
    }
    fun loadNextActivity(){
        loadAct.loadActivityComplete(this,OnboardingActivity::class.java,this,true,2500)
    }
}