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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Locale;

public class fragmentTimer extends Fragment {

    private static final String TAG = "fragmentTimer";
    private Button decHour;
    private Button decMinute;
    private Button decSecond;
    private Button incHour;
    private Button incMinute;
    private Button incSecond;

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
        decHour = (Button) view.findViewById(R.id.decrementHour);
        decMinute = (Button) view.findViewById(R.id.decrementMinute);
        decSecond = (Button) view.findViewById(R.id.decrementSecond);
        incHour = (Button) view.findViewById(R.id.incrementHour);
        incMinute = (Button) view.findViewById(R.id.incrementMinute);
        incSecond = (Button) view.findViewById(R.id.incrementSecond);
        Log.d(TAG,"onCreateView: started.");


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
                    setTimeString(TimeLeftInMillis);
                }
            }
        });

        decMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning && (TimeLeftInMillis >= 60000)) {
                    TimeLeftInMillis -= 60000;
                    setTimeString(TimeLeftInMillis);
                }
            }
        });

        decSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning && (TimeLeftInMillis >= 1000)) {
                    TimeLeftInMillis -= 1000;
                    setTimeString(TimeLeftInMillis);
                }
            }
        });

        incHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning){
                    TimeLeftInMillis += 3600000;
                    setTimeString(TimeLeftInMillis);
                }
            }
        });

        incMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning){
                    TimeLeftInMillis += 60000;
                    setTimeString(TimeLeftInMillis);
                }
            }
        });

        incSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning){
                    TimeLeftInMillis += 1000;
                    setTimeString(TimeLeftInMillis);
                }
            }
        });

        return view;
    }
    private void startTimer(){
        CountDownTimer = new CountDownTimer(TimeLeftInMillis,1000){
            @Override
            public void onTick(long millisUntilFinished){
                TimeLeftInMillis = millisUntilFinished;
                setTimeString(TimeLeftInMillis);
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
        setTimeString(START_TIME_IN_MILLIS);
        ButtonTimerReset.setVisibility(View.INVISIBLE);
        ButtonTimerStart.setVisibility(View.VISIBLE);
    }

    public void setTimeString(long remainingTime) {
        boolean isNegative = false;
        if(remainingTime < 0){
            remainingTime = -remainingTime;
            isNegative = true;
        }
        int hours = (int) (remainingTime / 3600000);
        int remainder = (int) (remainingTime % 3600000);

        int minutes = (int) (remainder / 60000);
        remainder = (int) (remainder % 60000);

        int seconds = (int) (remainder / 1000);
        remainder = (int) (remainder % 1000);

        //Round up to the next second
        if(!isNegative && remainder != 0){
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
                if(minutes == 60){
                    hours++;
                }
            }
        }

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);

        TextViewCountDown.setText(timeLeftFormatted);
    }

}
