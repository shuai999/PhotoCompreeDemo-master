package com.xzt.demo.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {


    /**
     *
     * @param filePath  要加载的文件路径
     * @param pixelW 真正显示的宽和高
     * @param pixelH
     */
    public static Bitmap ratio(String filePath  ,int pixelW , int pixelH){
        BitmapFactory.Options options = new BitmapFactory.Options() ;
        // 不用加载图片本身，只加载图片的宽高，
        options.inJustDecodeBounds = true ;
        // 位深度：ARGB_8888默认占8字节，这里设置为RGB_565，只占2个字节
        options.inPreferredConfig = Bitmap.Config.RGB_565 ;
        // 预加载
        BitmapFactory.decodeFile(filePath , options) ;

        // 获取图片原始的宽高
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        // 计算采样率
        options.inSampleSize = getSimpleSize(originalWidth , originalHeight , pixelW , pixelH) ;

        options.inJustDecodeBounds = false ;

        // 返回 bitmap，然后设置
        return BitmapFactory.decodeFile(filePath , options) ;
    }

    /**
     * 原始的宽高、要显示的宽高
     */
    private static int getSimpleSize(int originalWidth, int originalHeight, int pixelW, int pixelH) {
        int simpleSize = 1 ;

        // 以宽度计算采样值
        if (originalWidth > originalHeight && originalWidth > pixelW){
            simpleSize = originalWidth / pixelW ;
        // 以高度计算采样率
        }else if (originalWidth < originalHeight && originalHeight > pixelH){
            simpleSize = originalHeight / pixelH ;
        }

        if (simpleSize <= 0){
            simpleSize = 1 ;
        }

        return simpleSize;
    }
}
