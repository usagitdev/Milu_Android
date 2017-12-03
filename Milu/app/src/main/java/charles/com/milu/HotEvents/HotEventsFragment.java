package charles.com.milu.HotEvents;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import net.robinx.lib.blur.utils.BlurUtils;
import net.robinx.lib.blur.widget.BlurDrawable;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.R;

public class HotEventsFragment extends BaseFragment implements HotEventAdapter.ItemClickListener {

    @BindView(R.id.hotEvent_meetupcheckicon)
    ImageView meetupIcon;

    @BindView(R.id.hotEvent_meetupcheckbtn)
    ImageView meetupBtn;

    @BindView(R.id.hotEvent_eventcheckbutton)
    ImageView filterCheckbtn;

    @BindView(R.id.hotEvent_eventcheckicon)
    ImageView filterCheckicon;

    @BindView(R.id.hotEvent_ftitle)
    TextView filterTitle;

    @BindView(R.id.hotEvent_gather_title)
    TextView gatherTitle;

    @BindView(R.id.hotEvent_event_title)
    TextView eventTitle;

    @BindView(R.id.hotEvent_meetup_title)
    TextView meetTitle;

    @BindView(R.id.hotEvent_distance_title)
    TextView distanceTitle;

    @BindView(R.id.hotEvent_rating_title)
    TextView ratingTitle;

    @BindView(R.id.hotEvent_interest_title)
    TextView interestsTitle;

    @BindView(R.id.hotEvent_averagetitle)
    TextView averageTitle;

    @BindView(R.id.hotEvent_friendtitle)
    TextView friendTitle;

    @BindView(R.id.hotEvent_peopletitle)
    TextView peopleTitle;

    @BindView(R.id.hotEvent_upvotetitle)
    TextView upvoteTitle;

    @BindView(R.id.hotEvent_liketitle)
    TextView likeTitle;

    @BindView(R.id.hotEvent_FilterTitleBar)
    RelativeLayout mFilterTitleBlurLayout;

    @BindView(R.id.hotEvent_filterscrollview)
    ScrollView filterScrollView;

    @BindView(R.id.hotEvent_blurlayout)
    RelativeLayout mFilterBlurLayout;

    @BindView(R.id.hotEvent_distance_seekbar)
    SeekBar distanceSeek;

    @BindView(R.id.hotEvent_filterdistancelabel)
    TextView distanceTxt;

    @BindView(R.id.hotEvent_distancemaximg)
    ImageView distanceMaximg;

    @BindView(R.id.hotEvent_Filterlayout)
    RelativeLayout filterLayout;

    @BindView(R.id.hotEvent_fcbtn)
    ImageView filterClosebtn;

    @BindView(R.id.hotEvent_RecyclerView)
    RecyclerView event_RecyclerView;



    ArrayList<HotEventItem> eventItems;
    Typeface workSans_Light;
    ImageView backButton;
    float sideWidth;
    float filterWidth;
    boolean filterFlag = false;
    private RelativeLayout mFilterView;
    boolean checkFlag = false;
    boolean meetFlag = false;
    int height;
    int width;

    public static HotEventsFragment getInstance() {
        // Required empty public constructor
        HotEventsFragment fragment = new HotEventsFragment();
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
                sideWidth = width;
                filterLayout.animate().translationX(-sideWidth);
                filterFlag = true;
                ViewGroup.LayoutParams params=filterLayout.getLayoutParams();
                params.width=width;
                filterLayout.setLayoutParams(params);

                break;
        }
    }
    @Override
    protected int addView() {
        return R.layout.eventdash_hotevents_fragment;
    }

    @Override
    public void initView() {
        super.initView();

        setToolBar();

        setFilterCloseButton();

        setScrollView();

        setSeekBar();

        setFilterTitleBarBlurEffect();

        setScrollview_BlurEffect();

        setFilterview_Fonts();

        setEvent_Filtercheckevent();

        setMeet_Filtercheckevent();

        setAdapter();
    }

    @Override
    public void setToolBar(){
        super.setToolBar();
        final DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = (int) (displaymetrics.widthPixels * (float)(295.0f / 360.0f));

        assert txtLeft != null;
        txtLeft.setText("hot event");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_filter_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);

    }

    public void setMeet_Filtercheckevent(){


        meetupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (meetFlag){
                    meetupIcon.setVisibility(View.VISIBLE);
                    meetFlag = false;
                }else{
                    meetupIcon.setVisibility(View.INVISIBLE);
                    meetFlag = true;
                }

            }
        });
    }

    public void setEvent_Filtercheckevent(){

        filterCheckbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkFlag){
                        filterCheckicon.setVisibility(View.VISIBLE);
                        checkFlag = false;
                    }else{
                        filterCheckicon.setVisibility(View.INVISIBLE);
                        checkFlag = true;
                    }
                }
            });
    }

    public void setFilterview_Fonts(){

        Typeface workSans_Light = Typeface.createFromAsset(getActivity().getAssets(),"WorkSans-Light.ttf");


            filterTitle.setTypeface(workSans_Light);
            gatherTitle.setTypeface(workSans_Light);
            eventTitle.setTypeface(workSans_Light);
            meetTitle.setTypeface(workSans_Light);
            distanceTitle.setTypeface(workSans_Light);
            ratingTitle.setTypeface(workSans_Light);
            interestsTitle.setTypeface(workSans_Light);
            averageTitle.setTypeface(workSans_Light);
            friendTitle.setTypeface(workSans_Light);
            peopleTitle.setTypeface(workSans_Light);
            upvoteTitle.setTypeface(workSans_Light);
            likeTitle.setTypeface(workSans_Light);
    }


    public void setFilterTitleBarBlurEffect(){

        mFilterTitleBlurLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFilterTitleBlurLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
                blurDrawable.setDrawOffset(mFilterTitleBlurLayout.getLeft(), mFilterTitleBlurLayout.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
                blurDrawable.setCornerRadius(5);
                blurDrawable.setBlurRadius(5);
                blurDrawable.setOverlayColor(getResources().getColor(R.color.color_Onyx));
                mFilterTitleBlurLayout.setBackgroundDrawable(blurDrawable);
            }
        });
//
    }

    public void setScrollview_BlurEffect(){

        filterScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                filterScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
                blurDrawable.setDrawOffset(filterScrollView.getLeft(), filterScrollView.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
                blurDrawable.setCornerRadius(2);
                blurDrawable.setBlurRadius(5);
                filterScrollView.setBackgroundDrawable(blurDrawable);
            }
        });
    }

    public void setFilterBlurEffect(){

        mFilterBlurLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mFilterBlurLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
                blurDrawable.setDrawOffset(mFilterBlurLayout.getLeft(), mFilterBlurLayout.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
                blurDrawable.setCornerRadius(2);
                blurDrawable.setBlurRadius(5);
                mFilterBlurLayout.setBackgroundDrawable(blurDrawable);
            }
        });
    }

    public void setSeekBar(){

        distanceSeek.getProgressDrawable().setColorFilter(getResources().getColor(R.color.color_Milumain), PorterDuff.Mode.SRC_IN);
        distanceSeek.getThumb().setColorFilter(getResources().getColor(R.color.color_Milumain), PorterDuff.Mode.SRC_IN);

                distanceTxt.setVisibility(View.VISIBLE);
                distanceTxt.setText("0mi");

        distanceMaximg.setVisibility(View.INVISIBLE);

                distanceSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        String tempStr = progress + "mi";
                        distanceTxt.setVisibility(View.VISIBLE);
                        distanceTxt.setText(tempStr);
                        if (progress == 100){
                            distanceTxt.setVisibility(View.INVISIBLE);
                            distanceMaximg.setVisibility(View.VISIBLE);
                        }else{
                            distanceTxt.setVisibility(View.VISIBLE);
                            distanceMaximg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

    }
    public void setScrollView(){

    }
    public void setTitlebarBlurView(){

//        mTitleBlurLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mTitleBlurLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
//                blurDrawable.setDrawOffset(mTitleBlurLayout.getMinimumWidth(), mTitleBlurLayout.getTop() + BlurUtils.getStatusBarHeight(getActivity()) + 20);
//                blurDrawable.setCornerRadius(5);
//                blurDrawable.setBlurRadius(5);
//                mTitleBlurLayout.setBackgroundDrawable(blurDrawable);
//            }
//        });
    }
//    public void setFonts(){
//
//        TextView filterTitle = (TextView) view.findViewById(R.id.hotEvent_ftitle);
//        TextView gatherTitle = (TextView) view.findViewById(R.id.hotEvent_gather_title);
//        TextView eventTitle = (TextView) view.findViewById(R.id.hotEvent_event_title);
//        TextView meetupTitle = (TextView) view.findViewById(R.id.hotEvent_meetup_title);
//        TextView distanceTitle = (TextView) view.findViewById(R.id.hotEvent_distance_title);
//        TextView sortTitle = (TextView) view.findViewById(R.id.hotEvent_sort_title);
//        TextView ratingTitle = (TextView) view.findViewById(R.id.hotEvent_rating_title);
//        TextView interestTitle = (TextView) view.findViewById(R.id.hotEvent_interest_title);
//        TextView gentherTitle = (TextView) view.findViewById(R.id.hotEvent_gendertitle);
//        TextView averageTitle = (TextView) view.findViewById(R.id.hotEvent_averagetitle);
//        TextView friendTitle = (TextView) view.findViewById(R.id.hotEvent_friendtitle);
//        TextView peopleTitle = (TextView) view.findViewById(R.id.hotEvent_peopletitle);
//        TextView upTitle = (TextView) view.findViewById(R.id.hotEvent_upvotetitle);
//        TextView likeTitle = (TextView) view.findViewById(R.id.hotEvent_liketitle);
//
//        workSans_Light = Typeface.createFromAsset(getActivity().getAssets(),"WorkSans-Light.ttf");
//
//        filterTitle.setTypeface(workSans_Light);
//        gatherTitle.setTypeface(workSans_Light);
//        eventTitle.setTypeface(workSans_Light);
//        meetupTitle.setTypeface(workSans_Light);
//        distanceTitle.setTypeface(workSans_Light);
//        sortTitle.setTypeface(workSans_Light);
//        ratingTitle.setTypeface(workSans_Light);
//        interestTitle.setTypeface(workSans_Light);
//        gentherTitle.setTypeface(workSans_Light);
//        averageTitle.setTypeface(workSans_Light);
//        friendTitle.setTypeface(workSans_Light);
//        peopleTitle.setTypeface(workSans_Light);
//        upTitle.setTypeface(workSans_Light);
//        likeTitle.setTypeface(workSans_Light);
//
//    }

    public void setFilterButton(){
//        final DisplayMetrics displaymetrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
//        final int width = (int) (displaymetrics.widthPixels * (float)(295.0f / 360.0f));
//
//            filterButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    DisplayMetrics displayMetrics = new DisplayMetrics();
//                    ((Activity) getContext()).getWindowManager()
//                            .getDefaultDisplay()
//                            .getMetrics(displayMetrics);
//                    sideWidth = width;
//                    filterLayout.animate().translationX(-sideWidth);
//                    filterFlag = true;
//                    ViewGroup.LayoutParams params=filterLayout.getLayoutParams();
//                    params.width=width;
//                    filterLayout.setLayoutParams(params);
//
//                }
//            });
    }

    public void setFilterCloseButton(){

            filterClosebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    filterLayout.animate().translationX(0);
                    filterFlag = false;

                }
            });
    }

    public void setAdapter(){


        eventItems = HotEventItem.createContactsList(0);
        eventItems.add(0, new HotEventItem(R.drawable.neweventback00,"the roots","2K people • 12 interests","5.0"));
        eventItems.add(1, new HotEventItem(R.drawable.neweventback01,"chemical brothers","2K people • 124 interests","3.0"));
        eventItems.add(2, new HotEventItem(R.drawable.neweventback02,"daft punk","2K people • 34 interests","2.0"));
        eventItems.add(3, new HotEventItem(R.drawable.neweventback03,"black keys","2K people • 12 interests","4.6"));
        eventItems.add(4, new HotEventItem(R.drawable.neweventback04,"chemical brothers","2K people • 34 interests","2.5"));
        eventItems.add(5, new HotEventItem(R.drawable.neweventback05,"the roots","2K people • 67 interests","3.6"));
        eventItems.add(6, new HotEventItem(R.drawable.neweventback06,"daft punk","2K people • 3 interests","5.0"));
        eventItems.add(7, new HotEventItem(R.drawable.neweventback07,"black keys","2K people • 5 interests","1.5"));
        eventItems.add(8, new HotEventItem(R.drawable.neweventback08,"the roots","2K people • 56 interests","4.6"));
        eventItems.add(9, new HotEventItem(R.drawable.neweventback00,"the roots","2K people • 8 interests","5.0"));
        eventItems.add(10, new HotEventItem(R.drawable.neweventback01,"chemical brothers","2K people • 89 interests","3.0"));
        eventItems.add(11, new HotEventItem(R.drawable.neweventback02,"daft punk","2K people • 23 interests","2.0"));
        eventItems.add(12, new HotEventItem(R.drawable.neweventback03,"black keys","2K people • 12 interests","4.6"));
        eventItems.add(13, new HotEventItem(R.drawable.neweventback04,"chemical brothers","2K people • 4 interests","2.5"));
        eventItems.add(14, new HotEventItem(R.drawable.neweventback05,"the roots","2K people • 56 interests","3.6"));
        eventItems.add(15, new HotEventItem(R.drawable.neweventback06,"daft punk","2K people • 34 interests","5.0"));
        eventItems.add(16, new HotEventItem(R.drawable.neweventback07,"black keys","2K people • 2 interests","1.5"));
        eventItems.add(17, new HotEventItem(R.drawable.neweventback08,"the roots","2K people • 6 interests","4.6"));

        HotEventAdapter adapter = new HotEventAdapter(mAct, eventItems);
        event_RecyclerView.setAdapter(adapter);
        adapter.setListener(this);
        event_RecyclerView.setLayoutManager(new LinearLayoutManager(mAct));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mAct, 1);
        event_RecyclerView.setLayoutManager(gridLayoutManager);

        event_RecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                ViewCompat.postInvalidateOnAnimation(mTitleBlurLayout);
                ViewCompat.postInvalidateOnAnimation(filterScrollView);
                ViewCompat.postInvalidateOnAnimation(mFilterTitleBlurLayout);
            }
        });
    }

    @Override
    public void hotEventCell_Clicked(View v, int adapterPosition) {


    }
}
