package com.gsoc2021.ngodonate

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        this.auth = FirebaseAuth.getInstance()
        SignupBtn.setOnClickListener{
            val txtEmail: String = editTextEmail.text.toString()
            val txtEmail2: String = editTextEmail2.text.toString()
            val txtPassword: String = editTextPassword.text.toString()
            if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtEmail2) || TextUtils.isEmpty(txtPassword)) {
                Toast.makeText(
                    this@SignUpActivity,
                    "All fields are required",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (txtPassword.length < 6) {
                Toast.makeText(
                    this@SignUpActivity,
                    "Password must be at least 6 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                register(txtEmail, txtPassword)
            }
        }
    }

    private fun register(
        email: String,
        password: String
    ) {
        auth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth!!.currentUser!!
                    val userid = firebaseUser.uid
                    val user = hashMapOf(
                        "id" to userid,
                        "email" to email
                    )
                    db.collection("users").document(userid)
                        .set(user)
                        .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
                        .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "You can't register with this email or password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}