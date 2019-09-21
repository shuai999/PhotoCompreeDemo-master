package com.xzt.demo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xzt.demo.R;

public class ActivityLifeCircle extends AppCompatActivity {

    /**
     *  一个activity：
     *   app刚启动：
     *          onCreate、onStart、onResume
     *   点击返回键
     *          onPause、onStop、onDestroy
     *
     *
     *   跳转SecondActivity
     *      MainActivity: onPause（应用场景：比如正看视频、正听音乐时，突然有人打电话过来，就需要在这个方法中暂停正在播放的音乐、视频，
     *                            否则打电话的时候都能听到视频和音乐声音，这个不好）
     *      SecondActivity: onCreate、onStart、onResume
     *      MainActivity: onStop
     *
     *             为什么先让 MAinActivity暂停，然后创建SecondActivity，然后MAinActivity执行onStop不可见，为什么不直接让 MAinActivity先暂停然后 onStop？
     *              防止SecondActivity创建时候崩溃了，这个时候MAinActivity也黑屏了，这样不友好；
     *              当SecondActivity创建成功之后，再让MAinActivity不可见，这样考虑比较周全
     *
     *    点击返回键：
     *        SecondActivity: onPause
     *        MainActivity: onRestart、onStart、onResume
     *        SecondActivity: onStop、onDestroy
     *
     *
     *      可以看到：当启动新的activity或者点击返回键退出到当前activity，都是先让当前activity暂停onPause
     *               然后在启动新的activity；
     *
     *
     *      横竖屏切换：先销毁，再创建
     *              onPause、onSaveInstanceState、onStop、onDestroy，onCreate、onStart、onResume
     *
     *
     */
    private static final String TAG = "ActivityLifeCircle" ;

    private Button mBtn ;
    private TextView mText ;

    /**
     * 用于横竖屏切换，activity销毁时调用，保存数据
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // 这里为了测试，存储一个bundle，用于演示
        outState.putString("name" , "iameverywhere");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_circle);

        Log.e(TAG , "===== ActivityLifeCircle onCreate =====")  ;

        mText = findViewById(R.id.tv_text) ;
        mBtn = findViewById(R.id.btn) ;
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.start(ActivityLifeCircle.this);
            }
        });

        if (savedInstanceState != null){
            String name = savedInstanceState.getString("name") ;
            mText.setText(name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG , "===== ActivityLifeCircle onStart =====")  ;
    }


    /**
     * 界面可见，让音视频继续播放
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG , "=====  ActivityLifeCircle onResume =====")  ;
    }

    /**
     * 比如：当前正在播放音视频，如果跳转到别的页面，可以在onPause中暂停播放
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG , "=====  ActivityLifeCircle onPause =====")  ;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG , "=====  ActivityLifeCircle onRestart =====")  ;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG , "=====  ActivityLifeCircle onStop =====")  ;
    }

    /**
     * 一般做一些收尾工作，
     * 1>：销毁handler
     * 2>：销毁音视频控件，然后将其置为null
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG , "=====  ActivityLifeCircle onDestroy =====")  ;
    }
}
