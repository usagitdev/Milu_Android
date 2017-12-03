package charles.com.milu.CalendarView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import charles.com.milu.Base.BaseFragment;
import charles.com.milu.EventBusListeners.ReplaceFragmentListener;
import charles.com.milu.HomeScreen.Tabbar_eventdash;
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.NewEvents.NewEventsAdapter;
import charles.com.milu.R;
import butterknife.BindView;

public class CalendarFragment extends BaseFragment implements OnDateSelectedListener, OnMonthChangedListener, CalendarEventAdapter.ItemClickListener,CalendarCollectionAdapter.ItemClickListener {

    @BindView(R.id.calListView)
    LinearLayout cal_ListView;

    @BindView(R.id.calCollectionView)
    LinearLayout cal_CollectionView;

    @BindView(R.id.calEvent_RecyclerView)
    RecyclerView listRecyclerView ;

    @BindView(R.id.calCollection_RecyclerView)
    RecyclerView collectionRecyclerView;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.calAdd_button)
    ImageView addButton;

    @BindView(R.id.calendarView)
    MaterialCalendarView mCalender;


    ArrayList<CalendarEventItem> mEventItems;
    ArrayList<CalendarCollectionItem> mCollectionItems;
    boolean convertFlag;

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
            case R.id.toolbar_btn_right1:
                if (convertFlag){

                    cal_CollectionView.setVisibility(View.VISIBLE);
                    cal_ListView.setVisibility(View.INVISIBLE);
                    rightButton1.setImageResource(R.drawable.nav_bar_collection_icon1);
                    convertFlag = false;

                }else{

                    cal_CollectionView.setVisibility(View.INVISIBLE);
                    cal_ListView.setVisibility(View.VISIBLE);
                    rightButton1.setImageResource(R.drawable.nav_bar_list_icon1);
                    convertFlag = true;
                }
                break;
            case R.id.toolbar_btn_right2:
                break;
        }
    }

    @Override
    protected int addView() {
        return R.layout.evanedash_calendar_fragment;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setCalendar();
        setAdapter();
        setCollectionViewData();
        calAddButtonSet();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert txtLeft != null;
        txtLeft.setText("september");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton1 != null;
        rightButton1.setImageResource(R.drawable.nav_bar_list_icon1);
        rightButton1.setVisibility(View.VISIBLE);
        rightButton1.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_search_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);

        convertFlag = true;
        cal_CollectionView.setVisibility(View.INVISIBLE);
        rightButton1.setImageResource(R.drawable.nav_bar_list_icon1);
    }



    public static CalendarFragment getInstance() {
        // Required empty public constructor
        CalendarFragment fragment = new CalendarFragment();
        return fragment;
    }

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.evanedash_calendar_fragment, container, false);
//
//        cal_ListView = (LinearLayout) view.findViewById(R.id.calListView);
//        cal_CollectionView = (LinearLayout) view.findViewById(R.id.calCollectionView);
//        listRecyclerView = (RecyclerView) view.findViewById(R.id.calEvent_RecyclerView);
//        collectionRecyclerView = (RecyclerView) view.findViewById(R.id.calCollection_RecyclerView);
//
//        textView = (TextView) view.findViewById(R.id.textView);
//
//        setTitleType(view);
//        setBackButton(view);
//        setListIcon(view);
//        setCalendar(view);
//        setAdapter(view);
//        setCollectionViewData(view);
//        calAddButtonSet(view);
//
//        return view;
//    }
    public void calAddButtonSet(){

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext()," Calendar add button clicked!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setCollectionViewData(){

        mCollectionItems = CalendarCollectionItem.createContactsList(0);
        mCollectionItems.add(0, new CalendarCollectionItem(R.drawable.neweventback00,"the roots","7PM 101 independence are"));
        mCollectionItems.add(1, new CalendarCollectionItem(R.drawable.neweventback01,"chemical brothers","2K people • 12 interests"));
        mCollectionItems.add(2, new CalendarCollectionItem(R.drawable.neweventback02,"daft punk","223 people • 126 interests"));
        mCollectionItems.add(3, new CalendarCollectionItem(R.drawable.neweventback03,"black keys","2K people • 123 interests"));
        mCollectionItems.add(4, new CalendarCollectionItem(R.drawable.neweventback04,"chemical brothers","2K people • 456 interests"));
        mCollectionItems.add(5, new CalendarCollectionItem(R.drawable.neweventback05,"absolutely fabulous","2K people • 56 interests"));
        mCollectionItems.add(6, new CalendarCollectionItem(R.drawable.neweventback06,"amfa afterparty with me","7PM 101 independence are"));
        mCollectionItems.add(7, new CalendarCollectionItem(R.drawable.neweventback07,"cocktail party","2K people • 45 interests"));
        mCollectionItems.add(8, new CalendarCollectionItem(R.drawable.neweventback08,"halloween party","2K people • 45 interests"));
        mCollectionItems.add(9, new CalendarCollectionItem(R.drawable.neweventback00,"the roots","2K people • 12 interests"));
        mCollectionItems.add(10, new CalendarCollectionItem(R.drawable.neweventback01,"chemical brothers","7PM 101 independence are"));
        mCollectionItems.add(11, new CalendarCollectionItem(R.drawable.neweventback02,"daft puck party","223 people • 345 interests"));
        mCollectionItems.add(12, new CalendarCollectionItem(R.drawable.neweventback03,"chmical party with hie","7PM 101 independence are"));
        mCollectionItems.add(13, new CalendarCollectionItem(R.drawable.neweventback04,"absolutely fabulous","2K people • 12 interests"));
        mCollectionItems.add(14, new CalendarCollectionItem(R.drawable.neweventback05,"all-star after party","2K people • 45 interests"));
        mCollectionItems.add(15, new CalendarCollectionItem(R.drawable.neweventback06,"cocktail party","223 people • 345 interests"));
        mCollectionItems.add(16, new CalendarCollectionItem(R.drawable.neweventback07,"halloween midnight party","7PM 101 independence are"));
        mCollectionItems.add(17, new CalendarCollectionItem(R.drawable.neweventback08,"amfa afterparty with me","2K people • 12 interests"));

        CalendarCollectionAdapter adapter = new CalendarCollectionAdapter(getContext(), mCollectionItems);
        collectionRecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        collectionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        collectionRecyclerView.setLayoutManager(gridLayoutManager);

        collectionRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 5, true));

    }
    public void setAdapter(){

        mEventItems = CalendarEventItem.createContactsList(0);
        mEventItems.add(0, new CalendarEventItem(true,R.drawable.neweventback00,"the roots"));
        mEventItems.add(1, new CalendarEventItem(false,R.drawable.neweventback01,"chemical brothers"));
        mEventItems.add(2, new CalendarEventItem(true,R.drawable.neweventback02,"daft punk"));
        mEventItems.add(3, new CalendarEventItem(true,R.drawable.neweventback03,"black keys"));
        mEventItems.add(4, new CalendarEventItem(false,R.drawable.neweventback04,"chemical brothers"));
        mEventItems.add(5, new CalendarEventItem(true,R.drawable.neweventback05,"absolutely fabulous"));
        mEventItems.add(6, new CalendarEventItem(true,R.drawable.neweventback06,"amfa afterparty with me"));
        mEventItems.add(7, new CalendarEventItem(false,R.drawable.neweventback07,"cocktail party"));
        mEventItems.add(8, new CalendarEventItem(true,R.drawable.neweventback08,"halloween party"));
        mEventItems.add(9, new CalendarEventItem(false,R.drawable.neweventback00,"the roots"));
        mEventItems.add(10, new CalendarEventItem(false,R.drawable.neweventback01,"chemical brothers"));
        mEventItems.add(11, new CalendarEventItem(true,R.drawable.neweventback02,"daft puck party"));
        mEventItems.add(12, new CalendarEventItem(true,R.drawable.neweventback03,"chmical party with hie"));
        mEventItems.add(13, new CalendarEventItem(false,R.drawable.neweventback04,"absolutely fabulous"));
        mEventItems.add(14, new CalendarEventItem(false,R.drawable.neweventback05,"all-star after party"));
        mEventItems.add(15, new CalendarEventItem(true,R.drawable.neweventback06,"cocktail party"));
        mEventItems.add(16, new CalendarEventItem(true,R.drawable.neweventback07,"halloween midnight party"));
        mEventItems.add(17, new CalendarEventItem(true,R.drawable.neweventback08,"amfa afterparty with me"));

        CalendarEventAdapter adapter = new CalendarEventAdapter(getContext(), mEventItems);
        listRecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        listRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        listRecyclerView.setLayoutManager(gridLayoutManager);

        listRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 5, true));

    }


    public void setCalendar(){

//        mCalender.state().edit()
//                .setMinimumDate(CalendarDay.from(2016,12,1))
//                .setMinimumDate(CalendarDay.from(2018,12,1))
//                .commit();

    }
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

        Toast.makeText(this.getContext()," MeetUp item clicked!!! cell position = " + String.valueOf(widget.getSelectedDate()), Toast.LENGTH_SHORT).show();

    }

    private String getSelectedDatesString() {
        CalendarDay date = mCalender.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

    }

    @Override
    public void mEventCell_Clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," Calendar List item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mCalendarCollectionEventCell_Clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," Calendar CollectionView item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }
}
