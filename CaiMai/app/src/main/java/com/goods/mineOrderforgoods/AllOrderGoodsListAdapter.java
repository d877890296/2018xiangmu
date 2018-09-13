package com.goods.mineOrderforgoods;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.customview.MyListView;
import com.dev.customview.TextViewUtils;
import com.goods.city.GoodsListModel;
import com.goods.netrequst.Logger;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.LzBaseAdapter;
import com.xfkc.caimai.util.Utils;

import java.util.ArrayList;

public class AllOrderGoodsListAdapter extends RecyclerView.Adapter<AllOrderGoodsListAdapter.ViewHolder> {
    public Context mContext;
    private LayoutInflater mLayoutInflater;

    private int baseType = 0;
    private Handler handler;
    ArrayList<OrderModel> shopsList;

    private OnListViewClickLinstener onListViewClickLinstener;

    public AllOrderGoodsListAdapter(Context mContext, Handler handler) {
        this.mContext = mContext;
        this.handler = handler;
        mLayoutInflater = LayoutInflater.from(this.mContext);
        shopsList = new ArrayList<OrderModel>();

    }

    public void setOnListViewClickLinstener(OnListViewClickLinstener onListViewClickLinstener) {
        this.onListViewClickLinstener = onListViewClickLinstener;
    }

    public void setBaseType(int baseType) {
        this.baseType = baseType;
        this.notifyDataSetChanged();
    }

    public void setData(ArrayList<OrderModel> shopsList) {
        this.shopsList = shopsList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return shopsList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // TODO Auto-generated method stub
        final int curtentIndex = getRealPosition(holder);
        OrderModel model = shopsList.get(curtentIndex);
        if (baseType == 2) {
            holder.lookLogistics_textView.setVisibility(View.GONE);
            holder.suregetGoods_textView.setText("提醒发货");
        } else {
            holder.lookLogistics_textView.setVisibility(View.VISIBLE);
            holder.lookLogistics_textView.setText("查看物流");
            holder.suregetGoods_textView.setText("确认发货");
        }

        holder.order_no.setText("订单编号:"+model.orderNum);
        holder.order_time.setText("订单时间:"+ Utils.timeStamp2Date(model.createTime, "yyyy-MM-dd"));

//
//
//		holder.listitem_liner.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if (onListViewClickLinstener != null) {
//					onListViewClickLinstener.itemClick(position, holder, 0);
//				}
//
//			}
//		});

        // 取消订单
        holder.cannelOrder_textView.setOnClickListener(new OnClickListener() {
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
        // 立即支付
        holder.pay_btGoods_textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (onListViewClickLinstener != null) {
                    onListViewClickLinstener.itemClick(position, holder, 3);
                }

            }
        });
        // 申请退款
        holder.tuikuan_textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (onListViewClickLinstener != null) {
                    onListViewClickLinstener.itemClick(position, holder, 4);
                }

            }
        });
        // 评价
        holder.pj_textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (onListViewClickLinstener != null) {
                    onListViewClickLinstener.itemClick(position, holder, 5);
                }

            }
        });

        // 查看详情
        holder.lookd_btGoods_textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (onListViewClickLinstener != null) {
                    onListViewClickLinstener.itemClick(position, holder, 6);
                }

            }
        });


        holder.setData(position, model);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO Auto-generated method stub
        View v = null;
        v = mLayoutInflater.inflate(R.layout.goods_allorder_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getPosition();

        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public MyListView order_goodslist;
        public OrderChildAdapter orderChildAdapter;
        public TextView goods_status, goods_allnum, goods_allprice, cannelOrder_textView, lookLogistics_textView, suregetGoods_textView, pay_btGoods_textView;
        public TextView tuikuan_textView, pj_textView, lookd_btGoods_textView,order_no,order_time;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            order_goodslist = (MyListView) itemView.findViewById(R.id.order_goodslist);
            orderChildAdapter = new OrderChildAdapter(mContext);

            order_no = (TextView) itemView.findViewById(R.id.order_no);
            order_time = (TextView) itemView.findViewById(R.id.order_time);
            goods_status = (TextView) itemView.findViewById(R.id.goods_status);
            goods_allnum = (TextView) itemView.findViewById(R.id.goods_allnum);
            goods_allprice = (TextView) itemView.findViewById(R.id.goods_allprice);
            cannelOrder_textView = (TextView) itemView.findViewById(R.id.cannelOrder_textView);

            tuikuan_textView = (TextView) itemView.findViewById(R.id.tuikuan_textView);
            pj_textView = (TextView) itemView.findViewById(R.id.pj_textView);

            pay_btGoods_textView = (TextView) itemView.findViewById(R.id.pay_btGoods_textView);
            lookLogistics_textView = (TextView) itemView.findViewById(R.id.lookLogistics_textView);
            suregetGoods_textView = (TextView) itemView.findViewById(R.id.suregetGoods_textView);

            tuikuan_textView = (TextView) itemView.findViewById(R.id.tuikuan_textView);
            pj_textView = (TextView) itemView.findViewById(R.id.pj_textView);
            lookd_btGoods_textView = (TextView) itemView.findViewById(R.id.lookd_btGoods_textView);

        }

        public void setData(int position, OrderModel model) {
            orderChildAdapter.setItemOrderDetailList(position, model.itemOrderDetailList);
            order_goodslist.setAdapter(orderChildAdapter);

            goods_status.setText(backStatusContent(model.status));
            goods_allnum.setText("共计" + model.itemOrderDetailList.size() + "件商品");
            goods_allprice.setText(model.price + "康币");
            setSitis(goods_allprice);
            //status` int  1待支付 2待发货 3配送中 4待评价 5已评价 6已取消
            if (model.status == 1) {
                cannelOrder_textView.setVisibility(View.VISIBLE);
                lookLogistics_textView.setVisibility(View.GONE);
                suregetGoods_textView.setVisibility(View.GONE);
                pay_btGoods_textView.setVisibility(View.VISIBLE);

                tuikuan_textView.setVisibility(View.GONE);
                pj_textView.setVisibility(View.GONE);
                lookd_btGoods_textView.setVisibility(View.GONE);
                Drawable drawableLeft = mContext.getResources().getDrawable(R.mipmap.dfk_icon);
                goods_status.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                goods_status.setCompoundDrawablePadding(5);
            } else if (model.status == 2) {
                cannelOrder_textView.setVisibility(View.VISIBLE);
                lookLogistics_textView.setVisibility(View.GONE);
                suregetGoods_textView.setVisibility(View.GONE);
                pay_btGoods_textView.setVisibility(View.GONE);

                tuikuan_textView.setVisibility(View.GONE);
                pj_textView.setVisibility(View.GONE);
                lookd_btGoods_textView.setVisibility(View.GONE);
                Drawable drawableLeft = mContext.getResources().getDrawable(R.mipmap.dfh_icon);
                goods_status.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                goods_status.setCompoundDrawablePadding(5);
            } else if (model.status == 3) {
                cannelOrder_textView.setVisibility(View.GONE);
                lookLogistics_textView.setVisibility(View.VISIBLE);
                suregetGoods_textView.setText("确认收货");
                suregetGoods_textView.setVisibility(View.VISIBLE);
                pay_btGoods_textView.setVisibility(View.GONE);
                tuikuan_textView.setVisibility(View.GONE);
                pj_textView.setVisibility(View.GONE);
                lookd_btGoods_textView.setVisibility(View.GONE);
                lookd_btGoods_textView.setVisibility(View.GONE);
                Drawable drawableLeft = mContext.getResources().getDrawable(R.mipmap.psz_icon);
                goods_status.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                goods_status.setCompoundDrawablePadding(5);
            } else if (model.status == 4 || model.status == 5) {
                cannelOrder_textView.setVisibility(View.GONE);
                lookLogistics_textView.setVisibility(View.GONE);
                suregetGoods_textView.setVisibility(View.GONE);
                pay_btGoods_textView.setVisibility(View.GONE);
                tuikuan_textView.setVisibility(View.VISIBLE);
                pj_textView.setVisibility(View.VISIBLE);
                lookd_btGoods_textView.setVisibility(View.GONE);
                Drawable drawableLeft = mContext.getResources().getDrawable(R.mipmap.complete_icon);
                goods_status.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                goods_status.setCompoundDrawablePadding(5);
            } else if (model.status == 6) {
                cannelOrder_textView.setVisibility(View.GONE);
                lookLogistics_textView.setVisibility(View.GONE);
                suregetGoods_textView.setVisibility(View.GONE);
                pay_btGoods_textView.setVisibility(View.GONE);
                tuikuan_textView.setVisibility(View.GONE);
                pj_textView.setVisibility(View.GONE);
                lookd_btGoods_textView.setVisibility(View.VISIBLE);
                Drawable drawableLeft = mContext.getResources().getDrawable(R.mipmap.cancle_icon);
                goods_status.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                goods_status.setCompoundDrawablePadding(5);
            }
        }
    }

    public void setSitis(TextView goods_prace) {
        String content = goods_prace.getText().toString();
        TextViewUtils.setContentTextSize(goods_prace, content, (int) Tools.dip2px(mContext, 20), 0, content.length() - 2);
    }

    public class OrderChildAdapter extends LzBaseAdapter {
        public ArrayList<GoodsListModel> itemOrderDetailList;//学习列表信息
        public int fatherPosition;

        public OrderChildAdapter(Context context) {
            super(context);

        }

        public void setItemOrderDetailList(int fatherPosition, ArrayList<GoodsListModel> itemOrderDetailList) {
            this.fatherPosition = fatherPosition;
            this.itemOrderDetailList = itemOrderDetailList;
            if (this.itemOrderDetailList == null) {
                this.itemOrderDetailList = new ArrayList<GoodsListModel>();
            }
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            Logger.e("-------------", itemOrderDetailList.size() + "-");
            return itemOrderDetailList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemOrderDetailList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyOrderHolder myOrderHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.goods_orderchilds_item_layout, null);
                myOrderHolder = new MyOrderHolder(convertView);
                convertView.setTag(myOrderHolder);
            } else {
                myOrderHolder = (MyOrderHolder) convertView.getTag();
            }
            GoodsListModel gModel = itemOrderDetailList.get(position);
            myOrderHolder.setModel(gModel);

            onClickOption(myOrderHolder.itemliner, fatherPosition, position);

            return convertView;
        }

        public void onClickOption(LinearLayout itemliner, final int fatherPosition, final int position) {
            itemliner.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Message msg = new Message();
                    msg.arg1 = fatherPosition;
                    msg.arg2 = position;
                    msg.what = 90;
                    handler.sendMessage(msg);


                }
            });
        }

        public class MyOrderHolder {
            public LinearLayout itemliner;

            public ImageView goods_image;
            public TextView goods_name, goods_des, goods_prace, goods_num;

            public MyOrderHolder(View convertView) {
                itemliner = (LinearLayout) convertView.findViewById(R.id.itemliner);
                goods_image = (ImageView) convertView.findViewById(R.id.goods_image);
                goods_name = (TextView) convertView.findViewById(R.id.goods_name);
                goods_des = (TextView) convertView.findViewById(R.id.goods_des);
                goods_prace = (TextView) convertView.findViewById(R.id.goods_prace);
                goods_num = (TextView) convertView.findViewById(R.id.goods_num);
            }

            public void setModel(GoodsListModel model) {
                if (Tools.IsEmpty(model.pic)) {
                    goods_image.setImageResource(R.mipmap.error_icon);
                } else {
                    app.imageLoader.displayImage(model.pic, goods_image);
                }
                goods_name.setText(model.title + "");
                goods_des.setText(model.sellPoint + "");
                goods_prace.setText(model.itemPrice + "康币");
                goods_num.setText("x" + model.buyNum + "");
                setSitis(goods_prace);
            }

            public void setSitis(TextView goods_prace) {
                String content = goods_prace.getText().toString();
                TextViewUtils.setContentTextSize(goods_prace, content, (int) Tools.dip2px(context, 20), 0, content.length() - 2);
            }
        }

    }


    /***
     *  status` int  1待支付 2待发货 3配送中 4待评价 5已评价 6已取消
     * @param status
     * @return
     */
    public String backStatusContent(int status) {
        switch (status) {
            case 1:
                return "待支付";
            case 2:
                return "待发货";
            case 3:
                return "配送中";
            case 4:
                return "待评价";
            case 5:
                return "已评价";
            case 6:
                return "已取消";
        }
        return "";
    }

    public interface OnListViewClickLinstener {
        public void itemClick(int position, ViewHolder holder, int state);

    }
}
