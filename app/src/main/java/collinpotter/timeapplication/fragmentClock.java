package collinpotter.timeapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class fragmentClock extends Fragment {

    private static final String TAG = "fragmentClock";

    private Button btnNavClock;
    private ImageButton btnNavAlarm;
    private ImageButton btnNavTimer;
    private Button btnNavStopwatch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock,container,false);

        btnNavClock = (Button) view.findViewById(R.id.clockMenuButton);
        btnNavAlarm = (ImageButton) view.findViewById(R.id.alarmMenuButton);
        btnNavTimer = (ImageButton) view.findViewById(R.id.timerMenuButton);
        btnNavStopwatch = (Button) view.findViewById(R.id.stopwatchMenuButton);
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

        return view;
    }
}
