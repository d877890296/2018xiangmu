package com.xfkc.caimai.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/***
 * 关于我们
 */
public class GYMineActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_title_image)
    ImageView toolbarTitleImage;
    @Bind(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @Bind(R.id.toolbar_right_img)
    ImageView toolbarRightImg;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content)
    TextView content;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_gymine;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("关于我们");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);


//        content.setText("1、  “幸福康城”是什么？\n" +
//                "是以“情怀链”为特征的亲友间的社群产品和服务交互平台\n" +
//                "是以“邻里情”为地理位置区域的社区产品和服务交互平台\n" +
//                "幸福康城的终极目标是打造“人尽其才物尽其用”且涵盖“衣食住行康乐”的美好生活生态系统\n" +
//                "2、  幸福康城做什么？\n" +
//                "做您的私家采购买手\n" +
//                "做您的私人管家\n" +
//                "做您的私人健康管家\n" +
//                "3、  幸福康城怎么做？\n" +
//                "帮您规划全家全年的物质采买和服务采买订单\n" +
//                "帮您家对接健康服务资源\n" +
//                "4、  您与幸福康城\n" +
//                "您可以加入我们的幸福公社，做一名“理念相通、同频共振”的社员；\n" +
//                "您可以加入我们的小区团长，做一名“服务邻居、共享快乐”的团长；\n" +
//                "您可以做引荐人，您真心赞赏幸福公社的社群体系，就可以引荐亲朋好友一起来加入幸福公社。社员越多，幸福公社的价值越大，前期加入的每一位社员的获益也越多。");

        content.setText("幸福康城APP简介\n" +
                "\n" +
                "1、  “幸福康城”是什么？\n" +
                "是以“情怀链”为特征的亲友间的社群内的“产品”和“服务”交互平台。因此我们有“亲友团团长”为自己的亲友服务；\n" +
                "是以“邻里情”为地理位置区域的社区社群内的“产品”和“服务”交互平台。因此我们有“社区团团长”为社区邻居服务；\n" +
                "幸福康城的终极目标是通过“采购买手”的品牌定位，打造“人尽其才、物尽其用”且涵盖“衣食住行康乐”各领域的美好生活生态系统。因此我们有深谙各自领域采买规则的“专业采购买手团队”为全体社员服务。\n" +
                "2、  幸福康城做什么？\n" +
                "做您的私家采购买手，帮您全家人省心省力省时间省金钱；\n" +
                "做您的私人管家，帮您全家人张罗日常生活杂碎事情；\n" +
                "做您的私人健康管家，帮您全家人整合名医专家和健康物质资源。\n" +
                "3、  幸福康城怎么做？\n" +
                "帮您规划全家全年的物质采买和服务采买订单\n" +
                "帮您全家对接健康服务资源\n" +
                "4、  您与幸福康城\n" +
                "您可以加入我们的幸福公社，做一名“理念相通、同频共振”的社员；\n" +
                "您可以利用业余时间做引荐人发展亲友团，做亲友团团长服务自己的亲友；\n" +
                "您可以利用业余时间加入我们的社区团长，做一名“服务邻居、共享快乐”的团长；\n" +
                "您可以利用业余时间联络产品和服务的采买资源，做某一领域的专业采购买手；\n" +
                "......\n" +
                "社员越多，幸福公社的力量价值就越大，加入幸福公社的每一位社员获益也会越多。\n" +
                "让我们一起努力，发展壮大我们的社员队伍吧！");

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }

}
