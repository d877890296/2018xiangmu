package com.goods.details;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.dev.customview.AdViewPaper;
import com.dev.customview.AdViewPaper.OnSingleTouchListener;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.application.MyApplication;
import com.xfkc.caimai.base.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 设置导航 图片轮播
 * 
 * @author lyy
 *
 */
public class GoodsDetailsHeader {
	private List<ImageView> pointViewArr = new ArrayList<ImageView>();
	private int count = 0;
	private int currentViewPagerIndex = 0;
	private List<AdTopPicModel> topPicData;
	private List<View> viewadata;
	private ViewPagerAdapter viewPagerAdapter;
	private int imges[] = { R.drawable.default_shuig, R.drawable.gd_model1_img, R.drawable.default_shuig,
			R.mipmap.error_icon };
	private Timer timer = new Timer();
	private Context context;
	private AdViewPaper adViewPaper;
	private TextView point_textView;
	private String[] imgArray;
	private MyApplication app;

	public GoodsDetailsHeader(Activity context) {
		this.context = context;
		viewadata = new ArrayList<View>();
		viewPagerAdapter = new ViewPagerAdapter();
	}

	public void setImgArray(String[] imgArray) {
		this.imgArray = imgArray;
		setAdData();
	}

	public void setView(AdViewPaper adViewPaper, TextView point_textView) {
		this.adViewPaper = adViewPaper;
		this.point_textView = point_textView;
		initView();
		app = MyApplication.getInstance();
//		setAdData();
	}

	private void initView() {
		adViewPaper.setOnPageChangeListener(onPageChangeListener);
		adViewPaper.setOnSingleTouchListener(onSingleTouchListener);

	}

	private void setAdData() {
		topPicData = new ArrayList<AdTopPicModel>();
		 for (int i = 0; i < imgArray.length; i++) {
		 AdTopPicModel model = new AdTopPicModel();
		 model.setImagepath(imgArray[i] + "");
		 model.setId(i);
		 topPicData.add(model);
		 }
//		for (int i = 0; i < imgArray.length; i++) {
//			AdTopPicModel model = new AdTopPicModel();
//			model.setImagepath(LmsConfig.FileService + imgArray[i]);
//			model.setId(i);
//			topPicData.add(model);
//		}
		viewadata = viewPagerImage(topPicData);
		viewPagerAdapter.setViewadata(viewadata);
		adViewPaper.setAdapter(viewPagerAdapter);
		adViewPaper.setCurrentItem(currentViewPagerIndex, false);

		point_textView.setText(1 + "/" + viewadata.size());

	}

	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			handler.sendEmptyMessage(0);
		}
	};

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				currentViewPagerIndex++;
				if (currentViewPagerIndex == count - 1) {
					currentViewPagerIndex = 1;
				}
				adViewPaper.setCurrentItem(currentViewPagerIndex, false);
			}
		};
	};

	private OnSingleTouchListener onSingleTouchListener = new OnSingleTouchListener() {

		@Override
		public void onSingleTouch() {
			// TODO Auto-generated method stub
			// currentViewPagerIndex d

//			GoodsImgValue.getInstance().setCurIndex(currentViewPagerIndex);
//			GoodsImgValue.getInstance().setTopPicData(topPicData);
//			Intent intent = new Intent();
//			intent.setClass(context, PreViewImgActivity.class);
//			context.startActivity(intent);

		}

	};
	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			// if (position == count - 1) {
			// currentViewPagerIndex = 1;
			// } else if (position == 0) {
			// currentViewPagerIndex = count;
			// } else {
			currentViewPagerIndex = position;
			// }
			adViewPaper.setCurrentItem(currentViewPagerIndex, false);

			point_textView.setText((currentViewPagerIndex + 1) + "/" + viewadata.size());
			// setCurPointBackGround(currentViewPagerIndex);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int position) {
		}
	};

	/***
	 * 设置当前导航的背景颜色
	 * 
	 * @param position
	 */
	public void setCurPointBackGround(int position) {
		for (int i = 0; i < pointViewArr.size(); i++) {
			if (i == position) {
				pointViewArr.get(i).setImageResource(R.drawable.guidepoint_red);
			} else {
				pointViewArr.get(i).setImageResource(R.drawable.guide_dot_white);
			}
		}
	}

	/***
	 * 图片的初始化
	 * 
	 * @param
	 * @return
	 */
	public List<View> viewPagerImage(List<AdTopPicModel> topPicData) {
		List<View> images = new ArrayList<View>();
		// if (topPicData.size() == 1) {// 当轮播图只有一张图片时
		// ImageView imageView = new ImageView(context);
		// imageView.setScaleType(ScaleType.FIT_XY);
		// imageView.setTag(100);
		// // app.imageLoader.displayImage(topPicData.get(count -
		// // 1).getImagepath(), imageView);
		// imageView.setImageResource(Integer.parseInt(topPicData.get(0).getImagepath()));
		// images.add(imageView);
		// } else {
		// timer.schedule(task, 2000, 5000);
		// count = topPicData.size() + 2;
		// ImageView imageView = new ImageView(context);
		// imageView.setScaleType(ScaleType.FIT_XY);
		// imageView.setTag(100);
		// // app.imageLoader.displayImage(topPicData.get(count -
		// // 1).getImagepath(), imageView);
		// imageView.setImageResource(Integer.parseInt(topPicData.get(topPicData.size()
		// - 1).getImagepath()));
		// images.add(imageView);

		for (int i = 0; i < topPicData.size(); i++) {
			ImageView imageView = new ImageView(context);
			imageView.setBackgroundResource(R.drawable.adi);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setClickable(true);
			imageView.setTag(100 + (i + 1));
			
			// app.imageLoader.displayImage(topPicData.get(i).getImagepath(),
			// imageView);
			app.imageLoader.displayImage(topPicData.get(i).getImagepath(), imageView);
//			imageView.setImageResource(Integer.parseInt(topPicData.get(i).getImagepath()));
			images.add(imageView);
		}

		// imageView = new ImageView(context);
		// imageView.setScaleType(ScaleType.FIT_XY);
		// imageView.setTag(100 + count + 1);
		// // app.imageLoader.displayImage(topPicData.get(0).getImagepath(),
		// // imageView);
		// imageView.setImageResource(Integer.parseInt(topPicData.get(0).getImagepath()));
		// images.add(imageView);
		// createPointView(topPicData.size());
		// }
		// setTonch(imageView);// 设置手势
		return images;
	}

	/***
	 * 创建导航视图
	 * 
	 * @param size
	 */
	public void createPointView(int size) {
		for (int i = 0; i < size; i++) {
			LayoutParams parms_ = new LayoutParams((int) Tools.dip2px(context, 10),
					(int) Tools.dip2px(context, 10));
			// LinearLayout.LayoutParams parms_ = new
			// LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
			// LinearLayout.LayoutParams.WRAP_CONTENT);
			ImageView pointView = new ImageView(context);
			pointView.setImageResource(R.drawable.guide_dot_white);
			parms_.gravity = Gravity.RIGHT | Gravity.CENTER;
			parms_.leftMargin = 10;
			parms_.rightMargin = 10;
			parms_.bottomMargin = 15;
			parms_.topMargin = 10;
			pointView.setLayoutParams(parms_);
			pointViewArr.add(pointView);
			// point_linear.addView(pointView);
		}
		setCurPointBackGround(0);
	}

	
	
	// private void setTonch(ImageView imageView) {
	// imageView.setOnTouchListener(new OnTouchListener() {
	//
	// @Override
	// public boolean onTouch(View v, MotionEvent event) {
	// switch (event.getAction()) {
	// case MotionEvent.ACTION_DOWN:
	// // 手指摁下，停止轮播任务
	// timer.cancel();
	// break;
	// case MotionEvent.ACTION_UP:
	// timer.schedule(task, 2000, 2000);//
	// break;
	// case MotionEvent.ACTION_CANCEL:
	// timer.schedule(task, 2000, 2000);//
	// break;
	// case MotionEvent.ACTION_MOVE:
	// timer.cancel();
	// break;
	// default:
	// break;
	// }
	// return true;
	// }
	// });
	// }

}
