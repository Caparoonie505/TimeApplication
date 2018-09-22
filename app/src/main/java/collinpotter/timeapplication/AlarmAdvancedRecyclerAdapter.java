package collinpotter.timeapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmAdvancedRecyclerAdapter extends RecyclerView.Adapter<AlarmAdvancedRecyclerAdapter.ViewHolder> {

    private static final String TAG = "AlarmAdvancedRecyclerAd";

    private ArrayList<String> advancedSettingNames;
    private Context mContext;

    public AlarmAdvancedRecyclerAdapter(ArrayList<String> advancedSettingNames, Context context) {
        this.advancedSettingNames = advancedSettingNames;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_alarm_advanced,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.advancedText.setText(advancedSettingNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on " + advancedSettingNames.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return advancedSettingNames.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView advancedText;
        Switch advancedSwitch;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            advancedText = itemView.findViewById(R.id.advancedTextView);
            advancedSwitch = itemView.findViewById(R.id.advancedSwitch);
            parentLayout = itemView.findViewById(R.id.parent_advanced);
        }
    }
}
