package charles.com.milu.FavouriteEvents;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.model.people.Person;

import net.robinx.lib.blur.utils.BlurUtils;
import net.robinx.lib.blur.widget.BlurDrawable;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.EventBusListeners.ReplaceFragmentListener;
import charles.com.milu.HomeScreen.Tabbar_eventdash;
import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.R;


public class FavEventFragment extends BaseFragment implements FavEventAdapter.ItemClickListener{

    @BindView(R.id.favEvent_FilterTitleBar)
    RelativeLayout mFilterTitileBar;


    @BindView(R.id.favEvent_filterscrollview)
    ScrollView mFilterScrollView;

    @BindView(R.id.favEvent_filter_closebtn)
    ImageView filterClosebtn;

    @BindView(R.id.favEvent_RecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.favEvent_Filterlayout)
    RelativeLayout mFilterLayout;

    ArrayList<FavEventItem> mEventItems;

    public static FavEventFragment getInstance() {
        // Required empty public constructor
        FavEventFragment fragment = new FavEventFragment();
        return fragment;
    }

    @Override
    protected int addView() {
        return R.layout.eventdash_favevent_fragment;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setAdapter();
        setTitleBar_BlurEffect();
        setFilterButton();
        setFilterCloseButton();
        setFilterBlurEffect();
        setFilterTitleBar_Blureffect();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert txtLeft != null;
        txtLeft.setText("favourite events");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_filter_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;

            case R.id.toolbar_btn_right2:
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) getContext()).getWindowManager()
                        .getDefaultDisplay()
                        .getMetrics(displayMetrics);
                mFilterLayout.animate().translationX(-mFilterLayout.getWidth());

                break;
        }
    }
    public void setFilterTitleBar_Blureffect(){
//
//        mFilterTitileBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mFilterTitileBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
//                blurDrawable.setDrawOffset(mFilterTitileBar.getLeft(), mFilterTitileBar.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
//                blurDrawable.setCornerRadius(2);
//                blurDrawable.setBlurRadius(5);
//                mFilterTitileBar.setBackgroundDrawable(blurDrawable);
//            }
//        });
    }
    public void setFilterBlurEffect(){

//        mFilterScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mFilterScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
//                blurDrawable.setDrawOffset(mFilterScrollView.getLeft(), mFilterScrollView.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
//                blurDrawable.setCornerRadius(2);
//                blurDrawable.setBlurRadius(5);
//                mFilterScrollView.setBackgroundDrawable(blurDrawable);
//            }
//        });
    }

    public void setFilterCloseButton(){

            filterClosebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mFilterLayout.animate().translationX(0);

                }
            });
    }
    public void setFilterButton(){

//            filterBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    DisplayMetrics displayMetrics = new DisplayMetrics();
//                    ((Activity) getContext()).getWindowManager()
//                            .getDefaultDisplay()
//                            .getMetrics(displayMetrics);
//                    mFilterLayout.animate().translationX(-mFilterLayout.getWidth());
//                }
//            });
    }

    public void setTitleBar_BlurEffect(){

//        mTitleLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mTitleLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
//                blurDrawable.setDrawOffset(mTitleLayout.getLeft(), mTitleLayout.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
//                blurDrawable.setCornerRadius(5);
//                blurDrawable.setBlurRadius(5);
//                mTitleLayout.setBackgroundDrawable(blurDrawable);
//            }
//        });
    }

    public void setAdapter(){


        mEventItems = FavEventItem.createContactsList(0);
        mEventItems.add(0, new FavEventItem(R.drawable.neweventback00,"the roots","2K people","423"));
        mEventItems.add(1, new FavEventItem(R.drawable.neweventback01,"chemical brothers","2K people","1K"));
        mEventItems.add(2, new FavEventItem(R.drawable.neweventback02,"daft punk","2K people","456"));
        mEventItems.add(3, new FavEventItem(R.drawable.neweventback03,"black keys","2K people","1K"));
        mEventItems.add(4, new FavEventItem(R.drawable.neweventback04,"chemical brothers","2K people","720"));
        mEventItems.add(5, new FavEventItem(R.drawable.neweventback05,"the roots","2K people","345"));
        mEventItems.add(6, new FavEventItem(R.drawable.neweventback06,"daft punk","2K people","23"));
        mEventItems.add(7, new FavEventItem(R.drawable.neweventback07,"black keys","2K people","435"));
        mEventItems.add(8, new FavEventItem(R.drawable.neweventback08,"inxa rave","2K people","45"));
        mEventItems.add(9, new FavEventItem(R.drawable.neweventback00,"bbq party","2K people","7K"));
        mEventItems.add(10, new FavEventItem(R.drawable.neweventback01,"chemical brothers","2K people","3K"));
        mEventItems.add(11, new FavEventItem(R.drawable.neweventback02,"summer beach party","2K people","223"));
        mEventItems.add(12, new FavEventItem(R.drawable.neweventback03,"black keys","2K people","4K"));
        mEventItems.add(13, new FavEventItem(R.drawable.neweventback04,"chemical brothers","2K people","245"));
        mEventItems.add(14, new FavEventItem(R.drawable.neweventback05,"all-star after party","2K people","36K"));
        mEventItems.add(15, new FavEventItem(R.drawable.neweventback06,"daft punk","2K people","235"));
        mEventItems.add(16, new FavEventItem(R.drawable.neweventback07,"black keys","2K people","5K"));
        mEventItems.add(17, new FavEventItem(R.drawable.neweventback08,"the roots","2K people","348"));

        FavEventAdapter adapter = new FavEventAdapter(getContext(), mEventItems);
        mRecyclerView.setAdapter(adapter);
        adapter.setListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 5, true));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                ViewCompat.postInvalidateOnAnimation(mTitleLayout);
                ViewCompat.postInvalidateOnAnimation(mFilterScrollView);

            }
        });
    }

    @Override
    public void mEventCell_Clicked(View v, int adapterPosition) {

        Toast.makeText(v.getContext()," Fav Event item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }
}
