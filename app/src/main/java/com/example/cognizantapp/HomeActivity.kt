package com.example.cognizantapp

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class HomeActivity : AppCompatActivity() {
    lateinit var contactEditText: EditText
    lateinit var contactTextView: TextView
    lateinit var websiteTextView: TextView
    val LOG_TAG = HomeActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) //inflating of activity_home.xml file
        // inflation is creating the xml into the ram for use -- layout inflater
        contactEditText = findViewById(R.id.etContact)
        contactTextView = findViewById(R.id.contact_tv)
        websiteTextView = findViewById(R.id.website_tv)
        val intent = intent
        val message: String = intent.extras?.getString("EXTRA_MESSAGE").toString()
        contactTextView.text = message
        contactEditText.setOnFocusChangeListener { _, hasFocus ->
            super.onWindowFocusChanged(hasFocus)
            if (hasFocus) {
                Toast.makeText(this, "Focused", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Not focused", Toast.LENGTH_SHORT).show()
            }
        }
//        val user = intent.getParcelableExtra<User>("user")
//        Log.d("HomeActivity", user.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var mi = menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId){
            R.id.settings_mi ->{
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            }
            R.id.logout_mi ->{
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }


    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

    private fun clickHandler() {
        var contact = contactEditText.text.toString()
        contactTextView.text = contact
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$contact")
        startActivity(intent)
    }

    private fun websiteHandler() {
        val websiteUrl = websiteTextView.text.toString()
        val uri = Uri.parse(websiteUrl)
        var intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun handleBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("REPLY", "I got your contact")
        setResult(RESULT_OK, intent)
        finish()
    }

    fun handleButton(view: View) {
        when (view.id) {
            R.id.btn_contact -> {
                clickHandler()
            }
            R.id.web_btn -> {
                websiteHandler()
            }
        }
    }

    fun handleAlert(view: View) {
        var myAlertDialog = AlertDialog.Builder(this)
        myAlertDialog.setTitle("Alert")
        myAlertDialog.setMessage("Click OK to continue, or Cancel to stop")
        myAlertDialog.setPositiveButton("OK", DialogInterface.OnClickListener {
                dialog, which ->
                Toast.makeText(this, "Pressed OK", Toast.LENGTH_SHORT).show()
        })
        myAlertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener {
                dialog, which ->
            Toast.makeText(this, "Pressed Cancel", Toast.LENGTH_SHORT).show()
        })
        myAlertDialog.show()
    }
}