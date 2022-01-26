package com.ezatpanah.listcheckboxdynamically

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import org.greenrobot.eventbus.EventBus

class SubtaskAdapter(private val data: List<SubtaskModel>) : RecyclerView.Adapter<SubtaskAdapter.MyViewHolder>() {

    private var listData: MutableList<SubtaskModel> = data as MutableList<SubtaskModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_checkbox, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData[position], position)
    }


    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val chkBox = view.findViewById<CheckBox>(R.id.chkBox)
        val imgDelete = view.findViewById<ImageView>(R.id.imgDelete)


        fun bind(subtaskModel: SubtaskModel, index: Int) {


            chkBox.text = subtaskModel.subTasktitle
            imgDelete.setOnClickListener {
                deleteItem(index)
                EventBus.getDefault().post(EvenDelete(true))

            }

        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(index: Int) {
        listData.removeAt(index)
        notifyDataSetChanged()
    }


}