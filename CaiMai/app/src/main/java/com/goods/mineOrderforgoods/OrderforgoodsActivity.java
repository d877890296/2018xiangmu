package com.goods.mineOrderforgoods;

import java.util.ArrayList;
import java.util.List;


import com.dev.customview.BarLineSetting;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.base.FreamentAdapter;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OrderforgoodsActivity extends BaseActivity {
	private int baseType = 0;
	private RadioGroup radioGroup_ordergoods;
	private RadioButton allOrder_table, radio_1, radio_2, radio_3, radio_4, radio_5;
	private TextView barLine;
	// bar相关配置
	private List<RadioButton> barView;
	private BarLineSetting barLineSetting;
	// 选中的bar字体颜色
	private int curSelectBarTextColor = Color.parseColor("#18B4ED");
	// 默认bar字体颜色
	private int defaultBarTextColor = 0xFF323232;

	private ViewPager orderGoods_pager;
	private FreamentAdapter adapter;
	private ArrayList<Fragment> listData;

	private AllOrderGoodsFragment allOrderGoodsFragment,  allOrderGoodsFragment3,
			allOrderGoodsFragment4, allOrderGoodsFragment5;
	private DfkOrderGoodsFragment dfkOrderGoodsFragment;
    private DfhOrderGoodsFragment dfhOrderGoodsFragment;

	@Override
	protected int getLayoutResource() {
		baseType = Integer.parseInt(getIntent().getStringExtra("baseType"));
		return R.layout.gd_orderforgoodsactivity_layout;
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		viewInit();
		dataInit();
	}



	@Override
	protected void loadData() {

	}


	public void viewInit() {
		back_btn = (ImageButton) findViewById(R.id.back_img_btn);
		topbar_title = (TextView) findViewById(R.id.text_title_content);
		topbar_title.setText("我的订单");
		back_btn.setOnClickListener(onClickListener);

		radioGroup_ordergoods = (RadioGroup) findViewById(R.id.radioGroup_ordergoods);

		allOrder_table = (RadioButton) findViewById(R.id.allOrder_table);
		radio_1 = (RadioButton) findViewById(R.id.radio_1);
		radio_2 = (RadioButton) findViewById(R.id.radio_2);
		radio_3 = (RadioButton) findViewById(R.id.radio_3);
		radio_4 = (RadioButton) findViewById(R.id.radio_4);
		radio_5 = (RadioButton) findViewById(R.id.radio_5);

		barLine = (TextView) findViewById(R.id.barLine);
		orderGoods_pager = (ViewPager) findViewById(R.id.orderGoods_pager);
		orderGoods_pager.setOnPageChangeListener(onPageChangeListener);
		radioGroup_ordergoods.setOnCheckedChangeListener(onCheckedChangeListener);

	}

	/***
	 * 数据的初始化
	 */
	public void dataInit() {
		barView = new ArrayList<RadioButton>();
		// 添加bar
		barView.add(allOrder_table);
		barView.add(radio_1);
		barView.add(radio_2);
		barView.add(radio_3);
		barView.add(radio_4);
		barView.add(radio_5);
		// 设置barline
		barLineSetting = new BarLineSetting(barLine);
		barLineSetting.setCurSelectBarTextColor(curSelectBarTextColor);
		barLineSetting.setDefaultBarTextColor(defaultBarTextColor);
		// 设置barView
		barLineSetting.setBarView(barView, app.phoneResolution_w);
		// 设置参考的view视图
		barLineSetting.setReferenceView(allOrder_table);

		listData = new ArrayList<Fragment>();
		adapter = new FreamentAdapter(getSupportFragmentManager());
		// 所有商品
		allOrderGoodsFragment = new AllOrderGoodsFragment();
		// 待付款
		dfkOrderGoodsFragment = new DfkOrderGoodsFragment();
		// 待发货商品
		dfhOrderGoodsFragment = new DfhOrderGoodsFragment();
		allOrderGoodsFragment3 = new AllOrderGoodsFragment();
		allOrderGoodsFragment4 = new AllOrderGoodsFragment();
		allOrderGoodsFragment5 = new AllOrderGoodsFragment();

		listData.add(allOrderGoodsFragment);
		listData.add(dfkOrderGoodsFragment);
		listData.add(dfhOrderGoodsFragment);
		listData.add(allOrderGoodsFragment3);
		listData.add(allOrderGoodsFragment4);
		listData.add(allOrderGoodsFragment5);
		adapter.setListData(listData);
		orderGoods_pager.setAdapter(adapter);

		if (baseType == 1) {
			orderGoods_pager.setCurrentItem(1);
		} else if (baseType == 2) {
			orderGoods_pager.setCurrentItem(2);
		} else if (baseType == 3) {
			orderGoods_pager.setCurrentItem(3);
		} else if (baseType == 4) {
			orderGoods_pager.setCurrentItem(4);
		} else if (baseType == 5) {
			orderGoods_pager.setCurrentItem(5);
		}

	}

	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			barLineSetting.Amination(position);

			dealtBar(position);
		}
	};

	public void dealtBar(int position) {
		for (int i = 0; i < listData.size(); i++) {
			if (i == position) {
				barView.get(i).setChecked(true);
			} else {
				barView.get(i).setChecked(false);
			}

		}
	}

	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.allOrder_table:

				orderGoods_pager.setCurrentItem(0);
				break;
			case R.id.radio_1:

				orderGoods_pager.setCurrentItem(1);
				break;
			case R.id.radio_2:

				orderGoods_pager.setCurrentItem(2);
				break;
			case R.id.radio_3:

				orderGoods_pager.setCurrentItem(3);
				break;
			case R.id.radio_4:

				orderGoods_pager.setCurrentItem(4);
				break;
			case R.id.radio_5:
				orderGoods_pager.setCurrentItem(5);
				break;
			}
		}
	};

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.back_img_btn:
				backHistory(-1, true, false, extraMap);
				break;

			default:
				break;
			}

		}

	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			backHistory(-1, true, false, extraMap);
			return true;
		}
		return false;
	}
}
