package charles.com.milu.LiveEvents.LiveFeedMain;


import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.R;
import charles.com.milu.utils.CustomClickTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFeedMingleSettingFragment extends BaseFragment {

    @BindView(R.id.buy_more_button)
    TitleTextView buy_more_button;

    @BindView(R.id.interest_button)
    RelativeLayout interest_button;

    @BindView(R.id.age_of_range_button)
    RelativeLayout age_of_range_button;

    @BindView(R.id.txt_interest)
    TitleTextView txt_interest;

    @BindView(R.id.txt_age_of_range)
    TitleTextView txt_age_of_range;

    @BindView(R.id.switch_show_me_mingle)
    SwitchCompat switch_show_me_mingle;


    public static LiveFeedMingleSettingFragment getInstance() {
        // Required empty public constructor
        LiveFeedMingleSettingFragment fragment = new LiveFeedMingleSettingFragment();
        return fragment;
    }

    @Override
    protected int addView() {
        return R.layout.fragment_live_mingle_setting;
    }

    @Override
    public void initView() {
        super.initView();
        buy_more_button.setOnClickListener(this);
        setToolBar();


    }


    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("mingle setting");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_cancel_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
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
                mAct.onBackPressed();
                break;
            case R.id.buy_more_button:
                mAct.onBackPressed();
                break;
        }
    }


}
