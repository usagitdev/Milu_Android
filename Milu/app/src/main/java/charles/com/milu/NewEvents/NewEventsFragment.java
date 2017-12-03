package charles.com.milu.NewEvents;

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
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.R;

public class NewEventsFragment extends BaseFragment implements NewEventsAdapter.ItemClickListener{

    @BindView(R.id.newEvent_RecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.newEvent_distance_seekbar)
    SeekBar mDistanceSeekbar;

    @BindView(R.id.newEvent_FilterTitleBar)
    RelativeLayout mFilterTitileBar;

    @BindView(R.id.newEvent_filterscrollview)
    ScrollView mFilterScrollView;

    @BindView(R.id.newEvent_filter_closebtn)
    ImageView filterClosebtn;

    @BindView(R.id.newEvent_Filterlayout)
    RelativeLayout mFilterLayout;

    ArrayList<NewEventItem> mEventItems;
    Typeface workSans_Light;
    ImageView backButton;
    RelativeLayout mTitleLayout;


    public static NewEventsFragment getInstance() {
        // Required empty public constructor
        NewEventsFragment fragment = new NewEventsFragment();
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
        return R.layout.eventdash_newevents_fragment;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setAdapter();
        setFilterButton();
        setFiltercloseButton();
        setFilterScrollView();
        setFilterTitleBar();
        setSeekBar();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert txtLeft != null;
        txtLeft.setText("newevents");

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
//        View view = inflater.inflate(R.layout.eventdash_newevents_fragment, container, false);
//
//        setTitleType(view);
//        setBackButton(view);
//        setAdapter(view);
//        setTitleBar_BlurEffect(view);
//        setFilterButton(view);
//        setFiltercloseButton(view);
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

    public void setFiltercloseButton(){

        filterClosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFilterLayout.animate().translationX(0);

            }
        });
    }

    public void setFilterButton(){

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
    public void setTitleBar_BlurEffect(){

//        mTitleLayout = (RelativeLayout) view.findViewById(R.id.newEvent_TitleBar);
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


        mEventItems = NewEventItem.createContactsList(0);
        mEventItems.add(0, new NewEventItem(R.drawable.neweventback00,"the roots","2K people • 12 interests"));
        mEventItems.add(1, new NewEventItem(R.drawable.neweventback01,"chemical brothers","2K people • 12 interests"));
        mEventItems.add(2, new NewEventItem(R.drawable.neweventback02,"daft punk","223 people • 126 interests"));
        mEventItems.add(3, new NewEventItem(R.drawable.neweventback03,"black keys","2K people • 123 interests"));
        mEventItems.add(4, new NewEventItem(R.drawable.neweventback04,"chemical brothers","223 people • 345 interests"));
        mEventItems.add(5, new NewEventItem(R.drawable.neweventback05,"absolutely fabulous","245 people • 19 interests"));
        mEventItems.add(6, new NewEventItem(R.drawable.neweventback06,"amfa afterparty with me","2K people • 12 interests"));
        mEventItems.add(7, new NewEventItem(R.drawable.neweventback07,"cocktail party","2K people • 234 interests"));
        mEventItems.add(8, new NewEventItem(R.drawable.neweventback08,"halloween midnight party","2K people • 34 interests"));
        mEventItems.add(9, new NewEventItem(R.drawable.neweventback00,"the roots","2K people • 45 interests"));
        mEventItems.add(10, new NewEventItem(R.drawable.neweventback01,"chemical brothers","2K people • 234 interests"));
        mEventItems.add(11, new NewEventItem(R.drawable.neweventback02,"daft puck party","2K people • 56 interests"));
        mEventItems.add(12, new NewEventItem(R.drawable.neweventback03,"chmical party with hie","2K people • 12 interests"));
        mEventItems.add(13, new NewEventItem(R.drawable.neweventback04,"absolutely fabulous","2K people • interests"));
        mEventItems.add(14, new NewEventItem(R.drawable.neweventback05,"all-star after party","2K people • 456 interests"));
        mEventItems.add(15, new NewEventItem(R.drawable.neweventback06,"cocktail party","2K people • 56 interests"));
        mEventItems.add(16, new NewEventItem(R.drawable.neweventback07,"halloween midnight party","2K people • 23 interests"));
        mEventItems.add(17, new NewEventItem(R.drawable.neweventback08,"amfa afterparty with me","2K people • 45 interests"));

        NewEventsAdapter adapter = new NewEventsAdapter(getContext(), mEventItems);
        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 0, true));

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
    public void mEventCell_Clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," New Event item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }
}
