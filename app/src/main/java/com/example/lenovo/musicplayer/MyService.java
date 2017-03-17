package com.example.lenovo.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;

    public MyService() {}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Toast.makeText(this, "Service started!", Toast.LENGTH_LONG).show();

        mediaPlayer = MediaPlayer.create(this, R.raw.takhial);
        mediaPlayer.start();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stopSelf(startId);
            }
        });
        thread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service destroyed!", Toast.LENGTH_LONG).show();
        mediaPlayer.stop();
    }
}
