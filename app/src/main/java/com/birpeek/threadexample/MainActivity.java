package com.birpeek.threadexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar bar;
    private TextView    tvPercent;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progressBar1);
        tvPercent = (TextView) findViewById(R.id.tvPercent);

//        new Thread(new Task()).start();

    }

    public void startProgress(View view) {
        if(thread==null || !thread.isAlive()) {
            thread = new Thread(new Task());
            thread.start();
        }

    }

    private class Task implements Runnable {

        @Override
        public void run() {

            for (int i=0; i<=100; i+=10){

                 final int value = i;
                bar.setProgress(value);
                runOnUiThread(new showValue(value));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        private class showValue implements Runnable{
            private final int i;

            public showValue(int i) {
                this.i = i;
            }

            @Override
            public void run() {
                tvPercent.setText("" + i);
            }
        }
    }
}
