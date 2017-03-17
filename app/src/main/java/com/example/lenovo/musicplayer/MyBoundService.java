package com.example.lenovo.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyBoundService extends Service {
    private MediaPlayer mediaPlayer;
    private final IBinder mBinder = new LocalService();

    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Service onBind!", Toast.LENGTH_LONG).show();

        mediaPlayer = MediaPlayer.create(this, R.raw.takhial);
        mediaPlayer.start();

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Service onUnbind!", Toast.LENGTH_LONG).show();
        mediaPlayer.stop();
        return super.onUnbind(intent);
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public class LocalService extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }
}
