package com.ezatpanah.listcheckboxdynamically

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubtaskAdapter (private val items: MutableList<SubtaskModel>) :
    RecyclerView.Adapter<SubtaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_checkbox, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position]) // Set the view to fade in

    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val chkBox = itemView.findViewById<CheckBox>(R.id.chkBox)


        fun bind(item: SubtaskModel) {

            chkBox.text=item.subTasktitle
        }

    }

}