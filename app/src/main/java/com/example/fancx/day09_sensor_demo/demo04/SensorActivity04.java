package com.example.fancx.day09_sensor_demo.demo04;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.fancx.day09_sensor_demo.R;

public class SensorActivity04 extends AppCompatActivity implements SensorEventListener{

    private ImageView mShakeUp;
    private ImageView mShakeDown;

    private SensorManager sensorManager;//TODO 传感器管理器对象

    //动画
    private TranslateAnimation upAnimation;
    private TranslateAnimation downAnimation;

    //震动
    private Vibrator vibrator;

    //添加声音
    private SoundPool soundPool;//音效池
    private int mLoadId;//当前加载的声音


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor04);

        mShakeUp = (ImageView) findViewById(R.id.shake_up_id);
        mShakeDown = (ImageView) findViewById(R.id.shake_down_id);

        //TODO 1, 传感器管理器对象
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //TODO 得到震动的管理器对象
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //TODO 初始化声音
        //最大值, 类型, 质量 默认0
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        mLoadId = soundPool.load(this,R.raw.awe,1);

        //初始化动画
        initAnimation();
    }

    private void initAnimation() {

        //上
        upAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,
                                              Animation.RELATIVE_TO_SELF,0,
                                              Animation.RELATIVE_TO_SELF,0,
                                                Animation.RELATIVE_TO_SELF,-1);//0.5 一半, 1 代表是自己
        upAnimation.setDuration(3000);
        // 下
        downAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,
                                                Animation.RELATIVE_TO_SELF,0,
                                                 Animation.RELATIVE_TO_SELF,0,
                                                    Animation.RELATIVE_TO_SELF,1);//0.5 一半, 1 代表是自己
        downAnimation.setDuration(3000);
    }


    //TODO 2, 注册监听器  -- 当前类, 实现接口SensorEventListener
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(1),SensorManager.SENSOR_DELAY_GAME);
    }
    //TODO 3, 取消注册监听器
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //TODO 4, 重写接口中的方法, 获取每一个数值, 启动动画, 实现微信摇一摇
    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] data = event.values;
        if(Math.abs(data[0])>13 || Math.abs(data[1])>13 || Math.abs(data[2])>13)
        {
            //添加震动
            long[] pattern = {300,500};
            vibrator.vibrate(pattern,-1);

            //播放音乐
            //声音id, 左声道(0.0f -- 1.0f) , 右声道, 优先级, 是否循环(0 不循环, 1 循环) , 播放比例( 正常播放 1)
            soundPool.play(mLoadId,1.0f,1.0f,1,0,1.0f);

            //启动动画
            mShakeUp.startAnimation(upAnimation);
            mShakeDown.startAnimation(downAnimation);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
