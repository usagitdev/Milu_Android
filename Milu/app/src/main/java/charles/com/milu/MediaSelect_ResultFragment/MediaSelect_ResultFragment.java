package charles.com.milu.MediaSelect_ResultFragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.LightEditText;
import charles.com.milu.MediaSelect_Fragment.MediaSelectGalleryAdapter;
import charles.com.milu.Models.MediaObject;
import charles.com.milu.Models.Pusher;
import charles.com.milu.R;
import charles.com.milu.utils.AppConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaSelect_ResultFragment extends BaseFragment implements MediaSelectResultGalleryAdapter.ItemClickListener{


    @BindView(R.id.mediaSelect_RecyclerView)
    RecyclerView mediaSelectedRecyclerView;
    @BindView(R.id.edt_comment)
    LightEditText commentEditText;
    ArrayList<MediaObject> mediaObjectArrayList = new ArrayList<>();
    MediaSelectResultGalleryAdapter galleryAdapter;

    public static MediaSelect_ResultFragment getInstance() {
        return new MediaSelect_ResultFragment();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_media_select_result;
    }

    @Override
    public void initView() {
        super.initView();
        galleryAdapter = new MediaSelectResultGalleryAdapter(getContext(), mediaObjectArrayList, 5);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        mediaSelectedRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mediaSelectedRecyclerView.setAdapter(galleryAdapter);
        mediaSelectedRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_IDLE) {
                    galleryAdapter.onScrolled(recyclerView);
                }
            }

        });
        galleryAdapter.setListener(this);

    }

    public String getComment(){
        return commentEditText.getText().toString();
    }

    public void setRecyclerView(ArrayList<MediaObject> arrayList){
        mediaObjectArrayList = arrayList;
        galleryAdapter.setObject(arrayList);

//        mediaSelectedRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    galleryAdapter.onScrolled(recyclerView);
//                }
//            }
//
//        });

    }

    @Override
    public void mediaCellClicked(View v, int adapterPosition) {

    }

    @Override
    public void mediaDeleteClicked(View v, int adapterPosition) {
        EventBus.getDefault().post(new Pusher(AppConstants.SELECT_MEDIA_SELECT_ITEM, galleryAdapter.getMediaObjects()));

    }
}
