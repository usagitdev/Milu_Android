package charles.com.milu.UpcomingEventHostPage;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.rubensousa.gravitysnaphelper.GravityPagerSnapHelper;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import net.robinx.lib.blur.utils.BlurUtils;
import net.robinx.lib.blur.widget.BlurDrawable;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.EventBusListeners.ReplaceFragmentListener;
import charles.com.milu.LiveEvents.LiveFeedMain.LiveFeedMainFragment;
import charles.com.milu.PlacesTab.PlaceMapFragment;
import charles.com.milu.R;
import charles.com.milu.UpComingEvents.UpComingFragment;
import charles.com.milu.Utility.SquareImageView;
import charles.com.milu.utils.logger.Images;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingHostFragment extends BaseFragment implements OnMapReadyCallback , GravitySnapHelper.SnapListener, GoogleMap.OnMarkerClickListener{

    Typeface workSans_Light;
    private GoogleMap mMap;
    private Marker customMarker;
    private LatLng markerLatLng;
    private GoogleMap googleMap;
    private RelativeLayout mTitleBarBlurLayout;

    @BindView(R.id.upComingHost_ScrollView)
    ScrollView mMainScrollView;

    @BindView(R.id.upcomingHost_CommentRecyclerView)
    RecyclerView commentRecyclerView;

    @BindView(R.id.upcomingHost_LikeRecyclerView)
    RecyclerView likeRecyclerView;

    @BindView(R.id.upcomingHost_PeopleRecyclerView)
    RecyclerView peopleRecyclerView;

    @BindView(R.id.map)
    MapView mapView;


    public static UpcomingHostFragment getInstance() {
        // Required empty public constructor
        UpcomingHostFragment fragment = new UpcomingHostFragment();
        return fragment;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
            case R.id.toolbar_btn_right2:
                break;
        }
    }
    @Override
    protected int addView() {
        return R.layout.fragment_upcoming_host;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setScrollView();

        setRecyclerView();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert txtLeft != null;
        txtLeft.setText("events");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton1 != null;
        rightButton1.setImageResource(R.drawable.nav_bar_share_icon);
        rightButton1.setVisibility(View.VISIBLE);
        rightButton1.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_setting_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);
    }


    public void setScrollView(){

    }

    public void setRecyclerView() {
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(mAct, LinearLayoutManager.HORIZONTAL, false));
        new GravitySnapHelper(Gravity.START, false, this).attachToRecyclerView(peopleRecyclerView);
//        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        new GravityPagerSnapHelper(Gravity.START).attachToRecyclerView(peopleRecyclerView);
        peopleRecyclerView.setAdapter(new Adapter(false));

        likeRecyclerView.setLayoutManager(new LinearLayoutManager(mAct, LinearLayoutManager.HORIZONTAL, false));
        new GravitySnapHelper(Gravity.START, false, this).attachToRecyclerView(likeRecyclerView);
        likeRecyclerView.setAdapter(new Adapter(false));

        commentRecyclerView.setLayoutManager(new LinearLayoutManager(mAct, LinearLayoutManager.HORIZONTAL, false));
        new GravityPagerSnapHelper(Gravity.START).attachToRecyclerView(commentRecyclerView);
        commentRecyclerView.setAdapter(new Adapter(true));
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

    }

    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(48.8567,2.3508);
//        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        map.addMarker(new MarkerOptions().position(sydney));


        if (map == null) {
            return;
        }

        // adding a marker on map with image from  drawable
        map.addMarker(new MarkerOptions()
                .position(sydney)
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.arts))));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        map.setOnMarkerClickListener(this);

    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_maker_layout, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.map_DropImage);
//        markerImageView.setImageResource(resId);
        Picasso.with(getContext()).load(resId).into(markerImageView);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight() + 5);
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public void onSnap(int position) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        addFragment(PlaceMapFragment.getInstance(), true);
        return true;
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private boolean mPager;

        public Adapter(boolean pager) {
            mPager = pager;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mPager) {
                return new ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.coment_recycler_item, parent, false));
            } else {
                return new ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_image_item, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String url = Images.imageThumbUrls[position];
            Picasso.with(getContext()).load(url).into(holder.imageView);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return Images.imageUrls.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public SquareImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                imageView = (SquareImageView) itemView.findViewById(R.id.user_image);
            }

            @Override
            public void onClick(View v) {

            }
        }

    }

}
