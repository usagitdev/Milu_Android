package charles.com.milu.HomeScreen;


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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.LiveEvents.LiveEventFragment;
import charles.com.milu.PlacesTab.FavoritePlacesFragment;
import charles.com.milu.PlacesTab.LivePlacesFragment;
import charles.com.milu.PlacesTab.NearbyPlacesFragment;
import charles.com.milu.PlacesTab.PlaceMapFragment;
import charles.com.milu.PlacesTab.PopularPlacesFragment;
import charles.com.milu.R;
import charles.com.milu.utils.util.ImageCache;
import charles.com.milu.utils.util.ImageFetcher;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabBar_places extends BaseFragment implements OnMapReadyCallback{

    @BindView(R.id.rl_liveplaces)
    RelativeLayout btnLivePlaces;

    @BindView(R.id.rl_favorite_places)
    RelativeLayout btnFavPlaces;

    @BindView(R.id.rl_myplaces)
    RelativeLayout btnMyPlaces;

    @BindView(R.id.rl_nearby)
    RelativeLayout btnNearByPlaces;

    @BindView(R.id.rl_popular_places)
    RelativeLayout btnPopularPlaces;

    @BindView(R.id.mapView)
    MapView mapView;

    @BindView(R.id.ll_txt_direction)
    LinearLayout viewDirection;


    private GoogleMap mMap;
    private Marker customMarker;
    private LatLng markerLatLng;
    private GoogleMap googleMap;


    public static TabBar_places getInstance() {
        return new TabBar_places();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_tab_bar_places;
    }

    @Override
    public void initView() {
        super.initView();

        setToolBar();
        setPlaces();
    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.logo);
        assert txtLeft != null;
        txtLeft.setText("milu");
        assert rightButton1 != null;
        rightButton1.setImageResource(R.drawable.nav_bar_add_icon);
        rightButton1.setOnClickListener(this);
        assert rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_search_icon);
        rightButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.toolbar_btn_right2:
                break;
            case R.id.toolbar_btn_right1:
                showPlacesMapFragment();
                break;
        }
    }

    public void showPlacesMapFragment(){
        addFragment(PlaceMapFragment.getInstance(), true);
    }

    public void setPlaces() {

        btnLivePlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addChildFragment(LivePlacesFragment.getInstance(), true);

            }
        });

        btnPopularPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChildFragment(PopularPlacesFragment.getInstance(), true);
            }
        });
        btnNearByPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChildFragment(NearbyPlacesFragment.getInstance(), true);
            }
        });
        btnFavPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChildFragment(FavoritePlacesFragment.getInstance(), true);
            }
        });

    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    public void onMapReady(GoogleMap map) {
        MapsInitializer.initialize(getContext());
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

    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_maker_layout, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.map_DropImage);
        markerImageView.setImageResource(resId);
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
