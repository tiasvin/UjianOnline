package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var loginbtn : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var signupBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)

        signupBtn = findViewById(R.id.btnregister)
        loginbtn = findViewById(R.id.btn_login)
        auth = FirebaseAuth.getInstance()
        loginbtn.setOnClickListener {
            var email: String = email.text.toString()
            var password: String = password.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@LoginFragment, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        signupBtn.setOnClickListener{
            val intent = Intent(this, RegisterFragment::class.java)
            startActivity(intent)
            finish()
        }
    }



}