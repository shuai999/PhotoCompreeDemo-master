package com.xzt.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xzt.demo.R;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity" ;


    public static void start(Context context){
        Intent intent = new Intent(context , SecondActivity.class) ;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.e(TAG , "===== SecondActivity onCreate =====")  ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG , "===== SecondActivity onStart =====")  ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG , "===== SecondActivity  onResume =====")  ;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG , "=====  SecondActivity onPause =====")  ;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG , "=====  SecondActivity onRestart =====")  ;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG , "=====  SecondActivity onStop =====")  ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG , "=====  SecondActivity onDestroy =====")  ;
    }
}
