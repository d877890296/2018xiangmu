package com.goods.shoppingcar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.customview.BadgeCountTextView;
import com.dev.customview.TextViewUtils;
import com.goods.city.GoodsCityListAdapter;
import com.goods.city.GoodsListModel;
import com.hyf.tdlibrary.utils.Tools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.recycle.sliding.RecyclerUtils;
import com.recycle.sliding.SlidingButtonView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.application.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarAdapter extends RecyclerView.Adapter<ShoppingCarAdapter.ViewHolder> implements SlidingButtonView.IonSlidingButtonListener {
    Context context;
    MyApplication app;
    private String fileServer;
    LayoutInflater layoutInflater;
    private List<GoodsListModel> data;
    private DisplayImageOptions options;


    public List<String> deleteArray;
    private OnCheckBoxBack onCheckBoxBack;


    private SlidingButtonView mMenu = null;
    private IonSlidingViewClickListener mIonSlidingViewClickListener;


    public ShoppingCarAdapter(Context context) {
        this.context = context;
        app = MyApplication.getInstance();
        layoutInflater = LayoutInflater.from(context);
        fileServer = "";

        deleteArray = new ArrayList<String>();
    }


    // 定义接口
    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);

        void onLongItemClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);
    }

    // 设置监听
    public void setDeleteLister(IonSlidingViewClickListener iDeleteBtnClickListener) {
        if (iDeleteBtnClickListener != null) {
            mIonSlidingViewClickListener = iDeleteBtnClickListener;
        }
    }


    public void setOnCheckBoxBack(OnCheckBoxBack onCheckBoxBack) {
        this.onCheckBoxBack = onCheckBoxBack;
    }

    public List<GoodsListModel> getData() {
        return data;
    }

    public void setData(List<GoodsListModel> data) {
        this.data = data;
        if (this.data == null) {
            this.data = new ArrayList<GoodsListModel>();
        }

        this.notifyDataSetChanged();
    }

    public List<String> getDeleteArray() {
        return deleteArray;
    }

    public void setDeleteArray(List<String> deleteArray) {
        this.deleteArray = deleteArray;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return data.size() > 0 ? data.size() : 0;
    }


    // 创建ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.gd_shoppingcar_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            ((SlidingButtonView) v).setSlidingButtonListener(ShoppingCarAdapter.this);
            return viewHolder;

    }



    // 绑定ViewHolder
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int curtentIndex = getRealPosition(holder);
        GoodsListModel model = data.get(position);
        if (isRepeateData(model.id + "") == false) {
            holder.checkBox.setChecked(false);
        } else {
            holder.checkBox.setChecked(true);
        }
        checkBoxCheck(curtentIndex, holder.checkBox, model.id + "");
        checkCheck(curtentIndex, holder.goods_remove_btn, model.id + "");
        checkCheck(curtentIndex, holder.goods_add_btn, model.id + "");

        String ch2 = model.shopName;
        if (position > 0) {
            String ch1 = data.get(position - 1).shopName;
            if (ch1.equals(ch2)) {
                holder.fristWord.setVisibility(View.GONE);
                holder. viewline.setVisibility(View.GONE);
            } else {
                holder.fristWord.setVisibility(View.VISIBLE);
                holder.fristWord.setText(ch2);
                holder. viewline.setVisibility(View.VISIBLE);
            }
        } else {
            holder.fristWord.setVisibility(View.VISIBLE);
            holder.fristWord.setText(ch2);
            holder. viewline.setVisibility(View.VISIBLE);
        }
        /**
         * 显示图片 参数1：图片url 参数2：显示图片的控件 参数3：显示图片的设置 参数4：监听器
         */
        // app.imageLoader.displayImage(model.getShopGoodsImg(), viewHoder.pic,
        // options,
        // animateFirstListener);
        if (Tools.IsEmpty(model.pic)) {
            holder.pic.setImageResource(R.mipmap.error_icon);
        } else {
            if (model.pic.contains(",")) {
                app.imageLoader.displayImage(model.pic.split(",")[0], holder.pic);
            } else {
                app.imageLoader.displayImage(model.pic, holder.pic);
            }
        }
        holder.testTitle.setText(model.title);
        holder.guig_textView.setText(model.paramData + "");
        holder.discroub_textview.setText(model.sellPoint + "");
        holder.learntime_textview.setText(model.itemPrice + "康币");
        holder.goodsNum.setText("" + model.buyNum);
        setSitis(holder.learntime_textview);

// 设置内容布局的宽为屏幕宽度
        holder.mViewGroup.getLayoutParams().width = RecyclerUtils.getScreenWidth(context);
        /** 触发点击事件 */
        holder.mViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "点击内容");
                // 判断是否有删除菜单打开
                if (menuIsOpen()) {
                    Log.i("TAG", "菜单处于打开状态");
                    // 关闭菜单
                    closeMenu();
                } else {
                    Log.i("TAG", "菜单处于关闭状态");

                    int curtentIndex = getRealPosition(holder);
                    mIonSlidingViewClickListener.onItemClick(v, curtentIndex);
                }
            }
        });
        /** 触发长点击事件 */
        holder.mViewGroup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("TAG", "点击内容");
                // 判断是否有删除菜单打开
                if (menuIsOpen()) {
                    Log.i("TAG", "菜单处于打开状态");
                    // 关闭菜单
                    closeMenu();
                } else {
                    Log.i("TAG", "菜单处于关闭状态");
                    int curtentIndex = getRealPosition(holder);
                    mIonSlidingViewClickListener.onLongItemClick(v, curtentIndex);
                }
                return true;
            }
        });
        /** 触发删除事件 */
        holder.mDeleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int curtentIndex = getRealPosition(holder);
                mIonSlidingViewClickListener.onDeleteBtnCilck(v, curtentIndex);
                // 关闭菜单
                closeMenu();
            }
        });





    }




//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        ViewHoder viewHoder = null;
//        if (convertView == null) {
//            viewHoder = new ViewHoder();
//            convertView = layoutInflater.inflate(R.layout.gd_shoppingcar_item, null);
//            viewHoder.fristWord = (TextView) convertView.findViewById(R.id.fristWord);
//            viewHoder.testTitle = (TextView) convertView.findViewById(R.id.testTitle);
//            viewHoder.discroub_textview = (TextView) convertView.findViewById(R.id.discroub_textview);
//            viewHoder.guig_textView = (TextView) convertView.findViewById(R.id.guig_textView);
//            viewHoder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
//            viewHoder.learntime_textview = (TextView) convertView.findViewById(R.id.learntime_textview);
//            viewHoder.goodsNum = (TextView) convertView.findViewById(R.id.goodsNum);
//            viewHoder.pic = (ImageView) convertView.findViewById(R.id.pic);
//
//            viewHoder.goods_remove_btn = (ImageView) convertView.findViewById(R.id.goods_remove_btn);
//            viewHoder.goods_add_btn = (ImageView) convertView.findViewById(R.id.goods_add_btn);
//
//            convertView.setTag(viewHoder);
//        } else {
//            viewHoder = (ViewHoder) convertView.getTag();
//        }
//        GoodsListModel model = data.get(position);
//        if (isRepeateData(model.id + "") == false) {
//            viewHoder.checkBox.setChecked(false);
//        } else {
//            viewHoder.checkBox.setChecked(true);
//        }
//        checkBoxCheck(position, viewHoder.checkBox, model.id + "");
//        checkCheck(position, viewHoder.goods_remove_btn, model.id + "");
//        checkCheck(position, viewHoder.goods_add_btn, model.id + "");
//
//        String ch2 = model.shopName;
//        if (position > 0) {
//            String ch1 = data.get(position - 1).shopName;
//            if (ch1.equals(ch2)) {
//                viewHoder.fristWord.setVisibility(View.GONE);
//            } else {
//                viewHoder.fristWord.setVisibility(View.VISIBLE);
//                viewHoder.fristWord.setText(ch2);
//            }
//        } else {
//            viewHoder.fristWord.setVisibility(View.VISIBLE);
//            viewHoder.fristWord.setText(ch2);
//        }
//
//        /**
//         * 显示图片 参数1：图片url 参数2：显示图片的控件 参数3：显示图片的设置 参数4：监听器
//         */
//        // app.imageLoader.displayImage(model.getShopGoodsImg(), viewHoder.pic,
//        // options,
//        // animateFirstListener);
//        if (Tools.IsEmpty(model.pic)) {
//            viewHoder.pic.setImageResource(R.mipmap.error_icon);
//        } else {
//            if (model.pic.contains(",")) {
//                app.imageLoader.displayImage(model.pic.split(",")[0], viewHoder.pic);
//            } else {
//                app.imageLoader.displayImage(model.pic, viewHoder.pic);
//            }
//        }
//
//        viewHoder.testTitle.setText(model.title);
//        viewHoder.guig_textView.setText(model.paramData + "");
//        viewHoder.discroub_textview.setText(model.sellPoint + "");
//        viewHoder.learntime_textview.setText(model.itemPrice + "康币");
//        viewHoder.goodsNum.setText("" + model.buyNum);
//        setSitis(viewHoder.learntime_textview);
//
//        return convertView;
//    }

    public void setSitis(TextView goods_prace) {
        String content = goods_prace.getText().toString();
        TextViewUtils.setContentTextSize(goods_prace, content, (int) Tools.dip2px(context, 20), 0, content.length() - 2);
    }

    public void checkBoxCheck(final int position, final CheckBox checkBox, final String id) {
        checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                onCheckBoxBack.backState(position, id, checkBox.isChecked());
            }
        });

    }

    public void checkCheck(final int position, final ImageView checkBox, final String id) {
        checkBox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switch (v.getId()) {
                    case R.id.goods_remove_btn:
                        onCheckBoxBack.backShopState(position, id, false);
                        break;
                    case R.id.goods_add_btn:

                        onCheckBoxBack.backShopState(position, id, true);
                        break;
                    default:
                        break;
                }

            }
        });

    }

    public interface OnCheckBoxBack {
        public void backState(int position, String id, boolean check);

        public void backShopState(int position, String id, boolean check);

    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getPosition();
        return  position ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mDeleteText;
        ViewGroup mViewGroup;
        TextView fristWord, testTitle, discroub_textview, learntime_textview, guig_textView, goodsNum;
        ImageView pic;
        CheckBox checkBox;
        View viewline;

        ImageView goods_remove_btn, goods_add_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            mViewGroup = (ViewGroup) itemView.findViewById(R.id.item_sliding_lay);
            mDeleteText = (TextView) itemView.findViewById(R.id.item_sliding_delete);
            viewline = (View) itemView.findViewById(R.id.viewline);
            fristWord = (TextView) itemView.findViewById(R.id.fristWord);
            testTitle = (TextView) itemView.findViewById(R.id.testTitle);
            discroub_textview = (TextView) itemView.findViewById(R.id.discroub_textview);
            guig_textView = (TextView) itemView.findViewById(R.id.guig_textView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            learntime_textview = (TextView) itemView.findViewById(R.id.learntime_textview);
            goodsNum = (TextView) itemView.findViewById(R.id.goodsNum);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            goods_remove_btn = (ImageView) itemView.findViewById(R.id.goods_remove_btn);
            goods_add_btn = (ImageView) itemView.findViewById(R.id.goods_add_btn);


        }
    }


    /***
     *
     * 是否有重复对象
     *
     * @param ldid
     * @return
     */
    public boolean isRepeateData(String ldid) {
        for (int i = 0; i < deleteArray.size(); i++) {
            String tempLdid = deleteArray.get(i);
            if (tempLdid.equals(ldid)) {
                return true;
            }

        }

        return false;
    }


    @Override
    public void onMenuIsOpen(View view) {
        Log.i("TAG", "onMenuIsOpen   23");
        mMenu = (SlidingButtonView) view;
        Log.i("TAG", "删除菜单打开信息接收");
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        Log.i("TAG", "关闭菜单");
        mMenu.closeMenu();
        mMenu = null;

    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            Log.i("asd", "删除菜单处于打开状态");
            return true;
        }
        Log.i("asd", "删除菜单处于关闭状态");
        return false;
    }

    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        Log.i("TAG", "onDownOrMove    22");
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                Log.i("TAG", "关闭删除菜单111");
                closeMenu();
            }
        }
    }


}
