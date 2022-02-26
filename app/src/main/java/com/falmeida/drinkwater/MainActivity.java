package com.falmeida.drinkwater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private EditText etMinutes;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = findViewById(R.id.btnNotify);
        etMinutes = findViewById(R.id.etNumberIterval);
        timePicker = findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sInterval = etMinutes.getText().toString();

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                int interval = Integer.parseInt(sInterval);

                Log.d("Teste", "hora: " + hour + " minute: " + minute + " interval: " + interval);
            }
        });
    }
}