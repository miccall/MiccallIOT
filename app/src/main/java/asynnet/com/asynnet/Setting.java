package asynnet.com.asynnet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

/**
 *
 * Created by miccall on 2016/11/2.
 */
public class Setting extends Activity {

    private EditText et_ip;
    private EditText et_port;
    private EditText et_wendu_yz;
    private EditText et_shidu_yz;
    private EditText et_yanwu_yz;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private Button bt_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activaty_setting);
        sp = this.getSharedPreferences("config",0);
        editor = sp.edit();
        init();
    }

    private void init() {
        et_ip = (EditText) findViewById(R.id.et_ip);
        et_port = (EditText) findViewById(R.id.et_port);
        et_wendu_yz = (EditText) findViewById(R.id.wendu_baojing);
        et_shidu_yz = (EditText) findViewById(R.id.shidu_baojing);
        et_yanwu_yz = (EditText) findViewById(R.id.yanwu_baojing);
        bt_save = (Button) findViewById(R.id.bt_save);
        if(!sp.getString("ip","192.168.1.33").isEmpty()){
            et_ip.setHint("ip: "+sp.getString("ip","192.168.1.133"));
        }

        if(sp.getInt("port",3030) != 0 ){
            System.out.println("get port : "+ sp.getInt("port",3030));
            et_port.setHint("port: "+sp.getInt("port",3030));
        }
        if(sp.getInt("wendu_baojing",100) != 0 ){
            System.out.println("get wendu_baojing : "+ sp.getInt("wendu_baojing",100));
            et_wendu_yz.setHint("温度阈值 "+sp.getInt("wendu_baojing",100));
        }
        if(sp.getInt("shidu_baojing",100) != 0 ){
            System.out.println("get shidu_baojing : "+ sp.getInt("shidu_baojing",100));
            et_shidu_yz.setHint("湿度阈值 "+sp.getInt("shidu_baojing",100));
        }
        if(sp.getInt("yanwu_baojing",100) != 0 ){
            System.out.println("get yanwu_baojing : "+ sp.getInt("yanwu_baojing",100));
            et_yanwu_yz.setHint("烟雾阈值 "+sp.getInt("yanwu_baojing",100));
        }
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip_user = et_ip.getText().toString();
                String port_user = et_port.getText().toString();
                String wendu_baojing = et_wendu_yz.getText().toString();
                String shidu_baojing = et_shidu_yz.getText().toString();
                String yanwu_baojing = et_yanwu_yz.getText().toString();
                if(ip_user.isEmpty() || port_user.isEmpty()){
                    Toast.makeText(getApplicationContext(),"不能为空",Toast.LENGTH_SHORT);
                }else{
                    try {
                        int port_user_int = Integer.parseInt(port_user);
                        int wd_user_int = Integer.parseInt(wendu_baojing);
                        int sd_user_int = Integer.parseInt(shidu_baojing);
                        int yw_user_int = Integer.parseInt(yanwu_baojing);
                        System.out.println("port_user_int " + port_user_int );
                        editor.putInt("port",port_user_int);
                        editor.putString("ip",ip_user);
                        editor.putInt("yanwu_baojing",yw_user_int);
                        editor.putInt("shidu_baojing",sd_user_int);
                        editor.putInt("wendu_baojing",wd_user_int);
                        editor.apply();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),"save success",Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    void save(View v){
        String ip_user = et_ip.getText().toString();
        String port_user = et_port.getText().toString();
        String wendu_baojing = et_wendu_yz.getText().toString();
        String shidu_baojing = et_shidu_yz.getText().toString();
        String yanwu_baojing = et_yanwu_yz.getText().toString();
        if(ip_user.isEmpty() || port_user.isEmpty()){
            Toast.makeText(getApplicationContext(),"不能为空",Toast.LENGTH_SHORT);
        }else{
            try {
                int port_user_int = Integer.parseInt(port_user);
                int wd_user_int = Integer.parseInt(wendu_baojing);
                int sd_user_int = Integer.parseInt(shidu_baojing);
                int yw_user_int = Integer.parseInt(yanwu_baojing);
                System.out.println("port_user_int " + port_user_int );
                editor.putInt("port",port_user_int);
                editor.putString("ip",ip_user);
                editor.putInt("yanwu_baojing",yw_user_int);
                editor.putInt("shidu_baojing",sd_user_int);
                editor.putInt("wendu_baojing",wd_user_int);
                editor.apply();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"save success",Toast.LENGTH_SHORT).show();
        }

    }
}
