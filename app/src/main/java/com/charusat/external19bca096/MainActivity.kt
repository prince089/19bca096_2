package com.charusat.external19bca096

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed(Runnable {
            var pref = getSharedPreferences("Loginpref", MODE_PRIVATE)
            var prefstr = pref.getString("UserName","null")

            if (prefstr.equals("null")){
                var nextscreen = Intent(this,login::class.java)
                startActivity(nextscreen)
                finish()
            }
            else{
                var nextscreen = Intent(this,Home::class.java)
                startActivity(nextscreen)
                finish()
            }


        },3000)
    }
}


