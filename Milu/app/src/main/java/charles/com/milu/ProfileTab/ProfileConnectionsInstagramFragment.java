package charles.com.milu.ProfileTab;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.Items.RecyclingImageView;
import charles.com.milu.R;
import charles.com.milu.utils.logger.Images;
import charles.com.milu.utils.util.Utils;

/**
 * Created by mac_dev on 10/25/17.
 */

public class ProfileConnectionsInstagramFragment extends BaseFragment implements ProfileConnectionsFollowingRecyclerViewAdapter.RowClickListener,
        ProfileConnectionsFragment.onClickRightButtonListner, ProfileConnectionsFragment.onClickMenuButtonListner {



    @BindView(R.id.instagram_recyclerView)
    RecyclerView recyclerView;

    boolean isCollection;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;


    public static ProfileConnectionsInstagramFragment getInstance() {
        // Required empty public constructor
        ProfileConnectionsInstagramFragment fragment = new ProfileConnectionsInstagramFragment();
        return fragment;
    }
    protected int addView() {
        return R.layout.fragment_profile_instagram;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        isCollection = false;
        populateRecyclerListView();
    }

    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("@virginiatucker");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_collection_icon1);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setSelected(false);
        rightButton2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;

            case R.id.toolbar_btn_right2:
                onClickRightButton();
                break;
        }
    }

    public void onClickRightButton() {
        assert rightButton2 != null;
        rightButton2.setSelected(!rightButton2.isSelected());
        if (rightButton2.isSelected()) {
            isCollection = true;
            rightButton2.setImageResource(R.drawable.nav_bar_list_icon1);
            populateRecyclerCollectionView();
        }else{
            isCollection =false;
            rightButton2.setImageResource(R.drawable.nav_bar_collection_icon1);
            populateRecyclerListView();
        }

    }


    private void populateRecyclerListView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        final ImageAdapter mInstagramAdapter;
        mInstagramAdapter = new ImageAdapter(getContext(), false);
        recyclerView.setAdapter(mInstagramAdapter);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (!isCollection){
                            if (mInstagramAdapter.getItemCount() == 0) {
                                final int columnWidth =
                                        recyclerView.getMeasuredHeight();
                                mInstagramAdapter.setItemHeight(columnWidth);
                                if (Utils.hasJellyBean()) {
                                    recyclerView.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    recyclerView.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }

                            }
                        }
                    }
                });
    }

    private void populateRecyclerCollectionView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        final ImageAdapter mInstagramAdapter;
        mInstagramAdapter = new ImageAdapter(getContext(), true);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        if (isCollection){
                            if (mInstagramAdapter.getItemCount() == 0) {
                                final int numColumns = 3;
                                final int columnWidth =
                                        (recyclerView.getMeasuredWidth() / numColumns);
                                mInstagramAdapter.setItemHeight(columnWidth);
                                if (Utils.hasJellyBean()) {
                                    recyclerView.getViewTreeObserver()
                                            .removeOnGlobalLayoutListener(this);
                                } else {
                                    recyclerView.getViewTreeObserver()
                                            .removeGlobalOnLayoutListener(this);
                                }

                            }
                        }
                    }
                });

        recyclerView.setAdapter(mInstagramAdapter);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        setUserVisibleHint(false);

    }


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

    }

    @Override
    public void onRowClick(View view, int position) {
    }

    @Override
    public void onClickRightButton(boolean b) {
        if (b) {
            populateRecyclerCollectionView();
        }else{
            populateRecyclerListView();
        }
    }

    @Override
    public void onClickMenuButton(int index) {

    }

    private class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context context;
        private int mItemHeight = 0;
        private RecyclerView.LayoutParams mImageViewLayoutParams;
        private boolean isCollection;

        ImageAdapter(Context context, boolean isCollection) {
            super();
            this.context = context;
            this.isCollection = isCollection;
            mImageViewLayoutParams = new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            ImageView imageView;
            imageView = new RecyclingImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(mImageViewLayoutParams);

            final CollectionViewHolder viewHolder = new CollectionViewHolder(imageView);
            if (isCollection) {

                if (viewHolder.imageView.getLayoutParams().height != mItemHeight) {
                    DisplayMetrics metrics;
                    metrics = context.getResources().getDisplayMetrics();
                    int width = metrics.widthPixels;
                    mImageViewLayoutParams = new RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, width / 3);
                    viewHolder.imageView.setLayoutParams(mImageViewLayoutParams);
                }
            }else{
                if (viewHolder.imageView.getLayoutParams().height != mItemHeight) {
                    DisplayMetrics metrics;
                    metrics = context.getResources().getDisplayMetrics();
                    int height = metrics.heightPixels;
                    mImageViewLayoutParams = new RecyclerView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, height);
                    viewHolder.imageView.setLayoutParams(mImageViewLayoutParams);
                }

            }

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                if (isCollection){
                    ((CollectionViewHolder) holder).bindTo(Images.imageThumbUrls[position], context);
                }else{
                    ((CollectionViewHolder) holder).bindTo(Images.imageUrls[position], context);
                }
        }

        void setItemHeight(int height) {
            if (height == mItemHeight) {
                return;
            }
            mItemHeight = height;
            mImageViewLayoutParams =
                    new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return Images.imageThumbUrls.length;
        }

        class CollectionViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            CollectionViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView;
            }

            void bindTo(@NonNull String url, @NonNull Context context) {
                Picasso.with(context).load(url).into(imageView);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
