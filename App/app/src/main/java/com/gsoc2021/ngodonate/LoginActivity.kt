package com.gsoc2021.ngodonate

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        LoginBtn.setOnClickListener(View.OnClickListener {
            val txtEmail: String = editTextEmail.text.toString()
            val txtPassword: String = editTextPassword.text.toString()
            if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                Toast.makeText(
                    this@LoginActivity,
                    "All fields are required",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                auth!!.signInWithEmailAndPassword(txtEmail, txtPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent =
                                Intent(this@LoginActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Authentication failed!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        })
    }
    fun backToWelcome(view: View){
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}