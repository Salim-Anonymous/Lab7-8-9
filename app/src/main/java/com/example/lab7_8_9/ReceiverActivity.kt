package com.example.lab7_8_9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class ReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)
    }

    fun onButtonClicked(view: View) {
        val intent = Intent("com.example.lab7_8_9.LOCAL_MESSAGE")
        val value = "Roses are red, violets are blue, this is a message from the receiver activity for you!"
        intent.putExtra("message", value)
        Toast.makeText(this, "Sending Broadcast", Toast.LENGTH_SHORT).show()
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        Toast.makeText(this, "Broadcast sent", Toast.LENGTH_SHORT).show()
    }
}