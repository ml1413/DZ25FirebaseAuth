package com.example.dz25firebaseauth

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dz25firebaseauth.databinding.FragmentSplashBinding
import com.example.dz25firebaseauthentification.OnAuthenticationLaunch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private var animator: ObjectAnimator? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as OnAuthenticationLaunch

        binding.cardView.alphaAnimationPulls()
        binding.cardView.setOnClickListener {
            it.alphaAnimationPulls(off = true)
            activity.launchIntent(intent = GAccount.getSignInIntent(context = requireContext()))
        }
    }

    private fun View.alphaAnimationPulls(off: Boolean = false) {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(this, View.ALPHA, 0.7F, 1F)

        }
        if (off) {
            animator?.cancel()
        } else {
            animator?.repeatCount = ObjectAnimator.INFINITE
            animator?.duration = 500
            animator?.start()
        }
    }
}

