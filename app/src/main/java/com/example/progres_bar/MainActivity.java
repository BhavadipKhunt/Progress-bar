package com.example.progres_bar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button start, stop;
    public static ProgressBar progressBar;
    public int progressStatus = 0;
    TextView textView;
    private Handler handler = new Handler();
    MyCountDownTimer myCountDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.btnstart);
        stop = findViewById(R.id.btnstop);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);

//        new Thread(new Runnable() {
//            public void run() {
//                while (progressStatus < 100) {
//                    progressStatus += 1;
//                    // Update the progress bar and display the
//                    //current value in the text view
//                    handler.post(new Runnable() {
//                        public void run() {
//                            progressBar.setProgress(progressStatus);
//                            textView.setText(progressStatus + "/" + progressBar.getMax());
//                        }
//                    });
//                    try {
//                        // Sleep for 200 milliseconds.
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCountDownTimer = new MyCountDownTimer(10000, 1000);
                myCountDownTimer.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCountDownTimer.cancel();
            }
        });
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int progress = (int) (millisUntilFinished/1000);
            System.out.println("progress="+progress+"\tmillis="+millisUntilFinished);
            progressBar.setProgress(progressBar.getMax() - progress);
        }
        @Override
        public void onFinish() {
            finish();
        }
    }
}