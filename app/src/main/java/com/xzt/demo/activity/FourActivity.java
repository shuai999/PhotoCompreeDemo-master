package com.xzt.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xzt.demo.MainActivity;
import com.xzt.demo.R;

public class FourActivity extends AppCompatActivity implements View.OnClickListener{

    public static void startIntent(Context context , String name , String age){
        Intent intent = new Intent(context , FourActivity.class) ;
        intent.putExtra("name" , name) ;
        intent.putExtra("age" , age) ;
        context.startActivity(intent);
    }


    public static void startBundle(Context context , String name , String age){
        Intent intent = new Intent(context , FourActivity.class) ;
        Bundle bundle = new Bundle() ;
        bundle.putString("name" , name);
        bundle.putString("age" , age);
        intent.putExtras(bundle) ;
        context.startActivity(intent);
    }


    public static void startSerizlizable(Context context , Person person){
        Intent intent = new Intent(context , FourActivity.class) ;
        Bundle bundle = new Bundle() ;
        bundle.putSerializable("person" , person);
        intent.putExtras(bundle) ;
        context.startActivity(intent);
    }


    public static void startBitmap(Context context , Bitmap bitmap){
        Intent intent = new Intent(context , FourActivity.class) ;
        Bundle bundle = new Bundle() ;
        bundle.putParcelable("bitmap" , bitmap);
        intent.putExtras(bundle) ;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        // intent获取数据
        String name = getIntent().getStringExtra("name") ;
        String age = getIntent().getStringExtra("age") ;


        /*// bundle获取数据
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name") ;
        String age = bundle.getString("age") ;*/

        // serizlizable获取数据
        //Person person = (Person) getIntent().getSerializableExtra("person");


        // 获取bitmap
        Bitmap bitmap = getIntent().getParcelableExtra("bitmap") ;

        // 我是新建test02分支后修改的代码




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
