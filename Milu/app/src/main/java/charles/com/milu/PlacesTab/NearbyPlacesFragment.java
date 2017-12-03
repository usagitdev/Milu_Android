package charles.com.milu.PlacesTab;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.R;

public class NearbyPlacesFragment extends BaseFragment implements NearbyPlacesAdapter.ItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    ArrayList<NearbyPlacesItem> mEventItems;

    public static NearbyPlacesFragment getInstance() {
        // Required empty public constructor
        NearbyPlacesFragment fragment = new NearbyPlacesFragment();
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
        txtLeft.setText("nearby places");

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


        mEventItems = NearbyPlacesItem.createContactsList(0);
        mEventItems.add(0, new NearbyPlacesItem(R.drawable.neweventback00,"cup of coffe cafe","56 people • 12 interests","0.9mi", true));
        mEventItems.add(1, new NearbyPlacesItem(R.drawable.neweventback01,"secret hidden bar","72 people • 17 interests","1.2mi", false));
        mEventItems.add(2, new NearbyPlacesItem(R.drawable.neweventback02,"top notch tavern","64 people • 19 interests","4.6mi",true));
        mEventItems.add(3, new NearbyPlacesItem(R.drawable.neweventback03,"crisp craft beer hall","96 people • 13 interests","1.2mi",false));
        mEventItems.add(4, new NearbyPlacesItem(R.drawable.neweventback04,"cup of coffe cafe","56 people • 12 interests","0.9mi", true));
        mEventItems.add(5, new NearbyPlacesItem(R.drawable.neweventback05,"secret hidden bar","72 people • 17 interests","1.2mi", false));
        mEventItems.add(6, new NearbyPlacesItem(R.drawable.neweventback06,"top notch tavern","64 people • 19 interests","4.6mi",true));
        mEventItems.add(7, new NearbyPlacesItem(R.drawable.neweventback07,"crisp craft beer hall","96 people • 13 interests","1.2mi",false));
        mEventItems.add(8, new NearbyPlacesItem(R.drawable.neweventback08,"cup of coffe cafe","56 people • 12 interests","0.9mi", true));
        mEventItems.add(9, new NearbyPlacesItem(R.drawable.neweventback09,"secret hidden bar","72 people • 17 interests","1.2mi", false));
        mEventItems.add(10, new NearbyPlacesItem(R.drawable.neweventback10,"top notch tavern","64 people • 19 interests","4.6mi",true));
        mEventItems.add(11, new NearbyPlacesItem(R.drawable.neweventback11,"secret hidden bar","72 people • 17 interests","1.2mi", false));
        mEventItems.add(12, new NearbyPlacesItem(R.drawable.neweventback12,"top notch tavern","64 people • 19 interests","4.6mi",true));
        mEventItems.add(13, new NearbyPlacesItem(R.drawable.neweventback13,"crisp craft beer hall","96 people • 13 interests","1.2mi",false));
        mEventItems.add(14, new NearbyPlacesItem(R.drawable.neweventback00,"cup of coffe cafe","56 people • 12 interests","0.9mi", true));
        mEventItems.add(15, new NearbyPlacesItem(R.drawable.neweventback01,"secret hidden bar","72 people • 17 interests","1.2mi", false));
        mEventItems.add(16, new NearbyPlacesItem(R.drawable.neweventback02,"top notch tavern","64 people • 19 interests","4.6mi",true));
        mEventItems.add(17, new NearbyPlacesItem(R.drawable.neweventback03,"crisp craft beer hall","96 people • 13 interests","1.2mi",false));
        mEventItems.add(18, new NearbyPlacesItem(R.drawable.neweventback04,"cup of coffe cafe","56 people • 12 interests","0.9mi", true));
        mEventItems.add(19, new NearbyPlacesItem(R.drawable.neweventback05,"secret hidden bar","72 people • 17 interests","1.2mi", false));
        mEventItems.add(20, new NearbyPlacesItem(R.drawable.neweventback06,"top notch tavern","64 people • 19 interests","4.6mi",true));

        NearbyPlacesAdapter adapter = new NearbyPlacesAdapter(getContext(), mEventItems);
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
