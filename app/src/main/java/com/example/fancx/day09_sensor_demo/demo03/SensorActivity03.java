package com.example.fancx.day09_sensor_demo.demo03;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.fancx.day09_sensor_demo.R;

/**
 * 摇晃手机, 切换图片
 */
public class SensorActivity03 extends AppCompatActivity implements SensorEventListener{

    //TODO 1, 传感器管理器对象
    private SensorManager sensorManager;
    private LinearLayout mLayout;
    //需要切换的图片
    private int[] images = {R.mipmap.image1,R.mipmap.image2,R.mipmap.image3};
    private int index = 0;//当前显示图片的下标

    //TODO 添加震动
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor03);

        mLayout = (LinearLayout) findViewById(R.id.layout_id);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //TODO 得到震动管理器对象
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    //TODO 2,注册传感器的监听器  -- 让当前的类, 实现接口SensorEventListener
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(1),SensorManager.SENSOR_DELAY_GAME);
    }

    //TODO 3,取消注册传感器的监听器
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //TODO ---监听器的回调方法---
    @Override
    public void onSensorChanged(SensorEvent event) {
        // 获取加速度传感器的返回值
        float[] data = event.values;
        if(Math.abs(data[0])>13 || Math.abs(data[1])>13 || Math.abs(data[2])>13)
        {
            //添加震动
            //摇晃多长时间开始震动并且震动的持续时间 long[] , 重复的次数 (-1  不重复)
            long[] pattern = {300,500};//摇晃300毫秒开始震动,  震动时长为500毫秒
            vibrator.vibrate(pattern,-1);//添加震动权限

            // TODO X, Y , Z 三个轴 的数值 , 有一个值大于13 则切换图片  -- 为控件, 更改背景图片
           //防止下标越界
            if(index>2) index = 0;
            mLayout.setBackgroundResource(images[index]);
            index++;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
