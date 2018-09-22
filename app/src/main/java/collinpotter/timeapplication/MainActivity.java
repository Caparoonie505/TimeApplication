package collinpotter.timeapplication;

import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btnNavClock;
    private ImageButton btnNavAlarm;
    private ImageButton btnNavTimer;
    private Button btnNavStopwatch;
    private SectionStatePageAdapter mSectionStatePageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        mSectionStatePageAdapter = new SectionStatePageAdapter(getSupportFragmentManager());

        btnNavClock = (Button) findViewById(R.id.clockMenuButton);
        btnNavAlarm = (ImageButton) findViewById(R.id.alarmMenuButton);
        btnNavTimer = (ImageButton) findViewById(R.id.timerMenuButton);
        btnNavStopwatch = (Button) findViewById(R.id.stopwatchMenuButton);
        mViewPager = (ViewPager) findViewById(R.id.container);
        //Setup pager
        setupViewPager(mViewPager);

        btnNavClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to fragment
                setViewPager(0);
            }
        });

        btnNavAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to fragment
                setViewPager(1);
            }
        });

        btnNavTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to fragment
                setViewPager(2);
            }
        });

        btnNavStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to fragment
                setViewPager(3);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager){
        SectionStatePageAdapter adapter = new SectionStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentClock(),"fragmentClock");
        adapter.addFragment(new fragmentAlarm(),"fragmentAlarm");
        adapter.addFragment(new fragmentTimer(),"fragmentTimer");
        adapter.addFragment(new fragmentStopwatch(),"fragmentStopwatch");
        adapter.addFragment(new fragmentAlarmCreate(), "fragmentAlarmCreate");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

}
