package com.transcendence.universe.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class FitImgSize {
	String tag="FitImgSize";
	// ͼƬ����
	public Bitmap fitSizeImg(String path, int screenWidth) {
		if (path == null || path.length() < 1) {
			return null;
		}
		Bitmap bmp = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);
		opts.inSampleSize = computeSampleSize(opts, -1, 200 * 200);
		opts.inJustDecodeBounds = false;
		try {
			bmp = BitmapFactory.decodeFile(path, opts);
		} catch (OutOfMemoryError err) {
		}
//		bmp = Bitmap.createScaledBitmap(bmp, (screenWidth - 6) / 2,
//				(screenWidth - 6) / 2, true);
		bmp = getImage(bmp,screenWidth);
		return bmp;
	}
	
	  private Bitmap getImage(Bitmap bMap, int screenWidth){
		   int screenW=screenWidth;
			int screenH=screenWidth;
			int x=0;
			int y=0;
			int width=0;
			int height=0;
			
			if((double)bMap.getHeight()/(double)bMap.getWidth()<(double)screenH/(double)screenW){
				width=(int) Math.round((double)screenW*((double)bMap.getHeight()/(double)screenH));
				height=bMap.getHeight();
				x=(bMap.getWidth()-width)/2;
				y=0;
			}else{
				width=bMap.getWidth();
				height=(int) Math.round((double)screenH*((double)bMap.getWidth()/(double)screenW));
				x=0;
				y=(bMap.getHeight()-height)/2;
			}
			Log.i(tag,"width:"+bMap.getWidth()+",height:"+bMap.getHeight());
			Log.i(tag,"new width:"+width+",height:"+height+" x:"+x+" Y:"+y);
			return bMap=Bitmap.createBitmap(bMap,x,y,width,height);
	   }
	/*** 
	* 图片的缩放方法 
	*

	* @param bgimage 
	* ：源图片资源

	* @param newWidth 
	* ：缩放后宽度 
	* @param newHeight

	* ：缩放后高度 
	* @return

	*/

	public Bitmap zoomImage(Bitmap bgimage, int newWidth) {

	// 获取这个图片的宽和高
	int width = bgimage.getWidth(); 
	int height = bgimage.getHeight();
	float scaleWidth=width>height?((float) newWidth)/width:((float) newWidth)/height;
	// 创建操作图片用的matrix对象
	Matrix matrix = new Matrix();
	// 缩放图片动作
	matrix.postScale(scaleWidth, scaleWidth); 
	Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, width, height, 
	matrix, true); 
	return bitmap;

	}
	public int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

}
