package com.goods.mineOrderforgoods;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.dev.customview.MyToast;

import com.goods.details.ShoppingCarModel;
import com.goods.mineOrderforgoods.DfhOrderGoodsListAdapter.OnListViewClickLinstener;
import com.goods.mineOrderforgoods.DfhOrderGoodsListAdapter.ViewHolder;


import com.recycle.view.MyRecyclerView;
import com.refushView.RefreshLayout;
import com.refushView.holder.DefineBAGRefreshWithLoadView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/****
 * 
 * 
 * @author lyy
 *
 */
public class DfhOrderGoodsFragment extends BaseFragment implements RefreshLayout.RefreshLayoutDelegate {

	private RecyclerView recyclerView;
	private RefreshLayout mBGARefreshLayout;
	/** 设置刷新和加载 */
	private DefineBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;
	private MyRecyclerView myRecyclerView;
	private DfhOrderGoodsListAdapter dfhOrderGoodsListAdapter;
	private List<GoodsDataModel> data;



	public void baseDataInit() {
		// TODO Auto-generated method stub
		if(data==null) {
			dfhOrderGoodsListAdapter = new DfhOrderGoodsListAdapter(mContext);
			data = new ArrayList<GoodsDataModel>();
		}

	}




	@Override
	protected int getLayoutResource() {
		baseDataInit();
		return R.layout.gd_order_fragment_layout;
	}


	@Override
	protected void initData() {

		viewInit();
	}



	public void viewInit() {

		progress_liner = (LinearLayout) rootView.findViewById(R.id.progress_liner);
		nodataview_textview = (TextView) rootView.findViewById(R.id.nodataview_textview);

		mBGARefreshLayout = (RefreshLayout) rootView.findViewById(R.id.define_sliding_bga);
		recyclerView = (RecyclerView) rootView.findViewById(R.id.ordergoodslist_recycler);

		if (myRecyclerView == null) {
			dfhOrderGoodsListAdapter.setOnListViewClickLinstener(OnListViewClickLinstener);
			dfhOrderGoodsListAdapter.setBaseType(2);
			// 设置刷新和加载监听
			mBGARefreshLayout.setDelegate(this);
			setBgaRefreshLayout();
			myRecyclerView = new MyRecyclerView(mContext, recyclerView);
			myRecyclerView.setListView(true);
			recyclerView.setAdapter(dfhOrderGoodsListAdapter);
//			app.netRequst.shoppingCartsOrderDatasRequst(userId, acc.getStoreId(), "20", "1", "100",
//					netRequstAjaxCallBack.shopingCarDataCallback);

			requstNetData();
		}else{
			dfhOrderGoodsListAdapter.setBaseType(2);
			dfhOrderGoodsListAdapter.setData(data);
			recyclerView.setAdapter(dfhOrderGoodsListAdapter);

		}

	

	}

	/**
	 * 设置 BGARefreshLayout刷新和加载
	 */
	public void setBgaRefreshLayout() {
		mDefineBAGRefreshWithLoadView = new DefineBAGRefreshWithLoadView(mContext, true, true);
		// 设置刷新样式
		mBGARefreshLayout.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
		mDefineBAGRefreshWithLoadView.updateLoadingMoreText("自定义加载更多");
		// mBGARefreshLayout.beginRefreshing();
		// onBGARefreshLayoutBeginRefreshing(mBGARefreshLayout);

	}

	@Override
	public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
		// TODO Auto-generated method stub

		mDefineBAGRefreshWithLoadView.updateLoadingMoreText("自定义加载更多");
		mDefineBAGRefreshWithLoadView.showLoadingMoreImg();
		msgHandler.sendEmptyMessage(LIST_REFUSH_WHAT);
		mBGARefreshLayout.endRefreshing();

	}

	@Override
	public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
		// TODO Auto-generated method stub
		if (isMoreData == false) {
			mDefineBAGRefreshWithLoadView.updateLoadingMoreText("没有更多数据");
			mDefineBAGRefreshWithLoadView.hideLoadingMoreImg();
			msgHandler.sendEmptyMessage(LIST_LOADMORE_WHAT);
			return true;
		} else {
			msgHandler.sendEmptyMessage(LIST_LOADMORE_WHAT);
		}
		mBGARefreshLayout.endLoadingMore();
		return true;
	}
	public void requstNetData(){


		GoodsDataModel modelT = new GoodsDataModel();
		String orderId ="orderId";
		modelT.setOrderId(orderId);

		List<ShoppingCarModel> addressData = new ArrayList<ShoppingCarModel>();

		for (int j = 0; j < 4; j++) {
			String className ="className";
			String goodsStoreId = "scId";
			String goodsId = "goodsId";
			String image1 = "goodsMainPhoto";
			String title = "goodName";
			String goodsPrice = "goodsPrice" + "";
			String count ="count"+ "";
			String totalPrace ="price" + "";
			String storePrice = "storePrice" + "";
			String gcid = "gcId";
			String id ="id";
			String goodsInfo = "specInfo";

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
			addressData.add(model);
		}

		modelT.setAddressData(addressData);

		data.add(modelT);
		dfhOrderGoodsListAdapter.setData(data);


	}
//	private OnNetRequstAjaxCallBack onNetRequstAjaxCallBack = new OnNetRequstAjaxCallBack() {
//
//		@Override
//		public void MsgCallBack(boolean isSuccess, String errorMsg, Object object) {
//			// TODO Auto-generated method stub
//
//			JSONArray list = (JSONArray) object;
//			if(list!=null&&list.length()!=0){
//			for (int i = 0; i < list.length(); i++) {
//				try {
//					JSONObject objT = list.getJSONObject(i);
//
//					GoodsDataModel modelT = new GoodsDataModel();
//					String orderId = objT.getString("orderId");
//					modelT.setOrderId(orderId);
//
//					JSONArray goodscart = objT.getJSONArray("goodscart");
//					List<ShoppingCarModel> addressData = new ArrayList<ShoppingCarModel>();
//					for (int j = 0; j < goodscart.length(); j++) {
//
//						JSONObject obj = goodscart.getJSONObject(j);
//						String className = obj.getString("className");
//						String goodsStoreId = obj.getString("scId");
//						String goodsId = obj.getString("goodsId");
//						String image1 = obj.getString("goodsMainPhoto");
//						String title = obj.getString("goodName");
//						String goodsPrice = obj.getDouble("goodsPrice") + "";
//						String count = obj.getInt("count") + "";
//						String totalPrace = obj.getDouble("price") + "";
//						String storePrice = obj.getDouble("storePrice") + "";
//						String gcid = obj.getString("gcId");
//						String id = obj.getString("id");
//						String goodsInfo = obj.getString("specInfo");
//
//						ShoppingCarModel model = new ShoppingCarModel();
//						model.setShopName(className);
//						model.setShopId(goodsStoreId);
//						model.setShopGoodsName(title);
//						model.setId(id);
//						model.setShopGoodsId(goodsId);
//						model.setGcId(gcid);
//						model.setShopGoodsImg(LmsConfig.FileService + image1);
//						model.setShopGoodsInfo(goodsInfo);
//						model.setShopGoodsNumber(count);
//						model.setShopGoodsPrace(goodsPrice + "");
//						model.setToalPrace(totalPrace + "");
//						model.setShopGoodsOriginalPrace(storePrice + "");
//						model.setShopGoodsStyle("上衣");
//						addressData.add(model);
//					}
//
//					modelT.setAddressData(addressData);
//
//					data.add(modelT);
//
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			nodataview_textview.setVisibility(View.GONE);
//			dfhOrderGoodsListAdapter.setData(data);
//			}else{
//				nodataview_textview.setVisibility(View.VISIBLE);
//				nodataview_textview.setText("暂无数据");
//			}
//
//		}
//
//	};

	private Handler msgHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case LIST_REFUSH_WHAT:// 刷新
				// reSetData(true);
				break;
			case LIST_LOADMORE_WHAT:// 加载更多
				// reSetData(false);
				break;
			case 2:
				mBGARefreshLayout.endLoadingMore();
				break;
			}
		}
	};
	private OnListViewClickLinstener OnListViewClickLinstener = new OnListViewClickLinstener() {

		@Override
		public void itemClick(int position, ViewHolder holder, int state) {
			// TODO Auto-generated method stub
			switch (state) {
			case 0:
			//	skip_classView(OrderforgoodsInfoActivity.class, extraMap, false, false);
				break;
			case 1:

			//	skip_classView(OrderforgoodsLogisticsActivity.class, extraMap, false, -1);
				break;
			case 2:
			//	MyToast.showMyToast(context, "确认收货", -1);
				break;

			default:
				break;
			}

		}

	};



}
