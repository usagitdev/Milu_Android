package charles.com.milu.HomeScreen;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.R;

import static android.R.attr.enabled;

public class Tabbar extends AppCompatActivity {


    private NonSwipeableViewPager tabBarPager;
    private BottomBar bottomBar;
    public ImageView  tabblurView;

    private ImmersionBar mImmersionBar;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar);

//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.init();

        tabBarPager = (NonSwipeableViewPager) findViewById(R.id.contanerBar);
        tabBarPager.setAdapter(new SectionPageAdapter(getSupportFragmentManager()));
        tabBarPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return false;
            }
        });

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_liveFeed:
                        tabBarPager.setCurrentItem(0, false);
                        break;

                    case R.id.tab_eventDash:
                        tabBarPager.setCurrentItem(1, false);
                        break;

                    case R.id.tab_chatDash:
                        tabBarPager.setCurrentItem(2, false);
                        break;

                    case R.id.tab_placeDash:
                        tabBarPager.setCurrentItem(3, false);
                        break;

                    case R.id.tab_userProfile:
                        tabBarPager.setCurrentItem(4, false);
                        break;
                }
            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mImmersionBar != null)
//            mImmersionBar.destroy();
//    }
}
