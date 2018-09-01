package com.goods.mineOrderforgoods;

import java.util.ArrayList;
import java.util.List;


import com.dev.customview.MyGridView;
import com.goods.details.ShoppingCarModel;
import com.xfkc.caimai.R;
import com.xfkc.caimai.application.MyApplication;
import com.xfkc.caimai.base.LzBaseAdapter;

import android.content.Context;
import android.drm.ProcessedData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DfkOrderGoodsListAdapter extends RecyclerView.Adapter<DfkOrderGoodsListAdapter.ViewHolder> {
	public Context mContext;
	private LayoutInflater mLayoutInflater;

	private int baseType = 0;

	private List<GoodsDataModel> data;

	private OnListViewClickLinstener onListViewClickLinstener;
	
	public MyApplication myApp;

	public DfkOrderGoodsListAdapter(Context mContext) {
		this.mContext = mContext;
		mLayoutInflater = LayoutInflater.from(this.mContext);
		data = new ArrayList<GoodsDataModel>();
		myApp=MyApplication.getInstance();
	}

	public void setOnListViewClickLinstener(OnListViewClickLinstener onListViewClickLinstener) {
		this.onListViewClickLinstener = onListViewClickLinstener;
	}

	public void setBaseType(int baseType) {
		this.baseType = baseType;
		this.notifyDataSetChanged();
	}

	public void setData(List<GoodsDataModel> data) {
		this.data = data;
		this.notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		// TODO Auto-generated method stub
		final int curtentIndex = getRealPosition(holder);

		GoodsDataModel model = data.get(curtentIndex);
		List<ShoppingCarModel> childData = model.getAddressData();

		holder.listitem_liner.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (onListViewClickLinstener != null) {
					onListViewClickLinstener.itemClick(position, holder, 0);
				}

			}
		});
		// 查看物流
		holder.lookLogistics_textView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (onListViewClickLinstener != null) {
					onListViewClickLinstener.itemClick(position, holder, 1);
				}

			}
		});
		// 确认收货
		holder.suregetGoods_textView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (onListViewClickLinstener != null) {
					onListViewClickLinstener.itemClick(position, holder, 2);
				}

			}
		});

		holder.setData(childData);

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		View v = null;
		v = mLayoutInflater.inflate(R.layout.gd_dfkordergoods_listitem_layout, parent, false);
		ViewHolder viewHolder = new ViewHolder(v);
		return viewHolder;
	}

	public int getRealPosition(RecyclerView.ViewHolder holder) {
		int position = holder.getPosition();

		return position;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public LinearLayout listitem_liner;
		public ImageView goods_image;
		public TextView goods_name, goods_prace, lookLogistics_textView, suregetGoods_textView;

		public LinearLayout singleone_ordergoods_lienr, second_ordergoods_lienr;

		public TextView goodsNumber_textView,totalNumber_textView,totalPrace_textView;
		
		public MyGridView second_myGridView;
		public SecondAdapter secondAdapter;
		List<ShoppingCarModel> childData;

		public ViewHolder(View itemView) {
			super(itemView);

			listitem_liner = (LinearLayout) itemView.findViewById(R.id.listitem_liner);
			goods_image = (ImageView) itemView.findViewById(R.id.goods_image);
			goods_name = (TextView) itemView.findViewById(R.id.goods_name);
			goods_prace = (TextView) itemView.findViewById(R.id.goods_prace);

			lookLogistics_textView = (TextView) itemView.findViewById(R.id.lookLogistics_textView);
			suregetGoods_textView = (TextView) itemView.findViewById(R.id.suregetGoods_textView);

			singleone_ordergoods_lienr = (LinearLayout) itemView.findViewById(R.id.singleone_ordergoods_lienr);
			second_ordergoods_lienr = (LinearLayout) itemView.findViewById(R.id.second_ordergoods_lienr);
			goodsNumber_textView= (TextView) itemView.findViewById(R.id.goodsNumber_textView);
			 totalNumber_textView= (TextView) itemView.findViewById(R.id.totalNumber_textView);
			 totalPrace_textView= (TextView) itemView.findViewById(R.id.totalPrace_textView);
			
			second_myGridView = (MyGridView) itemView.findViewById(R.id.second_myGridView);
			childData = new ArrayList<ShoppingCarModel>();
			secondAdapter = new SecondAdapter(mContext);
			secondAdapter.setChildData(childData);
			second_myGridView.setAdapter(secondAdapter);
		}

		public void setData(List<ShoppingCarModel> childData) {
			if (childData != null && childData.size() == 1) {
				singleone_ordergoods_lienr.setVisibility(View.VISIBLE);
				second_ordergoods_lienr.setVisibility(View.GONE);
				ShoppingCarModel model = childData.get(0);
				
			//	myApp.imageLoader.displayImage(model.getShopGoodsImg(), goods_image);
				goods_name.setText(model.getShopGoodsName());

				goods_prace.setText("￥"+model.getShopGoodsPrace());
				goodsNumber_textView.setText("x"+model.getShopGoodsNumber() );
				totalNumber_textView.setText("共"+model.getShopGoodsNumber()+"件商品  合计" );
				totalPrace_textView.setText("￥"+model.getToalPrace());
				
			} else {
				singleone_ordergoods_lienr.setVisibility(View.GONE);
				second_ordergoods_lienr.setVisibility(View.VISIBLE);

				secondAdapter.setChildData(childData);

			}
		}

		public class SecondAdapter extends LzBaseAdapter {
			public List<ShoppingCarModel> childData;

			public SecondAdapter(Context context) {
				super(context);
				// TODO Auto-generated constructor stub
				childData = new ArrayList<ShoppingCarModel>();
			}

			public void setChildData(List<ShoppingCarModel> childData) {
				this.childData = childData;
				this.notifyDataSetChanged();
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return childData.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return childData.get(position);
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				MySecondHolder mySecondHolder = null;

				if (convertView == null) {
					convertView = inflater.inflate(R.layout.gd_secondgoods_item_layout, null);
					mySecondHolder = new MySecondHolder(convertView);
					convertView.setTag(mySecondHolder);
				} else {
					mySecondHolder = (MySecondHolder) convertView.getTag();
				}
				ShoppingCarModel model=childData.get(position);
				mySecondHolder.setImgData(model);
				return convertView;
			}

			public class MySecondHolder {
				ImageView goods_imageView;

				public MySecondHolder(View convertView) {
					goods_imageView = (ImageView) convertView.findViewById(R.id.goods_imageView);

				}

				public void setImgData(ShoppingCarModel model) {
					//myApp.imageLoader.displayImage(model.getShopGoodsImg(), goods_imageView);
				}
			}

		}

	}

	public interface OnListViewClickLinstener {
		public void itemClick(int position, ViewHolder holder, int state);

	}
}
