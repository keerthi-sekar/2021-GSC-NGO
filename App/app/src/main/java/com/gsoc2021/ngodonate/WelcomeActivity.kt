package com.gsoc2021.ngodonate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    private var firebaseUser: FirebaseUser? = null
    val RC_SIGN_IN: Int = 1
    lateinit var signInClient: GoogleSignInClient
    lateinit var signInOptions: GoogleSignInOptions
    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore
    private var signUp = true

    override fun onStart() {
        super.onStart()
        firebaseUser = FirebaseAuth.getInstance().currentUser

        //check if user is null
        if (firebaseUser != null) {
            val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupGoogleLogin() {
        signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(this, signInOptions)
    }

    private fun initializeUI() {
        SigninGoogle.setOnClickListener {
            signUp = false
            login()
        }
        SignupGoogle.setOnClickListener {
            login()
        }

    }
    private fun login() {
        val loginIntent: Intent = signInClient.signInIntent
        startActivityForResult(loginIntent, RC_SIGN_IN)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    googleFirebaseAuth(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }



    private fun googleFirebaseAuth(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val firebaseUser = auth.currentUser
                val name = firebaseUser?.displayName
                val email = firebaseUser?.email
                val userid = firebaseUser?.uid
                val photoUrl = firebaseUser?.photoUrl
                val user = hashMapOf(
                    "id" to userid,
                    "email" to email,
                    "name" to name,
                    "photourl" to photoUrl.toString(),
                    "points" to "0",
                    "monthlyTarget" to "200")

                if (userid != null) {
                    if (!signUp){
                        db.collection("users").document(firebaseUser!!.uid)
                            .get()
                            .addOnSuccessListener { document ->
                                val user2 = hashMapOf(
                                    "id" to document.get("id"),
                                    "email" to document.get("email"),
                                    "name" to document.get("name"),
                                    "photourl" to document.get("photourl"),
                                    "points" to document.get("points"),
                                    "monthlyTarget" to document.get("monthlyTarget"))
                                db.collection("users").document(userid)
                                    .set(user2)
                                    .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
                                    .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
                            }
                            .addOnFailureListener { exception ->
                                Log.w("TAG", "Error getting documents: ", exception)
                            }
                    }
                    else{
                        db.collection("users").document(userid)
                            .set(user)
                            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
                            .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
                    }

                }
                startActivity(MainActivity.getLaunchIntent(this))
            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        auth = FirebaseAuth.getInstance()
        initializeUI()
        setupGoogleLogin()
    }
    fun loginScreen(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    fun signupScreen(view: View){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }


}