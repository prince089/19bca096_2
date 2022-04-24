
package com.charusat.external19bca096

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dilog.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        refereshrecycle()


    }


    fun refereshrecycle(){
        var db = DBhelper(this)
        var arr:ArrayList<Pogo> = db.retriveall()
        var adapter = Adapter(this,arr)
        myrecyle.adapter = adapter

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if(id.equals(R.id.btnlogout))
        {
            var pref = getSharedPreferences("Loginpref", MODE_PRIVATE)
            var prefedit = pref.edit()
            prefedit.clear()
            prefedit.commit()
            var intent = Intent(this,login::class.java)
            startActivity(intent)
            finish()
        }
        if(id.equals(R.id.btnprofile))
        {
            Toast.makeText(applicationContext,"profile",Toast.LENGTH_LONG).show()
        }
        if(id.equals(R.id.btntemp))
        {
            var intent = Intent(this,Data::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }


        fun delete(position:Int){
        var db = DBhelper(this)
        var arr:ArrayList<Pogo> = db.retriveall()
        var pos = arr.get(position)
        db.delete(pos.id)
        refereshrecycle()
    }


    fun update(position: Int){
        var db = DBhelper(this)
        var arr : ArrayList<Pogo> = db.retriveall()
        var pos = arr.get(position)
        var dilog = Dialog(this)
        dilog.setContentView(R.layout.dilog)
        dilog.setCancelable(false)

        dilog.edtdilogusername.setText(pos.name)
        dilog.edtdilogpass.setText(pos.password)

        dilog.btndilogupdate.setOnClickListener {
            var id = pos.id
            var username = dilog.edtdilogusername.text.toString()
            var pasword = dilog.edtdilogpass.text.toString()
            var tempogo = Pogo(id,username,pasword)
            db.update(tempogo)
            dilog.dismiss()
            refereshrecycle()
        }
        dilog.show()
    }
}