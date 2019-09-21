package com.xzt.demo.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.xzt.demo.R;

public class PicTest extends AppCompatActivity {

    private ImageView iv_1 , iv_2 ;
    Bitmap mCurrentBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        initView();

        loadOriginSize() ;

        loadInBitmap() ;
    }

    /**
     * inBitmap的使用
     */
    private void loadInBitmap() {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() ;
        String filePath = sdcard + "/savephoto.jpg" ;

        BitmapFactory.Options options = new BitmapFactory.Options() ;
        options.inBitmap = mCurrentBitmap ;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        // 这个就是 第二章图片完全复用第一张图片
        iv_2.setImageBitmap(bitmap);
    }


    /**
     * 直接加载 sdcard 中的图片
     */
    private void loadOriginSize() {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() ;
        String filePath = sdcard + "/savephoto.jpg" ;

        Log.e("TAG" , "sdcard: "+sdcard + ", filePath: "+filePath) ;
        mCurrentBitmap = BitmapFactory.decodeFile(filePath);
        iv_1.setImageBitmap(mCurrentBitmap);
    }

    /**
     * 尺寸图片
     */
    private void picOptionSize(){
        // 手机根目录，就是手机最外层目录
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() ;
        String filePath = sdcard + "/savephoto.jpg" ;

        BitmapFactory.Options options = new BitmapFactory.Options() ;
        // 不用将图片加载到内存，就可以获取到图片的宽高，避免内存溢出
        options.inJustDecodeBounds = true ;
        // 图片宽高会存储到 options 中
        BitmapFactory.decodeFile(filePath , options) ;


        // 从 options 中获取宽高
        int width = options.outWidth;
        // 最终压缩到200，降低图片采样率，减少图片占用内存
        options.inSampleSize = width / 200 ;
        // 这样一张图片像素就占用 2个字节
        options.inPreferredConfig = Bitmap.Config.RGB_565 ;

        // 把图片真正加载进来
        options.inJustDecodeBounds = false ;

        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        iv_1.setImageBitmap(bitmap);

    }

    private void initView() {
        iv_1 = findViewById(R.id.iv_1);
        iv_2 = findViewById(R.id.iv_2);
    }
}
