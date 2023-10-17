package com.example.dz25firebaseauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dz25firebaseauth.databinding.FragmentAddBinding
import kotlin.concurrent.thread


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btAdd.setOnClickListener {
            if (binding.btAdd.text.isNotBlank() && binding.label.text.isNotBlank()) {
                addDataToFirebase()
                thread {//время для проигрывания анимации и возврат на пред экран
                    Thread.sleep(200)
                    parentFragmentManager.popBackStack()
                }
            } else
                Toast.makeText(
                    requireContext(),
                    getString(R.string.messageIfFieldIsEmpty),
                    Toast.LENGTH_SHORT
                ).show()

        }
    }

    private fun addDataToFirebase() {
        //спрятать клавиатуру
        binding.root.clearFocus()
        val id = System.currentTimeMillis().toString()
        val target =
            FireBaseDB.getReferenceToSave(account = GAccount.getAccount(context = requireContext()))
        target.child(id).setValue(
            ModelTask(
                id = id,
                nameTask = binding.label.text.toString(),
                task = binding.message.text.toString()
            )
        )
        binding.cardView.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.button_animatiom)
        )

    }
}