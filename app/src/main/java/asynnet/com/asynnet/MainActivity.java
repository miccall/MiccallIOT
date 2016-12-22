package asynnet.com.asynnet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button bt_stop;
    private TextView tv_wendu ;
    private TextView tv_shidu ;
    private TextView tv_yanwu ;
    private TextView tv_wendu_time;
    private TextView tv_shidu_time;
    private TextView tv_yanwu_time;
    private SocketClient socketClient;
    private RelativeLayout bt_wendu;
    private RelativeLayout bt_shidu;
    private RelativeLayout bt_yanwu;
    private RelativeLayout bt_setting;
    public static Activity mainActivity ;
    public static Context mcontext ;
    private MediaPlayer m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m = MediaPlayer.create(getApplicationContext(), R.raw.jb);
        mainActivity = this;
        mcontext = getApplicationContext();
        init();
        //initData();
        click();
    }

    private void click() {
        bt_wendu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("bt_wendu");
                socketClient = new SocketClient();
                socketClient.conncet(getApplicationContext());
                socketClient.setData(tv_wendu,tv_shidu,tv_yanwu);
                socketClient.setTime(tv_wendu_time,tv_shidu_time,tv_yanwu_time);
            }
        });
        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(".............");
                Intent intent = new Intent(MainActivity.this,Setting.class);
                System.out.println(".............");
                startActivity(intent);
            }
        });
        bt_yanwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(".............");
            }
        });
        bt_shidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(".............");
            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m.isPlaying()){
                    System.out.println("stop............................");
                    m.stop();
                    System.out.println("release............................");
                    m.release();
                    System.out.println("ffffff............................");
                }
            }
        });
    }

    private void init() {
        bt_stop = (Button) findViewById(R.id.stop);
        tv_wendu = (TextView) findViewById(R.id.tv_wendu);
        tv_shidu = (TextView) findViewById(R.id.tv_shidu);
        tv_yanwu = (TextView) findViewById(R.id.tv_yanwu);
        tv_wendu_time = (TextView) findViewById(R.id.tv_wendu_time);
        tv_shidu_time = (TextView) findViewById(R.id.tv_shidu_time);
        tv_yanwu_time = (TextView) findViewById(R.id.tv_yanwu_time);
        bt_wendu = (RelativeLayout) findViewById(R.id.iv_1);
        bt_shidu = (RelativeLayout) findViewById(R.id.iv_4);
        bt_yanwu = (RelativeLayout) findViewById(R.id.iv_2);
        bt_setting = (RelativeLayout) findViewById(R.id.iv_3);
    }
}
