package charles.com.milu.MediaSelect_ShareFragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.LightEditText;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.Models.MediaObject;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaSelect_ShareFragment extends BaseFragment implements MediaSelectResultGalleryAdapter.ItemClickListener{


    @BindView(R.id.share_Button)
    ImageButton mShareButton;
    @BindView(R.id.edt_comment)
    TitleTextView commentEditText;
    ArrayList<MediaObject> mediaObjects = new ArrayList<>();

    public static MediaSelect_ShareFragment getInstance() {
        return new MediaSelect_ShareFragment();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_media_select_share;
    }

    @Override
    public void initView() {
        super.initView();

    }


    public void setShareMedias(ArrayList<MediaObject> objects) {
        mediaObjects = objects;
    }

    public void setShareComment(String comment) {
        commentEditText.setText(comment);
    }

    @Override
    public void mediaCellClicked(View v, int adapterPosition) {

    }

    @Override
    public void mediaDeleteClicked(View v, int adapterPosition) {

    }
}
