package com.example.equal.activity_home

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.bccintern3.invisiblefunction.LoadFragment
import com.example.equal.R
import com.example.equal.activity_home.callfragment.CallFragment
import com.example.equal.activity_home.homefragment.ArtikelFragment
import com.example.equal.activity_home.homefragment.PsikologFragment
import com.example.equal.activity_home.homefragment.ReportFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class HomeActivity:AppCompatActivity(R.layout.home_activity) {
    lateinit var navbar:BottomNavigationView
    //lateinit var flManager:FrameLayout
    lateinit var callFrag:CallFragment
    lateinit var artikelFrag:ArtikelFragment
    lateinit var psikologFrag:PsikologFragment
    lateinit var reportFrag:ReportFragment
    lateinit var loadFrag:LoadFragment

    fun init(){
        navbar = findViewById(R.id.homeactivity_navbar)
        //flManager = findViewById(R.id.homeactivity_framelayout)
        callFrag = CallFragment()
        artikelFrag = ArtikelFragment()
        psikologFrag = PsikologFragment()
        reportFrag = ReportFragment()
        loadFrag = LoadFragment()
        loadFrag.transfer(supportFragmentManager,R.id.homeactivity_framelayout,psikologFrag)
        navbar.menu.getItem(0).setChecked(false)
        navbar.menu.getItem(2).setChecked(true)
    }
    fun runNavbarListener(){
        navbar.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.navbar_call->{
                        loadFrag.transfer(supportFragmentManager,R.id.homeactivity_framelayout,callFrag)
                    }
                    R.id.navbar_search->{
                    }
                    R.id.navbar_home->{
                        loadFrag.transfer(supportFragmentManager,R.id.homeactivity_framelayout,psikologFrag)
                    }
                    R.id.navbar_schedule->{
                    }
                    R.id.navbar_profile->{
                    }
                }
                return true
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        runNavbarListener()
    }
}