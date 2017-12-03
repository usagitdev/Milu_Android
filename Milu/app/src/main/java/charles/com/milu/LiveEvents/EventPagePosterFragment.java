package charles.com.milu.LiveEvents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.R;

/**
 * Created by mac on 2017-11-30.
 */

public class EventPagePosterFragment extends BaseFragment {

    @BindView(R.id.post_ImageView)
    ImageView postImageView;


    public static EventPagePosterFragment getInstance() {
        // Required empty public constructor
        EventPagePosterFragment fragment = new EventPagePosterFragment();
        return fragment;
    }

    @Override
    protected int addView() {
        return R.layout.fragment_events_page_poster_view;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();

    }

    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("events");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_share_icon);
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
                shareAction();
                break;
        }
    }

    public void shareAction(){
        postImageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = postImageView.getDrawingCache();
        File root = Environment.getExternalStorageDirectory();
        File cachePath = new File(root.getAbsolutePath() + "/DCIM/Camera/image.jpg");
        try
        {
            root.createNewFile();
            FileOutputStream ostream = new FileOutputStream(root);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(root.getAbsolutePath()+"/DCIM/Camera/image.jpg")));
        startActivity(Intent.createChooser(shareIntent, "Share Image Using"));
    }


}
