package com.example.fancx.day09_sensor_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.fancx.day09_sensor_demo.demo01.SensorActivity01;
import com.example.fancx.day09_sensor_demo.demo02.SensorActivity02;
import com.example.fancx.day09_sensor_demo.demo03.SensorActivity03;
import com.example.fancx.day09_sensor_demo.demo04.SensorActivity04;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.but_01:
                startActivity(new Intent(this, SensorActivity01.class));
                break;
            case R.id.but_02:
                startActivity(new Intent(this, SensorActivity02.class));
                break;
            case R.id.but_03:
                startActivity(new Intent(this, SensorActivity03.class));
                break;
            case R.id.but_04:
                startActivity(new Intent(this, SensorActivity04.class));
                break;
        }
    }
}
