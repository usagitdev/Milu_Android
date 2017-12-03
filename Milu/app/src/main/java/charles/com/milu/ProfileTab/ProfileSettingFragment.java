package charles.com.milu.ProfileTab;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.R;

/**
 * Created by mac_dev on 10/28/17.
 */

public class ProfileSettingFragment extends BaseFragment {

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    public static ProfileSettingFragment getInstance() {
        // Required empty public constructor
        ProfileSettingFragment fragment = new ProfileSettingFragment();
        return fragment;
    }
    protected int addView() {
        return R.layout.fragment_profile_setting;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
    }

    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("profile setting");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
        }
    }


}
