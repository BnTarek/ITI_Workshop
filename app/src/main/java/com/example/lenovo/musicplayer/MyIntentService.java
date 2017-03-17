package com.example.lenovo.musicplayer;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;


public class MyIntentService extends IntentService {
    private MediaPlayer mediaPlayer;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Intent service started!", Toast.LENGTH_LONG).show();

        mediaPlayer = MediaPlayer.create(this, R.raw.takhial);
        mediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Intent service destroyed!", Toast.LENGTH_LONG).show();
        mediaPlayer.stop();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
