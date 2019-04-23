package com.transcendence.universe.abpPublicModule.imagesfresco;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.transcendence.universe.R;
import com.transcendence.universe.service.AppAplication;
import com.transcendence.universe.utils.Logs;
import com.transcendence.universe.utils.StringUtils;
import com.transcendence.universe.utils.Utility;

import java.io.File;

/**
 * Created by liuf on 16/4/4.
 * 设置图片显示
 */
public class DisplayImage {
    static String tag = "DisplayImage";
    static Context context;
    static DisplayImage instance;

    /*
         * 单例模式中获取唯一的CursorUtility实例
         */
    public static DisplayImage getInstance() {
        if (null == instance) {
            context = AppAplication.getInstance();
            instance = new DisplayImage();
        }
        return instance;
    }
    /**
     * 加载普通网络图片
     *
     * @param imageView
     * @param url
     */
    public void displayImageFromNet(SimpleDraweeView imageView, String url) {
        Drawable userDrawable = Utility.getDrawable(context, R.drawable.place_holder);

        GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
        hierarchy.setFailureImage(userDrawable);
        hierarchy.setPlaceholderImage(userDrawable);
        imageView.setHierarchy(hierarchy);
        displayNetworkImage(imageView, url);
    }

    /**
     * 加载普通网络图片 加载失败和正在加载的图片设置
     *
     * @param imageView
     * @param url
     */
    public void displayNetworkImage(SimpleDraweeView imageView, String url) {
        if (StringUtils.isStringNull(url)) {
            displayResImage(imageView, R.drawable.place_holder);
            return;
        }
        Uri imageUri = Uri.parse(url);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //tap-to-retry load image
                .setTapToRetryEnabled(true)//点击重试
                .setAutoPlayAnimations(true)//动画自动播放
                //在构建新的控制器时需要setOldController，这可以防止重新分配内存
                .setOldController(imageView.getController())
                //注意:uri是指绝对路径
                .setUri(imageUri)
                .build();
        imageView.setController(controller);

    }

    /**
     * 设置头像，加载失败和正在加载的图片设置
     */
    public void displayAvatarImage(SimpleDraweeView imageView, String url) {
        if (!StringUtils.isStringNull(url) && imageView != null) {
            Drawable userDrawable = Utility.getDrawable(context, R.drawable.default_avatar);
            GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
            hierarchy.setFailureImage(userDrawable);
            hierarchy.setPlaceholderImage(userDrawable);
            imageView.setHierarchy(hierarchy);
//            if (url.contains("bj.p.solot.com/avatar.png")){
//                displayResImage(imageView, R.drawable.default_avatar);
//            }else {
                displayNetworkImage(imageView, url);
//            }
        } else {
            displayResImage(imageView, R.drawable.default_avatar);
        }

    }

    /**
     * 加载本地图片
     *
     * @param imageView
     * @param url
     */
    public void displayLocFileImage(SimpleDraweeView imageView, String url) {
        url = "file://" + url;
        Uri imageUri = Uri.parse(url);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //tap-to-retry load image
                .setTapToRetryEnabled(true)//点击重试
                .setAutoPlayAnimations(false)//动画自动播放
                //在构建新的控制器时需要setOldController，这可以防止重新分配内存
                // .setOldController(imageView.getController())
                //注意:uri是指绝对路径
                .setUri(imageUri)
                .build();
        imageView.setController(controller);
        Drawable userDrawable = Utility.getDrawable(context, R.drawable.camera_friends_sends_pictures_no);
        GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
        hierarchy.setFailureImage(userDrawable);
        hierarchy.setPlaceholderImage(userDrawable);
        imageView.setHierarchy(hierarchy);
//        displayNetworkImage(imageView, url);
    }

    /**
     * 加载本地图片
     *
     * @param imageView
     * @param url
     */
    public void displayLocFileImage2(SimpleDraweeView imageView, String url) {
        url = "file://" + url;
//        Uri imageUri = Uri.parse(url);
        displayNetworkImage(imageView, url);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                //tap-to-retry load image
//                .setTapToRetryEnabled(true)//点击重试
//                .setAutoPlayAnimations(false)//动画自动播放
//                //在构建新的控制器时需要setOldController，这可以防止重新分配内存
//                // .setOldController(imageView.getController())
//                //注意:uri是指绝对路径
//                .setUri(imageUri)
//                .build();
//        imageView.setController(controller);
//        Drawable userDrawable = Utility.getDrawable(context, R.drawable.camera_friends_sends_pictures_no);
//        GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
//        hierarchy.setFailureImage(userDrawable);
//        hierarchy.setPlaceholderImage(userDrawable);
//        imageView.setHierarchy(hierarchy);
//        displayNetworkImage(imageView, url);
    }
    /**
     * 加载图片，不管网络与本地
     *
     * @param imageView
     * @param url
     */

    public void dislayImg(SimpleDraweeView imageView, String url) {
        if (url.contains("http://") || url.contains("https://")) {
            displayNetworkImage(imageView, url);
        } else {
            displayLocFileImage(imageView, url);
        }

    }

    /**
     * 加载Res图片
     *
     * @param imageView
     * @param id
     */
    public void displayResImage(SimpleDraweeView imageView, int id) {
        String url = "res://com.catches/" + id;
        displayNetworkImage(imageView, url);
    }

    /**
     * 加载Asset图片
     *
     * @param imageView
     * @param name
     */
    public void displayAssetImage(SimpleDraweeView imageView, String name) {
        String url = "asset:///" + name;
        displayNetworkImage(imageView, url);
    }

    /**
     * 加载Asset图片 显示国旗
     *
     * @param imageView
     * @param name
     */
    public void showCountryFlag(SimpleDraweeView imageView, String name) {
        String url = "asset:///countryFlag/" + name + ".png";
        displayNetworkImage(imageView, url);
    }

    public void showWeatherTide(SimpleDraweeView imageView, String name) {
        String url = "asset:///weather_image_tide/" + name + ".png";
        Logs.logI("--url:" + url);
        displayNetworkImage(imageView, url);
    }

    public void showWeatherNewsLeftTide(SimpleDraweeView imageView, String name) {
        String url = "asset:///weather_image_news_left/" + name + ".png";
        Logs.logI("--url:" + url);
        displayNetworkImage(imageView, url);
    }

    public void showWeatherNewsRightTide(SimpleDraweeView imageView, String name) {
        String url = "asset:///weather_image_news_right/" + name + ".png";
        Logs.logI("--url:" + url);
        displayNetworkImage(imageView, url);
    }

    /**
     * 获取渔获相关天气图
     *
     * @param imageView
     * @param name
     */
    public void showWeatherCatches(SimpleDraweeView imageView, String name) {
        String url = "asset:///weather_image_catches/" + name + ".png";
        Logs.logI("--url:" + url);
        displayNetworkImage(imageView, url);
    }

    /**
     * 加载Asset/fishimg图片
     *
     * @param imageView
     * @param url
     */
    public void showFishImg(SimpleDraweeView imageView, String url) {
        displayNetworkImage(imageView, url);
    }



    /**
     * 加载普通网络图片
     *
     * @param imageView
     * @param url
     */
    public void displayBlackAndWhiteImage(SimpleDraweeView imageView, String url) {

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setPostprocessor(new BasePostprocessor() {
                    @Override
                    public void process(Bitmap bitmap) {
                        super.process(toGrayscale(bitmap));
                    }
                })
                .setLocalThumbnailPreviewsEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //tap-to-retry load image
                .setTapToRetryEnabled(true)//点击重试
                .setAutoPlayAnimations(true)//动画自动播放
                //在构建新的控制器时需要setOldController，这可以防止重新分配内存
                .setOldController(imageView.getController())
                //注意:uri是指绝对路径
                .setImageRequest(request)
                .build();
        imageView.setController(controller);
    }

    /**
     * 8     * 图片去色,返回灰度图片
     * 9     * @param bmpOriginal 传入的图片
     * 10     * @return 去色后的图片
     * 11
     */
    public static Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    /**
     * 设置圆形
     *
     * @param imageView
     */
    public void setOverlayImage(SimpleDraweeView imageView) {
        //圆形属性
        GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
        imageView.setHierarchy(hierarchy);
    }

    /**
     * 设置圆形
     *
     * @param imageView
     */
    public void setCircle(SimpleDraweeView imageView) {
        //圆形属性
        GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
        hierarchy.getRoundingParams().setRoundAsCircle(true);
        imageView.setHierarchy(hierarchy);
    }

    /**
     * 设置圆角
     *
     * @param imageView
     * @param Radius
     */
    public void setRound(SimpleDraweeView imageView, int Radius) {
        GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
        hierarchy.getRoundingParams().setCornersRadius(Radius);
        imageView.setHierarchy(hierarchy);
    }

    public void setRound(SimpleDraweeView imageView) {
        setRound(imageView, 15);
    }

    /**
     * 设置宽高比例
     *
     * @param imageView
     * @param ratio
     */
    public void setRatio(SimpleDraweeView imageView, float ratio) {
        imageView.setAspectRatio(ratio);
    }

    /**
     * 设置淡入淡出时间
     */
    public void setFadeDuration(SimpleDraweeView imageView, int times) {
        GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
        hierarchy.setFadeDuration(times);
        imageView.setHierarchy(hierarchy);
    }

    /**
     * 设置加载失败图片
     *
     * @param imageView
     */
    Drawable failDrawable;

    public void setFailureImage(SimpleDraweeView imageView) {
        if (failDrawable == null) {
            failDrawable = Utility.getDrawable(context, R.drawable.camera_friends_sends_pictures_no);
        }
        GenericDraweeHierarchy hierarchy = getHierarchy(imageView);
        hierarchy.setFailureImage(failDrawable);
        imageView.setHierarchy(hierarchy);
    }



    private GenericDraweeHierarchy getHierarchy(SimpleDraweeView imageView) {
        GenericDraweeHierarchy hierarchy = imageView.getHierarchy();
        if (hierarchy == null) {
            GenericDraweeHierarchyBuilder builder =
                    new GenericDraweeHierarchyBuilder(context.getResources());
            RoundingParams roundingParams = new RoundingParams();
            hierarchy = builder.setRoundingParams(roundingParams).
                    build();
        }
        if (hierarchy.getRoundingParams() == null) {
            RoundingParams roundingParams = new RoundingParams();
            hierarchy.setRoundingParams(roundingParams);
        }
        return hierarchy;
    }

    /**
     * 获取缓存图片
     *
     * @param url
     * @return
     */
    public Bitmap returnBitmap(String url) {
        Bitmap bitmap = null;
        try {
            Uri uri = Uri.parse(url);
            FileBinaryResource resource = (FileBinaryResource)
                    Fresco.getImagePipelineFactory().getMainDiskStorageCache().getResource(new SimpleCacheKey(uri.toString()));
            File file = resource.getFile();
            bitmap = BitmapFactory.decodeFile(file.getPath());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;

    }






}
