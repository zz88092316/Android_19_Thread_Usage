package com.example.android_19_thread_usage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private static final int msgKey1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView)findViewById(R.id.txt);

        Thread t = new Thread(runnable);
        t.start();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            do{
                try {
                    Message msg = new Message();
                    mHandler.sendMessage(msg);
                    Thread.sleep(2000);
                    Message msg2 = new Message();
                    msg2.what = msgKey1;
                    mHandler.sendMessage(msg2);
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    };
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    txt.setText("Got it");
                    break;
                default:
                    txt.setText("Got message");
                    break;
            }
        }
    };
}