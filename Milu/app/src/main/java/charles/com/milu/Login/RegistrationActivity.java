package charles.com.milu.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoAlphaAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoBackgroundColorAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoPositionAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoRotationAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoScaleAnimation;
import com.nightonke.wowoviewpager.Enum.Chameleon;
import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.WoWoViewPager;
import com.nightonke.wowoviewpager.WoWoViewPagerAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import charles.com.milu.Base.MainActivity;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.MiluApplication;
import charles.com.milu.R;
import charles.com.milu.utils.logger.Log;


/**
 * Created by mac_dev on 10/31/17.
 */

public class RegistrationActivity extends AppCompatActivity {
    protected WoWoViewPager wowo;
    private boolean animationAdded = false;

    protected int ease = Ease.Linear;
    protected boolean useSameEaseTypeBack = true;

    protected int fragmentNumber() {
        return 3;
    }
    protected int screenW;
    protected int screenH;

    AVLoadingIndicatorView progressBar;

    TitleTextView textView1;
    TitleTextView textView2;
    TitleTextView textView3;

    ImageView imageView1, imageView2, imageView3, imageView4;
    FrameLayout backgroundImageview;

    boolean flag1 = true;
    boolean flag2 = false;
    boolean flag3 = false;

    private ImmersionBar mImmersionBar;

    ImageView icon1, icon2, icon3;

    protected Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.transparent,
                R.color.transparent,
                R.color.transparent
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login_screen1);
        setStatusBar();
        setIndicators();

        textView1 = (TitleTextView)findViewById(R.id.register1Text);
        textView2 = (TitleTextView)findViewById(R.id.register2Text);
        textView3 = (TitleTextView)findViewById(R.id.register3Text);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        backgroundImageview = (FrameLayout)findViewById(R.id.background_image_view);
        progressBar = (AVLoadingIndicatorView)findViewById(R.id.progressBar);
        init();

        wowo = (WoWoViewPager)findViewById(R.id.wowo_viewpager);
        wowo.setAdapter(WoWoViewPagerAdapter.builder()
                .fragmentManager(getSupportFragmentManager())
                .count(fragmentNumber())                       // Fragment Count
                .colorsRes(fragmentColorsRes())                // Colors of fragments
                .build());
        setPageTV(wowo);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    public void setStatusBar(){

        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        addAnimations();
    }

    private void addAnimations() {
        if (animationAdded) return;
        animationAdded = true;
        screenW = WoWoUtil.getScreenWidth(this);
        screenH = WoWoUtil.getScreenHeight(this);

        int x = textView1.getLeft();
        Log.e("Left", String.valueOf(textView1.getLeft()));
        Log.e("X", String.valueOf(textView1.getX()));
        Log.e("Left", String.valueOf(textView1.getPivotX()));

        Log.e("Y", String.valueOf(textView1.getY()));
        Log.e("Top", String.valueOf(textView1.getTop()));

        ViewAnimation viewAnimation1 = new ViewAnimation(textView1);
        viewAnimation1.add(WoWoRotationAnimation.builder().page(0).fromX(0).toX(100).fromY(0).toY(-10).fromZ(0).toZ(-30).build());
        viewAnimation1.add(WoWoAlphaAnimation.builder().page(0).from(1).to(0).build());
        viewAnimation1.add(WoWoPositionAnimation.builder().page(0).fromX(textView1.getLeft()).toX(-screenW).fromY(textView1.getTop()).toY(textView1.getTop()).build());

        ViewAnimation viewAnimation2 = new ViewAnimation(imageView1);
        viewAnimation2.add(WoWoAlphaAnimation.builder().page(0).from(1).to(0).build());
        viewAnimation2.add(WoWoScaleAnimation.builder().page(0).fromXY(1).toXY(0.7).build());
        viewAnimation2.add(WoWoPositionAnimation.builder().page(0).fromX(imageView1.getLeft()).toX(-screenW).fromY(imageView1.getTop()).toY(imageView1.getTop()).build());
        wowo.addAnimation(viewAnimation1);
        wowo.addAnimation(viewAnimation2);

        ViewAnimation viewAnimation3 = new ViewAnimation(textView2);
        viewAnimation3.add(WoWoAlphaAnimation.builder().page(0).from(0).to(1).build());
        viewAnimation3.add(WoWoRotationAnimation.builder().page(0).fromX(100).toX(0).fromY(10).toY(0).fromZ(30).toZ(0).build());
        viewAnimation3.add(WoWoPositionAnimation.builder().page(0).fromX(screenW).toX(textView2.getX()).fromY(textView2.getY()).toY(textView2.getY()).build());
        viewAnimation3.add(WoWoRotationAnimation.builder().page(1).fromX(0).toX(100).fromY(0).toY(-10).fromZ(0).toZ(-30).build());
        viewAnimation3.add(WoWoAlphaAnimation.builder().page(1).from(1).to(0).build());
        viewAnimation3.add(WoWoPositionAnimation.builder().page(1).fromX(textView2.getLeft()).toX(-screenW).fromY(textView2.getTop()).toY(textView2.getTop()).build());
        wowo.addAnimation(viewAnimation3);

        ViewAnimation viewAnimation4 = new ViewAnimation(imageView2);
        viewAnimation4.add(WoWoAlphaAnimation.builder().page(0).from(0).to(1).build());
        viewAnimation4.add(WoWoScaleAnimation.builder().page(0).fromXY(1.3).toXY(1).build());
        viewAnimation4.add(WoWoPositionAnimation.builder().page(0).fromX(screenW).toX(imageView2.getX()).fromY(imageView2.getTop()).toY(imageView2.getTop()).build());
        viewAnimation4.add(WoWoAlphaAnimation.builder().page(1).from(1).to(0).build());
        viewAnimation4.add(WoWoScaleAnimation.builder().page(1).fromXY(1).toXY(0.2).build());
        viewAnimation4.add(WoWoPositionAnimation.builder().page(1).fromX(imageView2.getLeft()).toX(-screenW).fromY(imageView2.getTop()).toY(imageView2.getTop()).build());
        wowo.addAnimation(viewAnimation4);

        ViewAnimation viewAnimation5 = new ViewAnimation(textView3);
        viewAnimation5.add(WoWoAlphaAnimation.builder().page(1).from(0).to(1).build());
        viewAnimation5.add(WoWoRotationAnimation.builder().page(1).fromX(100).toX(0).fromY(10).toY(0).fromZ(30).toZ(0).build());
        viewAnimation5.add(WoWoPositionAnimation.builder().page(1).fromX(screenW).toX(textView3.getX()).fromY(textView3.getY()).toY(textView3.getY()).build());
        viewAnimation5.add(WoWoRotationAnimation.builder().page(2).fromX(0).toX(100).fromY(0).toY(-10).fromZ(0).toZ(-30).build());
        viewAnimation5.add(WoWoAlphaAnimation.builder().page(2).from(1).to(0).build());
        viewAnimation5.add(WoWoPositionAnimation.builder().page(2).fromX(textView3.getLeft()).toX(-screenW).fromY(textView3.getTop()).toY(textView3.getTop()).build());
        wowo.addAnimation(viewAnimation5);

        ViewAnimation viewAnimation6 = new ViewAnimation(imageView4);
        viewAnimation6.add(WoWoAlphaAnimation.builder().page(1).from(0).to(1).build());
        viewAnimation6.add(WoWoScaleAnimation.builder().page(1).fromXY(1).toXY(1).build());
        viewAnimation6.add(WoWoPositionAnimation.builder().page(1).fromX(screenW).toX(imageView4.getX()).fromY(imageView4.getTop()).toY(imageView4.getTop()).build());
        viewAnimation6.add(WoWoAlphaAnimation.builder().page(2).from(1).to(0).build());
        viewAnimation6.add(WoWoScaleAnimation.builder().page(2).fromXY(1).toXY(1).build());
        viewAnimation6.add(WoWoPositionAnimation.builder().page(2).fromX(imageView4.getLeft()).toX(-screenW).fromY(imageView4.getTop()).toY(imageView4.getTop()).build());
        wowo.addAnimation(viewAnimation6);

        ViewAnimation viewAnimation7 = new ViewAnimation(imageView3);
        viewAnimation7.add(WoWoAlphaAnimation.builder().page(1).from(0).to(1).build());
        viewAnimation7.add(WoWoScaleAnimation.builder().page(1).fromXY(1).toXY(1).build());
        viewAnimation7.add(WoWoPositionAnimation.builder().page(1).fromX(screenW).toX(imageView3.getX()).fromY(imageView3.getTop()).toY(imageView3.getTop()).build());
        viewAnimation7.add(WoWoAlphaAnimation.builder().page(2).from(1).to(0).build());
        viewAnimation7.add(WoWoScaleAnimation.builder().page(2).fromXY(1).toXY(1).build());
        viewAnimation7.add(WoWoPositionAnimation.builder().page(2).fromX(imageView3.getLeft()).toX(-screenW).fromY(imageView3.getTop()).toY(imageView3.getTop()).build());
        wowo.addAnimation(viewAnimation7);

        ViewAnimation viewAnimation8 = new ViewAnimation(backgroundImageview);
        backgroundImageview.setVisibility(View.GONE);
        viewAnimation8.add(WoWoBackgroundColorAnimation.builder().page(0).from("#00000000").to("#000000ff").start(0).end(0.5).chameleon(Chameleon.RGB).build());
        viewAnimation8.add(WoWoBackgroundColorAnimation.builder().page(0).from("#000000ff").to("#660000ff").start(0.5).end(0.75).chameleon(Chameleon.RGB).build());
        viewAnimation8.add(WoWoBackgroundColorAnimation.builder().page(0).from("#660000ff").to("#000000ff").start(0.75).end(1).chameleon(Chameleon.RGB).build());
        viewAnimation8.add(WoWoBackgroundColorAnimation.builder().page(1).from("#00000000").to("#660000ff").start(0).end(0.5).chameleon(Chameleon.RGB).build());
        viewAnimation8.add(WoWoBackgroundColorAnimation.builder().page(1).from("#660000ff").to("#00000000").start(0.5).end(1).chameleon(Chameleon.RGB).build());
        viewAnimation8.add(WoWoBackgroundColorAnimation.builder().page(2).from("#00000000").to("#660000ff").start(0).end(0.5).chameleon(Chameleon.RGB).build());
        viewAnimation8.add(WoWoBackgroundColorAnimation.builder().page(2).from("#660000ff").to("#00000000").start(0.5).end(1).chameleon(Chameleon.RGB).build());
        wowo.addAnimation(viewAnimation8);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }


    private void init() {
        progressBar.hide();
    }

    private void setPageTV(WoWoViewPager wowo) {
        wowo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if ((i == 0) && (v == 0.0)){

                    flag1 = true;
                    flag2 = false;
                    flag3 = false;
                    setAct_icon1();
                }

                if ((i == 1) && (v == 0.0)){

                    flag1 = false;
                    flag2 = true;
                    flag3 = false;
                    setAct_icon2();
                }

                if ((i == 2) && (v == 0.0)){

                    flag1 = false;
                    flag2 = false;
                    flag3 = true;
                    setAct_icon3();
                }

                android.util.Log.d("flag1 = ", String.valueOf(flag1));
                android.util.Log.d("flag2 = ", String.valueOf(flag2));
                android.util.Log.d("flag3 = ", String.valueOf(flag3));
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                backgroundImageview.setVisibility(View.VISIBLE);

            }
        });
    }
    protected int dp2px(float dp) {
        return WoWoUtil.dp2px((int) dp, this);
    }

    protected int dp2px(double dp) {
        return WoWoUtil.dp2px((int) dp, this);
    }
    public void setAct_icon1(){

        icon1.setAlpha((float) 0.9);
        icon2.setAlpha((float) 0.22);
        icon3.setAlpha((float) 0.22);

    }
    public void setAct_icon2(){

        icon1.setAlpha((float) 0.22);
        icon2.setAlpha((float) 0.9);
        icon3.setAlpha((float) 0.22);

    }
    public void setAct_icon3(){

        icon1.setAlpha((float) 0.22);
        icon2.setAlpha((float) 0.22);
        icon3.setAlpha((float) 0.9);

    }
    public void setIndicators(){

        icon1 = (ImageView) findViewById(R.id.icon1);
        icon2 = (ImageView) findViewById(R.id.icon2);
        icon3 = (ImageView) findViewById(R.id.icon3);
    }
    public void floginClicked(View view){
        ((MiluApplication) getApplication()).appInfo.saveLogin(true);
        progressBar.smoothToShow();
        Thread myThread = new Thread() {

            public void run() {
                try {
                    sleep(1000);
//                    progressBar.hide();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

    public void skipClicked(View view){

        ((MiluApplication) getApplication()).appInfo.saveLogin(false);
        progressBar.smoothToShow();
        Thread myThread = new Thread() {

            public void run() {
                try {
                    sleep(1000);
//                    progressBar.hide();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }

}
