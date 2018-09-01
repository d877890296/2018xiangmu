package com.goods.shoppingcar;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.dev.customview.CustomListView;
import com.dev.customview.MyToast;


import com.goods.details.GoodsDetailsActivity;
import com.goods.details.ShoppingCarModel;
import com.goods.shoppingcar.ShoppingCarAdapter.OnCheckBoxBack;


import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.order.OrderActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/****
 * 购物车
 * 
 * @author
 *
 */
public class ShoppingCarActivity extends BaseActivity {
	private String goodsStoreId;
	private CustomListView mine_myListView;
	private ShoppingCarAdapter tradeIntegralAdapter;
	private List<ShoppingCarModel> data;

	public List<String> deleteArray;

	private CheckBox seletall_btn;
	private boolean isCheckAll;
	private TextView allPrace_textView, count_textView;
	private double allPrace = 0;

	private boolean isEdit;
	private LinearLayout showcountinfo_liner;


	public void deaultDataInit() {
		// TODO Auto-generated method stub

		SureCarValue.getInstance().init();
		data = new ArrayList<ShoppingCarModel>();
		deleteArray = new ArrayList<String>();


		String className = "fsfs";
		String goodsStoreId ="12344";
		String goodsId = "344";
		String image1 = "222";
		String title ="一户";
		String goodsPrice = "20.9";
		String count =  "2";
		String totalPrace = "24.90";
		String storePrice =  "24.90";
		String gcid ="345";
		String id ="2344";
		String goodsInfo = "00009ji";

		ShoppingCarModel model = new ShoppingCarModel();
		model.setShopName(className);
		model.setShopId(goodsStoreId);
		model.setShopGoodsName(title);
		model.setId(id);
		model.setShopGoodsId(goodsId);
		model.setGcId(gcid);
		model.setShopGoodsImg( image1);
		model.setShopGoodsInfo(goodsInfo);
		model.setShopGoodsNumber(count);
		model.setShopGoodsPrace(goodsPrice + "");
		model.setToalPrace(totalPrace + "");
		model.setShopGoodsOriginalPrace(storePrice + "");
		model.setShopGoodsStyle("上衣");

		data.add(model);

	}


	@Override
	protected int getLayoutResource() {
		return R.layout.gd_shoppingcar_activity_layout;
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {

		deaultDataInit();

		viewInit();
		dataInit();
	}
	@Override
	protected void loadData() {


	}
	public void viewInit() {

		back_btn = (ImageButton) findViewById(R.id.back_btn);
		back_btn.setVisibility(View.VISIBLE);
		TextView 	topbar_img_title = (TextView) findViewById(R.id.topbar_img_title);
		topbar_img_title.setVisibility(View.GONE);
		topbar_title = (TextView) findViewById(R.id.topbar_title);
		topbar_title.setVisibility(View.VISIBLE);
		topbar_title.setText("购物车");
		other_btn= (ImageButton) findViewById(R.id.other_btn);
		other_btn.setVisibility(View.GONE);
		other_morbtn = (Button) findViewById(R.id.other_morbtn);
		other_morbtn.setVisibility(View.VISIBLE);
		other_morbtn.setText("编辑");
		back_btn.setOnClickListener(onClickListener);
		other_morbtn.setOnClickListener(onClickListener);

		nodataview_textview = (TextView) findViewById(R.id.nodataview_textview);

		mine_myListView = (CustomListView) findViewById(R.id.mine_myListView);

		mine_myListView.setOnItemClickListener(onItemClickListener);

		showcountinfo_liner = (LinearLayout) findViewById(R.id.showcountinfo_liner);
		allPrace_textView = (TextView) findViewById(R.id.allPrace_textView);
		count_textView = (TextView) findViewById(R.id.count_textView);

		seletall_btn = (CheckBox) findViewById(R.id.checkBox);
		seletall_btn.setOnClickListener(onClickListener);
		count_textView.setOnClickListener(onClickListener);
	}

	private boolean isRemove;

	public void dataInit() {

		// data = ShoppingCar.get().getCarData();
		// if (data != null && data.size() != 0) {
		// tradeIntegralAdapter = new ShoppingCarAdapter(mContext);
		// tradeIntegralAdapter.setOnCheckBoxBack(onCheckBoxBack);
		// tradeIntegralAdapter.setData(data);
		// mine_myListView.setAdapter(tradeIntegralAdapter);
		// } else {
		tradeIntegralAdapter = new ShoppingCarAdapter(mContext);
		tradeIntegralAdapter.setOnCheckBoxBack(onCheckBoxBack);
		tradeIntegralAdapter.setData(data);
		mine_myListView.setAdapter(tradeIntegralAdapter);
		// }

		isRemove = false;
		showMbProgress("数据加载中...");
//		app.netRequst.shoppingCartsDtasRequst(acc.getUserId(), goodsStoreId, "0", "20",
//				netRequstAjaxCallBack.shopingCarDataCallback);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				dissMbProgress();
			}
		},2000);
	}




//	private OnNetRequstAjaxCallBack onNetRequstAjaxCallBack = new OnNetRequstAjaxCallBack() {
//
//		@Override
//		public void MsgCallBack(boolean isSuccess, String errorMsg, Object object) {
//			// TODO Auto-generated method stub
//			dissMbProgress();
//			if (isSuccess) {
//				if (errorMsg.equals("9")) {
//					isRemove = false;
//					showMbProgress("数据加载中...");
//					app.netRequst.shoppingCartsDtasRequst(acc.getUserId(), goodsStoreId, "0", "20",
//							netRequstAjaxCallBack.shopingCarDataCallback);
//				} else if (errorMsg.equals("10")) {
//					isRemove = false;
//					showMbProgress("数据加载中...");
//					app.netRequst.shoppingCartsDtasRequst(acc.getUserId(), goodsStoreId, "0", "20",
//							netRequstAjaxCallBack.shopingCarDataCallback);
//				} else {
//
//					if (isRemove == false) {
//						if (isClear) {
//							data.clear();
//							isClear = false;
//						}
//						JSONArray list = (JSONArray) object;
//						for (int i = 0; i < list.length(); i++) {
//							try {
//								JSONObject obj = list.getJSONObject(i);
//								String className = obj.getString("className");
//								String goodsStoreId = obj.getString("scId");
//								String goodsId = obj.getString("goodsId");
//								String image1 = obj.getString("goodsMainPhoto");
//								String title = obj.getString("goodName");
//								String goodsPrice = obj.getDouble("goodsPrice") + "";
//								String count = obj.getInt("count") + "";
//								String totalPrace = obj.getDouble("price") + "";
//								String storePrice = obj.getDouble("storePrice") + "";
//								String gcid = obj.getString("gcId");
//								String id = obj.getString("id");
//								String goodsInfo = obj.getString("specInfo");
//
//								ShoppingCarModel model = new ShoppingCarModel();
//								model.setShopName(className);
//								model.setShopId(goodsStoreId);
//								model.setShopGoodsName(title);
//								model.setId(id);
//								model.setShopGoodsId(goodsId);
//								model.setGcId(gcid);
//								model.setShopGoodsImg( image1);
//								model.setShopGoodsInfo(goodsInfo);
//								model.setShopGoodsNumber(count);
//								model.setShopGoodsPrace(goodsPrice + "");
//								model.setToalPrace(totalPrace + "");
//								model.setShopGoodsOriginalPrace(storePrice + "");
//								model.setShopGoodsStyle("上衣");
//
//								data.add(model);
//
//							} catch (JSONException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//
//						data = SortClass.SortHaveData(data);
//						if (data.size() == 0) {
//							nodataview_textview.setVisibility(View.VISIBLE);
//							nodataview_textview.setText("购物车空空如也，赶快去商城添加吧！");
//						} else {
//							nodataview_textview.setVisibility(View.GONE);
//							tradeIntegralAdapter.setData(data);
//						}
//					} else {
//						isRemove = false;
//						showMbProgress("数据加载中...");
//						app.netRequst.shoppingCartsDtasRequst(acc.getUserId(), goodsStoreId, "0", "20",
//								netRequstAjaxCallBack.shopingCarDataCallback);
//					}
//				}
//			} else {
//				nodataview_textview.setVisibility(View.VISIBLE);
//				nodataview_textview.setText("购物车空空如也，赶快去商城添加吧！");
//				// MyToast.showMyToast(mContext, errorMsg, -1);
//			}
//
//		}
//	};

	private boolean isClear;

	private OnCheckBoxBack onCheckBoxBack = new OnCheckBoxBack() {

		@Override
		public void backState(int index, String position, boolean check) {
			// TODO Auto-generated method stub

			SureCarValue.getInstance().setAddressData(data.get(index));

			if (isRepeateData(position) == false) {
				deleteArray.add(position);
			} else {
				int id_ = isRepeate(position);
				if (id_ != -1) {
					deleteArray.remove(id_);
				}
			}
			tradeIntegralAdapter.setDeleteArray(deleteArray);
			// 选择情况
			statisticsChoose();
		}

		@Override
		public void backShopState(int position, String id, boolean check) {
			// TODO Auto-generated method stub
			isClear = true;
			if (check) {// 添加
				// 添加购物车
//				app.netRequst.shoppingCartSaveRequst(acc.getUserId(), goodsStoreId, id, "1", "", "",
//						netRequstAjaxCallBack.shopingCarAddCallback);
			} else {// 减少
				showMbProgress("数据加载中...");
//				app.netRequst.shoppingCartreduceRequst(acc.getUserId(), goodsStoreId, id,
//						netRequstAjaxCallBack.shopingCarRemoveCallback);
			}

		}

	};

	/***
	 * 
	 * 是否有重复对象
	 * 
	 * @param ldid
	 * @return
	 */
	public boolean isRepeateData(String ldid) {
		for (String tempLdid : deleteArray) {
			if (tempLdid.equals(ldid)) {
				return true;
			}
		}
		return false;
	}

	/***
	 * 
	 * 是否有重复对象
	 * 
	 * @param ldid
	 * @return
	 */
	public int isRepeate(String ldid) {
		int count = deleteArray.size();
		for (int i = 0; i < count; i++) {
			String tempLdid = deleteArray.get(i);
			if (tempLdid.equals(ldid)) {
				return i;
			}
		}
		return -1;
	}

	/***
	 * 统计选择情况
	 * 
	 */
	public void statisticsChoose() {
		if (deleteArray == null) {
			seletall_btn.setText("全选");
		}
		int count = deleteArray.size();
		if (count >= 0 && count < data.size()) {
			seletall_btn.setText("全选");
			seletall_btn.setChecked(false);
			isCheckAll = false;
		} else if (count == data.size()) {
			seletall_btn.setText("全选");
			seletall_btn.setChecked(true);
			isCheckAll = true;
		}
		countParce();
	}

	public void countParce() {
		allPrace = 0;
		String ids = "";
		for (int i = 0; i < deleteArray.size(); i++) {
			String id = deleteArray.get(i);
			ids += id + ",";
			for (int j = 0; j < data.size(); j++) {
				String tempId = data.get(j).getId();
				if (id.equals(tempId)) {
					double prace = Double.parseDouble(data.get(j).getToalPrace());
					allPrace += prace;
				}

			}
		}

		if (isEdit == false) {
			allPrace_textView.setText("￥" + allPrace);
			count_textView.setText("结算(" + deleteArray.size() + ")");
		} else {
			isRemove = true;

			showcountinfo_liner.setVisibility(View.INVISIBLE);
			count_textView.setText("删除(" + deleteArray.size() + ")");

		}

	}

	public String[] getIds() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < deleteArray.size(); i++) {
			String id = deleteArray.get(i) + ",";
			sb.append(id);

		}
		String str = sb.toString();
		String ids = str.substring(0, str.length() - 1);
		return ids.split(",");

	}

	public String getIdString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < deleteArray.size(); i++) {
			String id = deleteArray.get(i) + ",";
			sb.append(id);

		}
		String str = sb.toString();
		String ids = str.substring(0, str.length() - 1);
		return ids;

	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub

//			ShoppingCarModel model = data.get(arg2);
//
//			extraMap.put("goodsImg", model.getShopGoodsImg());
//			extraMap.put("goodsStoreId", goodsStoreId);
//			extraMap.put("goodsId", model.getShopGoodsId());
//			extraMap.put("count", model.getShopGoodsNumber());
//			extraMap.put("goodsProperty", model.getShopGoodsInfo());
//			extraMap.put("goodsName", model.getShopGoodsName());
//			extraMap.put("price", model.getShopGoodsPrace());

			
		
			
			
			skip_classView(GoodsDetailsActivity.class, extraMap, false);
		}
	};

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.back_btn:
				backHistory(-1, true, false, extraMap);
				break;
			case R.id.other_morbtn:
				if (isEdit == false) {
					isEdit = true;
					other_morbtn.setText("完成");
					showcountinfo_liner.setVisibility(View.INVISIBLE);
					count_textView.setText("删除(" + deleteArray.size() + ")");
				} else {
					isEdit = false;
					other_morbtn.setText("编辑");
					count_textView.setText("结算(" + deleteArray.size() + ")");
					showcountinfo_liner.setVisibility(View.VISIBLE);
				}

				break;
			case R.id.checkBox:
				if (isCheckAll == false) {
					deleteArray.clear();
					for (int i = 0; i < data.size(); i++) {
						deleteArray.add(data.get(i).getId());
					}
					SureCarValue.getInstance().setAllData(data);
				} else {
					// SureCarValue.getInstance().removeAllData();
					deleteArray.clear();
				}

				tradeIntegralAdapter.setDeleteArray(deleteArray);
				// 选择情况
				statisticsChoose();
				break;
			case R.id.count_textView:
				if (isEdit) {
					isClear = true;
					showMbProgress("移除中...");
//					app.netRequst.shoppingCartsRemoveRequst(acc.getUserId(), goodsStoreId, getIdString(),
//							netRequstAjaxCallBack.shopingCarAddCallback);
					String[] ids = getIds();
					for (int i = 0; i < ids.length; i++) {
						String id = ids[i];
						for (int j = 0; j < deleteArray.size(); j++) {
							String tempid = deleteArray.get(j);
							if (id.equals(tempid)) {
								deleteArray.remove(j);
							}
						}

					}
					countParce();
					allPrace_textView.setText("￥" + allPrace);
					seletall_btn.setText("全选");
					seletall_btn.setChecked(false);
					isCheckAll = false;
					tradeIntegralAdapter.setDeleteArray(deleteArray);
				} else {
					if (allPrace == 0) {
						MyToast.showMyToast(mContext, "请选择商品", -1);
					} else {
						extraMap.put("allPrace", allPrace);
					skip_classView(OrderActivity.class, extraMap, false);
					}

				}

				isEdit = false;
				other_morbtn.setText("编辑");
				count_textView.setText("结算(" + deleteArray.size() + ")");
				showcountinfo_liner.setVisibility(View.VISIBLE);
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

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SureCarValue.getInstance().removeAllData();
		SureCarValue.getInstance().reSet();
	}
}
