package com.xzt.demo.bitmap;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

/**
 * 加载网络图片，并缓存图片到 内存缓存LruCache 和 磁盘缓存DiskLruCache：
 *      如果 内存缓存 LruCache中有，就直接设置给imageview，如果没有就在 磁盘缓存DiskLruCache中根据url查找对应bitmap；
 *      如果有就直接设置给 imageview，然后给内存缓存LruCache中缓存一份，以url为key，bitmap为value；
 *      如果 磁盘缓存 DiskLruCache中也没有，就去请求网络，成功后给 内存缓存和磁盘缓存各存一份
 */
public class SimpleImageLoader {

    private static SimpleImageLoader mLoader ;
    private LruCache<String , Bitmap> mLrucache ;

    // 这里模拟 DiskLruCache本地缓存
    //private DiskLruCacheHelper mDiskLrucacheHepler ;

    /**
     * 私有构造方法：用于初始化缓存对象
     */
    private SimpleImageLoader(){
        // 获取最大可用内存空间
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mLrucache = new LruCache<String, Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        } ;

        /*try {
            mDiskLrucacheHepler = new DiskLruCacheHelper() ;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static SimpleImageLoader getInstance(){
        if (mLoader == null){
            synchronized (SimpleImageLoader.class){
                if (mLoader == null){
                    mLoader = new SimpleImageLoader() ;
                }
            }
        }
        return mLoader ;
    }

    /**
     * 加载网络图片
     */
    public void displayImage(ImageView imageView , String url){

        // 首先 从内存中根据url 获取bitmap
        Bitmap bitmap = getBitmapFromCache(url) ;
        // 如果缓存中已经有这张图片，就直接加载到 imageView
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);

        // 如果缓存中没有，就联网下载图片
        }else{
            downloadImage(imageView , url);
        }
    }

    /**
     * 从缓存中读取图片
     */
    public Bitmap getBitmapFromCache(String url){
        if (!TextUtils.isEmpty(url)){

            // 获取图片时：
            // 首先  从内存缓存中获取，如果有，就直接设置，
            // 如果没有就 从本地缓存中读取，如果有，取出来设置；
            // 如果都没有，就请求网络，下载成功后，会给 内存缓存、本地缓存中都缓存一份
            if (mLrucache.get(url) != null){
                return mLrucache.get(url) ;
            }

            /*if (mDiskLrucacheHepler.get(url) != null){
                // 对于 本地缓存，这里就只暴露这一个方法，没有直接操作 DiskLruCache，而是操作它封装后的帮助类，
                // 如果不想使用 DiskLruCache，想使用别的好的缓存图片框架，这个工具类不用动，
                // 直接修改 mDiskLrucacheHepler这个帮助类就ok，这样就使得 业务逻辑代码完全和第三方框架解耦
                return mDiskLrucacheHepler.getAsBitmap(url) ;
            }*/
        }
        return null ;
    }

    /**
     * 将下载下来的图片保存在内存缓存中
     */
    public void putBitmapToCache(String url , Bitmap bitmap){
        if (bitmap != null) {
            // 把数据保存到 LruCache中
            mLrucache.put(url, bitmap);

            // 把数据保存到 DiskLruCache中
            //mDiskLrucacheHepler.put(url, bitmap);
        }
    }

    /**
     * 用okhttp下载网络图片
     */
    private void downloadImage(ImageView imageView , final String url){
        OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        Bitmap bitmap = response ;

                        // 下载成功后，把图片保存一份，url作为key，bitmap为value
                        putBitmapToCache(url , bitmap);
                    }

                });
    }
}
