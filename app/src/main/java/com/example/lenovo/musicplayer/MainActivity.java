package com.example.lenovo.musicplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MyBoundService myBoundService;
    private boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startMusic(View view) {
        mediaPlayer = MediaPlayer.create(this, R.raw.takhial);
        mediaPlayer.start();

        try {
            Thread.sleep(15000);
            Toast.makeText(this, "End of thread dealy!", Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void startService(View view) {
        Intent serviceIntent = new Intent(this, MyIntentService.class);
        startService(serviceIntent);
    }

    public void stopService(View view) {
        Intent serviceIntent = new Intent(this, MyIntentService.class);
        stopService(serviceIntent);
    }

    public void bindService(View view) {
        Intent boundServiceIntent = new Intent(this, MyBoundService.class);
        bindService(boundServiceIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void getCurrentPosition(View view) {
        Toast.makeText(this, myBoundService.getCurrentPosition() + "", Toast.LENGTH_SHORT).show();
    }

    public void ubindService(View view) {
        unbindService(mConnection);
        isBind = false;
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.LocalService localService = (MyBoundService.LocalService) iBinder;
            myBoundService = localService.getService();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;
        }
    };
}
