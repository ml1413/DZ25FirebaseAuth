package com.example.dz25firebaseauth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    val click: (model: ModelTask) -> Unit = {}
) : RecyclerView.Adapter<ModelTaskViewHolder>() {
    private var listItem: MutableList<ModelTask>  = mutableListOf()
    fun getListItem() = listItem
    fun setList(list: List<ModelTask>) {
        listItem = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = listItem.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ModelTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelTaskViewHolder, position: Int) {
        holder.initView(listItem[position],click)
    }
}


