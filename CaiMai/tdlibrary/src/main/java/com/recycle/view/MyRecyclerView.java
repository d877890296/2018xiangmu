package com.recycle.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/***
 * 我的牛逼视图 之RecyclerView的辅助类
 * 
 * @author liyunayou
 *
 * @category 2016年12月16日星期五--西格玛公寓B1005华瑞思为
 *
 */
public class MyRecyclerView {
	Context mContext;
	public RecyclerView mRecyclerView;
	private DividerItemDecoration dividerItemDecoration;
	private DividerGridItemDecoration dividerGridItemDecoration;

	public MyRecyclerView(Context mContext, RecyclerView mRecyclerView) {
		this.mContext = mContext;
		this.mRecyclerView = mRecyclerView;

	}

	/***
	 * 列表视图
	 * 
	 * 
	 * @param isAddDivider（是否添加底部线）
	 */
	public void setListView(boolean isAddDivider) {
		// 设备管理器
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		// 设置item动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		dividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);
		if (isAddDivider) {
			// 添加分割线
			mRecyclerView.addItemDecoration(dividerItemDecoration);
		} else {
		//	mRecyclerView.removeItemDecoration(dividerItemDecoration);
		}
	}

	/***
	 * 
	 * 网格视图
	 * 
	 * @param isAddDivider（是否添加底部线）
	 * @param spanCount(列数)
	 */
	public void setGridView(boolean isAddDivider, int spanCount) {
		GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
		// 设备管理器
		mRecyclerView.setLayoutManager(gridLayoutManager);
		// 设置item动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		dividerGridItemDecoration = new DividerGridItemDecoration(mContext);
		if (isAddDivider) {
			// 添加分割线
			mRecyclerView.addItemDecoration(dividerGridItemDecoration);
		} else {
		
			if (dividerItemDecoration != null) {
				mRecyclerView.removeItemDecoration(dividerItemDecoration);
			}
		}
	}

	/***
	 * 横线滚动流水布局---需要注意宽度
	 * 
	 * @param spanCount(列数)
	 * @param VERTICAL_HORIZONTAL(横向和纵向)
	 */
	public void setFlowView(int spanCount, int VERTICAL_HORIZONTAL) {
		if (VERTICAL_HORIZONTAL == 0) {
			// 设备管理器
			mRecyclerView
					.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
		} else {
			// 设备管理器
			mRecyclerView
					.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL));
		}
		// 设置item动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
	}
}
