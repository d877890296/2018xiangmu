package com.goods.city;

import java.util.ArrayList;
import java.util.List;


import com.goods.model.GoodsModel;
import com.xfkc.caimai.R;
import com.xfkc.caimai.application.MyApplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GoodsCityListAdapter extends RecyclerView.Adapter<GoodsCityListAdapter.ViewHolder> {
	public Context mContext;
	private LayoutInflater mLayoutInflater;

	private int baseType = 0;
	private MyApplication app;

	private OnListViewClickLinstener onListViewClickLinstener;
	private List<GoodsModel> goodsData;

	public GoodsCityListAdapter(Context mContext) {
		this.mContext = mContext;
		mLayoutInflater = LayoutInflater.from(this.mContext);
		goodsData = new ArrayList<GoodsModel>();
		app=MyApplication.getInstance();

	}
	

	public void setGoodsData(List<GoodsModel> goodsData) {
		this.goodsData = goodsData;
		this.notifyDataSetChanged();
	}


	public void setOnListViewClickLinstener(OnListViewClickLinstener onListViewClickLinstener) {
		this.onListViewClickLinstener = onListViewClickLinstener;
	}

	public void setBaseType(int baseType) {
		this.baseType = baseType;
		this.notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return goodsData.size();
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		// TODO Auto-generated method stub
		final int curtentIndex = getRealPosition(holder);
		holder.listitem_liner.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (onListViewClickLinstener != null) {
					onListViewClickLinstener.itemClick(position, holder);
				}

			}
		});

		GoodsModel model = goodsData.get(curtentIndex);
		holder.goods_name.setText(model.getGoodsName());
		holder.goods_prace.setText("￥" + model.getGoodsPrice());
		//app.imageLoader.displayImage(model.getGoodsMainPhotoId(), holder.goods_image,app.options);

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		View v = null;
		if (baseType == 0) {
			v = mLayoutInflater.inflate(R.layout.gd_goodscity_listgriditem_layout, parent, false);
		} else {
			v = mLayoutInflater.inflate(R.layout.gd_goodscity_listitem_layout, parent, false);
		}
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
		public TextView goods_name, goods_prace;

		public ViewHolder(View itemView) {
			super(itemView);

			listitem_liner = (LinearLayout) itemView.findViewById(R.id.listitem_liner);
			goods_image = (ImageView) itemView.findViewById(R.id.goods_image);
			goods_name = (TextView) itemView.findViewById(R.id.goods_name);
			goods_prace = (TextView) itemView.findViewById(R.id.goods_prace);
		}
	}

	public interface OnListViewClickLinstener {
		public void itemClick(int position, ViewHolder holder);
	}
}
