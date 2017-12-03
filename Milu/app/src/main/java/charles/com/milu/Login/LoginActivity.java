package charles.com.milu.Login;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;

import charles.com.milu.Adapters.BaseActivity;
import charles.com.milu.Base.EventBaseActivity;
import charles.com.milu.Base.MainActivity;
import charles.com.milu.HomeScreen.Tabbar;
import charles.com.milu.R;

public class LoginActivity extends AppCompatActivity {

    TextView loginTxt, skipTxt;
    ImageView floginTxt;
    Typeface workSans_Light;
    ViewPager loginViewPager;

    boolean flag1 = true;
    boolean flag2 = false;
    boolean flag3 = false;

    private ImmersionBar mImmersionBar;

    ImageView icon1, icon2, icon3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        setStatusBar();

        setPageView();

        setIndicators();

        fontSet();
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

    public void setIndicators(){

        icon1 = (ImageView) findViewById(R.id.icon1);
        icon2 = (ImageView) findViewById(R.id.icon2);
        icon3 = (ImageView) findViewById(R.id.icon3);
    }

    public void setPageView(){

        loginViewPager = (ViewPager) findViewById(R.id.login_ViewPager);
        loginViewPager.setAdapter(new LoginViewPageAdapter(getSupportFragmentManager()));
        loginViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.d("kkk view Pager Position", String.valueOf(position));
                Log.d("222 view pager position", String.valueOf(positionOffset));
                Log.d("333 view pager position", String.valueOf(positionOffsetPixels));

                if ((position == 0) && (positionOffset == 0.0)){

                    flag1 = true;
                    flag2 = false;
                    flag3 = false;
                }

                if ((position == 1) && (positionOffset == 0.0)){

                    flag1 = false;
                    flag2 = true;
                    flag3 = false;
                }

                if ((position == 2) && (positionOffset == 0.0)){

                    flag1 = false;
                    flag2 = false;
                    flag3 = true;
                }

                Log.d("flag1 = ", String.valueOf(flag1));
                Log.d("flag2 = ", String.valueOf(flag2));
                Log.d("flag3 = ", String.valueOf(flag3));

                if (flag1){
                    setText1();
                    setImage2Scal((float) 1.2);
                    setFirstImage_outleft(positionOffset);
                    setImage2_inRight(positionOffset);
                    setAct_icon1();
                }

                if (flag2){

                    setAct_icon2();
                    setImage2Scal((float) 1.0);
                    addAlpha_onFirstImage_inLeft(positionOffset);
                    setonSecondImage_outLeft(positionOffset);

                    if (position == 0){
                        setImage2Scal((float) 1.0);
                        setImage2_outRight(positionOffset);
                    }
                }

              if (flag3){
                  setAct_icon3();
                  setImage2Scal((float) 1.0);
                  Registration3_Fragment.thirdImage.setAlpha((float) 1.0);

                  if (position == 1){
//                      setImage3(positionOffset);
                  }
              }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
    public void setText1(){

        Rotate3dAnimation testanimation = new Rotate3dAnimation(45.0f,10.0f,10.0f,20.0f,20.0f,true);
        Registration1_Fragment.firstText.clearAnimation();
        Registration1_Fragment.firstText.startAnimation(testanimation);

    }
    public void setImage3Scale(float alpha){

        float alphaValue = (float) 1.0;
        Registration3_Fragment.thirdImage.setAlpha(alphaValue);
    }

    public void setImage3(float positionoffset){
        float alphaValue2 = 1 - (1 - positionoffset);
        Registration3_Fragment.thirdImage.setAlpha(alphaValue2);
    }
    public void setImage2Scal(float scals){
        Registration2_Fragment.secondImage.setAlpha((float) 1.0);
        Registration2_Fragment.secondImage.setScaleY(scals);
        Registration2_Fragment.secondImage.setScaleX(scals);
    }
    public void setImage2_inRight(float positionoffset){

        float scalValue = (float) (1.2 - (positionoffset) * 0.2);
        Registration2_Fragment.secondImage.setScaleX(scalValue);
        Registration2_Fragment.secondImage.setScaleY(scalValue);
    }
    public void addAlpha_onFirstImage_inLeft(float alpha){
        // Image1 set //
        float alphaValue = (float) 1.0;
        float scalValue = (float) 1.0;
        Registration1_Fragment.firstImage.setAlpha(alphaValue);
        Registration1_Fragment.firstImage.setScaleY(scalValue);
    }
    public void setImage2_outRight(float positionoffset){
        // Image2 set //
        float alphaValue2 = 1 - (1 - positionoffset);
        float scalValue2 = (float) (1 + (1 - positionoffset) * 0.1);
        Registration2_Fragment.secondImage.setAlpha(alphaValue2);
        Registration2_Fragment.secondImage.setScaleY(scalValue2);
    }

    public void setFirstImage_outleft(float alpha){
        float alphaValue = 1 - alpha;
        float scalValue = (float) (1- (alpha) * 0.1);
        Registration1_Fragment.firstImage.setAlpha(alphaValue);
        Registration1_Fragment.firstImage.setScaleY(scalValue);
    }

    public void setonSecondImage_outLeft(float alpha){

        float alphaValue = 1 - alpha;
        float scalValue = (float) (1- (alpha) * 0.9);

        Registration2_Fragment.secondImage.setAlpha(alphaValue);
        Registration2_Fragment.secondImage.setScaleY(scalValue);
        Registration2_Fragment.secondImage.setScaleX(scalValue);
    }

    public void fontSet(){

        workSans_Light = Typeface.createFromAsset(getAssets(),"WorkSans-Light.ttf");
        floginTxt = (ImageView) findViewById(R.id.floginButton);
        loginTxt = (TextView) findViewById(R.id.login);
        skipTxt = (TextView) findViewById(R.id.skipButton);

        loginTxt.setTypeface(workSans_Light);
        skipTxt.setTypeface(workSans_Light);

    }

    public void floginClicked(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void skipClicked(View view){

        Toast.makeText(getApplicationContext(),"Skip Button Clicked!!", Toast.LENGTH_SHORT).show();

    }
}
