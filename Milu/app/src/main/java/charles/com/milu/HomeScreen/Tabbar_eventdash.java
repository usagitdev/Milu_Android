package charles.com.milu.HomeScreen;


import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CalendarView.CalendarFragment;
import charles.com.milu.EventDash.EventDashAdapter;
import charles.com.milu.EventDash.EventDashItem;
import charles.com.milu.FavouriteEvents.FavEventFragment;
import charles.com.milu.Fragments.CreateEventFragment;
import charles.com.milu.HotEvents.HotEventsFragment;
import charles.com.milu.LiveEvents.LiveEventFragment;
import charles.com.milu.MeetUps.MeetUpFragment;
import charles.com.milu.MyEvents.MyEventsFragment;
import charles.com.milu.NewEvents.NewEventsFragment;
import charles.com.milu.R;
import charles.com.milu.UpComingEvents.UpComingFragment;
import charles.com.milu.utils.Utilities;
import charles.com.milu.utils.util.ImageCache;
import charles.com.milu.utils.util.ImageFetcher;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tabbar_eventdash extends BaseFragment implements EventDashAdapter.ItemClickListener{


    ArrayList<EventDashItem> feedItems;

    @BindView(R.id.eventDashLayout)
    LinearLayout mainLayoout;

    @BindView(R.id.firstView)
    RelativeLayout firstLayoout;

    @BindView(R.id.secondView)
    RelativeLayout secondLayoout;

    @BindView(R.id.liveEventLayout)
    RelativeLayout liveEventLayout;

    @BindView(R.id.hotEventLayout)
    RelativeLayout hotEventLayout;

    @BindView(R.id.favEvnetLayout)
    RelativeLayout favEventLayout;

    @BindView(R.id.upComingLayout)
    RelativeLayout upEventLayout;

    @BindView(R.id.myEventLayout)
    RelativeLayout myEventLayout;

    @BindView(R.id.newEventLayout)
    RelativeLayout newEventLayout;

    @BindView(R.id.calLayout)
    RelativeLayout calLayout;

    @BindView(R.id.meetLayout)
    RelativeLayout meetLayout;

    @BindView(R.id.inviteLayout)
    RelativeLayout inviteLayout;

    @BindView(R.id.eventDash_sideView1)
    ImageView sideView1;

    @BindView(R.id.eventDash_sideView2)
    ImageView sideView2;

    @BindView(R.id.eventDash_RecyclerView)
    RecyclerView evnetDash_RecyclerView;

    @BindView(R.id.liveEvent_title)
    TextView liveEvnet_title;

    @BindView(R.id.liveEvent_count)
    TextView liveEvnet_count;

    @BindView(R.id.hotEvent_title)
    TextView hotEvnet_title;

    @BindView(R.id.hotEvent_count)
    TextView hotEvnet_count;

    @BindView(R.id.upEvent_title)
    TextView upComing_title;

    @BindView(R.id.upEvent_count)
    TextView upComing_count;

    @BindView(R.id.favEvent_title)
    TextView favEvent_title;

    @BindView(R.id.favEvent_count)
    TextView favEvent_count;

    @BindView(R.id.myEvent_title)
    TextView myEvent_title;

    @BindView(R.id.newEvent_title)
    TextView newEvent_title;

    @BindView(R.id.newEvent_count)
    TextView newEvent_count;

    @BindView(R.id.calEvent_title)
    TextView calEvent_title;

    @BindView(R.id.calEvent_count)
    TextView calEvent_count;

    @BindView(R.id.calEvent_mon)
    TextView calEvent_mon;

    @BindView(R.id.calEvent_date)
    TextView calEvent_date;

    @BindView(R.id.meetUp_title)
    TextView meetEvnet_title;

    @BindView(R.id.meetUp_count)
    TextView meetEvnet_count;

    @BindView(R.id.invite_title)
    TextView invite_tile;

    @BindView(R.id.invite_count)
    TextView invite_count;


    float sideWidth, sideWith2;
    int width;
    public TextView titleBar_title;
    boolean flag;
    Typeface workSans_Light;
    Typeface workSans_Regular;

    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private ImageFetcher mImageFetcher;
    private static final String TAG = "ImageGridFragment";
    private static final String IMAGE_CACHE_DIR = "thumbs";

    public static Tabbar_eventdash getInstance() {
        return new Tabbar_eventdash();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_tab_bar_eventdash;
    }

    @Override
    public void initView() {
        super.initView();
        mImageThumbSize = (R.dimen.image_thumbnail_size);
        ImageCache.ImageCacheParams cacheParams =
                new ImageCache.ImageCacheParams(getActivity(), IMAGE_CACHE_DIR);

        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory

        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        mImageFetcher = new ImageFetcher(getActivity(), mImageThumbSize);
        mImageFetcher.setLoadingImage(R.drawable.empty_photo);
        mImageFetcher.addImageCache(getActivity().getSupportFragmentManager(), cacheParams);

        setToolBar();
//        setDashboardText_font();
        setSlideView();
        setAdapter();
        setEachEventlayoutClickEvent();
    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.logo);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);
        assert txtLeft != null;
        txtLeft.setText("milu");
        assert rightButton1 != null;
        rightButton1.setImageResource(R.drawable.nav_bar_add_icon);
        rightButton1.setVisibility(View.VISIBLE);
        rightButton1.setOnClickListener(this);
        assert rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_search_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);

    }

    public void setEachEventlayoutClickEvent(){

        liveEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addChildFragment(LiveEventFragment.getInstance(), true);

            }
        });

        upEventLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addChildFragment(UpComingFragment.getInstance(), true);

            }
        });

        hotEventLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addChildFragment(HotEventsFragment.getInstance(), true);

            }
        });

        favEventLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addChildFragment(FavEventFragment.getInstance(), true);

            }
        });

        myEventLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addChildFragment(MyEventsFragment.getInstance(), true);

            }
        });

        newEventLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addChildFragment(NewEventsFragment.getInstance(), true);

            }
        });

        calLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addChildFragment(CalendarFragment.getInstance(), true);

            }
        });

        meetLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addChildFragment(MeetUpFragment.getInstance(), true);

            }
        });

        inviteLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"Invite Event Layout Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setSlideView(){


        flag = true;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        sideWidth =(width) * (float)((26.0/360.0));

        sideView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"side 1 Clicked!!", Toast.LENGTH_SHORT).show();
                firstLayoout.animate().translationX(0);
                secondLayoout.animate().translationX(0);
                flag = true;
            }
        });

        sideView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"side 2 Clicked!!", Toast.LENGTH_SHORT).show();
                firstLayoout.animate().translationX(-width + sideWidth + sideWidth);
                secondLayoout.animate().translationX(-width + sideWidth + sideWidth);
                flag = false;
            }
        });
    }

//    public void setTitleType(){
//
//        workSans_Light = Typeface.createFromAsset(getActivity().getAssets(),"WorkSans-Light.ttf");
//        workSans_Regular = Typeface.createFromAsset(getActivity().getAssets(),"WorkSans-Regular.ttf");
//
//        titleBar_title = (TextView) view.findViewById(R.id.titleBar_Title);
//        titleBar_title.setTypeface(workSans_Light);
//    }
    public  void setAdapter(){

        Utilities.setLayoutManager(mAct, evnetDash_RecyclerView, true, true, false);

        // Initialize contacts
        feedItems = EventDashItem.createContactsList(0);
        feedItems.add(0, new EventDashItem(R.drawable.user01,"andrea bocceli","high on the hog bbq","5"));
        feedItems.add(1, new EventDashItem(R.drawable.user02,"kjathryn palmer","chemical brothers","4"));
        feedItems.add(2, new EventDashItem(R.drawable.user03,"nathan was hington","on the rocks","?"));
        feedItems.add(3, new EventDashItem(R.drawable.user04,"gerald stanley","the sole proprietor","1"));
        feedItems.add(4, new EventDashItem(R.drawable.user05,"randy matthews","high on the hog bbq","4"));
        feedItems.add(5, new EventDashItem(R.drawable.user06,"amber king","chemical brothers","2"));
        feedItems.add(6, new EventDashItem(R.drawable.user07,"janice washington","on the rocks","5"));
        feedItems.add(7, new EventDashItem(R.drawable.user08,"carl meyer","the sole proprietor","3"));
        feedItems.add(8, new EventDashItem(R.drawable.user01,"andrea bocceli","high on the hog bbq","2"));
        feedItems.add(9, new EventDashItem(R.drawable.user02,"kjathryn palmer","chemical brothers","6"));
        feedItems.add(10, new EventDashItem(R.drawable.user03,"nathan was hington","on the rocks","2"));
        feedItems.add(11, new EventDashItem(R.drawable.user04,"gerald stanley","the sole proprietor","2"));
        feedItems.add(12, new EventDashItem(R.drawable.user05,"randy matthews","high on the hog bbq","?"));
        feedItems.add(13, new EventDashItem(R.drawable.user06,"amber king","chemical brothers","1"));
        feedItems.add(14, new EventDashItem(R.drawable.user07,"janice washington","on the rocks","3"));
        feedItems.add(15, new EventDashItem(R.drawable.user08,"carl meyer","the sole proprietor","2"));

        EventDashAdapter adapter = new EventDashAdapter(getContext(), feedItems);
        evnetDash_RecyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }

//    public  void  setDashboardText_font(){
//
//            liveEvnet_title.setTypeface(workSans_Light);
//            liveEvnet_count.setTypeface(workSans_Light);
//
//            hotEvnet_title.setTypeface(workSans_Regular);
//            hotEvnet_count.setTypeface(workSans_Light);
//
//            upComing_title.setTypeface(workSans_Light);
//            upComing_count.setTypeface(workSans_Light);
//
//            favEvent_title.setTypeface(workSans_Regular);
//            favEvent_count.setTypeface(workSans_Light);
//
//            myEvent_title.setTypeface(workSans_Light);
//
//            newEvent_title.setTypeface(workSans_Light);
//            newEvent_count.setTypeface(workSans_Light);
//
//            calEvent_title.setTypeface(workSans_Light);
//            calEvent_count.setTypeface(workSans_Light);
//            calEvent_mon.setTypeface(workSans_Light);
//            calEvent_date.setTypeface(workSans_Light);
//
//            meetEvnet_title.setTypeface(workSans_Regular);
//            meetEvnet_count.setTypeface(workSans_Light);
//
//            invite_tile.setTypeface(workSans_Light);
//            invite_count.setTypeface(workSans_Light);
//
//    }

    @Override
    public void eventDashCell_Clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext(),"event item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                break;
            case R.id.toolbar_btn_right1:
                addChildFragment(CreateEventFragment.getInstance(), true);
                break;
            case R.id.toolbar_btn_right2:
                break;
        }
    }

}
