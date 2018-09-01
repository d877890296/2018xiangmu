//package com.xfkc.caimai.order.adapter;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.RadioButton;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.xfkc.caimai.R;
//import com.xfkc.caimai.bean.EmptyBean;
//import com.xfkc.caimai.order.ChooseAddressActivity;
//
//import java.util.List;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//
///**
// * 1.地址列表 适配器
// * 3.@2018/5/4.
// */
//
//public class AddressListAdapter extends BaseAdapter {
//
//
//    private final Context context;
//    private List<EmptyBean> list;
//    private boolean isShow;
//    private ChooseAddressActivity activity;
//    private AddressGetLoad addressGetLoad;//编辑地址请求数据
//
//    public AddressListAdapter(Context context) {
//        this.context = context;
//        addressGetLoad=new AddressGetLoad();
//    }
//
//    public void setData(List<AddressListBean.AddressBean> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }
//
//    public void setActivity(ChooseAddressActivity chooseAddressActivity){
//        this.activity=chooseAddressActivity;
//        addressGetLoad.setActivity(activity,this);
//    }
//
//    public void showRightButton(boolean isShow) {
//        this.isShow = isShow;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//
//        if (convertView == null) {
//            convertView = View.inflate(context, R.layout.address_list_item, null);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        AddressListBean.AddressBean addressBean = list.get(position);
//
//        viewHolder.getGoodsName.setText("收货人: " + addressBean.userName);
//        viewHolder.getGoodsPhone.setText(addressBean.userPhone);
//        viewHolder.getGoodsAddress.setText("收货地址: " + addressBean.usAddress);
//
//        if (isShow){
//            viewHolder.addressCheckbox.setVisibility(View.VISIBLE);
//        }
//
//        if (addressBean.isMoRen) {
//            viewHolder.moren_Checkbox.setChecked(true);
//            viewHolder.moren_Checkbox.setTextColor(Color.RED);
//            viewHolder.moren_Checkbox.setClickable(false);
//        }else {
//            viewHolder.moren_Checkbox.setChecked(false);
//            viewHolder.moren_Checkbox.setTextColor(Color.BLACK);
//            viewHolder.moren_Checkbox.setClickable(true);
//        }
//
//        //设置默认监听
//        setMoRenClick(viewHolder.moren_Checkbox,position);
//
//        //设置编辑
//        setEditAddress(viewHolder.edit,position);
//        //设置删除
//        setDeleteAddress(viewHolder.delete,position);
//        //设置选中
//        setCheckedAddress(viewHolder.addressCheckbox,position);
//
//
//        return convertView;
//    }
//
//    /*设置选中地址*/
//    private void setCheckedAddress(RadioButton addressCheckbox, final int position) {
//        addressCheckbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addressGetLoad.setCheckAddress(list,position);
//            }
//        });
//    }
//
//    /*设置删除地址*/
//    private void setDeleteAddress(TextView delete, final int position) {
//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addressGetLoad.deleteAddress(list,position);
//                }
//            });
//    }
//
//    /*设置编辑地址*/
//    private void setEditAddress(TextView edit, final int position) {
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addressGetLoad.updateAddress(list,position);
//            }
//        });
//    }
//
//    /*设置默认监听*/
//    private void setMoRenClick(RadioButton moren_checkbox, final int position) {
//        moren_checkbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.updateDefault(position);
//            }
//        });
//    }
//
//    static class ViewHolder {
//        @Bind(R.id.get_goods_name)
//        TextView getGoodsName;
//        @Bind(R.id.get_goods_phone)
//        TextView getGoodsPhone;
//        @Bind(R.id.get_goods_address)
//        TextView getGoodsAddress;
//        @Bind(R.id.address_checkbox)
//        RadioButton addressCheckbox;
//        @Bind(R.id.shouhuo_address)
//        RelativeLayout shouhuoAddress;
//        @Bind(moren_checkbox)
//        RadioButton moren_Checkbox;
//        @Bind(R.id.edit)
//        TextView edit;
//        @Bind(R.id.delete)
//        TextView delete;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
//
//
//
//}
