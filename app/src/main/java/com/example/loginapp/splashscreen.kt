package com.example.loginapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashscreen : AppCompatActivity() {


    private val SPLASH_TIME_OUT:Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler().postDelayed({
            startActivity(Intent(this,LoginFragment::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}