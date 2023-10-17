package com.example.dz25firebaseauth
import com.example.dz25firebaseauthentification.TASK
import com.example.dz25firebaseauthentification.UNKNOWN_ACCOUNT
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FireBaseDB {
    fun getFireBaseDB() = FirebaseDatabase.getInstance()
    fun getReferenceToSave(account: GoogleSignInAccount?): DatabaseReference {
        return getFireBaseDB().reference
            .child(account?.id ?: UNKNOWN_ACCOUNT)
            .child(TASK)
    }

    fun getTargetData(account: GoogleSignInAccount?): DatabaseReference {
        return getFireBaseDB().reference
            .child(account?.id ?: UNKNOWN_ACCOUNT)
            .child(TASK)
    }

}