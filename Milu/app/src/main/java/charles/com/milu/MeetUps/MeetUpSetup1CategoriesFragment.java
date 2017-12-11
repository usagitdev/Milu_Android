package charles.com.milu.MeetUps;

import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.R;

public class MeetUpSetup1CategoriesFragment extends BaseFragment implements MeetUpAdapter.ItemClickListener {


    @BindView(R.id.meetup_categories_recyclerView)
    RecyclerView meet_RecyclerView;

    @BindView(R.id.location_view)
    LinearLayout locationView;

    ArrayList<MeetUpItem> meetUpItems;
    ArrayList<MeetUpItem> meetUpResults;
    MeetUpAdapter adapter;
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
            case R.id.toolbar_btn_right2:
                addChildFragment(MeetupSetup2TagsFragment.getInstance(), true);
                break;
            case R.id.location_view:
                addFragment(MeetUpLocationSelectionFragment.getInstance(), true);
                break;

        }
    }

    public static MeetUpSetup1CategoriesFragment getInstance() {
        // Required empty public constructor
        MeetUpSetup1CategoriesFragment fragment = new MeetUpSetup1CategoriesFragment();
        return fragment;
    }


    @Override
    protected int addView() {
        return R.layout.fragment_meetups_setup1_categories;
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
        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_disable_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setEnabled(false);
        rightButton2.setOnClickListener(this);

        locationView.setOnClickListener(this);
    }


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
        meetUpItems.add(17, new MeetUpItem(R.drawable.fashion_beauty,"sci-fi & games"));
        meetUpItems.add(18, new MeetUpItem(R.drawable.hobbies_crafts,"dance"));
        meetUpItems.add(19, new MeetUpItem(R.drawable.scifi_games,"pets"));
        meetUpItems.add(20, new MeetUpItem(R.drawable.scifi_games,"hobby & crafts"));
        meetUpItems.add(21, new MeetUpItem(R.drawable.scifi_games,"fashion & beauty"));
        meetUpItems.add(22, new MeetUpItem(R.drawable.scifi_games,"social"));
        meetUpItems.add(23, new MeetUpItem(R.drawable.scifi_games,"business"));

        adapter = new MeetUpAdapter(getContext(), meetUpItems);
        meet_RecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        meet_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        meet_RecyclerView.setLayoutManager(gridLayoutManager);


        meet_RecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 1, true));

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
