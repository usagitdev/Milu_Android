package charles.com.milu.LiveEvents.LiveFeedMain;


import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFeedChatSettingFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener{

    @BindView(R.id.switch_anonymous)
    SwitchCompat switch_anonymous;

    @BindView(R.id.switch_display_first_name)
    SwitchCompat switch_display_first_name;

    @BindView(R.id.switch_display_last_name)
    SwitchCompat switch_display_last_name;

    @BindView(R.id.txt_first_name)
    TitleTextView txt_first_name;

    @BindView(R.id.txt_last_name)
    TitleTextView txt_last_name;

    @BindView(R.id.switch_display_handle)
    SwitchCompat switch_display_handle;

    @BindView(R.id.ll_identity_settings)
    LinearLayout viewIdentitySettings;



    public static LiveFeedChatSettingFragment getInstance() {
        // Required empty public constructor
        LiveFeedChatSettingFragment fragment = new LiveFeedChatSettingFragment();
        return fragment;
    }

    @Override
    protected int addView() {
        return R.layout.fragment_live_chat_setting;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        switch_anonymous.setChecked(true);
        viewIdentitySettings.setVisibility(View.GONE);
        switch_display_first_name.setChecked(false);
        switch_display_last_name.setChecked(false);
        switch_display_first_name.setEnabled(false);
        switch_display_last_name.setEnabled(false);
        txt_first_name.setTextColor(ContextCompat.getColor(getContext(), R.color.color_DimGray));
        txt_last_name.setTextColor(ContextCompat.getColor(getContext(), R.color.color_DimGray));
        switch_display_handle.setChecked(true);
        switch_anonymous.setOnCheckedChangeListener(this);
        switch_display_handle.setOnCheckedChangeListener(this);
        switch_display_last_name.setOnCheckedChangeListener(this);
        switch_display_first_name.setOnCheckedChangeListener(this);

    }


    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("mingle setting");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_cancel_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

//        assert  rightButton2 != null;
//        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
//        rightButton2.setVisibility(View.VISIBLE);
//        rightButton2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_anonymous:
                if (isChecked) {
                    viewIdentitySettings.setVisibility(View.GONE);
                }else{
                    viewIdentitySettings.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.switch_display_handle:
                if (isChecked) {
                    switch_display_first_name.setChecked(false);
                    switch_display_last_name.setChecked(false);
                    switch_display_first_name.setEnabled(false);
                    switch_display_last_name.setEnabled(false);
                    txt_first_name.setTextColor(ContextCompat.getColor(getContext(), R.color.color_DimGray));
                    txt_last_name.setTextColor(ContextCompat.getColor(getContext(), R.color.color_DimGray));
                }else{
                    switch_display_first_name.setChecked(true);
                    switch_display_last_name.setChecked(true);
                    switch_display_first_name.setEnabled(true);
                    switch_display_last_name.setEnabled(true);
                    txt_first_name.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                    txt_last_name.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                }
                break;

            case R.id.switch_display_first_name:
                break;

            case R.id.switch_display_last_name:
                break;

        }

    }
}
