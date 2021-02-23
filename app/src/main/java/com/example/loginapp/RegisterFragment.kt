package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)

        auth = FirebaseAuth.getInstance()

        emailEt = findViewById(R.id.et_email)
        passwordEt = findViewById(R.id.et_password)

        loginBtn = findViewById(R.id.btnlogin)
        signUpBtn = findViewById(R.id.btn_register)

        signUpBtn.setOnClickListener{
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, LoginFragment::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        loginBtn.setOnClickListener{
            val intent = Intent(this, LoginFragment::class.java)
            startActivity(intent)
            finish()
        }
    }

}