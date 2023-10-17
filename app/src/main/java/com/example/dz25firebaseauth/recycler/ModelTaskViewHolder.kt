package com.example.dz25firebaseauth

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dz25firebaseauth.databinding.ItemBinding


class ModelTaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemBinding.bind(view)
    fun initView(modelTask: ModelTask, click: (model: ModelTask) -> Unit) {
        binding.label.text = modelTask.nameTask
        binding.message.text = modelTask.task
        itemView.setOnClickListener { click(modelTask) }
    }
}