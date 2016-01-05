package com.ihealth.aijiakang.imageloader;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

import iHealth.AiJiaKang.MI.R;

public class ImageLoaderTools {
	private final static String DirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/iHealthAiJiaKang/";
	private static ImageLoaderTools INSTANCE = null;
	private ImageLoader mImageLoader = null;
	private final DisplayImageOptions circle_options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.mipmap.default_img) // 设置图片在下载期间显示的图片
			.showImageForEmptyUri(R.mipmap.default_img)// 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(R.mipmap.default_img) // 设置图片加载/解码过程中错误时候显示的图片
			.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
			.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
			.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
			.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
			.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
			// 设置图片加入缓存前，对bitmap进行设置
			.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
			.displayer(new CircleBitmapDisplayer()).build();// 构建完成

	private final DisplayImageOptions default_anim_options = new DisplayImageOptions.Builder()
			.showImageOnLoading(0) // 设置图片在下载期间显示的图片
			.showImageForEmptyUri(0)// 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(0) // 设置图片加载/解码过程中错误时候显示的图片
			.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
			.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
			.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
			.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
			.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
			// 设置图片加入缓存前，对bitmap进行设置
			.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
			.displayer(new FadeInBitmapDisplayer(500)).build();// 构建完成
	public ImageLoaderTools() {
		mImageLoader = ImageLoader.getInstance();
	}

	public static ImageLoaderTools getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ImageLoaderTools();
		}

		return INSTANCE;
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}

	/** 判断目录是否存在 **/
	private File createDiscDir(String path) {
		File cacheDir = new File(path);
		if (!cacheDir.exists()) {
			cacheDir.mkdir();
		}

		return cacheDir;
	}

	/** 初始化ImageLoader **/
	public void initImageLoader(Context context) {
		if(context == null){
			return;
		}
		File cacheDir = createDiscDir(DirPath);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPoolSize(3) // default
				.threadPriority(Thread.NORM_PRIORITY - 1) // default
				.memoryCacheExtraOptions(480, 800) // default = device screen dimensions  
				.diskCacheExtraOptions(480, 800, null)  
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
				.diskCache(new UnlimitedDiskCache(cacheDir)) // default
				.diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(200)
				.diskCacheFileNameGenerator(new Md5FileNameGenerator()).defaultDisplayImageOptions(getDefaultOptions())
				.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)).build();

		ImageLoader.getInstance().init(config);
	}

	/** 默认图片显示参数设置 **/
	private DisplayImageOptions getDefaultOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(0) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(0)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(0) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
				// 设置图片加入缓存前，对bitmap进行设置
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				.build();// 构建完成
		return options;
	}

	/** NetWork显示方形图片 **/
	public void displayNetImg(String url, ImageView imgView) {
		if (mImageLoader == null) {
			return;
		}
		mImageLoader.displayImage(url, imgView);
	}

	/** NetWork显示圆形图片 **/
	public void displayNetRoundImg(String url, ImageView imgView) {
		if (mImageLoader == null || circle_options == null) {
			return;
		}
		mImageLoader.displayImage(url, imgView, circle_options);
	}

	/** SD卡显示默认方图 **/
	public void displaySDImg(String path, ImageView imgView) {
		if (mImageLoader == null) {
			return;
		}
		mImageLoader.displayImage("file://" + path, imgView);
	}

	/** SD卡显示默认圆图 **/
	public void displaySDRoundImg(String path, ImageView imgView) {
		if (mImageLoader == null) {
			return;
		}
		mImageLoader.displayImage("file://" + path, imgView, circle_options);
	}

	/** SD卡显示默认圆图 **/
	public void displaySDRoundImg(String path, ImageView imgView,ImageLoadingListener listener) {
		if (mImageLoader == null) {
			return;
		}
		mImageLoader.displayImage("file://" + path, imgView, circle_options,listener);
	}
	
	/** SD卡显示默认方图带动画 **/
	public void displaySDImgAnim(String path, ImageView imgView) {
		if (mImageLoader == null || default_anim_options == null) {
			return;
		}
		mImageLoader.displayImage("file://" + path, imgView, default_anim_options);
	}

	public void clearMemory()
	{
		if(mImageLoader!=null){
			mImageLoader.clearDiskCache();
			mImageLoader.clearMemoryCache();
		}
	}
}
