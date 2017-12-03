package charles.com.milu.LiveEvents.LiveMediaPost;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.List;

import charles.com.milu.MediaSelect_Activity.MediaSelectActivity;
import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMediaPostMainActivity extends AppCompatActivity {


    HorizontalPicker Hpicker;
    private List<String> dataValues = new ArrayList<>();
    private NonSwipeableViewPager mediaPostViewPager;
    private ImmersionBar mImmersionBar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_live_feed_media_post);

        setBackButton();

        setCheckButton();

        setHorizontalPicker();

        setStatusBar();

        mediaPostViewPager = (NonSwipeableViewPager) findViewById(R.id.liveMediapostMainContainer);
        mediaPostViewPager.setAdapter(new LiveMediaPostAdapter(getSupportFragmentManager()));
        mediaPostViewPager.setCurrentItem(0, false);

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

        Hpicker = (HorizontalPicker) findViewById(R.id.liveMediaPost_Hpicker);

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

        ImageView checkButton = (ImageView) findViewById(R.id.liveMediaPost_CheckButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"check button clicked!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MediaSelectActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    public void setBackButton(){

        ImageView backButton = (ImageView) findViewById(R.id.liveMediaPost_BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }
}
