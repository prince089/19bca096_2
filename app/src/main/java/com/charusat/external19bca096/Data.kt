package com.charusat.external19bca096

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_data.*

class Data : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        btndata1.setOnClickListener {
            var data1 =edtdata1.text.toString()
            var data2 = edtdata2.text.toString().toInt()

            var db = DBhelper(this)
            var pogo2 = POGO2(data1,data2)

            var pogo2insreted = db.insert2(pogo2)
            if (pogo2insreted){
                Toast.makeText(applicationContext,"data insertednot 2 ",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(applicationContext,"data inserted",Toast.LENGTH_LONG).show()
            }
        }
        btndata2.setOnClickListener {

        }
    }
}