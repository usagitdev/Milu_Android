package charles.com.milu.MyEvents;

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
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.R;

public class MyEventsFragment extends BaseFragment implements MyEventAdapter.ItemClickListener{

    @BindView(R.id.myEvent_RecyclerView)
    RecyclerView mRecyclerView;

    ArrayList<MyEventItem> mEventItems;
    Typeface workSans_Light;
    ImageView backButton;

    public static MyEventsFragment getInstance() {
        // Required empty public constructor
        MyEventsFragment fragment = new MyEventsFragment();
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
        return R.layout.eventdash_myevents_fragment;
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
        txtLeft.setText("myevents");

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
//        View view = inflater.inflate(R.layout.eventdash_myevents_fragment, container, false);
//
//        setTitleType(view);
//        setBackButton(view);
//        setAdapter(view);
//
//        return view;
//    }

    public void setAdapter(){


        mEventItems = MyEventItem.createContactsList(0);
        mEventItems.add(0, new MyEventItem(R.drawable.neweventback00,"the roots","2K people","4.7", true));
        mEventItems.add(1, new MyEventItem(R.drawable.neweventback01,"chemical brothers","2K people","12H", false));
        mEventItems.add(2, new MyEventItem(R.drawable.neweventback02,"daft punk","2K people","4.6",true));
        mEventItems.add(3, new MyEventItem(R.drawable.neweventback03,"black keys","2K people","12H",false));
        mEventItems.add(4, new MyEventItem(R.drawable.neweventback04,"chemical brothers","2K people","3.5",true));
        mEventItems.add(5, new MyEventItem(R.drawable.neweventback05,"the roots","2K people","3.5",true));
        mEventItems.add(6, new MyEventItem(R.drawable.neweventback06,"daft punk","2K people","2.3",true));
        mEventItems.add(7, new MyEventItem(R.drawable.neweventback07,"black keys","2K people","4.5",true));
        mEventItems.add(8, new MyEventItem(R.drawable.neweventback08,"inxa rave","2K people","4.5",true));
        mEventItems.add(9, new MyEventItem(R.drawable.neweventback00,"bbq party","2K people","7K",false));
        mEventItems.add(10, new MyEventItem(R.drawable.neweventback01,"chemical brothers","2K people","3K",false));
        mEventItems.add(11, new MyEventItem(R.drawable.neweventback02,"summer beach party","2K people","2.3",true));
        mEventItems.add(12, new MyEventItem(R.drawable.neweventback03,"black keys","2K people","4K",false));
        mEventItems.add(13, new MyEventItem(R.drawable.neweventback04,"chemical brothers","2K people","2.5",true));
        mEventItems.add(14, new MyEventItem(R.drawable.neweventback05,"all-star after party","2K people","3K",false));
        mEventItems.add(15, new MyEventItem(R.drawable.neweventback06,"daft punk","2K people","2.5",true));
        mEventItems.add(16, new MyEventItem(R.drawable.neweventback07,"black keys","2K people","5K",false));
        mEventItems.add(17, new MyEventItem(R.drawable.neweventback08,"the roots","2K people","3.8",true));

        MyEventAdapter adapter = new MyEventAdapter(getContext(), mEventItems);
        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 5, true));

    }

    @Override
    public void mEventCell_Clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," My Event item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }
}
