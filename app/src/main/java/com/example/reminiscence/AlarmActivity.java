package com.example.reminiscence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlarmActivity extends AppCompatActivity {

    EditText mHourEditText;
    EditText mMinuteEditText;

    Button mSetAlarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);


        mHourEditText = (EditText) findViewById(R.id.set_alarm_hour);
        mMinuteEditText = (EditText) findViewById(R.id.set_alarm_minute);

        mSetAlarmButton = (Button) findViewById(R.id.set_alarm_button);
        mSetAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hour = Integer.parseInt(mHourEditText.getText().toString());
                int minute = Integer.parseInt((mMinuteEditText.getText().toString()));

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR,hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,minute);

                if(hour <= 24 && minute <=60){
                    startActivity(intent);
                }
            }
        });


    }
}