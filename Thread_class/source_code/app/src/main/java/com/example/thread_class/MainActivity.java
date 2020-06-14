package com.example.thread_class;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private ProgressBar mProgressBar;
    public static final long PLUS_SECONDS = 5;
    private Handler mUIHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.btn_start);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                new Thread(new Task(v)).start();
            }
        });

    }




    class Task implements Runnable{
        private View view;
        private TextView mTextView;
        public Task(View view){
            this.view = view.getRootView();
            mTextView = this.view.findViewById(R.id.tv_result);
        }

        @Override
        public void run() {
            counterIterations();
        }
        private void counterIterations() {

            long startTimeStamp = System.currentTimeMillis();
            long endTimeStamp = startTimeStamp + PLUS_SECONDS * 1000;

            long iterationCount = 0;
            while(System.currentTimeMillis()<=endTimeStamp){
                iterationCount++;
            }
            final long finalIterationCount = iterationCount;
            mUIHandler.post(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mTextView.setText("Total : " + finalIterationCount);
                }
            });

        }

    }


}