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

public class FavoritePlacesFragment extends BaseFragment implements FavoritePlacesAdapter.ItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    ArrayList<FavoritePlacesItem> mEventItems;

    public static FavoritePlacesFragment getInstance() {
        // Required empty public constructor
        FavoritePlacesFragment fragment = new FavoritePlacesFragment();
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
        txtLeft.setText("favorite places");

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


        mEventItems = FavoritePlacesItem.createContactsList(0);
        mEventItems.add(0, new FavoritePlacesItem(R.drawable.neweventback00,"blue and yellow gallery","2K people • 12 interests","403", true));
        mEventItems.add(1, new FavoritePlacesItem(R.drawable.neweventback01,"classic red theater","132 people • 7 interests","123", false));
        mEventItems.add(2, new FavoritePlacesItem(R.drawable.neweventback02,"great brick square","504 people • 19 interests","436",true));
        mEventItems.add(3, new FavoritePlacesItem(R.drawable.neweventback03,"the rusty general store","1.3k people • 13 interests","123",false));
        mEventItems.add(4, new FavoritePlacesItem(R.drawable.neweventback04,"blue and yellow gallery","2K people • 12 interests","403", true));
        mEventItems.add(5, new FavoritePlacesItem(R.drawable.neweventback05,"classic red theater","132 people • 7 interests","123", false));
        mEventItems.add(6, new FavoritePlacesItem(R.drawable.neweventback06,"great brick square","504 people • 19 interests","436",true));
        mEventItems.add(7, new FavoritePlacesItem(R.drawable.neweventback07,"the rusty general store","1.3k people • 13 interests","123",false));
        mEventItems.add(8, new FavoritePlacesItem(R.drawable.neweventback08,"blue and yellow gallery","2K people • 12 interests","403", true));
        mEventItems.add(9, new FavoritePlacesItem(R.drawable.neweventback09,"classic red theater","132 people • 7 interests","123", false));
        mEventItems.add(10, new FavoritePlacesItem(R.drawable.neweventback10,"great brick square","504 people • 19 interests","436",true));
        mEventItems.add(11, new FavoritePlacesItem(R.drawable.neweventback11,"the rusty general store","1.3k people • 13 interests","123",false));

        FavoritePlacesAdapter adapter = new FavoritePlacesAdapter(getContext(), mEventItems);
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
