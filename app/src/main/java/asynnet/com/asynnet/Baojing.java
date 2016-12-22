package asynnet.com.asynnet;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 *
 * Created by miccall on 2016/11/8.
 */
public class Baojing {
    private int wendu;
    private int shidu;
    private int yanwu;
    private int y_wendu;
    private int y_shidu;
    private int y_yanwu;
    private SharedPreferences sp;
    private MediaPlayer mp;
    private AudioManager am;

    public void setBaojingdata(Bean data,SharedPreferences sp){
        this.sp = sp;
        mp = MediaPlayer.create(MainActivity.mcontext,R.raw.jb);
        am = (AudioManager) MainActivity.mainActivity.getSystemService(Context.AUDIO_SERVICE);
        yanwu = (int) Float.parseFloat(data.getDate().getYanwu());
        wendu = (int) Float.parseFloat(data.getDate().getWendu());
        shidu = (int) Float.parseFloat(data.getDate().getShidu());
        getdata();
        linsten();

    }

    private void linsten() {
        if(wendu > y_wendu){
            VibratorUtil.Vibrate(MainActivity.mainActivity, 1000);
            if(!mp.isPlaying()){
                try {
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else {
                mp.stop();
        }

        if(shidu > y_shidu){
            VibratorUtil.Vibrate(MainActivity.mainActivity, 1000);
            if(!mp.isPlaying()){
                try {
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
                mp.stop();
        }

        if(yanwu > y_yanwu){
            VibratorUtil.Vibrate(MainActivity.mainActivity, 1000);
            if(!mp.isPlaying()){
                try {
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
                mp.stop();
        }
    }

    public void getdata() {
        y_wendu = sp.getInt("wendu_baojing",100);
        y_shidu = sp.getInt("shidu_baojing",100);
        y_yanwu = sp.getInt("yanwu_baojing",100);
    }
}
