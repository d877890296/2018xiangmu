package com.xfkc.caimai.welcmoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.MainActivity;
import com.xfkc.caimai.R;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.loading.LoadingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.welcome_iv)
    ImageView welcomeIv;

    private int guideId;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
//        StatusBarUtil.setTransparent(this);
        guideId = SharedPrefUtil.get(WelcomeActivity.this, SharedPref.GUIDE_GLAG, 1);
        token =SharedPrefUtil.get(this,SharedPref.TOKEN);
        //设置动画渐变
        setAnimation();

        //隐藏状态栏
//        StatusBarUtil.hideStatus(this);
    }


    AnimationSet animationSet;
    AlphaAnimation alphaAnimation;

    private void setAnimation() {
        //创建一个AnimationSet对象，参数为Boolean型，

        //true表示使用Animation的interpolator，false则是使用自己的

        animationSet = new AnimationSet(true);

        //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明

        alphaAnimation = new AlphaAnimation(1, 1);

        //设置动画执行的时间

        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        animationSet.setFillAfter(true);
        //将alphaAnimation对象添加到AnimationSet当中

        animationSet.addAnimation(alphaAnimation);

        //设置动画监听
        alphaAnimation.setAnimationListener(

                new RemoveAnimationListener());
        //使用ImageView的startAnimation方法执行动画
        welcomeIv.startAnimation(animationSet);
    }

    /*跳转到导航页*/
    public void goGuide() {
        startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
    }

    /***
     * 设置动画监听
     */
    private class RemoveAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (guideId == 1) {
                goGuide();
            } else {
                if (Tools.IsEmpty(token)){
                    startActivity(new Intent(WelcomeActivity.this, LoadingActivity.class));
                }else {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                }
            }
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }
}
