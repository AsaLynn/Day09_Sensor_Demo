package com.example.fancx.day09_sensor_demo.demo02;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fancx.day09_sensor_demo.R;

public class SensorActivity02 extends AppCompatActivity implements SensorEventListener{

    private TextView mTv_01;
    private TextView mTv_02;
    private TextView mTv_03;
    private TextView mTv_04;
    private TextView mTv_05;
    private SensorManager sensorManager;//传感器的管理器对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor02);

        initView();//初始化控件
        //TODO 1, 得到传感器的管理器对象
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
    //TODO 2, 注册传感器  -- 让当前的类型实现接口SensorEventListener
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(1),SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(4),SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(7),SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(5),SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(6),SensorManager.SENSOR_DELAY_GAME);
    }

    //TODO 3, 取消注册传感器
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    //----监听器的方法----
    @Override
    public void onSensorChanged(SensorEvent event) {
        //TODO 4, 得到每一种传感器的数值
        float[] data = event.values;
        switch (event.sensor.getType())
        {
            case 1:
                //加速度
                mTv_01.setText("加速度传感器 \n" + "X 轴: " + data[0] +"\n"+"Y轴: "+ data[1]+"\n"+"Z轴: "+ data[2]);
                break;
            case 4:
                //陀螺仪
                mTv_02.setText("陀螺仪传感器 \n" + "X 轴旋转角速度: " + data[0] +"\n"+"Y轴旋转角速度: "+ data[1]+"\n"+"Z轴旋转角速度: "+ data[2]);
                break;
            case 7:
                //温度
                mTv_03.setText("温度传感器 \n" + "当前的温度 : " + data[0]);
                //float current = data[0];if(Math.abs(current)>10) {//温度低于10度, 做操作}
                break;
            case 5:
                //光
                mTv_04.setText("光传感器 \n" + "当前光的强度 : " + data[0]);
                break;
            case 6:
                //压力
                mTv_05.setText("压力传感器 \n" + "当压力值  : " + data[0]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    /*** 初始化控件*/
    private void initView() {
        mTv_01 = (TextView) findViewById(R.id.tv_id_01);
        mTv_02 = (TextView) findViewById(R.id.tv_id_02);
        mTv_03 = (TextView) findViewById(R.id.tv_id_03);
        mTv_04 = (TextView) findViewById(R.id.tv_id_04);
        mTv_05 = (TextView) findViewById(R.id.tv_id_05);
    }

}
