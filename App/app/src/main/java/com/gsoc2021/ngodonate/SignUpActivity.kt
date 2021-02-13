package com.gsoc2021.ngodonate

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*
class SignUpActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        SignupBtn.setOnClickListener(View.OnClickListener {
            val txtUsername: String = editTextUsername.text.toString()
            val txtEmail: String = editTextEmail.text.toString()
            val txtPassword: String = editTextPassword.text.toString()
            if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(
                    txtPassword
                )
            ) {
                Toast.makeText(
                    this@SignUpActivity,
                    "All fields are required",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (txtPassword.length < 6) {
                Toast.makeText(
                    this@SignUpActivity,
                    "password must be at least 6 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                register(txtUsername, txtEmail, txtPassword)
            }
        })
    }

    private fun register(
        username: String,
        email: String,
        password: String
    ) {
        auth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth!!.currentUser!!
                    val userid = firebaseUser.uid
                    reference =
                        FirebaseDatabase.getInstance().getReference("Users").child(userid)
                    val hashMap =
                        HashMap<String, String>()
                    hashMap["id"] = userid
                    hashMap["username"] = username
                    hashMap["imageURL"] = "default"
                    hashMap["status"] = "offline"
                    hashMap["search"] = username.toLowerCase(Locale.ROOT)
                    reference!!.setValue(hashMap)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val intent =
                                    Intent(this@SignUpActivity, MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "You can't register with this email or password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun backToWelcome(view: View){
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}