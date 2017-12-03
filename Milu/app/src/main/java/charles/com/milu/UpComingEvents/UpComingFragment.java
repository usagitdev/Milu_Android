package charles.com.milu.UpComingEvents;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import net.robinx.lib.blur.utils.BlurUtils;
import net.robinx.lib.blur.widget.BlurDrawable;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.EventBusListeners.ReplaceFragmentListener;
import charles.com.milu.HomeScreen.Tabbar_eventdash;
import charles.com.milu.LiveEvents.LiveFeedMain.LiveFeedMainFragment;
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.R;
import charles.com.milu.UpcomingEventHostPage.UpcomingHostFragment;

public class UpComingFragment extends BaseFragment implements UpComingAdapter.ItemClickListener{


    @BindView(R.id.upEvent_distance_seekbar)
    SeekBar mDistanceSeekbar;

    @BindView(R.id.upEvent_filterscrollview)
    ScrollView mFilterScrollView;

    @BindView(R.id.upEvent_filter_closebtn)
    ImageView filterClosebtn;

    @BindView(R.id.upEvent_Filterlayout)
    RelativeLayout mFilterLayout;

    @BindView(R.id.upComingEvent_RecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.upEvent_FilterTitleBar)
    RelativeLayout mFilterTitileBar;


    ArrayList<UpComingItem> upComingItems;
    Typeface workSans_Light;
    ImageView backButton;


    public static UpComingFragment getInstance() {
        // Required empty public constructor
        UpComingFragment fragment = new UpComingFragment();
        return fragment;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
            case R.id.toolbar_btn_right2:
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) getContext()).getWindowManager()
                        .getDefaultDisplay()
                        .getMetrics(displayMetrics);
                mFilterLayout.animate().translationX(-mFilterLayout.getWidth());
                break;
        }
    }
    @Override
    protected int addView() {
        return R.layout.eventdash_upcoming_fragment;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setAdapter();
        setTitleBar_Blureffect();
        setFilterButtons();
        setFilter_CloseButton();
        setFilterScrollView();
        setFilterTitleBar();
        setSeekBar();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert txtLeft != null;
        txtLeft.setText("upcoming events");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_filter_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);
    }



//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.eventdash_upcoming_fragment, container, false);
//
//        setTitleType(view);
//        setBackButton(view);
//        setAdapter(view);
//        setTitleBar_Blureffect(view);
//        setFilterButtons(view);
//        setFilter_CloseButton(view);
//        setFilterScrollView(view);
//        setFilterTitleBar(view);
//        setSeekBar(view);
//
//        return view;
//    }
//
    public void setSeekBar(){

        mDistanceSeekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.color_Milumain), PorterDuff.Mode.SRC_IN);
        mDistanceSeekbar.getThumb().setColorFilter(getResources().getColor(R.color.color_Milumain), PorterDuff.Mode.SRC_IN);

    }

    public void setFilterTitleBar(){

        mFilterTitileBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFilterTitileBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
                blurDrawable.setDrawOffset(mFilterTitileBar.getLeft(), mFilterTitileBar.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
                blurDrawable.setCornerRadius(2);
                blurDrawable.setBlurRadius(5);
                mFilterTitileBar.setBackgroundDrawable(blurDrawable);
            }
        });
    }

    public void setFilterScrollView(){

        mFilterScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFilterScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
                blurDrawable.setDrawOffset(mFilterScrollView.getLeft(), mFilterScrollView.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
                blurDrawable.setCornerRadius(2);
                blurDrawable.setBlurRadius(5);
                mFilterScrollView.setBackgroundDrawable(blurDrawable);
            }
        });
    }

    public void setFilter_CloseButton(){

            filterClosebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mFilterLayout.animate().translationX(0);
                }
            });

    }

    public void setFilterButtons(){

//            filterBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    DisplayMetrics displayMetrics = new DisplayMetrics();
//                    ((Activity) getContext()).getWindowManager()
//                            .getDefaultDisplay()
//                            .getMetrics(displayMetrics);
//                    mFilterLayout.animate().translationX(-mFilterLayout.getWidth());
//
//                }
//            });
    }

    public void setTitleBar_Blureffect(){

//        mTitleLayout = (RelativeLayout) view.findViewById(R.id.upComingEvent_TitleBar);
//        mTitleLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mTitleLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
//                blurDrawable.setDrawOffset(mTitleLayout.getLeft(), mTitleLayout.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
//                blurDrawable.setCornerRadius(5);
//                blurDrawable.setBlurRadius(5);
//                mTitleLayout.setBackgroundDrawable(blurDrawable);
//            }
//        });
    }

    public void setAdapter(){


        upComingItems = UpComingItem.createContactsList(0);
        upComingItems.add(0, new UpComingItem(R.drawable.neweventback00,"the roots","2K people","1D"));
        upComingItems.add(1, new UpComingItem(R.drawable.neweventback01,"chemical brothers","2K people","16H"));
        upComingItems.add(2, new UpComingItem(R.drawable.neweventback02,"daft punk","2K people","12H"));
        upComingItems.add(3, new UpComingItem(R.drawable.neweventback03,"black keys","2K people","9H"));
        upComingItems.add(4, new UpComingItem(R.drawable.neweventback04,"chemical brothers","2K people","2H"));
        upComingItems.add(5, new UpComingItem(R.drawable.neweventback05,"the roots","2K people","3H"));
        upComingItems.add(6, new UpComingItem(R.drawable.neweventback06,"daft punk","2K people","5D"));
        upComingItems.add(7, new UpComingItem(R.drawable.neweventback07,"black keys","2K people","12H"));
        upComingItems.add(8, new UpComingItem(R.drawable.neweventback08,"inxa rave","2K people","4D"));
        upComingItems.add(9, new UpComingItem(R.drawable.neweventback00,"bbq party","2K people","5D"));
        upComingItems.add(10, new UpComingItem(R.drawable.neweventback01,"chemical brothers","2K people","3H"));
        upComingItems.add(11, new UpComingItem(R.drawable.neweventback02,"summer beach party","2K people","3H"));
        upComingItems.add(12, new UpComingItem(R.drawable.neweventback03,"black keys","2K people","4D"));
        upComingItems.add(13, new UpComingItem(R.drawable.neweventback04,"chemical brothers","2K people","2H"));
        upComingItems.add(14, new UpComingItem(R.drawable.neweventback05,"all-star after party","2K people","36H"));
        upComingItems.add(15, new UpComingItem(R.drawable.neweventback06,"daft punk","2K people","23H"));
        upComingItems.add(16, new UpComingItem(R.drawable.neweventback07,"black keys","2K people","5D"));
        upComingItems.add(17, new UpComingItem(R.drawable.neweventback08,"the roots","2K people","34H"));

        UpComingAdapter adapter = new UpComingAdapter(getContext(), upComingItems);
        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 5, true));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                ViewCompat.postInvalidateOnAnimation(mTitleLayout);
                ViewCompat.postInvalidateOnAnimation(mFilterScrollView);
                ViewCompat.postInvalidateOnAnimation(mFilterTitileBar);

            }
        });

    }


    @Override
    public void upComingEventCell_Clicked(View v, int adapterPosition) {

        addChildFragment(UpcomingHostFragment.getInstance(), true);

//        UpcomingHostFragment eventFragment = new UpcomingHostFragment();
//        EventBus.getDefault().post(new ReplaceFragmentListener(eventFragment, true));

    }
}
