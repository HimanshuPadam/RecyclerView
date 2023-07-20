package com.himanshu.recyclerviewactivitty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var arrayList: ArrayList<StudentData>, var listClickInterface: ListClickInterface) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(var view: View) :RecyclerView.ViewHolder(view){
        var tvName=view.findViewById<TextView>(R.id.tvName)
        var tvRollNo=view.findViewById<TextView>(R.id.tvRollNo)
        var btnUpdate=view.findViewById<Button>(R.id.btnUpdate)
        var btnDelete=view.findViewById<Button>(R.id.btnDelete)
        var btnFab= view.findViewById<Button>(R.id.btnFab)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text=arrayList[position].name
        holder.tvRollNo.text=arrayList[position].rollNo.toString()
        holder.btnUpdate.setOnClickListener {
            listClickInterface.onClickUpdate(position)
        }
        holder.btnDelete.setOnClickListener {
            listClickInterface.onClickDelete(position)
        }
//        holder.tvName.setText("Name Himanshu")
//        holder.tvRollNo.setText("Roll No")
    }
}