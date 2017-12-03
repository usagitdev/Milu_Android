package charles.com.milu.PlacesTab;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.LiveEvents.ColorAdapter;
import charles.com.milu.LiveEvents.LiveEventAdapter;
import charles.com.milu.LiveEvents.LiveEventItem;
import charles.com.milu.LiveEvents.LiveFeedMain.LiveFeedMainFragment;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivePlacesFragment extends BaseFragment implements LiveEventAdapter.ItemClickListener {

    @BindView(R.id.livePlaces_recyclerview)
    RecyclerView mRecyclerView;


    private Context mContext;

    RelativeLayout mRelativeLayout;

    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private StaggeredGridLayoutManager mLayoutManager;

    ArrayList<LiveEventItem> liveItems;
    ImageView backButton;

    public static LivePlacesFragment getInstance() {
        // Required empty public constructor
        LivePlacesFragment fragment = new LivePlacesFragment();
        return fragment;
    }

    @Override
    protected int addView() {
        return R.layout.fragment_places_live_places;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setAdapter();

    }

    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("live places");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_filter_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view = inflater.inflate(R.layout.fragment_live_event, container, false);
//
//
//        setAdapter(view);
//
////        setColorAdapter(view);
//        setBackButton(view);
//
//        return view;
//    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
        }
    }

    public void setAdapter(){


        liveItems = LiveEventItem.createContactsList(0);
        liveItems.add(0, new LiveEventItem(true,R.drawable.movements,"hometown tavern","320 people • 16 interests","4K","4.8"));
        liveItems.add(1, new LiveEventItem(false,R.drawable.outdoor_adventure,"hipster cafe", "720 people • 1 interests", "6K","4.1"));
        liveItems.add(2, new LiveEventItem(true,R.drawable.tech,"contemporary workspace","768 people • 334 interests","0K","3.6"));
        liveItems.add(3, new LiveEventItem(false,R.drawable.family,"cheers & beers bar","310 people • 17 interests","6K","5.0"));
        liveItems.add(4, new LiveEventItem(true,R.drawable.health_wellness,"the roots","2k people • 123 interests","4K","4.7"));
        liveItems.add(5, new LiveEventItem(false,R.drawable.sports_fitness,"daft punk","2k people • 56 interest", "7K","4.0"));
        liveItems.add(6, new LiveEventItem(false,R.drawable.learning,"black keys","123k people • 3 interests","5K","3.3"));
        liveItems.add(7, new LiveEventItem(false,R.drawable.photography,"chemical brothers","56 people • 39 interests","4K","2.4"));
        liveItems.add(8, new LiveEventItem(true,R.drawable.food,"contemporary workspace","768 people • 334 interests","0K","3.6"));
        liveItems.add(9, new LiveEventItem(true,R.drawable.writing,"black keys","123k people • 3 interests","5K","3.3"));
        liveItems.add(10, new LiveEventItem(true,R.drawable.language_culture,"cheers & beers bar","310 people • 17 interests","6K","5.0"));
        liveItems.add(11, new LiveEventItem(false,R.drawable.music,"the roots","2k people • 123 interests","4K","4.7"));
        liveItems.add(12, new LiveEventItem(false,R.drawable.religion,"hometown tavern","320 people • 16 interests","4K","4.8"));
        liveItems.add(13, new LiveEventItem(false,R.drawable.film,"chemical brothers","56 people • 39 interests","4K","2.4"));
        liveItems.add(14, new LiveEventItem(true,R.drawable.arts,"the roots","2k people • 123 interests","4K","4.7"));
        liveItems.add(15, new LiveEventItem(true,R.drawable.beliefs,"contemporary workspace","768 people • 334 interests","0K","3.6"));
        liveItems.add(16, new LiveEventItem(true,R.drawable.book_clubs,"black keys","123k people • 3 interests","5K","3.3"));
        liveItems.add(17, new LiveEventItem(false,R.drawable.fashion_beauty,"hipster cafe", "720 people • 1 interests", "6K","4.1"));
        liveItems.add(18, new LiveEventItem(false,R.drawable.hobbies_crafts,"black keys","123k people • 3 interests","5K","3.3"));
        liveItems.add(19, new LiveEventItem(false,R.drawable.scifi_games,"the roots","2k people • 123 interests","4K","4.7"));

        LiveEventAdapter adapter = new LiveEventAdapter(getContext(), liveItems);

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, LinearLayoutManager.HORIZONTAL);
//            staggeredGridLayoutManager.setSpanCount(4);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);

    }
    public void setBackButton(View view){

//        backButton = (ImageView) view.findViewById(R.id.liveEvent_Backicon);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Tabbar_eventdash eventFragment = new Tabbar_eventdash();
//                EventBus.getDefault().post(new ReplaceFragmentListener(eventFragment, false));
//            }
//        });
    }

    @Override
    public void eventCell_Clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," LiveEvent item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();
        addChildFragment(LiveFeedMainFragment.getInstance(), true);

//        LiveFeedMainFragment liveFeedFragment = new LiveFeedMainFragment();
//        EventBus.getDefault().post(new ReplaceFragmentListener(liveFeedFragment, true));
    }

    public void setColorAdapter(View view){
        mContext = view.getContext();

        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.liveEvnet_recyclerview);

        String[] colors = {
                "Red","Green","Blue","Yellow","Magenta","Cyan","Orange",
                "Aqua","Azure","Beige","Bisque","Brown","Coral","Crimson"
        };

        mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ColorAdapter(mContext,colors);

        mRecyclerView.setAdapter(mAdapter);
    }
}
