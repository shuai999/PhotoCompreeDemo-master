package com.xzt.demo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xzt.demo.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreeActivity extends AppCompatActivity {

    private Button btn ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        /**
         * 指定初始数据初始化，
         */
        List list = new ArrayList() ;
        btn = findViewById(R.id.btn) ;
        LinkedList linkedList ;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity之间传递数据
                // intent传递
                FourActivity.startIntent(ThreeActivity.this , "张三" , "13");


                // bundle传递
                FourActivity.startBundle(ThreeActivity.this , "李四" , "14");


                // serizlizable传递数据
                FourActivity.startSerizlizable(ThreeActivity.this , new Person("世道无情" , "28" , "beijing"));

                // bundle传递bitmap
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                FourActivity.startBitmap(ThreeActivity.this , bitmap);


            }
        });
    }
}
