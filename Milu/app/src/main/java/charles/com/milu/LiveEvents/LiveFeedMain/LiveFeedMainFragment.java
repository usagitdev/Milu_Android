package charles.com.milu.LiveEvents.LiveFeedMain;


import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;


import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFeedMainFragment extends BaseFragment {

    @BindView(R.id.liveFeedMainPage_MainTabbar)
    NavigationTabStrip navigationTabStrip;

    @BindView(R.id.liveFeedMainPageContainer)
    NonSwipeableViewPager liveFeedMainPager;

    Typeface workSans_Light;

    public static LiveFeedMainFragment getInstance() {
        // Required empty public constructor
        LiveFeedMainFragment fragment = new LiveFeedMainFragment();
        return fragment;
    }

    @Override
    protected int addView() {
        return R.layout.fragment_live_feed_main;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();

        workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Light.ttf");

        navigationTabStrip.setTitles("LIVE", "MINGLE", "CHAT");
        navigationTabStrip.setTabIndex(0, true);


        liveFeedMainPager.setAdapter(new LiveFeedPageAdapter(getChildFragmentManager()));


        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                switch (index) {
                    case 0:
                        liveFeedMainPager.setCurrentItem(0, false);
                        break;
                    case 1:
                        liveFeedMainPager.setCurrentItem(1, false);
                        break;

                    case 2:
                        liveFeedMainPager.setCurrentItem(2, false);
                        break;
                }
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });

    }


    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("live event");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_setting_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;

            case R.id.toolbar_btn_right2:
                showSetting();
                break;
        }
    }


    public void showSetting(){
        if (liveFeedMainPager.getCurrentItem() == 0) {

        }else if (liveFeedMainPager.getCurrentItem() == 1) {
            addFragment(LiveFeedMingleSettingFragment.getInstance(), true);
        }else if (liveFeedMainPager.getCurrentItem() == 2) {
            addFragment(LiveFeedChatSettingFragment.getInstance(), true);
        }
    }
}
