package com.example.dz25firebaseauthentification

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dz25firebaseauth.GAccount
import com.example.dz25firebaseauth.ListFragment
import com.example.dz25firebaseauth.R
import com.example.dz25firebaseauth.SplashFragment
import com.example.dz25firebaseauth.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

interface OnAuthenticationLaunch {
    fun launchIntent(intent: Intent)
}

class MainActivity : AppCompatActivity(), OnAuthenticationLaunch {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val account = GAccount.getAccount(context = this)
        if (account != null) showListFragment(ListFragment()) else showListFragment(SplashFragment())
    }

    override fun launchIntent(intent: Intent) {
        startActivityForResult(intent, 123)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            val token = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = token.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                val firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) showListFragment(ListFragment()) else {
                            Toast.makeText(this, "Error Firebase", Toast.LENGTH_SHORT).show()
                        }
                    }

            } catch (e: ApiException) {
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showListFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .setCustomAnimations(
                R.anim.in_fragment,
                R.anim.out_fragment,
                R.anim.in_fragment,
                R.anim.out_fragment,
            )
            .commit()
    }
}