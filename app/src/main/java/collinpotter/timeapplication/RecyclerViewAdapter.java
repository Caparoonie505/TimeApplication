package collinpotter.timeapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> alarmTimes;
    private ArrayList<String> alarmNames;
    private ArrayList<String> alarmPeriods;
    private ArrayList<String> alarmDays;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> times, ArrayList<String> names, ArrayList<String> periods, ArrayList<String> days, Context context) {
        this.alarmTimes = times;
        this.alarmNames = names;
        this.alarmPeriods = periods;
        this.alarmDays = days;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_alarmlistitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.alarmTime.setText(alarmTimes.get(position));
        holder.alarmName.setText(alarmNames.get(position));
        holder.alarmPeriod.setText(alarmPeriods.get(position));
        holder.alarmDay.setText(alarmDays.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + alarmNames.get(position));

                Toast.makeText(mContext,alarmNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmNames.size();
    }

    public void addItem(String time, String name, String period, String day){
        alarmTimes.add(time);
        alarmNames.add(name);
        alarmPeriods.add(period);
        alarmDays.add(day);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView alarmTime;
        TextView alarmName;
        TextView alarmPeriod;
        TextView alarmDay;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            alarmTime = itemView.findViewById(R.id.alarm_item_time);
            alarmName = itemView.findViewById(R.id.alarm_item_text);
            alarmPeriod = itemView.findViewById(R.id.timePeriod);
            alarmDay = itemView.findViewById(R.id.timeDays);
            parentLayout = itemView.findViewById(R.id.alarm_parent_layout);
        }
    }
}
