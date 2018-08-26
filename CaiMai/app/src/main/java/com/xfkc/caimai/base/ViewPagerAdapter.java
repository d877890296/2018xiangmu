package com.xfkc.caimai.base;

import java.util.List;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/***
 * 
 * view
 * 
 * @author Lyy
 * 
 */
public class ViewPagerAdapter extends PagerAdapter {
	List<View> viewadata;

	public List<View> getViewadata() {
		return viewadata;
	}

	public void setViewadata(List<View> viewadata) {
		this.viewadata = viewadata;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {

		return viewadata.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == (object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = viewadata.get(position);
		
		container.addView(view);
		return view;

	}

	@Override
	public void setPrimaryItem(View container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		try {
			container.removeView(viewadata.get(position));

		} catch (IndexOutOfBoundsException e) {
		}

	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

}
