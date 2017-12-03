package charles.com.milu.MeetUps;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.EventBusListeners.ReplaceFragmentListener;
import charles.com.milu.HomeScreen.Tabbar_eventdash;
import charles.com.milu.R;

public class MeetUpFragment extends BaseFragment implements MeetUpAdapter.ItemClickListener {


    @BindView(R.id.meetUp_RecyclerView)
    RecyclerView meet_RecyclerView;

    ArrayList<MeetUpItem> meetUpItems;
    ArrayList<MeetUpItem> meetUpResults;
    Typeface workSans_Light;
    ImageView backButton;

    private int spanCount;
    private int spacing;
    private boolean includeEdge;



    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
        }
    }

    public static MeetUpFragment getInstance() {
        // Required empty public constructor
        MeetUpFragment fragment = new MeetUpFragment();
        return fragment;
    }


    @Override
    protected int addView() {
        return R.layout.eventdash_meetup_fragment;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setAdapter();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert txtLeft != null;
        txtLeft.setText("meetups");

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
//        View view = inflater.inflate(R.layout.eventdash_meetup_fragment, container, false);
//
//        setTitleType(view);
//        setBackButton(view);
//        setAdapter(view);
//
//        return view;
//    }
//

    public void setAdapter(){


        meetUpResults = MeetUpItem.createContactsList(0);
        meetUpItems = MeetUpItem.createContactsList(0);
        meetUpItems.add(0, new MeetUpItem(R.drawable.movements,"movements"));
        meetUpItems.add(1, new MeetUpItem(R.drawable.outdoor_adventure,"outdoor & adventure"));
        meetUpItems.add(2, new MeetUpItem(R.drawable.tech,"tech"));
        meetUpItems.add(3, new MeetUpItem(R.drawable.family,"family"));
        meetUpItems.add(4, new MeetUpItem(R.drawable.health_wellness,"health & wellness"));
        meetUpItems.add(5, new MeetUpItem(R.drawable.sports_fitness,"sports & fitness"));
        meetUpItems.add(6, new MeetUpItem(R.drawable.learning,"learning"));
        meetUpItems.add(7, new MeetUpItem(R.drawable.photography,"photography"));
        meetUpItems.add(8, new MeetUpItem(R.drawable.food,"food & drink"));
        meetUpItems.add(9, new MeetUpItem(R.drawable.writing,"writing"));
        meetUpItems.add(10, new MeetUpItem(R.drawable.language_culture,"language & culture"));
        meetUpItems.add(11, new MeetUpItem(R.drawable.music,"music"));
        meetUpItems.add(12, new MeetUpItem(R.drawable.religion,"religion"));
        meetUpItems.add(13, new MeetUpItem(R.drawable.film,"film"));
        meetUpItems.add(14, new MeetUpItem(R.drawable.arts,"arts"));
        meetUpItems.add(15, new MeetUpItem(R.drawable.beliefs,"beliefs"));
        meetUpItems.add(16, new MeetUpItem(R.drawable.book_clubs,"book & clubs"));
        meetUpItems.add(17, new MeetUpItem(R.drawable.fashion_beauty,"fashion & beauty"));
        meetUpItems.add(18, new MeetUpItem(R.drawable.hobbies_crafts,"hobbies & crafts"));
        meetUpItems.add(19, new MeetUpItem(R.drawable.scifi_games,"scifi & games"));

        MeetUpAdapter adapter = new MeetUpAdapter(getContext(), meetUpItems);
        meet_RecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        meet_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        meet_RecyclerView.setLayoutManager(gridLayoutManager);


        meet_RecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 5, true));

    }

    @Override
    public void meetUpCell_Clicked(View v, int adapterPosition) {

        meetUpResults.add(meetUpItems.get(adapterPosition));
        Toast.makeText(v.getContext()," MeetUp item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }
}
