package com.himanshu.recyclerviewactivitty

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.himanshu.recyclerviewactivitty.databinding.ActivityMainBinding
import com.himanshu.recyclerviewactivitty.databinding.AddDialogLayoutBinding

class MainActivity : AppCompatActivity() , ListClickInterface{
    var arrayList = arrayListOf<StudentData>()
    lateinit var layoutManager: LinearLayoutManager
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerViewAdapter = RecyclerViewAdapter(arrayList,this)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        gridLayoutManager = GridLayoutManager(this,2)
        binding.recyclerView.layoutManager= layoutManager
        binding.recyclerView.adapter = recyclerViewAdapter
        binding.btnFab.setOnClickListener {

//            //for background heavy tasks
//            class insert : AsyncTask<Void, Void, Void>(){
//                override fun doInBackground(vararg p0: Void?): Void? {
//                    return null
//                }
//                override fun onPreExecute() {
//                    super.onPreExecute()
//                }
//                override fun onPostExecute(result: Void?) {
//                    super.onPostExecute(result)
//                }
//            }

            var dialog= Dialog(this)
            var dialogBinding = AddDialogLayoutBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.setCancelable(false)
            dialogBinding.btnAdd.setOnClickListener {
                if (dialogBinding.etName.text.toString().isNullOrEmpty()) {
                    dialogBinding.etName.error="Please Enter your name"
                }
                else if (dialogBinding.etRollNo.text.toString().isNullOrEmpty()) {
                    dialogBinding.etRollNo.error="Please Enter the roll no"
                }
                else {
                    arrayList.add(StudentData(dialogBinding.etName.text.toString(),dialogBinding.etRollNo.text.toString().toInt()))
                    recyclerViewAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
            //recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onClickUpdate(position: Int) {
        var dialog=Dialog(this)
        var dialogBinding = AddDialogLayoutBinding.inflate(layoutInflater)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.btnAdd.setText("Update")
        dialogBinding.tvName.setText("Update name")
        dialogBinding.etName.setText("${arrayList[position].name}")
        dialogBinding.tvRollNo.setText("Update roll no")
        dialogBinding.etRollNo.setText("${arrayList[position].rollNo}")
        dialogBinding.btnAdd.setOnClickListener {
            if (dialogBinding.etName.text.toString().isNullOrEmpty()) {
                dialogBinding.etName.error="Enter your name"
            } else if (dialogBinding.etRollNo.text.toString().isNullOrEmpty()) {
                dialogBinding.etRollNo.error="Enter your roll no"
            } else {
                arrayList.set(position,StudentData(dialogBinding.etName.text.toString(),dialogBinding.etRollNo.text.toString().toInt()))
                recyclerViewAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
        dialog.show()
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onClickDelete(position: Int) {
        arrayList.removeAt(position)
        recyclerViewAdapter.notifyDataSetChanged()
    }
}