package com.example.fancx.day09_sensor_demo.demo01;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fancx.day09_sensor_demo.R;

/**
 *  加速度传感器的介绍 --- 获取加速度传感器的数值 , 显示在TextView 上
 *
 *  SensorEventListener   传感器的监听器对象
 */
public class SensorActivity01 extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager ;
    private Sensor sensor;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor01);
        mTv = (TextView) findViewById(R.id.tv_id_01);

        //TODO 1, 获取传感器的管理器对象
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //TODO 2, 通过传感器的管理器对象, 得到传感器对象
        sensor = sensorManager.getDefaultSensor(1);//Sensor.TYPE_ACCELEROMETER = 1;

    }
    //TODO 3, 注册传感器的监听器  -- onResume() 生命周期方法中  -- 让当前的类, 实现SensorEventListener 接口,重写方法
    @Override
    protected void onResume() {
        super.onResume();
        //TODO 监听传感器时间的监听器,  传感器对象 , 刷新的频率
        //SensorManager.SENSOR_DELAY_FASTEST   最快的, 延迟最小, 同时也是最消耗资源的 -- 不推荐
        //SensorManager.SENSOR_DELAY_GAME      适合游戏的频率, 一般有实时要求高的可以使用
        //SensorManager.SENSOR_DELAY_NORMAL    正常频率, 一般有实时要求不高的可以使用
        //SensorManager.SENSOR_DELAY_UI        适合普通的应用频率, 这种模式比较省电, 系统开销小, 但是延迟大 , 适用普通小程序使用
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);
    }

    //TODO 4, 取消注册传感器的监听器  -- onPause() 生命周期方法中
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //TODO ---  传感器的监听方法 ---
    @Override
    public void onSensorChanged(SensorEvent event) {
        //TODO 监听传感器的变化
        //TODO 5, 获取传感器中的数值
        //event.sensor.getType()   当前检测的传感器的类型
        float[] data = event.values;
        float x = data[0];
        float y = data[1];
        float z = data[2];

        mTv.setText("X 轴的数值: " +x +" \n Y 轴的数值: " +y +"\n  Z 轴的数值: " +z );
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //TODO 精度发生变化的回调方法
    }
}
