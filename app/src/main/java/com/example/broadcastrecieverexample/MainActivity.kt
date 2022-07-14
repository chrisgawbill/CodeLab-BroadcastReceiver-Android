package com.example.broadcastrecieverexample

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    var customReceiver: CustomReciever = CustomReciever()
    //Intent filters tell the receiver what intents to recieve
    var filter: IntentFilter = IntentFilter()
    private val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    lateinit var broadcastButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Tells filter what intents to listen for
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        //Registers the receiver with the Main Activity, it is now active
        this.registerReceiver(customReceiver, filter)

        broadcastButton = findViewById(R.id.sendBroadcast)
        broadcastButton.setOnClickListener(View.OnClickListener {
            sendCustomBroadcast()
        })

        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))

    }
    override fun onDestroy() {
        //Unregister the receiver
        unregisterReceiver(customReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver)
        super.onDestroy()
    }
    fun sendCustomBroadcast(){
        var customIntent:Intent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customIntent)
    }
}