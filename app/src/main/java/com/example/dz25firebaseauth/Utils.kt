package com.example.dz25firebaseauthentification

import androidx.fragment.app.Fragment
import com.example.dz25firebaseauth.R

fun Fragment.replaceFragment(fragment: Fragment) {
    this.parentFragmentManager.beginTransaction()
        .addToBackStack("")
        .setCustomAnimations(
            R.anim.in_fragment,
            R.anim.out_fragment,
            R.anim.in_fragment,
            R.anim.out_fragment,
        )
        .replace(R.id.main_container, fragment)
        .commit()
}