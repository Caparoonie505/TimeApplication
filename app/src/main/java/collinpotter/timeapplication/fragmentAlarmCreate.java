package collinpotter.timeapplication;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class fragmentAlarmCreate extends Fragment{

    private static final String TAG = "fragmentAlarmCreate";

    private ArrayList<String> advancedSettingNames = new ArrayList<>();

    private Button btnSave;
    private Button btnCancel;
    private TextView textViewDay;
    private TimePicker recyclerViewTime;
    private Button btnDate;

    private int numSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_create,container,false);


        btnSave = (Button) view.findViewById(R.id.btnAlarmSave);
        btnCancel = (Button) view.findViewById(R.id.btnAlarmCancel);
        textViewDay = (TextView) view.findViewById(R.id.textAlarmCreateDay);
        recyclerViewTime = (TimePicker) view.findViewById(R.id.recyclerViewTimeList);
        btnDate = (Button) view.findViewById(R.id.btnAlarmCreateDate);

        if(numSettings != 6) {
            updateSettingList();
        }

        initRecyclerView(view);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(1);
                Toast.makeText(getActivity(),"Alarm Saved", Toast.LENGTH_SHORT).show();
                
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new fragmentDatePicker();
                newFragment.show(getChildFragmentManager(),"DatePicker");
            }
        });

        return view;
    }

    private void initRecyclerView(View view){
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        RecyclerView recyclerView = view.findViewById(R.id.advancedSettingsRecyclerView);
        AlarmAdvancedRecyclerAdapter adapter = new AlarmAdvancedRecyclerAdapter(advancedSettingNames,this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void updateSettingList(){
        numSettings = 6;
        advancedSettingNames.add("Repeat");
        advancedSettingNames.add("Alarm name");
        advancedSettingNames.add("Snooze");
        advancedSettingNames.add("Alarm tone and volume");
        advancedSettingNames.add("Vibration");
        advancedSettingNames.add("Read time aloud");
    }
}
