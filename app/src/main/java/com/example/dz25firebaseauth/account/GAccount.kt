package com.example.dz25firebaseauth

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

private const val ID_TOKEN =
    "925487796500-c9u0b9rb0l20310jdqdhkcphq7coqohc.apps.googleusercontent.com"

object GAccount {
    fun getAccount(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    fun getSignInIntent(context: Context): Intent {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(ID_TOKEN)
            .requestEmail()
            .build()

        val googleSigningClient = GoogleSignIn.getClient(context, options)

        return googleSigningClient.signInIntent

    }
}