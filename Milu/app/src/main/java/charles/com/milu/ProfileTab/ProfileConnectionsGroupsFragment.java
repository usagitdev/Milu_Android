package charles.com.milu.ProfileTab;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.appcompat.BuildConfig;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.ehamutcu.sectionpicker.SectionPicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import charles.com.milu.Base.BaseFragment;
import charles.com.milu.HomeScreen.TabBar_userProfile;
import charles.com.milu.Items.RecyclingImageView;
import charles.com.milu.R;
import charles.com.milu.utils.logger.Images;
import charles.com.milu.utils.util.ImageCache;
import charles.com.milu.utils.util.ImageFetcher;
import charles.com.milu.utils.util.Utils;

/**
 * Created by mac_dev on 10/25/17.
 */

public class ProfileConnectionsGroupsFragment extends BaseFragment implements AdapterView.OnItemClickListener{


    private static final String TAG = "ImageGridFragment";
    private static final String IMAGE_CACHE_DIR = "thumbs";

    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private ImageFetcher mImageFetcher;


    private ImageAdapter mFriendsAdapter;
    private ImageAdapter mFamilyAdapter;
    private ImageAdapter mFraternityAdapter;

    private GridView gFriendsGridView;
    private GridView gFamilyGridView;
    private GridView gFraternityGridView;

    public ProfileConnectionsGroupsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_connections_groups, container, false);

        gFriendsGridView = (GridView) view.findViewById(R.id.grid_friends);
        gFamilyGridView = (GridView) view.findViewById(R.id.grid_family);
        gFraternityGridView = (GridView) view.findViewById(R.id.grid_fraternity);

        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);

        mFriendsAdapter = new ImageAdapter(getActivity());
        mFamilyAdapter = new ImageAdapter(getActivity());
        mFraternityAdapter = new ImageAdapter(getActivity());
        ImageCache.ImageCacheParams cacheParams =
                new ImageCache.ImageCacheParams(getActivity(), IMAGE_CACHE_DIR);

        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory

        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        mImageFetcher = new ImageFetcher(getActivity(), mImageThumbSize);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.addImageCache(getActivity().getSupportFragmentManager(), cacheParams);

        setGridFamily();
        setGridFriends();
        setGridFraternity();
        return view;
    }

    public void setGridFriends() {

        gFriendsGridView.setAdapter(mFriendsAdapter);
        gFriendsGridView.setOnItemClickListener(this);
        gFriendsGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        gFriendsGridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mFriendsAdapter.getNumColumns() == 0) {
                            final int numColumns = 6;
                            if (numColumns > 0) {
                                final int columnWidth =
                                        (gFriendsGridView.getWidth() / numColumns) - mImageThumbSpacing;
                                mFriendsAdapter.setNumColumns(numColumns);
                                mFriendsAdapter.setItemHeight(columnWidth);
                                if (BuildConfig.DEBUG) {
                                    Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                }
                                if (Utils.hasJellyBean()) {
                                    gFriendsGridView.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    gFriendsGridView.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }
                                ViewGroup.LayoutParams glp = gFriendsGridView.getLayoutParams();
                                glp.height = columnWidth * 2;//convertDpToPixels(columnWidth * 2, getContext());
                                gFriendsGridView.setLayoutParams(glp);

                            }
                        }
                    }
                });

    }

    public void setGridFamily() {

        gFamilyGridView.setAdapter(mFamilyAdapter);
        gFamilyGridView.setOnItemClickListener(this);
        gFamilyGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        gFamilyGridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mFamilyAdapter.getNumColumns() == 0) {
                            final int numColumns = 6;
                            if (numColumns > 0) {
                                final int columnWidth =
                                        (gFamilyGridView.getWidth() / numColumns) - mImageThumbSpacing;
                                mFamilyAdapter.setNumColumns(numColumns);
                                mFamilyAdapter.setItemHeight(columnWidth);
                                if (BuildConfig.DEBUG) {
                                    Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                }
                                if (Utils.hasJellyBean()) {
                                    gFamilyGridView.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    gFamilyGridView.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }
                                ViewGroup.LayoutParams glp = gFamilyGridView.getLayoutParams();
                                glp.height = columnWidth * 2;//convertDpToPixels(columnWidth * 2, getContext());
                                gFamilyGridView.setLayoutParams(glp);

                            }
                        }
                    }
                });

    }

    public void setGridFraternity() {

        gFraternityGridView.setAdapter(mFraternityAdapter);
        gFraternityGridView.setOnItemClickListener(this);
        gFraternityGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        gFraternityGridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (mFraternityAdapter.getNumColumns() == 0) {
                            final int numColumns = 6;
                            if (numColumns > 0) {
                                final int columnWidth =
                                        (gFraternityGridView.getWidth() / numColumns) - mImageThumbSpacing;
                                mFraternityAdapter.setNumColumns(numColumns);
                                mFraternityAdapter.setItemHeight(columnWidth);
                                if (BuildConfig.DEBUG) {
                                    Log.d(TAG, "onCreateView - numColumns set to " + numColumns);
                                }
                                if (Utils.hasJellyBean()) {
                                    gFraternityGridView.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    gFraternityGridView.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }
                                ViewGroup.LayoutParams glp = gFraternityGridView.getLayoutParams();
                                glp.height = columnWidth * 2;//convertDpToPixels(columnWidth * 2, getContext());
                                gFraternityGridView.setLayoutParams(glp);

                            }
                        }
                    }
                });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//        CustomDialogClass cdd=new CustomDialogClass(mAct);
//        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        WindowManager.LayoutParams lp = cdd.getWindow().getAttributes();
//        lp.dimAmount=0.0f;
//        cdd.getWindow().setAttributes(lp);
//        cdd.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//        cdd.show();


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
        mFriendsAdapter.notifyDataSetChanged();
        mFamilyAdapter.notifyDataSetChanged();
        mFraternityAdapter.notifyDataSetChanged();
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
