package com.xzt.demo.memory;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xzt.demo.R;

public class MemoryActivity extends AppCompatActivity {

    private TextView tv_1 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        initView() ;
        calculate() ;

        loadBitmap();
    }

    /**
     * bitmap 4种加载方式
     */
    private void loadBitmap() {
        /*// 将 字节数组 转为 bitmap
        Bitmap bitmap1 = BitmapFactory.decodeByteArray() ;
        // 将 文件路径转为 bitmap
        Bitmap bitmap2 = BitmapFactory.decodeFile();
        // 将 资源id转为 bitmap
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources() , R.mipmap.ic_launcher , null);
        // 将 流转为 bitmap
        Bitmap bitmap4 = BitmapFactory.decodeStream();*/
    }

    /**
     * 计算
     */
    private void calculate() {
        StringBuilder sb = new StringBuilder() ;

        // 系统服务
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        // 以m兆为单位，获取app的内存限制，超过这个限制，就crash
        int memoryClass = manager.getMemoryClass();

        // 主要是针对清单文件中，android:largeHeap="true" 这个属性，在清单文件中申请了这个，就是告诉系统 app需要大的堆，
        // 一般不要这样用，因为这样用了会影响其他app
        int largeMemoryClass = manager.getLargeMemoryClass();
        sb.append("memoryClass: "+memoryClass+"\n") ;
        sb.append("largeMemoryClass: "+largeMemoryClass+"\n") ;

        // 运行时总的内存：已经分配的内存
        Float totalMemory = Runtime.getRuntime().totalMemory() * 1.0f / (1024 * 1024);
        // 空闲的内存
        Float freeMemory = Runtime.getRuntime().freeMemory() * 1.0f / (1024 * 1024);
        // 最大内存，这个就是最大限制内存
        Float maxMemory = Runtime.getRuntime().maxMemory() * 1.0f / (1024 * 1024);

        // 显示内存限制的数据
        tv_1.setText(sb.toString());


        // 测试机华为：
        //    memoryClass: 192
        //    largeMemoryClass: 512
        Log.e("TAG" ,"==== 华为最大内存：==="+sb.toString()) ;
    }

    private void initView() {
        tv_1 = findViewById(R.id.tv_1) ;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e("TAG" , "=======level: ===="+level) ;
    }
}
