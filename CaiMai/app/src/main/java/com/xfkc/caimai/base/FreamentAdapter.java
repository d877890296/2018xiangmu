package com.xfkc.caimai.base;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/***
 * 
 * Fragment
 * 
 * @author Lyy
 * 
 */
public class FreamentAdapter extends FragmentPagerAdapter {
	List<Fragment> listData;
	public FreamentAdapter(FragmentManager fm) {
		super(fm);
	}
	public void setListData(List<Fragment> listData) {
		this.listData = listData;
		this.notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int arg0) {
		return listData.get(arg0);
	}

	@Override
	public int getCount() {

		return listData.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

}
