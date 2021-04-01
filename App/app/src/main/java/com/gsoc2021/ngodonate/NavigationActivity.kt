package com.gsoc2021.ngodonate

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gsoc2021.ngodonate.ui.browse.NGO
import kotlinx.android.synthetic.main.activity_navigation.*


class NavigationActivity : AppCompatActivity() {
    private var firebaseUser: FirebaseUser? = null
    private var db = Firebase.firestore
    private var objectType = "Books"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val intent = intent
        val ngoName = intent.getStringExtra("NGOname")
        objectType = intent.getStringExtra("objectType")

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = object : WebChromeClient() {
            override fun onGeolocationPermissionsShowPrompt(
                origin: String,
                callback: GeolocationPermissions.Callback
            ) {
                callback.invoke(origin, true, false)
            }
        }
        // this will load the url of the website
        webView.loadUrl("https://www.google.com/maps/search/$ngoName/")
        // this will enable the javascript settings
        webView.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        webView.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webView.canGoBack())
            webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }

    fun doneDonation(view: View){

        var currentPoints: Int = 0
        firebaseUser = FirebaseAuth.getInstance().currentUser
           readData {
               when(objectType){
                   "Books" -> currentPoints = it.toInt() + 10
                   "Clothes" -> currentPoints = it.toInt() + 20
                   "Food" -> currentPoints = it.toInt() + 30
                   "Hygiene Products" -> currentPoints = it.toInt() + 40
                   "Electronics" -> currentPoints = it.toInt() + 50
               }
               db.collection("users").document(firebaseUser!!.uid)
                   .update("points", currentPoints.toString())
                   .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
                   .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
           }
        Toast.makeText(applicationContext, "Thank you for your donation!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun readData(myCallback: (String) -> Unit) {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        db.collection("users").document(firebaseUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                var points = ""
                if (document != null) {
                    points = document.get("points") as String
                }
                myCallback(points)
            }
    }

    fun backBtn(view: View){
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}