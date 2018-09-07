package com.goods.order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goods.details.ShoppingCarModel;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.LzBaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class SureGoodsAdapter extends LzBaseAdapter {
	private List<ShoppingCarModel> goodsData;

	public SureGoodsAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		goodsData = new ArrayList<ShoppingCarModel>();
	}

	public void setGoodsData(List<ShoppingCarModel> goodsData) {
		this.goodsData = goodsData;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodsData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.gd_suregoods_list_item, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
//		ShoppingCarModel model = goodsData.get(position);
//		viewHolder.setViewData(model);
		return convertView;
	}

	public class ViewHolder {
		public ImageView goods_image;
		public TextView goods_name, goods_prace;
		public TextView goodsNumber_textView,totalNumber_textView,totalPrace_textView;

		public ViewHolder(View convertView) {
			goods_image = (ImageView) convertView.findViewById(R.id.goods_image);
			goods_name = (TextView) convertView.findViewById(R.id.goods_name);
			goods_prace = (TextView) convertView.findViewById(R.id.goods_prace);
			goodsNumber_textView= (TextView) convertView.findViewById(R.id.goodsNumber_textView);
			totalNumber_textView= (TextView) convertView.findViewById(R.id.totalNumber_textView);
			totalPrace_textView= (TextView) convertView.findViewById(R.id.totalPrace_textView);
		}

		public void setViewData(ShoppingCarModel model) {
			/**
			 * 显示图片 参数1：图片url 参数2：显示图片的控件 参数3：显示图片的设置 参数4：监听器
			 */
			//app.imageLoader.displayImage(model.getShopGoodsImg(), goods_image);
			goods_name.setText(model.getShopGoodsName());
			goods_prace.setText("￥"+model.getShopGoodsPrace());
			goodsNumber_textView.setText("x"+model.getShopGoodsNumber());
			totalNumber_textView.setText("共"+model.getShopGoodsNumber()+"件商品  共计：");
			
			totalPrace_textView.setText(model.getToalPrace()+"");
		}
	}

}
