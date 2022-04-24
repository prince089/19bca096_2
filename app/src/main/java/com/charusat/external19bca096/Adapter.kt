package com.charusat.external19bca096

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*

class Adapter(val context: Context, var arr:ArrayList<Pogo>)
    : RecyclerView.Adapter<Adapter.PersonViewHolde>()

{


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolde {
//        var inflater=LayoutInflater.from(parent.context)
//        var view= inflater.inflate(R.layout.cardview,parent,false)
//        return PersonViewHolde(view)
//    }
//
//    override fun onBindViewHolder(holder: PersonViewHolde, position: Int) {
//        holder.bind(arr[position])
//
//    }
//
//    override fun getItemCount(): Int {
//        return  arr.size
//    }

    class PersonViewHolde(var view: View):RecyclerView.ViewHolder(view)
    {
        fun bind(p:Pogo)
        {
            view.tusername.setText(p.name.toString())
            view.tpassword.setText(p.password.toString())
//            view.tlname.setText(p.lname.toString())

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolde {

        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.card,parent,false)
        return PersonViewHolde(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolde, position: Int) {
        holder.bind(arr[position])
        holder.itemView.imgdelete.setOnClickListener {
            if (context is Home){
                Toast.makeText(context,"delete",Toast.LENGTH_LONG).show()
                context.delete(position)
            }

        }
        holder.itemView.imgupdate.setOnClickListener {
            if (context is Home){
                print(context)
                Toast.makeText(context,"Update",Toast.LENGTH_LONG).show()
                context.update(position)
            }

        }
    }

    override fun getItemCount(): Int {
        return  arr.size
    }
}