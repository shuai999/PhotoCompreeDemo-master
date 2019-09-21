package com.xzt.demo.memory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xzt.demo.R;

import java.lang.ref.SoftReference;

public class Gate extends AppCompatActivity {

    // 强引用 ，全局变量，生命周期和 activity一样，activity退出了，变量才会被回收；否则不会被回收
    private String strongRef ;
    // 软引用， 全局变量，string类型变量 指向 软引用，如果内存空间不够，gc是可以回收，不用等activity退出，
    // 使用 软引用后，不用管回收，系统会自动在内层不够时回收，比如bitmap，
    private SoftReference<String> softRef ;

    // 软引用如何管理图片

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);

        strongRef = String.valueOf(Math.random()) ;

        softRef = new SoftReference<>(String.valueOf(Math.random())) ;


    }
}
