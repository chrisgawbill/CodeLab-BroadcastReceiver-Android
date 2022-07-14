package com.example.broadcastrecieverexample

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf

class CustomReciever : BroadcastReceiver() {
    private val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        var intentAction:String = intent.action.toString()
        Log.d(TAG,"ACTION    " + intentAction)
        if(intentAction != null){
            var toastMessage:String = "Unknown intent action"
            when(intentAction){
                Intent.ACTION_POWER_CONNECTED -> {
                    toastMessage = "Power connected"
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    toastMessage = "Power disconnected"
                }
                ACTION_CUSTOM_BROADCAST -> {
                    toastMessage = "Custom broadcast recieved"
                }
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}