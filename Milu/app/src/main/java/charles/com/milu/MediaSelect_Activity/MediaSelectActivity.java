package charles.com.milu.MediaSelect_Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import charles.com.milu.Base.BaseActivity;
import charles.com.milu.Base.MainFragment;
import charles.com.milu.LiveEvents.LiveMediaPost.LiveMediaPostMainActivity;
import charles.com.milu.MediaSelect_Fragment.MediaSelect_Fragment;
import charles.com.milu.MediaSelect_ResultFragment.MediaSelect_ResultFragment;
import charles.com.milu.MediaSelect_ShareFragment.MediaSelect_ShareFragment;
import charles.com.milu.Models.Pusher;
import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.R;
import pl.tajchert.nammu.Nammu;

import static charles.com.milu.utils.AppConstants.SELECT_MEDIA_SELECT_ITEM;

public class MediaSelectActivity extends BaseActivity {


    private NonSwipeableViewPager mediaSelectViewPager;
    private MediaSelect_Fragment mediaSelect_fragment = MediaSelect_Fragment.getInstance();
    private MediaSelect_ResultFragment mediaSelect_resultFragment = MediaSelect_ResultFragment.getInstance();
    private MediaSelect_ShareFragment mediaSelect_shareFragment = MediaSelect_ShareFragment.getInstance();
    boolean selectFlag;

    ImageView button2_unselected;
    ImageView button1, button3;
    private ImmersionBar mImmersionBar;

    @BindView(R.id.mediaSelect_button2_sel)
    ImageView button2_sel;

    @BindView(R.id.mediaSelect_button2_un)
    ImageView button2_un;

    @BindView(R.id.mediaSelect_button3_sel)
    ImageView button3_sel;

    @BindView(R.id.mediaSelect_button3_un)
    ImageView button3_un;


    @Override
    protected int addView() {
        return R.layout.activity_media_select;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
//		setStatusBarTranslucent(true);

        selectFlag = false;
        setToolBar();
//
        setStepBar();
//
//        setStatusBar();
//
        setViewPager();
        EventBus.getDefault().register(this);


    }
    @Override
    public void setToolBar() {
        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_cancel_icon);
        btnMenu.setOnClickListener(this);

        assert txtLeft != null;
        txtLeft.setText("select");

        assert rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_disable_icon);
        rightButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_btn_right2:
                setCheckButton();
                break;
            case R.id.toolbar_btn_left:
                setCancelButton();
                break;
        }

    }

    public void setCheckButton(){

        if (mediaSelectViewPager.getCurrentItem() == 0) {
            selectFlag = true;
            button2_sel.setVisibility(View.VISIBLE);
            assert txtLeft != null;
            txtLeft.setText("comment");
            mediaSelectViewPager.setCurrentItem(1, false);
            mediaSelect_resultFragment.setRecyclerView(mediaSelect_fragment.getSelectedItems());
        }else if (mediaSelectViewPager.getCurrentItem() == 1){
            button3_sel.setVisibility(View.VISIBLE);
            assert txtLeft != null;
            txtLeft.setText("share");
            mediaSelectViewPager.setCurrentItem(2, false);
            mediaSelect_shareFragment.setShareMedias(mediaSelect_fragment.getSelectedItems());
            mediaSelect_shareFragment.setShareComment(mediaSelect_resultFragment.getComment());
        }

    }

    @SuppressLint("SetTextI18n")
    public void setCancelButton() {
        if (mediaSelectViewPager.getCurrentItem() == 1){

            assert txtLeft != null;
            txtLeft.setText("select");
            button2_sel.setVisibility(View.INVISIBLE);
            mediaSelectViewPager.setCurrentItem(0, false);
            selectFlag = false;

        }else if (mediaSelectViewPager.getCurrentItem() == 2){

            assert txtLeft != null;
            txtLeft.setText("comment");
            button3_sel.setVisibility(View.INVISIBLE);
            mediaSelectViewPager.setCurrentItem(1, false);
            selectFlag = false;

        }else {

            Intent intent = new Intent(getApplicationContext(), LiveMediaPostMainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void setStepBar(){

        button2_sel.setVisibility(View.INVISIBLE);

    }

    public void setViewPager(){

//        mediaSelect_fragment = new MediaSelect_Fragment();
//        mediaSelect_resultFragment = new MediaSelect_ShareFragment();

        mediaSelectViewPager = (NonSwipeableViewPager) findViewById(R.id.mediaSelectViewPager);
        mediaSelectViewPager.setAdapter(new MediaSelectAdapter(getSupportFragmentManager(), mediaSelect_fragment, mediaSelect_resultFragment, mediaSelect_shareFragment));
        mediaSelectViewPager.setCurrentItem(0, false);


    }

    public void setStatusBar(){

//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

//        if (mImmersionBar != null)
//            mImmersionBar.destroy();
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Pusher pusher) {
        int count = pusher.getMediaObjects().size();
        switch (pusher.getAction()) {
            case SELECT_MEDIA_SELECT_ITEM:
                if (mediaSelectViewPager.getCurrentItem() == 0){
                    if (count > 0) {
                        assert rightButton2 != null;
                        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
                    }else{
                        assert rightButton2 != null;
                        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_disable_icon);
                    }
                }else if (mediaSelectViewPager.getCurrentItem() == 1) {
                    if (count > 0) {
                        assert rightButton2 != null;
                        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
                    }else{
                        assert rightButton2 != null;
                        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_disable_icon);
                    }
                }
                break;
        }
    }
}
