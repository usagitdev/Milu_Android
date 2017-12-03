package charles.com.milu.MediaSelect_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import charles.com.milu.LiveEvents.LiveMediaPost.LiveMediaPostMainActivity;
import charles.com.milu.MediaSelect_Fragment.MediaSelect_Fragment;
import charles.com.milu.MediaSelect_ResultFragment.MediaSelect_ResultFragment;
import charles.com.milu.PreventScrollonPageView.NonSwipeableViewPager;
import charles.com.milu.R;

public class MediaSelectActivity extends AppCompatActivity {


    private NonSwipeableViewPager mediaSelectViewPager;
    private MediaSelect_Fragment mediaSelect_fragment;
    private MediaSelect_ResultFragment mediaSelect_resultFragment;
    boolean selectFlag;

    ImageView button2_unselected;
    ImageView button1, button2_sel, button2_un, button3;
    TextView title;
    private ImmersionBar mImmersionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_select);

        title = (TextView) findViewById(R.id.mediaSelect_title);

        selectFlag = false;

        setBackButtons();

        setCheckButton();

        setStepBar();

        setStatusBar();

        setViewPager();
    }

    public void setStepBar(){

        button2_sel = (ImageView) findViewById(R.id.mediaSelect_button2_sel);
        button2_sel.setVisibility(View.INVISIBLE);

        button2_un = (ImageView) findViewById(R.id.mediaSelect_button2_un);

    }

    public void setCheckButton(){


        ImageView checkButton = (ImageView) findViewById(R.id.mediaSelect_CheckButton);
            checkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectFlag = true;
                    button2_sel.setVisibility(View.VISIBLE);
                    title.setText("share");
                    mediaSelectViewPager.setCurrentItem(1, false);
                    mediaSelect_fragment.getSelectedItems();
                }
            });
    }
    public void setViewPager(){

        mediaSelect_fragment = new MediaSelect_Fragment();
        mediaSelect_resultFragment = new MediaSelect_ResultFragment();

        mediaSelectViewPager = (NonSwipeableViewPager) findViewById(R.id.mediaSelectViewPager);
        mediaSelectViewPager.setAdapter(new MediaSelectAdapter(getSupportFragmentManager(), mediaSelect_fragment, mediaSelect_resultFragment));
        mediaSelectViewPager.setCurrentItem(0, false);


    }

    public void setBackButtons(){

        ImageView backButton = (ImageView) findViewById(R.id.mediaSelect_BackButton);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (selectFlag){

                        title.setText("select");
                        button2_sel.setVisibility(View.INVISIBLE);
                        mediaSelectViewPager.setCurrentItem(0, false);
                        selectFlag = false;

                    }else {

                        Intent intent = new Intent(getApplicationContext(), LiveMediaPostMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

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
}
