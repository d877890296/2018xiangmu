package com.goods.details;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xfkc.caimai.R;

public class GoodsDetailsAdapter extends BaseAdapter {
	private int[] type = { 0, 1, 2 };
public Context context;
	public LayoutInflater inflater;
	public GoodsDetailsAdapter(Context context) {
		this.context=context;
		inflater=	LayoutInflater.from(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return type[position];
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		

		switch (position) {
		case 0:
			convertView = inflater.inflate(R.layout.gd_goodsother_fristitem, null);
			break;
		case 1:
			convertView = inflater.inflate(R.layout.gd_goodsother_seconditem, null);
			break;
		case 2:
			convertView = inflater.inflate(R.layout.gd_goodsother_threeitem, null);
			break;
		default:
			break;
		}

		return convertView;
	}

}
