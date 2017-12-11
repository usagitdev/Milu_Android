package charles.com.milu.MeetUps;

import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.R;

public class MeetUpSetup3InterestsFragment extends BaseFragment implements MeetUpSetup3InterestsAdapter.ItemClickListener {


    @BindView(R.id.meetup_categories_recyclerView)
    RecyclerView meet_RecyclerView;

    ArrayList<MeetUpSetup3InterstsItem> meetUpItems;
    ArrayList<MeetUpSetup3InterstsItem> meetUpResults;
    MeetUpSetup3InterestsAdapter adapter;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
        }
    }

    public static MeetUpSetup3InterestsFragment getInstance() {
        // Required empty public constructor
        MeetUpSetup3InterestsFragment fragment = new MeetUpSetup3InterestsFragment();
        return fragment;
    }


    @Override
    protected int addView() {
        return R.layout.fragment_meetups_setup3_interests;
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
        txtLeft.setText("interests");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_disable_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);
    }

    public void setAdapter(){


        meetUpResults = MeetUpSetup3InterstsItem.createContactsList(0);
        meetUpItems = MeetUpSetup3InterstsItem.createContactsList(0);
        meetUpItems.add(0, new MeetUpSetup3InterstsItem(R.drawable.movements,"san francisco web design meetup", "we are 50 strong"));
        meetUpItems.add(1, new MeetUpSetup3InterstsItem(R.drawable.outdoor_adventure,"san francisco web/internet startups", "we are 49 members"));
        meetUpItems.add(2, new MeetUpSetup3InterstsItem(R.drawable.tech,"mental health san francisco", "we are 36 meditators"));
        meetUpItems.add(3, new MeetUpSetup3InterstsItem(R.drawable.tech,"mental health san francisco", "we are 36 meditators"));
        meetUpItems.add(4, new MeetUpSetup3InterstsItem(R.drawable.movements,"san francisco web design meetup", "we are 50 strong"));
        meetUpItems.add(5, new MeetUpSetup3InterstsItem(R.drawable.outdoor_adventure,"san francisco web/internet startups", "we are 49 members"));
        meetUpItems.add(6, new MeetUpSetup3InterstsItem(R.drawable.tech,"mental health san francisco", "we are 36 meditators"));
        meetUpItems.add(7, new MeetUpSetup3InterstsItem(R.drawable.movements,"san francisco web design meetup", "we are 50 strong"));
        meetUpItems.add(8, new MeetUpSetup3InterstsItem(R.drawable.outdoor_adventure,"san francisco web/internet startups", "we are 49 members"));
        meetUpItems.add(9, new MeetUpSetup3InterstsItem(R.drawable.tech,"mental health san francisco", "we are 36 meditators"));
        meetUpItems.add(10, new MeetUpSetup3InterstsItem(R.drawable.movements,"san francisco web design meetup", "we are 50 strong"));
        meetUpItems.add(11, new MeetUpSetup3InterstsItem(R.drawable.outdoor_adventure,"san francisco web/internet startups", "we are 49 members"));
        meetUpItems.add(12, new MeetUpSetup3InterstsItem(R.drawable.tech,"mental health san francisco", "we are 36 meditators"));
        meetUpItems.add(13, new MeetUpSetup3InterstsItem(R.drawable.movements,"san francisco web design meetup", "we are 50 strong"));
        meetUpItems.add(14, new MeetUpSetup3InterstsItem(R.drawable.outdoor_adventure,"san francisco web/internet startups", "we are 49 members"));
        meetUpItems.add(15, new MeetUpSetup3InterstsItem(R.drawable.tech,"mental health san francisco", "we are 36 meditators"));
        meetUpItems.add(16, new MeetUpSetup3InterstsItem(R.drawable.movements,"san francisco web design meetup", "we are 50 strong"));
        meetUpItems.add(17, new MeetUpSetup3InterstsItem(R.drawable.outdoor_adventure,"san francisco web/internet startups", "we are 49 members"));
        meetUpItems.add(18, new MeetUpSetup3InterstsItem(R.drawable.tech,"mental health san francisco", "we are 36 meditators"));
        adapter = new MeetUpSetup3InterestsAdapter(getContext(), meetUpItems);
        meet_RecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        meet_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

////        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.HORIZONTAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//        meet_RecyclerView.setLayoutManager(gridLayoutManager);


        meet_RecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 5, true));

    }

    @Override
    public void meetUpCell_Clicked(View v, int adapterPosition) {

        if (meetUpResults.contains(meetUpItems.get(adapterPosition))) {
            meetUpResults.remove(meetUpItems.get(adapterPosition));
            adapter.setunSelectedItem(adapterPosition);
        }else{
            meetUpResults.add(meetUpItems.get(adapterPosition));
            adapter.setSelectedItem(adapterPosition);
        }
        if (meetUpResults.size() > 0) {
            assert rightButton2 != null;
            rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
            rightButton2.setEnabled(true);
        }else{
            assert rightButton2 != null;
            rightButton2.setImageResource(R.drawable.nav_bar_check_mark_disable_icon);
            rightButton2.setEnabled(false);
        }

    }
}
