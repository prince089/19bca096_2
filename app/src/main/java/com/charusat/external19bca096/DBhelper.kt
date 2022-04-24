package com.charusat.external19bca096

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(var context: Context) : SQLiteOpenHelper(context, DBNAME,null, DBVERSION){

    companion object{
        private var DBNAME = "PRINCE"
        private var DBVERSION = 2
        private var TBNAME = "MYTABLE"
        private var MYID = "ID"
        private var MYNAME = "NAME"
        private var MYPASS = "PASSWORD"
        private var TBNAME2 = "dtable"
        private var DTID = "DTID"
        private var DNAME = "DTNAME"
        private var DNUMBER = "DTNUMBER"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        var query = "CREATE TABLE $TBNAME ( $MYID INTEGER PRIMARY KEY AUTOINCREMENT,$MYNAME TEXT ,$MYPASS TEXT)"
        p0?.execSQL(query)
        var query2 = "CREATE TABLE $TBNAME2 ($DTID INTEGER PRIMARY KEY AUTOINCREMENT,$DNAME TEXT , $DNUMBER INTEGER)"
        p0?.execSQL(query2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        var query = "DROP TABLE $TBNAME IF EXISTS"
//        p0?.execSQL(query)
        var query2 = "DROP TABLE $TBNAME2 IF EXISTS"
//        p0?.execSQL(query2)
        onCreate(p0)
    }

    fun insert(pogo : Pogo) : Boolean{
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(MYNAME,pogo.name)
        cv.put(MYPASS,pogo.password)

        var isdatainser = db.insert(TBNAME,null,cv)
        if (isdatainser > 0){
            return true
        }
        else{
            return false
        }
    }


    fun insert2(pogo : POGO2) : Boolean{
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(DNAME,pogo.name)
        cv.put(DNUMBER,pogo.nummber)

        var isdatainser = db.insert(TBNAME2,null,cv)
        if (isdatainser > 0){
            return true
        }
        else{
            return false
        }
    }
    fun retriveall() :ArrayList<Pogo>{
        var db = readableDatabase
        var cur = db.query(TBNAME,null,null,null,null,null,null)
        var arr:ArrayList<Pogo> = ArrayList()
        while (cur.moveToNext()){
            var id = cur.getInt(0)
            var name = cur.getString(1)
            var passsword = cur.getString(2)
            var kkpk = Pogo(id,name,passsword)

            arr.add(kkpk)
        }
        return arr
    }



    fun delete(id:Int){
        var db = writableDatabase
        db.delete(TBNAME,"$MYID = $id",null)
        db.close()
    }

    fun update(pogo:Pogo){
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(MYNAME,pogo.name)
        cv.put(MYPASS,pogo.password)
        db.update(TBNAME,cv,"$MYID = ${pogo.id}",null)
        db.close()
    }





    fun checkUser(username: String, password: String): Boolean {
        val columns = arrayOf(MYID)
        val db = this.readableDatabase
        val selection = "$MYNAME = ? AND $MYPASS = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(
            TBNAME, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0)
            return true
        return false
    }

}