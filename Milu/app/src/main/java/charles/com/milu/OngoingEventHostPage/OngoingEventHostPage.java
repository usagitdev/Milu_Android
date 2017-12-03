package charles.com.milu.OngoingEventHostPage;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.EventDash.EventSettingFragment;
import charles.com.milu.LiveEvents.EventPagePosterFragment;
import charles.com.milu.LiveEvents.LiveFeedMain.LiveFeedMainFragment;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OngoingEventHostPage extends BaseFragment implements OnMapReadyCallback {

    @BindView(R.id.onGoingHost_Map)
    MapView mapView;

    @BindView(R.id.onGoingHost_ScrollView)
    NestedScrollView mScrollview;
    @BindView(R.id.liveEventButton)
    ImageButton liveEventButton;
    @BindView(R.id.onGoingHost_eventImage)
    ImageView eventImageView;

    public static OngoingEventHostPage getInstance() {
        // Required empty public constructor
        OngoingEventHostPage fragment = new OngoingEventHostPage();
        return fragment;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
            case R.id.toolbar_btn_right1:
                addChildFragment(EventPagePosterFragment.getInstance(), true);
                break;
            case R.id.toolbar_btn_right2:
                addChildFragment(EventSettingFragment.getInstance(), true);
                break;
            case R.id.liveEventButton:
                addChildFragment(LiveFeedMainFragment.getInstance(), true);
                break;
        }
    }
    @Override
    protected int addView() {
        return R.layout.fragment_ongoing_event_host_page;
    }

    @Override
    public void initView() {
        super.initView();
        liveEventButton.setOnClickListener(this);
        setToolBar();
        setScrollView();

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

    @OnClick(R.id.onGoingHost_eventImage)
    public void onClickFeedImage(View view) {
        addChildFragment(EventPagePosterFragment.getInstance(), true);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

    }

//    public void setTitleBarBlurEffect(){
//
//        mTitleblurlayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mTitleblurlayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
//                blurDrawable.setDrawOffset(mTitleblurlayout.getLeft(), mTitleblurlayout.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
//                blurDrawable.setCornerRadius(5);
//                blurDrawable.setBlurRadius(5);
//                blurDrawable.setDownSampleFactor(25);
//                mTitleblurlayout.setBackgroundDrawable(blurDrawable);
//                Toast.makeText(getContext(),"upvote Button2 Clicked!!   cell position = " + String.valueOf(mTitleblurlayout.getLeft()), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    public void setScrollView(){

        mScrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

//                ViewCompat.postInvalidateOnAnimation(mTitleblurlayout);

            }
        });

    }

    public void onMapReady(GoogleMap map) {

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(48.8567,2.3508);
        map.addMarker(new MarkerOptions().position(sydney));

        if (map == null) {
            return;
        }

        map.addMarker(new MarkerOptions()
                .position(sydney)
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.sports_fitness))));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

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

}
