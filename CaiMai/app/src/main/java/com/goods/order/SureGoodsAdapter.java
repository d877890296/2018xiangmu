package com.goods.order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.customview.TextViewUtils;
import com.goods.city.GoodsListModel;
import com.goods.details.ShoppingCarModel;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.LzBaseAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SureGoodsAdapter extends LzBaseAdapter {
	private List<GoodsListModel> goodsData;

	public SureGoodsAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		goodsData = new ArrayList<GoodsListModel>();
	}

	public void setGoodsData(List<GoodsListModel> goodsData) {
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

		if (position==0){
			viewHolder.first_view.setVisibility(View.VISIBLE);
		}else{
			viewHolder.first_view.setVisibility(View.GONE);
		}
		GoodsListModel model = goodsData.get(position);
		viewHolder.setViewData(model);
		return convertView;
	}

	public class ViewHolder {
		public ImageView goods_image;
		public TextView first_view;
		public TextView goodsTitle, guig_textView,discroub_textview;
		public TextView goodsNum,totalPrace_textView;

		ImageView goods_remove_btn, goods_add_btn;
		public ViewHolder(View convertView) {
			goods_image = (ImageView) convertView.findViewById(R.id.pic);
			first_view = (TextView) convertView.findViewById(R.id.first_view);
			goodsTitle = (TextView) convertView.findViewById(R.id.goodsTitle);
			guig_textView = (TextView) convertView.findViewById(R.id.guig_textView);
			discroub_textview = (TextView) convertView.findViewById(R.id.discroub_textview);
			totalPrace_textView= (TextView) convertView.findViewById(R.id.totalPrace_textView);

			goodsNum= (TextView) convertView.findViewById(R.id.goodsNum);
			goods_remove_btn = (ImageView) convertView.findViewById(R.id.goods_remove_btn);
			goods_add_btn = (ImageView) convertView.findViewById(R.id.goods_add_btn);
		}

		public void setViewData(GoodsListModel model) {
			/**
			 * 显示图片 参数1：图片url 参数2：显示图片的控件 参数3：显示图片的设置 参数4：监听器
			 */

			if(Tools.IsEmpty(model.pic)){
				goods_image.setImageResource(R.mipmap.error_icon);
			}else{
				app.imageLoader.displayImage(model.pic, goods_image);
			}

			goodsTitle.setText(model.title);
			guig_textView.setText(model.paramData+"");
			discroub_textview.setText(model.sellPoint+"");
			totalPrace_textView.setText(model.itemPrice+"康币");
			goodsNum.setText(model.buyNum+"");

			setSitis(totalPrace_textView);



		}




	public void setSitis(TextView goods_prace){
		String content=goods_prace.getText().toString();
		TextViewUtils.setContentTextSize(goods_prace,content,(int) Tools.dip2px(context,20),0,content.length()-2);
	}



	}

}
