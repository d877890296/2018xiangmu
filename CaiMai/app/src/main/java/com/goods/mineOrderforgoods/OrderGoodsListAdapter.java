package com.goods.mineOrderforgoods;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xfkc.caimai.R;

public class OrderGoodsListAdapter extends RecyclerView.Adapter<OrderGoodsListAdapter.ViewHolder> {
	public Context mContext;
	private LayoutInflater mLayoutInflater;

	private int baseType = 0;

	private List<GoodsDataModel> data;

	private OnListViewClickLinstener onListViewClickLinstener;

	public OrderGoodsListAdapter(Context mContext) {
		this.mContext = mContext;
		mLayoutInflater = LayoutInflater.from(this.mContext);
		data = new ArrayList<GoodsDataModel>();

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
		if (baseType == 2) {
			holder.lookLogistics_textView.setVisibility(View.GONE);
			holder.suregetGoods_textView.setText("提醒发货");
		} else {
			holder.lookLogistics_textView.setVisibility(View.VISIBLE);
			holder.lookLogistics_textView.setText("查看物流");
			holder.suregetGoods_textView.setText("确认发货");
		}

		
		
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

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		View v = null;
		v = mLayoutInflater.inflate(R.layout.gd_ordergoods_listitem_layout, parent, false);
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

		public ViewHolder(View itemView) {
			super(itemView);

			listitem_liner = (LinearLayout) itemView.findViewById(R.id.listitem_liner);
			goods_image = (ImageView) itemView.findViewById(R.id.goods_image);
			goods_name = (TextView) itemView.findViewById(R.id.goods_name);
			goods_prace = (TextView) itemView.findViewById(R.id.goods_prace);

			lookLogistics_textView = (TextView) itemView.findViewById(R.id.lookLogistics_textView);
			suregetGoods_textView = (TextView) itemView.findViewById(R.id.suregetGoods_textView);
		}
	}

	public interface OnListViewClickLinstener {
		public void itemClick(int position, ViewHolder holder, int state);

	}
}
