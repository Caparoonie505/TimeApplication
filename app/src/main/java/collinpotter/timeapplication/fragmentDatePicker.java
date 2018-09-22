package collinpotter.timeapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class fragmentDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use current date as default value for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
//        int dayOfWeek = c.get(Calendar.DAY_OF_MONTH);
        //Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        //DO STUFF HERE
        String date = String.valueOf(month+1) + "/" + String.valueOf(day) + "/" + String.valueOf(year);
        TextView textView = getActivity().findViewById(R.id.textAlarmCreateDay);
        textView.setText(date);
    }
}
