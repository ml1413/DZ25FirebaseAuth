package com.example.dz25firebaseauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.dz25firebaseauth.databinding.FragmentListBinding
import com.example.dz25firebaseauthentification.replaceFragment

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: RecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerAdapter { model -> clickItem(model) }
        binding.recyclerView.adapter = adapter
        binding.button.setOnClickListener { this.replaceFragment(AddFragment()) }
        swipeToDelete()
    }

    private fun clickItem(model: ModelTask) {
        AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle(model.nameTask)
            .setMessage(model.task)
            .setNegativeButton("Close") { dialog, id ->
                dialog.cancel()
            }
            .show()
            .getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }

    private fun swipeToDelete() {
        val swipeToDeleteCollBack = object : SwipeToDeleteCollBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition


                val target = FireBaseDB.getTargetData(GAccount.getAccount(requireContext()))
                target.child(adapter.getListItem()[position].id).removeValue()
                adapter.getListItem().removeAt(position)
                adapter.notifyItemRemoved(position)
                Toast.makeText(requireContext(), "Удалено", Toast.LENGTH_SHORT).show()
            }

        }
        ItemTouchHelper(swipeToDeleteCollBack).attachToRecyclerView(binding.recyclerView)
    }


    override fun onResume() {
        super.onResume()
        putInfoOnRecycler()
    }

    private fun putInfoOnRecycler() {
        val target =
            FireBaseDB.getTargetData(account = GAccount.getAccount(context = requireContext()))
        target.get().addOnCompleteListener { task ->
            val list = mutableListOf<ModelTask>()
            if (task.isSuccessful) {
                task.result.children.forEach { dataSnapshot ->
                    val modelTask = dataSnapshot.getValue(ModelTask::class.java)
                    modelTask?.let { list.add(it) }
                    adapter.setList(list = list)
                }
            }
        }
    }

}