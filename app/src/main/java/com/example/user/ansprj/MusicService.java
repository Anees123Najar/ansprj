package com.example.user.ansprj;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class MusicService extends Service {
    private MediaPlayer mp;
    private static boolean isRunning=true;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) { return null;}
    public void onCreate(){
        super.onCreate();
        mp=MediaPlayer.create(getApplicationContext(),R.raw.energy);
    }

    public int onStartCommand(Intent intent,int flags,int startId) {
        mp.setLooping(true);
        mp.start();
        isRunning=true;
        return START_NOT_STICKY;
    }
    @Override
    /**
     * Destroy and stop music
     */
    public void onDestroy() {
     mp.stop();
     isRunning=false;
     super.onDestroy();
    }
}
