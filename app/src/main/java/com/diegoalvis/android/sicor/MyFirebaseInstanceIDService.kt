package com.diegoalvis.android.sicor

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService



/**
 * Created by diegoalvis on 13/11/2017.
 */


class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {


    private val TAG = "MyFirebaseIIDService"

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)

    }

}