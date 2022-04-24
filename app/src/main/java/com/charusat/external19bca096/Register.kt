package com.charusat.external19bca096

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btnregister.setOnClickListener {
            var username = edtregiusername.text.toString()
            var password = edtregipassword.text.toString()

            if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                Toast.makeText(applicationContext,"enter all filed",Toast.LENGTH_LONG).show()
            }
            else{
                var pogo = Pogo(username,password)
                var db = DBhelper(this)
                var isdatainseretd = db.insert(pogo)
                if (isdatainseretd){
                    Toast.makeText(applicationContext,"register sucessfull",Toast.LENGTH_LONG).show()
                    var intent = Intent(applicationContext,login::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext,"register Faiiled",Toast.LENGTH_LONG).show()
                    var intent = Intent(applicationContext,Register::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}