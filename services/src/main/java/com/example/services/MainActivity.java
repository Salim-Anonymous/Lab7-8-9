package com.example.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public int NOTIFICATION_ID = 1;
    public NotificationManager notificationManager;
    public Button sendNotificationButton;
    public Button AlarmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = getSystemService(NotificationManager.class);
        AlarmButton = (Button) findViewById(R.id.button3);
        AlarmButton.setOnClickListener(v->{
            Intent intent = new Intent(this, Alarm.class);
            startActivity(intent);
        });

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Create the NotificationChannel main
        createNotificationChannel("main", "Main Channel", "This is the main channel");
        createNotificationChannel("alarm", "Alarm Channel", "This is the alarm channel");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "main")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        sendNotificationButton = (Button) findViewById(R.id.button2);
        sendNotificationButton.setOnClickListener(v -> {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        });

    }

    private void createNotificationChannel(String CHANNEL_ID, String CHANNEL_NAME, String CHANNEL_DESCRIPTION) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }
    }
}