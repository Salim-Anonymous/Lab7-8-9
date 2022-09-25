package com.example.lab7_8_9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //implement system broadcasts receiver ACTION_BATTERY_LOW, ACTION_POWER_CONNECTED
        //and ACTION_AIRPLANE_MODE_CHANGED and custom broadcast receiver
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        intentFilter.addAction("com.example.lab7_8_9.CUSTOM_BROADCAST")
        registerReceiver(MyBroadcastReceiver(), intentFilter)

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
            IntentFilter("com.example.lab7_8_9.LOCAL_MESSAGE")
        )

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent("com.example.lab7_8_9.CUSTOM_BROADCAST")
            sendBroadcast(intent)
        }

        //on clicking button3 start the ReceiverActivity
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, ReceiverActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(MyBroadcastReceiver())
    }

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            // Get extra data included in the Intent
            val message = intent.getStringExtra("message")
            // update the TextView
            val mTextView: TextView = findViewById(R.id.message)
            mTextView.text = message
        }
    }

    class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            when (p1?.action) {
                Intent.ACTION_BATTERY_LOW -> {
                    //create a toast message
                    Toast.makeText(p0, "Battery Low", Toast.LENGTH_LONG).show()
                }
                Intent.ACTION_POWER_CONNECTED -> {
                    //create a toast message
                    Toast.makeText(p0, "Power Connected", Toast.LENGTH_LONG).show()
                }
                Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                    //create a toast message
                    Toast.makeText(p0, "Airplane Mode Changed", Toast.LENGTH_LONG).show()
                }
                "com.example.lab7_8_9.CUSTOM_BROADCAST" -> {
                    //create a toast message
                    Toast.makeText(p0, "Custom Broadcast", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}