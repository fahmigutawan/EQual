package com.example.equal.activity_login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equal.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.squareup.picasso.Picasso

class LoginActivity:AppCompatActivity(R.layout.login_activity) {
    private lateinit var logo:ImageView
    private lateinit var bg:ImageView
    private lateinit var inputEmail:TextInputEditText
    private lateinit var inputPass:TextInputEditText
    private lateinit var loginBtn:Button
    private lateinit var googleBtn:FloatingActionButton
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var fbAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setBackground()
        setLogo()
        runCLickListener()
    }
    fun init(){
        logo = findViewById(R.id.login_activity_logo_iv)
        bg = findViewById(R.id.login_activity_bg)
        inputEmail = findViewById(R.id.login_activity_emilet)
        inputPass = findViewById(R.id.login_activity_passwordet)
        loginBtn = findViewById(R.id.login_activity_loginBtn)
        googleBtn = findViewById(R.id.login_activity_googlefab)
        fbAuth = FirebaseAuth.getInstance()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
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
        loginBtn.setOnClickListener {

        }
        googleBtn.setOnClickListener {
            signInGoogle()
        }
    }

    /**[START] sign in google method**/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(applicationContext,"Login dengan Google gagal, coba lagi nanti",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        fbAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"Login berhasil.\n\nOtomatis akan ke halaman beranda",
                        Toast.LENGTH_LONG).show()
                    Handler().postDelayed({
                    },1200)
                } else {
                    Toast.makeText(applicationContext,"Terjadi kesalahan tak terduga, coba lagi nanti", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    /**[END] sign in google method**/

    companion object {
        private const val RC_SIGN_IN = 120
    }
}