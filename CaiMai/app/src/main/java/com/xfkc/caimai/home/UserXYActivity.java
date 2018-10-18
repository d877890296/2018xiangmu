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

/*用户协议*/
public class UserXYActivity extends BaseActivity {


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
        return R.layout.activity_user_xy;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("用户协议");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
//        content.setText("在您加入幸福康城社员体系之前，请您仔细阅读本用户协议，在您充分理解并支持本协议的前提下，您再慎重决策是否加入。\n" +
//                "1、加入幸福公社，必须要有引荐人；\n" +
//                "2、加入幸福公社，需缴纳入社年费。年费365元（最初10000名只需3.65元，每天1分钱）；年费不退也不附带任何产品；您加入我们，与我们同频共振采取集体采买行动，你节省的费用比起缴纳的年费，一定物超所值；\n" +
//                "3、加入幸福公社，享用产品和服务，需要提前预订。没有提前预订，我们不保障能为您及时提供产品和服务；我们强烈建议您提前把您下周、下月、下一季度或全年的采买需求提交给我们匹配给您的“私家采购买手”，他（她）会把您的需求及时反馈到总部的采买信息中心，帮您提前准备；\n" +
//                "4、您预订的产品，提货请您自己最近的社区提货点自提。走几分钟路，有利健康。如果您确实需要配送到家，那配送费用需您自己承担；如果您单次订单满300元，配送到家的费用无需您自己承担；\n" +
//                "5、我们鼓励社员对家庭的采买工作从长计议，预存康币，提前给供应商付款预订产品。对事业合伙人首次预存康币的激励措施如下：\n" +
//                "事业合伙人类别\t首次预存康币数量\t享受的利益\n" +
//                "A类事业合伙人\t5000元，限额100名\t获得500康币奖励（仅限首次充值）\n" +
//                "B类事业合伙人\t3000元，限额300名\t获得18康币奖励（仅限首次充值）\n" +
//                "C类事业合伙人\t1000元，限额1000名\t获得60康币奖励（仅限首次充值）\n" +
//                "D类事业合伙人\t100元，限额1600名\t获得5奖励（仅限首次充值）\n" +
//                "以上激励措施是在我们的“毛利空间非常小”的背景下给出的最大激励措施。\n" +
//                "6、如果您做引荐人，您将享受如下利益：一级分销2%，二级分销1%；\n" +
//                "7、作为采购买手，我们要把运营成本降到最低。因此没有过多的客服人员，也没有繁杂的退换货服务体系，敬请理解和谅解。您下单那一刻，就决定了这个产品是您的了，无悔；由于我们的主观原因导致您受到损失，我们会直接赔付您康币解决；\n" +
//                "8、我们优先采买社员内部人员生产的产品，我们优先采买内部社员的提供的服务。前提是您所提供的产品或服务有自身的独特性或优越的性价比。其他社员接受您的产品或服务，这才是采买遴选标准的金指标。\n" +
//                "以上条款和约定，适合所有社员和事业合伙人。\n" +
//                "特此发布。\n" +
//                "感恩您对幸福康城的关注和支持。您的加入是我们的价值成长的最大力量。\n" +
//                "\n" +
//                "                      北京幸福康城养老服务有限公司\n" +
//                "                  \n" +
//                "                      2018-09-24（农历八月十五中秋节）");
        content.setText("在您加入幸福康城社员体系之前，请您仔细阅读本用户协议，在您充分理解并支持本协议的前提下，您再慎重决策是否加入。\n" +
                "1、加入幸福公社，必须要有引荐人；\n" +
                "2、加入幸福公社，无需缴纳入社年费。您只需要下一单不低于300元的家庭生活必须品的订单即可（社员从100个家庭必须品中选择300元以上的产品订单）；\n" +
                "3、加入幸福公社，享用产品和服务，需要提前预订。我们强烈建议您提前把您下周、下月、下一季度或全年的采买需求提交给我们匹配给您的“私家采购买手”，他（她）会把您的需求及时反馈到总部的采买信息中心，帮您提前准备；\n" +
                "4、您预订的产品，提货请您自己最近的社区提货点自提。货物已经到社区内了，烦劳您走几分钟路自提，更有利健康，也不会因为配送最后100米而加大您的采买的物流成本。如果您确实需要配送到家，那社区内的配送费用需您自己承担；\n" +
                "5、如果您做引荐人，您将象征性的享受消费额1%的激励；\n" +
                "6、作为采购买手，我们要把运营成本降到最低。因此没有过多的客服人员，也不接受退换货，敬请理解和谅解（不认可者请勿加入幸福公社）；\n" +
                "7、我们优先采买社员内部人员生产的产品，我们优先采买内部社员的提供的服务。前提是您所提供的产品或服务有自身的独特性或优越的性价比。其他社员接受您的产品或服务，这才是采买遴选标准的金指标。\n" +
                "以上条款和约定，适合所有社员和事业合伙人。\n" +
                "特此发布。\n" +
                "感恩您对幸福康城的关注和支持。您的加入是我们的价值成长的最大力量。\n" +
                "\n" +
                "                      北京幸福康城养老服务有限公司\n" +
                "                  \n" +
                "                      2018-10-08");

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }
}
