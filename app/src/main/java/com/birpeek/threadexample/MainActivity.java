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
        bar = findViewById(R.id.progressBar1);
        tvPercent = findViewById(R.id.tvPercent);

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

                bar.setProgress(i);
                runOnUiThread(new showValue(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        private class showValue implements Runnable{
            private final int i;

            private showValue(int i) {
                this.i = i;
            }

            @Override
            public void run() {
                tvPercent.setText("" + i);
            }
        }
    }
}
