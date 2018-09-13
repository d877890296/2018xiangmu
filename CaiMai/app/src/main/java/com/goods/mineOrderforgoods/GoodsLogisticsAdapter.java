package com.goods.mineOrderforgoods;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.LzBaseAdapter;
import com.xfkc.caimai.bean.LogisticsBean;

import java.util.List;

public class GoodsLogisticsAdapter extends LzBaseAdapter {
//	private List<LogisticsModel> data;
	private List<LogisticsBean.DataBean> data;

	public GoodsLogisticsAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setData(List<LogisticsBean.DataBean> data) {
		this.data = data;
		allSize = this.data.size();
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View viewContent, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (viewContent == null) {
			viewContent = inflater.inflate(R.layout.gd_orderforgoodslogistics_listitem_layout, null);
			viewHolder = new ViewHolder(viewContent);
			viewContent.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) viewContent.getTag();
		}
//		LogisticsModel model = data.get(position);
//		if(position==0){
//			viewHolder.father_imgbtn.setImageResource(R.drawable.readed);
//		}else{
//			viewHolder.father_imgbtn.setImageResource(R.drawable.guidepoint_red);
//		}
//		
		LogisticsBean.DataBean model = data.get(position);
		viewHolder.logisticscontent_textView.setText(model.logisticsInfo);
		// 这句很重要，必须加
		// viewHolder.logisticscontent_textView.setMovementMethod(LinkMovementMethod.getInstance());
		viewHolder.logistics_time_textView.setText(model.date);
		setLineHeight(viewHolder.logisticscontent_textView, viewHolder.lineView);
		

		return viewContent;
	}

	public void setLineHeight(final TextView logisticscontent_textView, final View lineView) {
		logisticscontent_textView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				LayoutParams params1 = (LayoutParams) lineView.getLayoutParams();
				params1.height = logisticscontent_textView.getHeight()+40;
				lineView.setLayoutParams(params1);
				// 这个回调会调用多次，获取完行数记得注销监听
				logisticscontent_textView.getViewTreeObserver().removeOnPreDrawListener(this);
				return false;
			}
		});

	}

	public class ViewHolder {
		TextView logisticscontent_textView, logistics_time_textView;
		View lineView;
		ImageView father_imgbtn;

		public ViewHolder(View viewContent) {
			father_imgbtn= (ImageView) viewContent.findViewById(R.id.father_imgbtn);
			logisticscontent_textView = (TextView) viewContent.findViewById(R.id.logisticscontent_textView);
			logistics_time_textView = (TextView) viewContent.findViewById(R.id.logistics_time_textView);
			lineView = (View) viewContent.findViewById(R.id.lineView);
		}

	}

}
