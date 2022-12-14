package io.king.authxml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import io.king.authxml.data.UserPreferences
import io.king.authxml.ui.auth.AuthActivity
import io.king.authxml.ui.home.HomeActivity
import io.king.authxml.ui.starttNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer{
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            starttNewActivity(activity)
        })

    }
}