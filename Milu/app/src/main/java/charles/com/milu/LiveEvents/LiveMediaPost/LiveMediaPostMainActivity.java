package charles.com.milu.LiveEvents.LiveMediaPost;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import charles.com.milu.Base.BaseActivity;
import charles.com.milu.MediaSelect_Activity.MediaSelectActivity;
import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMediaPostMainActivity extends BaseActivity {


    @BindView(R.id.liveMediaPost_Hpicker)
    HorizontalPicker Hpicker;
    private List<String> dataValues = new ArrayList<>();
    private ImmersionBar mImmersionBar;

    @BindView(R.id.liveMediapostMainContainer)
    NonSwipeableViewPager mediaPostViewPager;

    @Override
    protected int addView() {
        return R.layout.fragment_live_feed_media_post;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
//		setStatusBarTranslucent(true);

        setToolBar();
        setHorizontalPicker();

        mediaPostViewPager.setAdapter(new LiveMediaPostAdapter(getSupportFragmentManager()));
        mediaPostViewPager.setCurrentItem(0, false);


    }
    @Override
    public void setToolBar() {
        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_cancel_icon);
        btnMenu.setOnClickListener(this);

        assert rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
        rightButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_btn_right2:
                setCheckButton();
                break;
            case R.id.toolbar_btn_left:
                finish();
                break;
        }

    }

    public void setStatusBar(){

        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    public void setHorizontalPicker(){


        setHPickerVales();

        Hpicker.setOnItemSelectedListener(new HorizontalPicker.OnItemSelected() {
            @Override
            public void onItemSelected(int index) {

                switch (index) {
                    case 0:
                        mediaPostViewPager.setCurrentItem(0, false);
                        break;
                    case 1:
                        mediaPostViewPager.setCurrentItem(1, false);
                        break;
                    case 2:
                        mediaPostViewPager.setCurrentItem(2, false);
                        break;
                    case 3:
                        mediaPostViewPager.setCurrentItem(3, false);
                        break;
                    case 4:
                        mediaPostViewPager.setCurrentItem(4, false);
                        break;
                    case 5:
                        mediaPostViewPager.setCurrentItem(5, false);
                        break;
                }
            }
        });
    }

    private void setHPickerVales() {
        dataValues.clear();

        dataValues.add("PHOTO");
        dataValues.add("AUDIO");
        dataValues.add("CRITIQUE");
        dataValues.add("RATE");
        dataValues.add("VIDEO");

        CharSequence[] cs = dataValues.toArray(new CharSequence[dataValues.size()]);
        Hpicker.setValues(cs);
    }

    public void setCheckButton(){

        Toast.makeText(LiveMediaPostMainActivity.this,"check button clicked!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LiveMediaPostMainActivity.this, MediaSelectActivity.class);
        startActivity(intent);
        finish();
    }


}
