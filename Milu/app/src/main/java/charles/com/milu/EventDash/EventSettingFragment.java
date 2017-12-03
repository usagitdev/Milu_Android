package charles.com.milu.EventDash;

import android.util.DisplayMetrics;
import android.view.View;

import charles.com.milu.Base.BaseFragment;
import charles.com.milu.R;

public class EventSettingFragment extends BaseFragment {


    public static EventSettingFragment getInstance() {
        // Required empty public constructor
        EventSettingFragment fragment = new EventSettingFragment();
        return fragment;
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
        }
    }
    @Override
    protected int addView() {
        return R.layout.fragment_event_setting;
    }

    @Override
    public void initView() {
        super.initView();

        setToolBar();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();
        final DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        assert txtLeft != null;
        txtLeft.setText("event settings");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);

    }



}
