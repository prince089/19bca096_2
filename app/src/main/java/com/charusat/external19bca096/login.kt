package com.charusat.external19bca096

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnlogin.setOnClickListener {
            var username  = edtusername.text.toString()
            var password = edtpass.text.toString()

            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                Toast.makeText(applicationContext,"please enter all filed",Toast.LENGTH_LONG).show()
            }
            else{

                var db = DBhelper(this)
                var validuser = db.checkUser(username,password)

                if (validuser){
                    var int = Intent(applicationContext,Home::class.java)
                    startActivity(int)
                    var pref = getSharedPreferences("Loginpref", MODE_PRIVATE)
                    var edtpref = pref.edit()
                    edtpref.putString("UserName",username)
                    edtpref.commit()
                    finish()
                }
                else{
                    Toast.makeText(applicationContext,"invalid username or password",Toast.LENGTH_LONG).show()

                }
//
            }
        }

        btnregister.setOnClickListener {
            var intent = Intent(applicationContext,Register::class.java)
            startActivity(intent)
        }
    }
}