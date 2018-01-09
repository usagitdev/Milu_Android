package charles.com.milu.HomeScreen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.GlobalFeed.GlobalFeedItem;
import charles.com.milu.GlobalFeed.GlobalfeedAdapter;
import charles.com.milu.MiluApplication;
import charles.com.milu.R;
import charles.com.milu.utils.CustomImageButton;
import charles.com.milu.utils.Utilities;
import charles.com.milu.utils.util.ImageCache;
import charles.com.milu.utils.util.ImageFetcher;
import charles.com.milu.utils.util.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabBar_livefeed extends BaseFragment implements GlobalfeedAdapter.ItemClickListener{

    ArrayList<GlobalFeedItem> feedItems;

    @BindView(R.id.livefeed_RecyclerView)
    RecyclerViewPager rvContacts;

    @BindView(R.id.toolbar_btn_left)
    CustomImageButton appIcon;

    @BindView(R.id.toolbar_txt_left)
    TitleTextView titleTextView;

    @BindView(R.id.toolbar_btn_right1)
    CustomImageButton rightButton1;

    @BindView(R.id.toolbar_btn_right2)
    CustomImageButton rightButton2;

    @BindView(R.id.empty_feed_view)
    LinearLayout emptyFeedView;


    GlobalfeedAdapter adapter;

    private static final String TAG = "TabBar_livefeed";
    private static final String IMAGE_CACHE_DIR = "thumbs";
    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private ImageFetcher mImageFetcher;

    public static TabBar_livefeed getInstance() {
        return new TabBar_livefeed();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_tab_bar_livefeed;
    }

    @Override
    public void initView() {
        super.initView();
        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size1);
        ImageCache.ImageCacheParams cacheParams =
                new ImageCache.ImageCacheParams(getActivity(), IMAGE_CACHE_DIR);

        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory

        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        mImageFetcher = new ImageFetcher(getActivity(), mImageThumbSize);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.addImageCache(getActivity().getSupportFragmentManager(), cacheParams);
        if (((MiluApplication) mAct.getApplication()).appInfo.getUserLogin()) {
            emptyFeedView.setVisibility(View.GONE);
            rvContacts.setVisibility(View.VISIBLE);
        }else {
            emptyFeedView.setVisibility(View.VISIBLE);
            rvContacts.setVisibility(View.GONE);
        }
        setRecyclerView();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();


        appIcon.setImageResource(R.drawable.logo);
        titleTextView.setText("milu");
        rightButton1.setVisibility(View.INVISIBLE);
        rightButton2.setImageResource(R.drawable.nav_bar_filter_icon);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void setRecyclerView(){

        Utilities.setLayoutManager(mAct, rvContacts, true, true, false);
        // Initialize contacts
        feedItems = GlobalFeedItem.createContactsList(0);
        feedItems.add(0, new GlobalFeedItem(0,"andrea bocceli",R.drawable.user01,"1.6K",R.drawable.image1,R.string.globalfeed_description1, R.string.globalfeed_property1));
        feedItems.add(1, new GlobalFeedItem(1,"maxim",R.drawable.user02,"1.5K",R.drawable.image2,R.string.globalfeed_description2, R.string.globalfeed_property2));
        feedItems.add(2, new GlobalFeedItem(2,"vasily",R.drawable.user03,"1.8K",R.drawable.image3,R.string.globalfeed_description3, R.string.globalfeed_property3));
        feedItems.add(3, new GlobalFeedItem(3,"krystsina",R.drawable.user04,"1.4K",R.drawable.image4,R.string.globalfeed_description4, R.string.globalfeed_property4));
        feedItems.add(4, new GlobalFeedItem(4,"mihail",R.drawable.user05,"3.8K",R.drawable.image5,R.string.globalfeed_description5, R.string.globalfeed_property5));
        feedItems.add(5, new GlobalFeedItem(5,"ognijen",R.drawable.user06,"7.8K",R.drawable.image6,R.string.globalfeed_description6, R.string.globalfeed_property6));
        feedItems.add(6, new GlobalFeedItem(6,"ognijen",R.drawable.user07,"7.8K",R.drawable.image6,R.string.globalfeed_description6, R.string.globalfeed_property6));
        feedItems.add(7, new GlobalFeedItem(7,"ognijen",R.drawable.user08,"7.8K",R.drawable.image6,R.string.globalfeed_description6, R.string.globalfeed_property6));

        adapter = new GlobalfeedAdapter(mAct, feedItems);
        rvContacts.setAdapter(adapter);
        adapter.setListener(this);

        LinearLayoutManager layout = new LinearLayoutManager(mAct, LinearLayoutManager.VERTICAL,
                false);
        rvContacts.setTriggerOffset(0.0f);
        rvContacts.setFlingFactor(0.0f);
        rvContacts.setLayoutManager(layout);
        rvContacts.setHasFixedSize(true);
        rvContacts.setLongClickable(true);

//        rvContacts.setVisibility(View.GONE);

        rvContacts.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Before Honeycomb pause image loading on scroll to help with performance
                    if (!Utils.hasHoneycomb()) {
                        mImageFetcher.setPauseWork(true);
                    }
                } else {
                    mImageFetcher.setPauseWork(false);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
//                mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
                super.onScrolled(recyclerView, i, i2);
                int childCount = rvContacts.getChildCount();
                int width = rvContacts.getChildAt(0).getWidth();
                int padding = (rvContacts.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    if (v.getTop() <= padding) {
                        if (v.getTop() >= padding - v.getHeight()) {
                            rate = (padding - v.getTop()) * 1f / v.getHeight();
                        } else {
                            rate = 1;
                        }
//                        v.setScaleX(1 - rate * 0.1f);
//                        v.setScaleY(1 - rate * 0.1f);
                    } else {
                        //往右 从 padding 到 recyclerView.getHeight()-padding 的过程中，由大到小
                        if (v.getTop() <= recyclerView.getHeight() - padding) {
                            rate = (recyclerView.getHeight() - padding - v.getTop()) * 1f / v.getHeight();
                        }
//                        v.setScaleX(0.9f + rate * 0.1f);
//                        v.setScaleY(0.9f + rate * 0.1f);
                    }
                }
            }
        });

        rvContacts.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (rvContacts.getChildCount() < 3) {
                    if (rvContacts.getChildAt(1) != null) {
                        View v1 = rvContacts.getChildAt(1);
                        v1.setScaleY(1f);
                    }
                } else {
                    if (rvContacts.getChildAt(0) != null) {
                        View v0 = rvContacts.getChildAt(0);
                        v0.setScaleY(1f);
                    }
                    if (rvContacts.getChildAt(2) != null) {
                        View v2 = rvContacts.getChildAt(2);
                        v2.setScaleY(1f);
                    }
                }

            }
        });

    }

    @Override
    public void likeButtonClicked(View view, int position) {
        Log.e("Like Button Clicked", String.valueOf(position));

        Toast.makeText(view.getContext(),"like Button Clicked!! cell position = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shareButtuonClicked(View view, int position) {
        Log.e("Share Button Clicked", String.valueOf(position));

        Toast.makeText(view.getContext(),"share Button Clicked!! cell position = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moreButtonClicked(View view, int position) {
        Log.e("More Button Clicked", String.valueOf(position));
        Toast.makeText(view.getContext(),"more Button Clicked!!   cell position = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void chatButtonClicked(View view, int positon) {
        Log.e("chat Button Clicked", String.valueOf(positon));
        Toast.makeText(view.getContext(),"chat Button Clicked!!   cell position = " + String.valueOf(positon), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void upvoteButton1Clicked(View view, int positon) {

        Toast.makeText(view.getContext(),"upvote Button1 Clicked!!   cell position = " + String.valueOf(positon), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void upvoteButton2Clicked(View view, int positon) {

        Toast.makeText(view.getContext(),"upvote Button2 Clicked!!   cell position = " + String.valueOf(positon), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mImageFetcher.setExitTasksEarly(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        mImageFetcher.setPauseWork(false);
        mImageFetcher.setExitTasksEarly(true);
        mImageFetcher.flushCache();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mImageFetcher.closeCache();
    }


}
