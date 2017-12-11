package charles.com.milu.HomeScreen;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.appcompat.BuildConfig;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import at.favre.lib.dali.builder.live.LiveBlurWorker;
import butterknife.BindView;
import charles.com.milu.Adapters.ProfileGridViewAdapter;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.ExtraLightTextView;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.Items.ProfileGridItem;
import charles.com.milu.Items.RecyclingImageView;
import charles.com.milu.ProfileTab.ProfileConnectionsFragment;
import charles.com.milu.ProfileTab.ProfileConnectionsInstagramFragment;
import charles.com.milu.ProfileTab.ProfileInterestSettingFragment;
import charles.com.milu.ProfileTab.ProfileSettingFragment;
import charles.com.milu.R;
import charles.com.milu.utils.CustomImageButton;
import charles.com.milu.utils.logger.Images;
import charles.com.milu.utils.util.ImageCache;
import charles.com.milu.utils.util.ImageFetcher;
import charles.com.milu.utils.util.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherUserProfileFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    private static final String TAG = "ImageGridFragment";
    private static final String IMAGE_CACHE_DIR = "thumbs";

    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private ImageFetcher mImageFetcher;


    private ImageAdapter mInstagramAdapter;
    private ImageAdapter mConnectionsAdapter;
    private ImageAdapter mEventsAdapter;
    private ImageAdapter mMomentsAdapter;
    private ImageAdapter mInterestsAdapter;

    @BindView(R.id.grid_instagram)
    GridView gridInstagram;

    @BindView(R.id.grid_connections)
    GridView gridConnection;

    @BindView(R.id.grid_moments)
    GridView gridMoments;

    @BindView(R.id.grid_events)
    GridView gridEvents;

    @BindView(R.id.grid_interests)
    GridView gridInterests;


    @BindView(R.id.txtUserName)
    TitleTextView txtUserName;

    @BindView(R.id.txtUserPoint)
    ExtraLightTextView txtUserPoint;

    @BindView(R.id.txtUserWord)
    TitleTextView txtUserWord;

    @BindView(R.id.txtMore)
    TitleTextView txtMore;


    @BindView(R.id.toolbar_btn_left)
    CustomImageButton appIcon;

    @BindView(R.id.toolbar_txt_left)
    TitleTextView titleTextView;

    @BindView(R.id.toolbar_btn_right2)
    CustomImageButton rightButton2;

    @BindView(R.id.toolbar_btn_right1)
    CustomImageButton rightButton1;

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    private ProfileGridViewAdapter gridViewAdapter;
    LiveBlurWorker blurWorker;
    public static OtherUserProfileFragment getInstance() {
        return new OtherUserProfileFragment();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_other_user_profile;
    }

    @Override
    public void initView() {
        super.initView();
        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);

        mInstagramAdapter = new ImageAdapter(getActivity());
        mConnectionsAdapter = new ImageAdapter(getActivity());
        mMomentsAdapter = new ImageAdapter(getActivity());
        mEventsAdapter = new ImageAdapter(getActivity());
        mInterestsAdapter = new ImageAdapter(getActivity());

        ImageCache.ImageCacheParams cacheParams =
                new ImageCache.ImageCacheParams(getActivity(), IMAGE_CACHE_DIR);

        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory

        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        mImageFetcher = new ImageFetcher(getActivity(), mImageThumbSize);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.addImageCache(getActivity().getSupportFragmentManager(), cacheParams);


        setToolBar();
        setGridInstagram();
        setGridConnection();
        setGridMoments();
        setGridEvents();
        setGridInterests();
    }

    @Override
    public void setToolBar(){
        super.setToolBar();
//        blurWorker = Dali.create(mAct).liveBlur(nestedScrollView, imvBlur).downScale(8).assemble(true);
        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);
        titleTextView.setText("user profile");
        rightButton1.setVisibility(View.INVISIBLE);
        rightButton2.setImageResource(R.drawable.nav_bar_setting_icon);
        rightButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.toolbar_btn_left :
                onBackPressed();
                break;
            case R.id.toolbar_btn_right2 :
                break;
            default:

        }
    }

    public void gotoProfileSetting(){

//        addChildFragment(ProfileSettingFragment.getInstance(), true);
    }

    public void setGridInstagram() {

        gridInstagram.setAdapter(mInstagramAdapter);
        gridInstagram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                addChildFragment(ProfileConnectionsInstagramFragment.getInstance(), true);
            }
        });
        gridInstagram.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // Pause fetcher to ensure smoother scrolling when flinging
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
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

        // This listener is used to get the final width of the GridView and then calculate the
        // number of columns and the width of each column. The width of each column is variable
        // as the GridView has stretchMode=columnWidth. The column width is used to set the height
        // of each view so we get nice square thumbnails.
        gridInstagram.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mInstagramAdapter.getNumColumns() == 0) {
                            final int numColumns = 6;
                            if (numColumns > 0) {
                                final int columnWidth =
                                        (gridInstagram.getWidth() / numColumns) - mImageThumbSpacing;
                                mInstagramAdapter.setNumColumns(numColumns);
                                mInstagramAdapter.setItemHeight(columnWidth);
                                if (BuildConfig.DEBUG) {
                                    Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                }
                                if (Utils.hasJellyBean()) {
                                    gridInstagram.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    gridInstagram.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }
                                ViewGroup.LayoutParams glp = gridInstagram.getLayoutParams();
                                glp.height = columnWidth * 2;//convertDpToPixels(columnWidth * 2, getContext());
                                gridInstagram.setLayoutParams(glp);

                            }
                        }
                    }
                });

    }


    public void setGridConnection() {

        gridConnection.setAdapter(mConnectionsAdapter);
        gridConnection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                addChildFragment(ProfileConnectionsFragment.getInstance(), true);
            }
        });
        gridConnection.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // Pause fetcher to ensure smoother scrolling when flinging
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
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

        // This listener is used to get the final width of the GridView and then calculate the
        // number of columns and the width of each column. The width of each column is variable
        // as the GridView has stretchMode=columnWidth. The column width is used to set the height
        // of each view so we get nice square thumbnails.
        gridConnection.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mConnectionsAdapter.getNumColumns() == 0) {
                            final int numColumns = 6;
                            if (numColumns > 0) {
                                final int columnWidth =
                                        (gridConnection.getWidth() / numColumns) - mImageThumbSpacing;
                                mConnectionsAdapter.setNumColumns(numColumns);
                                mConnectionsAdapter.setItemHeight(columnWidth);
                                if (BuildConfig.DEBUG) {
                                    Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                }
                                if (Utils.hasJellyBean()) {
                                    gridConnection.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    gridConnection.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }
                                ViewGroup.LayoutParams glp = gridConnection.getLayoutParams();
                                glp.height = columnWidth * 2;//convertDpToPixels(columnWidth * 2, getContext());
                                gridConnection.setLayoutParams(glp);
                            }
                        }
                    }
                });
    }

    public void setGridMoments() {

        gridMoments.setAdapter(mMomentsAdapter);
        gridMoments.setOnItemClickListener(this);
        gridMoments.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // Pause fetcher to ensure smoother scrolling when flinging
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
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

        // This listener is used to get the final width of the GridView and then calculate the
        // number of columns and the width of each column. The width of each column is variable
        // as the GridView has stretchMode=columnWidth. The column width is used to set the height
        // of each view so we get nice square thumbnails.
        gridMoments.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mMomentsAdapter.getNumColumns() == 0) {
                            final int numColumns = 6;
                            if (numColumns > 0) {
                                final int columnWidth =
                                        (gridMoments.getWidth() / numColumns) - mImageThumbSpacing;
                                mMomentsAdapter.setNumColumns(numColumns);
                                mMomentsAdapter.setItemHeight(columnWidth);
                                if (BuildConfig.DEBUG) {
                                    Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                }
                                if (Utils.hasJellyBean()) {
                                    gridMoments.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    gridMoments.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }
                                ViewGroup.LayoutParams glp = gridMoments.getLayoutParams();
                                glp.height = columnWidth * 2;//convertDpToPixels(columnWidth * 2, getContext());
                                gridMoments.setLayoutParams(glp);
                            }
                        }
                    }
                });

    }

    public void setGridEvents() {

        gridEvents.setAdapter(mEventsAdapter);
        gridEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        gridEvents.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // Pause fetcher to ensure smoother scrolling when flinging
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
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

        // This listener is used to get the final width of the GridView and then calculate the
        // number of columns and the width of each column. The width of each column is variable
        // as the GridView has stretchMode=columnWidth. The column width is used to set the height
        // of each view so we get nice square thumbnails.
        gridEvents.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mEventsAdapter.getNumColumns() == 0) {
                            final int numColumns = 6;
                            if (numColumns > 0) {
                                final int columnWidth =
                                        (gridEvents.getWidth() / numColumns) - mImageThumbSpacing;
                                mEventsAdapter.setNumColumns(numColumns);
                                mEventsAdapter.setItemHeight(columnWidth);
                                if (BuildConfig.DEBUG) {
                                    Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                }
                                if (Utils.hasJellyBean()) {
                                    gridEvents.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    gridEvents.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }
                                ViewGroup.LayoutParams glp = gridEvents.getLayoutParams();
                                glp.height = columnWidth * 2;//convertDpToPixels(columnWidth * 2, getContext());
                                gridEvents.setLayoutParams(glp);
                            }
                        }
                    }
                });

    }

    public void setGridInterests() {

        gridInterests.setAdapter(mInterestsAdapter);
        gridInterests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                addChildFragment(ProfileInterestSettingFragment.getInstance(), true);

            }
        });
        gridInterests.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // Pause fetcher to ensure smoother scrolling when flinging
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
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

        // This listener is used to get the final width of the GridView and then calculate the
        // number of columns and the width of each column. The width of each column is variable
        // as the GridView has stretchMode=columnWidth. The column width is used to set the height
        // of each view so we get nice square thumbnails.
        gridInterests.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mInterestsAdapter.getNumColumns() == 0) {
//                            final int numColumns = (int) Math.floor(
//                                    gridInstagram.getWidth() / (mImageThumbSize + mImageThumbSpacing));
                            final int numColumns = 6;
                            if (numColumns > 0) {
                                final int columnWidth =
                                        (gridInterests.getWidth() / numColumns) - mImageThumbSpacing;
                                mInterestsAdapter.setNumColumns(numColumns);
                                mInterestsAdapter.setItemHeight(columnWidth);
                                if (BuildConfig.DEBUG) {
                                    Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                }
                                if (Utils.hasJellyBean()) {
                                    gridInterests.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    gridInterests.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }
                                ViewGroup.LayoutParams glp = gridInterests.getLayoutParams();
                                glp.height = columnWidth * 2;//convertDpToPixels(columnWidth * 2, getContext());
                                gridInterests.setLayoutParams(glp);
                            }
                        }
                    }
                });

    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

    }


    private ArrayList<ProfileGridItem> getData() {
        ArrayList<ProfileGridItem> gridItems = new ArrayList<>();
        for (int i = 0; i < Images.imageThumbUrls.length; i++){
            gridItems.add(new ProfileGridItem(Images.imageThumbUrls[i]));
        }
        return gridItems;
    }


    private class ImageAdapter extends BaseAdapter {

        private final Context mContext;
        private int mItemHeight = 0;
        private int mNumColumns = 0;
        private int mActionBarHeight = 0;
        private GridView.LayoutParams mImageViewLayoutParams;

        public ImageAdapter(Context context) {
            super();
            mContext = context;
            mImageViewLayoutParams = new GridView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        @Override
        public int getCount() {
            // If columns have yet to be determined, return no items
            if (getNumColumns() == 0) {
                return 0;
            }

            // Size + number of columns for top empty row
            return Images.imageThumbUrls.length + mNumColumns;
        }

        @Override
        public Object getItem(int position) {
            return position < mNumColumns ?
                    null : Images.imageThumbUrls[position - mNumColumns];
        }

        @Override
        public long getItemId(int position) {
            return position < mNumColumns ? 0 : position - mNumColumns;
        }

        @Override
        public int getViewTypeCount() {
            // Two types of views, the normal ImageView and the top row of empty views
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return (position < mNumColumns) ? 1 : 0;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            //BEGIN_INCLUDE(load_gridview_item)
            // First check if this is the top row
            if (position < mNumColumns) {
                if (convertView == null) {
                    convertView = new View(mContext);
                }
                // Set empty view with height of ActionBar
                convertView.setLayoutParams(new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, mActionBarHeight));
                return convertView;
            }

            // Now handle the main ImageView thumbnails
            ImageView imageView;
            if (convertView == null) { // if it's not recycled, instantiate and initialize
                imageView = new RecyclingImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(mImageViewLayoutParams);
            } else { // Otherwise re-use the converted view
                imageView = (ImageView) convertView;
            }

            // Check the height matches our calculated column width
            if (imageView.getLayoutParams().height != mItemHeight) {
                imageView.setLayoutParams(mImageViewLayoutParams);
            }

            // Finally load the image asynchronously into the ImageView, this also takes care of
            // setting a placeholder image while the background thread runs
            mImageFetcher.loadImage(Images.imageThumbUrls[position - mNumColumns], imageView);
            return imageView;
            //END_INCLUDE(load_gridview_item)
        }

        /**
         * Sets the item height. Useful for when we know the column width so the height can be set
         * to match.
         *
         * @param height
         */
        public void setItemHeight(int height) {
            if (height == mItemHeight) {
                return;
            }
            mItemHeight = height;
            mImageViewLayoutParams =
                    new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
            mImageFetcher.setImageSize(height);
            notifyDataSetChanged();
        }

        public void setNumColumns(int numColumns) {
            mNumColumns = numColumns;
        }

        public int getNumColumns() {
            return mNumColumns;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mImageFetcher.setExitTasksEarly(false);
        mInstagramAdapter.notifyDataSetChanged();
        mConnectionsAdapter.notifyDataSetChanged();
        mMomentsAdapter.notifyDataSetChanged();
        mEventsAdapter.notifyDataSetChanged();
        mInterestsAdapter.notifyDataSetChanged();
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
