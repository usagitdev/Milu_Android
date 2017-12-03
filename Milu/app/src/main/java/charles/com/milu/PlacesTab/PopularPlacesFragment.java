package charles.com.milu.PlacesTab;

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
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.MyEvents.MyEventAdapter;
import charles.com.milu.MyEvents.MyEventItem;
import charles.com.milu.R;

public class PopularPlacesFragment extends BaseFragment implements PopularPlacesAdapter.ItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    ArrayList<PopularPlacesItem> mEventItems;

    public static PopularPlacesFragment getInstance() {
        // Required empty public constructor
        PopularPlacesFragment fragment = new PopularPlacesFragment();
        return fragment;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
        }
    }

    @Override
    protected int addView() {
        return R.layout.fragment_places_popular;
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
        txtLeft.setText("popular places");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_filter_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);

    }


    public void setAdapter(){


        mEventItems = PopularPlacesItem.createContactsList(0);
        mEventItems.add(0, new PopularPlacesItem(R.drawable.neweventback00,"slate sky scraper","45mi away • 12 interests","3k", true));
        mEventItems.add(1, new PopularPlacesItem(R.drawable.neweventback01,"chinese tower","321mi • 17 interests","12", false));
        mEventItems.add(2, new PopularPlacesItem(R.drawable.neweventback02,"capital building","2.3mi • 19 interests","4.6k",true));
        mEventItems.add(3, new PopularPlacesItem(R.drawable.neweventback03,"classy restaurant","16mi • 13 interests","125",false));
        mEventItems.add(4, new PopularPlacesItem(R.drawable.neweventback04,"slate sky scraper","45mi away • 12 interests","3k", true));
        mEventItems.add(5, new PopularPlacesItem(R.drawable.neweventback05,"chinese tower","321mi • 17 interests","12", false));
        mEventItems.add(6, new PopularPlacesItem(R.drawable.neweventback06,"capital building","2.3mi • 19 interests","4.6k",true));
        mEventItems.add(7, new PopularPlacesItem(R.drawable.neweventback07,"classy restaurant","16mi • 13 interests","125",false));
        mEventItems.add(8, new PopularPlacesItem(R.drawable.neweventback08,"slate sky scraper","45mi away • 12 interests","3k", true));
        mEventItems.add(9, new PopularPlacesItem(R.drawable.neweventback09,"chinese tower","321mi • 17 interests","12", false));
        mEventItems.add(10, new PopularPlacesItem(R.drawable.neweventback12,"capital building","2.3mi • 19 interests","4.6k",true));
        mEventItems.add(11, new PopularPlacesItem(R.drawable.neweventback13,"classy restaurant","16mi • 13 interests","125",false));
        mEventItems.add(12, new PopularPlacesItem(R.drawable.neweventback02,"capital building","2.3mi • 19 interests","4.6k",true));
        mEventItems.add(13, new PopularPlacesItem(R.drawable.neweventback03,"classy restaurant","16mi • 13 interests","125",false));
        mEventItems.add(14, new PopularPlacesItem(R.drawable.neweventback04,"slate sky scraper","45mi away • 12 interests","3k", true));
        mEventItems.add(15, new PopularPlacesItem(R.drawable.neweventback05,"chinese tower","321mi • 17 interests","12", false));
        mEventItems.add(16, new PopularPlacesItem(R.drawable.neweventback06,"capital building","2.3mi • 19 interests","4.6k",true));
        mEventItems.add(17, new PopularPlacesItem(R.drawable.neweventback07,"classy restaurant","16mi • 13 interests","125",false));
        mEventItems.add(18, new PopularPlacesItem(R.drawable.neweventback08,"slate sky scraper","45mi away • 12 interests","3k", true));
        mEventItems.add(19, new PopularPlacesItem(R.drawable.neweventback09,"chinese tower","321mi • 17 interests","12", false));
        mEventItems.add(20, new PopularPlacesItem(R.drawable.neweventback10,"capital building","2.3mi • 19 interests","4.6k",true));
        mEventItems.add(21, new PopularPlacesItem(R.drawable.neweventback11,"classy restaurant","16mi • 13 interests","125",false));

        PopularPlacesAdapter adapter = new PopularPlacesAdapter(getContext(), mEventItems);
        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 0, true));

    }

    @Override
    public void mEventCell_Clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," My Event item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }
}
