package collinpotter.timeapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class fragmentAlarm extends Fragment {

    private static final String TAG = "fragmentAlarm";

    private ArrayList<String> mTime = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mPeriod = new ArrayList<>();
    private ArrayList<String> mDays = new ArrayList<>();

    private int numAlarms;

    private Button createAlarm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm,container,false);
        Log.d(TAG,"onCreateView: started.");

        createAlarm = (Button) view.findViewById(R.id.addAlarm);

        if(numAlarms != 3) {
            updateAlarmList();
        }

        initRecyclerView(view);

        createAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "SETVIEWPAGER(4) CLICKED");
                ((MainActivity)getActivity()).setViewPager(4);
            }
        });

        return view;
    }

    private void initRecyclerView(View view){
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mTime,mName,mPeriod,mDays,this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void updateAlarmList(){
        numAlarms = 3;
        //First item
        mTime.add("3:00");
        mName.add("School");
        mPeriod.add("PM");
        mDays.add("S   M   T   W   T   F   S");
        //Second item
        mTime.add("4:00");
        mName.add("Wake Up");
        mPeriod.add("AM");
        mDays.add("S   M   T   W   T   F   S");
        //Third item
        mTime.add("12:00");
        mName.add("Lunch Time!");
        mPeriod.add("PM");
        mDays.add("S   M   T   W   T   F   S");
    }

}
