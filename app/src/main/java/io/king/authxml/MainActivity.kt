package io.king.authxml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.king.authxml.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        finish()
        startActivity(Intent(this,  AuthActivity::class.java))
    }
}