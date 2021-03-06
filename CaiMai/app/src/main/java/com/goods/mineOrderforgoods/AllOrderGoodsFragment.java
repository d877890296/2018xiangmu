package com.goods.mineOrderforgoods;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goods.city.GoodsValue;
import com.goods.details.GoodsDetailsActivity;
import com.goods.mineOrderforgoods.AllOrderGoodsListAdapter.OnListViewClickLinstener;
import com.goods.netrequst.NetRequstAjaxCallBack;
import com.goods.netrequst.PostRequst;
import com.goods.sortlsitview.AjaxShopModel;
import com.google.gson.Gson;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.recycle.view.MyRecyclerView;
import com.refushView.RefreshLayout;
import com.refushView.holder.DefineBAGRefreshWithLoadView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.bean.AddOrderBean;
import com.xfkc.caimai.bean.GoodsKey;
import com.xfkc.caimai.bean.UserInfoBean;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.dialog.ShowPassWordDialog;
import com.xfkc.caimai.home.comment.ToCommentActivity;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;
import com.xfkc.caimai.pay.SettingPayPasswordActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

import static com.goods.netrequst.PostRequst.UPSUCCESS;

/****
 *
 *
 * @author lyy
 *
 */
public class AllOrderGoodsFragment extends BaseFragment implements RefreshLayout.RefreshLayoutDelegate {

    private RecyclerView recyclerView;
    private RefreshLayout mBGARefreshLayout;
    /**
     * 设置刷新和加载
     */
    private DefineBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;
    private MyRecyclerView myRecyclerView;
    private AllOrderGoodsListAdapter goodsCityListAdapter;
    ArrayList<OrderModel> shopsList;
    private int status;
    private PostRequst postRequst;
    private NetRequstAjaxCallBack ajaxCallBack;
    private int requstStyle = 0;
    private ShowPassWordDialog showPassWordDialog;

    public void baseDataInit() {
        // TODO Auto-generated method stub
        if (shopsList == null) {
            goodsCityListAdapter = new AllOrderGoodsListAdapter(mContext, handler);
            shopsList = new ArrayList<OrderModel>();
        }
        postRequst = new PostRequst(handler);
        ajaxCallBack = new NetRequstAjaxCallBack(mContext);
        ajaxCallBack.setOnNetRequstAjaxCallBack(onNetRequstAjaxCallBack);
        showPassWordDialog = new ShowPassWordDialog(mContext);
    }

    @Override
    protected int getLayoutResource() {
        baseDataInit();
        return R.layout.gd_order_fragment_layout;
    }

    @Override
    protected void initData() {
        viewInit();
    }

    public void viewInit() {

        progress_liner = (LinearLayout) rootView.findViewById(R.id.progress_liner);
        nodataview_textview = (TextView) rootView.findViewById(R.id.nodataview_textview);

        mBGARefreshLayout = (RefreshLayout) rootView.findViewById(R.id.define_sliding_bga);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.ordergoodslist_recycler);

        if (myRecyclerView == null) {
            goodsCityListAdapter.setOnListViewClickLinstener(onListViewClickLinstener);
            goodsCityListAdapter.setBaseType(2);
            // 设置刷新和加载监听
            mBGARefreshLayout.setDelegate(this);
            setBgaRefreshLayout();
            myRecyclerView = new MyRecyclerView(mContext, recyclerView);
            myRecyclerView.setListView(true);
            recyclerView.setAdapter(goodsCityListAdapter);

            requstGetMyOrder(this.status);

        } else {
            goodsCityListAdapter.setBaseType(2);
            goodsCityListAdapter.setData(shopsList);
            recyclerView.setAdapter(goodsCityListAdapter);
        }


    }

    public void setStatus(int status) {
        this.status = status;

    }

    /**
     * 设置 BGARefreshLayout刷新和加载
     */
    public void setBgaRefreshLayout() {
        mDefineBAGRefreshWithLoadView = new DefineBAGRefreshWithLoadView(mContext, true, true);
        // 设置刷新样式
        mBGARefreshLayout.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("自定义加载更多");
        // mBGARefreshLayout.beginRefreshing();
        // onBGARefreshLayoutBeginRefreshing(mBGARefreshLayout);

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        // TODO Auto-generated method stub

        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("自定义加载更多");
        mDefineBAGRefreshWithLoadView.showLoadingMoreImg();
        msgHandler.sendEmptyMessage(LIST_REFUSH_WHAT);
        mBGARefreshLayout.endRefreshing();

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        // TODO Auto-generated method stub
        if (isMoreData == false) {
            mDefineBAGRefreshWithLoadView.updateLoadingMoreText("没有更多数据");
            mDefineBAGRefreshWithLoadView.hideLoadingMoreImg();
            msgHandler.sendEmptyMessage(LIST_LOADMORE_WHAT);
            return true;
        } else {
            msgHandler.sendEmptyMessage(LIST_LOADMORE_WHAT);
        }
        mBGARefreshLayout.endLoadingMore();
        return true;
    }


    private Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LIST_REFUSH_WHAT:// 刷新
                    // reSetData(true);
                    break;
                case LIST_LOADMORE_WHAT:// 加载更多
                    // reSetData(false);
                    break;
                case 2:
                    mBGARefreshLayout.endLoadingMore();
                    break;
            }
        }
    };
    private OnListViewClickLinstener onListViewClickLinstener = new OnListViewClickLinstener() {

        @Override
        public void itemClick(int position, AllOrderGoodsListAdapter.ViewHolder holder, int state) {
            // TODO Auto-generated method stub
            switch (state) {
                case -1://取消订单
                    //	skip_classView(OrderforgoodsInfoActivity.class, extraMap, false, false);
                    break;
                case 0://取消订单
                    showMbProgress("订单取消中");
                    requstStyle = 1;
                    String orderNum = shopsList.get(position).orderNum;
                    requstUpdateOrderStatus(orderNum, 6);
                    //	skip_classView(OrderforgoodsInfoActivity.class, extraMap, false, false);
                    break;
                case 1://查看物流
                    String orderNum_ = shopsList.get(position).orderNum;
                    extraMap.put("orderNum", orderNum_);
                    skip_classView(OrderforgoodsLogisticsActivity.class, extraMap, false, -1);
                    break;
                case 2://确认收货
                    showMbProgress("订单确认中");
                    requstStyle = 2;
                    String orderNum1 = shopsList.get(position).orderNum;
                    requstUpdateOrderStatus(orderNum1, 4);
                    //MyToast.showMyToast(context, "确认收货", -1);
                    break;
                case 3:   //  立即支付
                    getData(position);
                    break;
                case 4:   // 申请退款
                    ShowPassWordDialog showPassWordDialog = new ShowPassWordDialog();
                    showPassWordDialog.showPhoneDialog(getActivity());
                    break;
                case 5:   // 评价
                    skip_classView(ToCommentActivity.class, extraMap, false, true);
                    break;
                case 6:   // 查看详情
                    break;

                default:
                    break;
            }

        }

    };

//	private OnClickListener onClickListener = new OnClickListener() {
//
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			switch (v.getId()) {
//			case R.id.mineInfo_relative:
//				skip_classView(MineInfoActivity.class, extraMap, false, false);
//				break;
//			default:
//				break;
//			}
//
//		}

    //};
    ///============================数据处理=============================

    public void requstGetMyOrder(int status) {
        requstStyle = 0;
        String userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        GoodsKey goodsKey = new GoodsKey();
        goodsKey.token = userToken;
        goodsKey.pageNum = 0 + "";
        goodsKey.pageSize = 20 + "";
        goodsKey.status = status;
        if (status == 0) {
            postRequst.getMyOrder(handler, goodsKey, true);
        } else {
            postRequst.getMyOrder(handler, goodsKey, false);
        }
    }

    /***
     * 更新订单
     * @param orderNum
     * @param status
     */
    public void requstUpdateOrderStatus(String orderNum, int status) {
        requstStyle = 1;
        String userToken = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        GoodsKey goodsKey = new GoodsKey();
        goodsKey.token = userToken;
        goodsKey.orderNum = orderNum + "";
        goodsKey.status = status;
        postRequst.updateOrderStatus(handler, goodsKey);

    }

    private NetRequstAjaxCallBack.OnNetRequstAjaxCallBack onNetRequstAjaxCallBack = new NetRequstAjaxCallBack.OnNetRequstAjaxCallBack() {

        @Override
        public void MsgCallBack(boolean isSuccess, String errorMsg, Object object) {
            // TODO Auto-generated method stub
            dissMbProgress();
            if (isSuccess) {
                nodataview_textview.setVisibility(View.GONE);
                shopsList = (ArrayList) object;
                goodsCityListAdapter.setData(shopsList);
            } else {
                nodataview_textview.setVisibility(View.VISIBLE);
            }


        }

    };
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dissMbProgress();
            switch (msg.what) {
                case UPSUCCESS://数据获取成功
                    if (msg.arg1 == 1) {//成功
                        String jsonObj = msg.obj.toString();
                        Log.e("-------", jsonObj);
                        if (Tools.IsEmpty(jsonObj)) {
                            android.widget.Toast.makeText(mContext,
                                    "数据错误", Toast.LENGTH_LONG).show();
                            if (requstStyle == 0) {
                                nodataview_textview.setVisibility(View.VISIBLE);
                                nodataview_textview.setText("购物车空空如也，赶快去商城添加吧！");
                            }
                            return;
                        }
                        if (requstStyle == 0) {
                            app.jsonHttp.getJsonObj(jsonObj, AjaxShopModel.class,
                                    ajaxCallBack.getMyOrder);
                            requstStyle = 0;
                        } else if (requstStyle == 1) {
                            try {
                                dissMbProgress();
                                //   {"message":"操作类型错误","retCode":0,"data":null}
                                JSONObject OBJ = new JSONObject(jsonObj);
                                int retCode = OBJ.getInt("retCode");
                                if (retCode == 0) {
                                    Toast.makeText(mContext, "订单取消失败", Toast.LENGTH_LONG).show();
                                } else {
                                    requstGetMyOrder(status);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            requstStyle = 0;
                        } else if (requstStyle == 2) {
                            try {
                                dissMbProgress();
                                //   {"message":"操作类型错误","retCode":0,"data":null}
                                JSONObject OBJ = new JSONObject(jsonObj);
                                int retCode = OBJ.getInt("retCode");
                                if (retCode == 0) {
                                    Toast.makeText(mContext, "订单确认失败", Toast.LENGTH_LONG).show();
                                } else {
                                    requstGetMyOrder(status);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            requstStyle = 0;
                        }

                    } else {//失败
                        dissMbProgress();
                        nodataview_textview.setVisibility(View.VISIBLE);
                        nodataview_textview.setText("购物车空空如也，赶快去商城添加吧！");
                    }

                    break;
                case 90:
                    int fatherPosition = msg.arg1;
                    int position = msg.arg2;
                    GoodsValue.getInstance().setGoodsListModel(shopsList.get(fatherPosition).itemOrderDetailList.get(position));
                    skip_classView(GoodsDetailsActivity.class, extraMap, false, -1);

                    break;
                case 1:
                    String code = (String) msg.obj;
                    payOrder(code);
                    break;
            }
        }
    };

    /*支付订单*/
    public void payOrder(String pwd) {
        HttpParams params = new HttpParams();
        params.put("orderNum", message);
        params.put("paymentWay", PAY_WAY);
        params.put("orderPrice", reall_price);
        params.put("payPwd", pwd);
        params.put("token", token);

        OkGo.post(Constant.BASE_URL + "/api/order/actualPayment")
                .tag(this)//url请求地址
                .params(params)
                .isMultipart(true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("----------", s);
                        Gson gson = new Gson();
                        AddOrderBean addOrderBean = gson.fromJson(s, AddOrderBean.class);
                        if (addOrderBean.retCode == 1) {
//                            extraMap.put("type", "1");
//                            skip_classView(PaySuccessActivity.class, extraMap, false, 101);
                            requstGetMyOrder(0);
                        } else {
                            if (Tools.IsEmpty(pwdword)) {
                                skip_classView(SettingPayPasswordActivity.class, extraMap, false, true);
                            } else {
                                ToastUtil.showToast(addOrderBean.message);
                            }
                        }
                        dissMbProgress();
                    }

                });
    }

    private String pwdword, token, message;
    private int PAY_WAY = 1;
    private double reall_price = 0;

    /*获取个人信息*/
    private void getData(final int position) {
        message = shopsList.get(position).orderNum;
        PAY_WAY = shopsList.get(position).paymentWay;
        reall_price = shopsList.get(position).freight + shopsList.get(position).price;
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        PayFactory.getPayService()
                .findUserDetByPhone(token)
                .compose(RxHelper.<UserInfoBean>io_main())
                .subscribe(new ProgressSubscriber<UserInfoBean>(mContext) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        double kbAmount = userInfoBean.data.kbAmount;
                        pwdword = userInfoBean.data.payPwd;
                        showPassWordDialog.orderShowTimeDialog(mContext, (shopsList.get(position).freight + shopsList.get(position).price), kbAmount + "", handler);

                    }
                });
    }
}
