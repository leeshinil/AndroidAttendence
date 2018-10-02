package com.example.shinillee.attendance;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class check extends AppCompatActivity {

    TextView DateTime1;
    ProgressHandler handler;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        DateTime1 = (TextView) findViewById(R.id.checkTime);

        handler = new ProgressHandler();

        runTime();

    }

    public void runTime() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);

                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        thread.start();
    }


    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            DateTime1.setText(time);
        }
    }
}
