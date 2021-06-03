package com.example.broadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangedReciever : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state",false) ?: return
        if (isAirplaneModeEnabled){
            Toast.makeText(context,"Airplane Mode enabled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Airplane Mode unenabled", Toast.LENGTH_SHORT).show()
        }
    }
}