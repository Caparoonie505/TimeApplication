package collinpotter.timeapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static long START_TIME_IN_MILLIS = 3600000;

    private TextView TextViewCountDown;
    private Button ButtonTimerStart;
    private Button ButtonTimerReset;

    private CountDownTimer CountDownTimer;
    private boolean isTimerRunning;
    private long TimeLeftInMillis = START_TIME_IN_MILLIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextViewCountDown = findViewById(R.id.textViewCountdown);
        ButtonTimerStart = findViewById(R.id.button_StartTimer);
        ButtonTimerReset = findViewById(R.id.button_ResetTimer);

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

        updateCountDownText();
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
