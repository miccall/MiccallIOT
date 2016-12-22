package asynnet.com.asynnet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * Created by miccall on 2016/10/10.
 */
public class SocketClient {

    private static Socket socket;
    private String buffer = "";
    private String sververIP = "192.168.1.136";
    private int  port = 3030;
    private String m_j;


    private TextView s_tv_wendu ;
    private TextView s_tv_shidu ;
    private TextView s_tv_yanwu ;
    private TextView s_tv_wendu_time;
    private TextView s_tv_shidu_time;
    private TextView s_tv_yanwu_time;
    private Baojing bj;
    private SharedPreferences sp;


    public  void conncet(Context context){
        System.out.println("点击");
        sp = context.getSharedPreferences("config",0);
        sververIP = sp.getString("ip","192.168.1.136");
        port = sp.getInt("port",3030);
        Toast.makeText(context.getApplicationContext(),sververIP,Toast.LENGTH_SHORT).show();
        new MyThread().start();
    }
    class MyThread extends Thread {

        public String txt1;
        public MyThread() {

        }
        @Override
        public void run() {
            //定义消息
            Message msg = new Message();

            msg.what = 0x11;
            Bundle bundle = new Bundle();
            bundle.clear();
            try {
                System.out.println("连接服务器");
                //连接服务器 并设置连接超时为5秒
                socket = new Socket();
                socket.connect(new InetSocketAddress(sververIP,port),5000);
                System.out.println("连上服务器");
                //向服务器发送信息
                System.out.println("向服务器发送信息");
                OutputStream os= socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("user : admin  password : admin ");
                pw.flush();
                socket.shutdownOutput();
                System.out.println("发送信息完成");
                //接收服务器发送信息
                System.out.println("接收服务器消息");
                InputStream is = socket.getInputStream();
                System.out.println("收到服务器消息");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String info = null;
                while((info = br.readLine())!=null){
                    //    System.out.println("cline ....   cline : "+info);
                    Message g_data = new Message();
                    g_data.obj = info ;
                    myHandler.sendMessage(g_data);
                }
                br.close();
                is.close();
                pw.close();
                os.close();
                socket.close();
            } catch (SocketTimeoutException aa) {
                //连接超时 在UI界面显示消息
                bundle.putString("msg", "服务器连接失败！请检查网络是否打开");
                msg.setData(bundle);
                //发送消息 修改UI线程中的组件
                myHandler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x11) {
                Bundle bundle = msg.getData();
                //txt1.append("server:"+bundle.getString("msg")+"\n");
            }
            m_j = (String) msg.obj;
            processData(m_j);

        }
    };

    private void processData(String mj) {
        Gson gson = new Gson();
        Bean data = gson.fromJson(mj, Bean.class);
        System.out.println(" json  : "+data.toString());
        setUI(data);
        bj = new Baojing();
        bj.setBaojingdata(data,sp);
    }

    private void setUI(Bean d) {
        s_tv_wendu.setText("温度"+d.getDate().getWendu()+"°");
        s_tv_shidu.setText("湿度"+d.getDate().getShidu()+"%");
        s_tv_yanwu.setText("烟雾"+d.getDate().getYanwu()+".");
        s_tv_wendu_time.setText("时间:"+d.getDate().getTime());
        s_tv_shidu_time.setText("时间:"+d.getDate().getTime());
        s_tv_yanwu_time.setText("时间:"+d.getDate().getTime());
    }
    public void setData(TextView a_tv_wendu,TextView a_tv_shidu,TextView a_tv_yanwu){
        this.s_tv_wendu = a_tv_wendu;
        this.s_tv_shidu = a_tv_shidu;
        this.s_tv_yanwu = a_tv_yanwu;
    }
    public void setTime(TextView b_tv_wendu,TextView b_tv_shidu,TextView b_tv_yanwu){
        this.s_tv_wendu_time = b_tv_wendu;
        this.s_tv_shidu_time = b_tv_shidu;
        this.s_tv_yanwu_time = b_tv_yanwu;
    }

    public Object getbean(){
        return m_j;
    }
}
