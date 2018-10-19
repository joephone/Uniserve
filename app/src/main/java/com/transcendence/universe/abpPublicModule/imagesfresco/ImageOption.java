package com.transcendence.universe.abpPublicModule.imagesfresco;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.transcendence.universe.R;
import com.transcendence.universe.utils.Loger;
import com.transcendence.universe.utils.StringUtils;


/**
 * Created by liuf on 16/6/22.
 */
public class ImageOption {

    public static DisplayImageOptions displayImageMiniUrl(String url, Resources res) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri( R.drawable.place_holder)
                .showImageOnFail( R.drawable.place_holder)
                .resetViewBeforeLoading(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300)).build();
        return options;
    }


    /**
     *
     */
    public static DisplayImageOptions initImageLoaderDisplay(int round) {
        DisplayImageOptions options1 = new DisplayImageOptions.Builder()
                // 设置正在加载图片
                .showImageForEmptyUri(R.drawable.default_fishicon)
                // 设置加载失败图片
                .showImageOnFail(R.drawable.camera_friends_sends_pictures_no)

                .cacheInMemory(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(round)) // 设置图片角度,0为方形，360为圆
                .build();
        return options1;
    }

    /**
     * 从内存卡中异步加载本地图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayFromSDCard(String uri, ImageView imageView) {
        // String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
        ImageLoader.getInstance().displayImage("file://" + uri, imageView);
    }

    /**
     * 从assets文件夹中异步加载图片
     *
     * @param imageName
     *            图片名称，带后缀的，例如：1.png
     * @param imageView
     */
    public static void dispalyFromAssets(String imageName, ImageView imageView) {
        // String imageUri = "assets://image.png"; // from assets
        ImageLoader.getInstance().displayImage("assets://" + imageName,
                imageView);
    }
    /**
     * 加载Asset图片 显示国旗
     * @param imageView
     * @param name
     */
    public static void showCountryFlag(ImageView imageView, String name){
        try {
            String hasc = name;
            if (!StringUtils.isStringNull(hasc)){
                if (name.length()>2) {
                    String[] array=name.split("\\.");
                    hasc =array[0];
                 }
                hasc = hasc.toUpperCase();
                String url = "assets://countryFlag/" + hasc + ".png";
                Loger.i("ImageOption", url);
                ImageLoader.getInstance().displayImage(url,
                        imageView);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 从drawable中异步加载本地图片
     *
     * @param imageId
     * @param imageView
     */
    public static void displayFromDrawable(int imageId, ImageView imageView) {
        // String imageUri = "drawable://" + R.drawable.image; // from drawables
        // (only images, non-9patch)
        ImageLoader.getInstance().displayImage("drawable://" + imageId,
                imageView);
    }

    /**
     * 从内容提提供者中抓取图片
     */
    public static void displayFromContent(String uri, ImageView imageView) {
        // String imageUri = "content://media/external/audio/albumart/13"; //
        // from content provider
        ImageLoader.getInstance().displayImage("content://" + uri, imageView);
    }
}
