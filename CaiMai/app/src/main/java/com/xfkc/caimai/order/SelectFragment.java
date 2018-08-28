package com.xfkc.caimai.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.dev.customview.CustomListView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.bean.EmptyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 1.优品展示商品列表
 * 2.@dongjinxu
 * 3.@2018/4/26.
 */

public class SelectFragment extends BaseFragment {

    @Bind(R.id.shop_list)
    CustomListView shopList;

    private String catId;

//    private ShopListAdapter shopListAdapter;
    private List<EmptyBean> list =new ArrayList<>();
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        catId = getArguments().getString("catId");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.shop_selectfragment_layout;
    }

    @Override
    protected void initData() {
//        shopListAdapter = new ShopListAdapter(mContext);
//        shopListAdapter.setData(list);
//        shopList.setAdapter(shopListAdapter);
        //获取网络数据
//        getLoadData();

        //设置列表监听
        setListClick();
        //设置刷新
        setRefres();
        //设置加载更多
        setLoadMore();
    }
    /*设置列表监听*/
    private void setListClick() {
        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position -1;
//                startActivity(new Intent(mContext,ShopContentActivity.class).putExtra("mpmodityId",list.get(position).mpmodityId));
            }
        });
    }

    /*获取网络请求*/
//    private void getLoadData() {
//        GetAllId getAllId=new GetAllId();
//        getAllId.setCategoryId(catId);
//        getAllId.setStart(start);
//        getAllId.setPageSize(pageSize);
//        PayFactory.getPayService()
//                .selectModityList(getAllId)
//                .compose(RxHelper.<SelectModityListBean>io_main())
//                .subscribe(new ProgressSubscriber<SelectModityListBean>(mContext) {
//                    @Override
//                    public void onNext(SelectModityListBean selectModityListBean) {
//                        if (selectModityListBean.shareInfo != null && selectModityListBean.shareInfo.size()!=0){
//                            if (selectModityListBean.shareInfo.size()<1){
//                                shopList.setShowMore(false);
//                            }
//                            list.addAll(selectModityListBean.shareInfo);
//                        }else {
//                            shopList.setShowMore(false);
//                        }
//                        stopMore();
//                    }
//                });
//    }

    /*设置刷新*/
    private void setRefres() {

        shopList.setOnRefreshListener(new CustomListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                start = 0;
                pageSize = 20;
                list.clear();
//                getLoadData();
            }
        });
    }

    /*加载更多*/
    private void setLoadMore() {
        shopList.setAutoLoadMore(true);
        shopList.setOnLoadListener(new CustomListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                start ++ ;
                pageSize = 20;
//                getLoadData();
            }
        });
    }

    /*停止刷新  加载更多*/
    public void stopMore(){
        shopList.onRefreshComplete();
        shopList.onLoadMoreComplete();
    }


}
