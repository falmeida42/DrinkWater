package com.falmeida.drinkwater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private EditText etMinutes;
    private TimePicker timePicker;

    private boolean activated = false;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = findViewById(R.id.btnNotify);
        etMinutes = findViewById(R.id.etNumberIterval);
        timePicker = findViewById(R.id.timePicker);
        preferences = getSharedPreferences("db", Context.MODE_PRIVATE);
        timePicker.setIs24HourView(true);

        activated = preferences.getBoolean("activated", false);

        if (activated) {
            btnNotify.setText(R.string.pause);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this, android.R.color.black));

            int interval = preferences.getInt("interval", 0);
            int hour = preferences.getInt("hour", timePicker.getCurrentHour());
            int minute = preferences.getInt("minute", timePicker.getCurrentMinute());

            btnNotify.setText(R.string.pause);
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
            etMinutes.setText(String.valueOf(interval));
        }
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sInterval = etMinutes.getText().toString();

                if (sInterval.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.error_msg, Toast.LENGTH_SHORT).show();
                    return;
                }

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                int interval = Integer.parseInt(sInterval);
                SharedPreferences.Editor editor = preferences.edit();

                if (!activated) {
                    btnNotify.setText(R.string.pause);
                    btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this, android.R.color.black));
                    activated = true;

                    editor.putBoolean("activated", true);
                    editor.putInt("interval", interval);
                    editor.putInt("hour", hour);
                    editor.putInt("minute", minute);
                    editor.apply();
                }
                else {
                    btnNotify.setText(R.string.notify);
                    btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this, R.color.purple_500));

                    editor.putBoolean("activated", false);
                    editor.remove("interval");
                    editor.remove("hour");
                    editor.remove("minute");
                    editor.apply();
                    activated = false;

                }

                Log.d("Teste", "hora: " + hour + " minute: " + minute + " interval: " + interval);
            }
        });
    }
}