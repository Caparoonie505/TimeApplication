package collinpotter.timeapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class fragmentTimer extends Fragment {

    private static final String TAG = "fragmentTimer";

    private Button btnNavClock;
    private Button btnNavAlarm;
    private Button btnNavTimer;
    private Button btnNavStopwatch;
    private Button decHour;
    private Button decMinute;
    private Button decSecond;

    private static long START_TIME_IN_MILLIS = 3600000;

    private TextView TextViewCountDown;
    private Button ButtonTimerStart;
    private Button ButtonTimerReset;

    private android.os.CountDownTimer CountDownTimer;
    private boolean isTimerRunning;
    private long TimeLeftInMillis = START_TIME_IN_MILLIS;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer,container,false);

        btnNavClock = (Button) view.findViewById(R.id.clockMenuButton);
        btnNavAlarm = (Button) view.findViewById(R.id.alarmMenuButton);
        btnNavTimer = (Button) view.findViewById(R.id.timerMenuButton);
        btnNavStopwatch = (Button) view.findViewById(R.id.stopwatchMenuButton);
        decHour = (Button) view.findViewById(R.id.decrementHour);
        decMinute = (Button) view.findViewById(R.id.decrementMinute);
        decSecond = (Button) view.findViewById(R.id.decrementSecond);
        Log.d(TAG,"onCreateView: started.");

        btnNavClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Navigating to Clock", Toast.LENGTH_SHORT).show();
                //Navigate to fragment
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        btnNavAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Navigating to Alarm", Toast.LENGTH_SHORT).show();
                //Navigate to fragment
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        btnNavTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Navigating to Timer", Toast.LENGTH_SHORT).show();
                //Navigate to fragment
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });

        btnNavStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Navigating to Stopwatch", Toast.LENGTH_SHORT).show();
                //Navigate to fragment
                ((MainActivity)getActivity()).setViewPager(3);
            }
        });

        TextViewCountDown = view.findViewById(R.id.textViewCountdown);
        ButtonTimerStart = view.findViewById(R.id.button_StartTimer);
        ButtonTimerReset = view.findViewById(R.id.button_ResetTimer);

        ButtonTimerStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });

        ButtonTimerReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        decHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning && (TimeLeftInMillis >= 3600000)) {
                    TimeLeftInMillis -= 3600000;
                    updateCountDownText();
                }
            }
        });

        decMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning && (TimeLeftInMillis >= 60000)) {
                    TimeLeftInMillis -= 60000;
                    updateCountDownText();
                }
            }
        });

        decSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning && (TimeLeftInMillis >= 1000)) {
                    TimeLeftInMillis -= 1000;
                    updateCountDownText();
                }
            }
        });

        updateCountDownText();

        return view;
    }
    private void startTimer(){
        CountDownTimer = new CountDownTimer(TimeLeftInMillis,1000){
            @Override
            public void onTick(long millisUntilFinished){
                TimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish(){
                isTimerRunning = false;
                ButtonTimerStart.setText("Start");
                ButtonTimerStart.setVisibility(View.INVISIBLE);
                ButtonTimerReset.setVisibility(View.VISIBLE);

            }
        }.start();

        isTimerRunning = true;
        ButtonTimerStart.setText("pause");
        ButtonTimerReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer(){
        CountDownTimer.cancel();
        isTimerRunning = false;
        ButtonTimerStart.setText("start");
        ButtonTimerReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer(){
        TimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        ButtonTimerReset.setVisibility(View.INVISIBLE);
        ButtonTimerStart.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText(){
        int hours = (int) TimeLeftInMillis / 1000 / 60 / 60;
        int minutes = (int) TimeLeftInMillis / 1000 / 60;
        int seconds = (int) TimeLeftInMillis / 1000 % 60;

        if(minutes == 60){
            minutes = 0;
        }
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);

        TextViewCountDown.setText(timeLeftFormatted);

    }
}
