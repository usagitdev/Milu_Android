package charles.com.milu.MeetUps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.LightEditText;
import charles.com.milu.R;
import charles.com.milu.utils.logger.Log;

public class MeetUpLocationSelectionFragment extends BaseFragment implements LocationListener, MeetUpAdapter.ItemClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    @BindView(R.id.meetup_search_recyclerView)
    RecyclerView meet_RecyclerView;

    @BindView(R.id.edt_searchView)
    LightEditText edtSearchView;

    ArrayList<MeetUpItem> meetUpItems;
    ArrayList<MeetUpItem> meetUpResults;
    String currentLocation, current_locality;

    private LinearLayoutManager mLinearLayoutManager;
    private PlacesAutoCompleteAdapter mAutoCompleteAdapter;
    protected GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    Location current;

    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));



    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                backFragment(false);
                break;
        }
    }

    public static MeetUpLocationSelectionFragment getInstance() {
        // Required empty public constructor
        MeetUpLocationSelectionFragment fragment = new MeetUpLocationSelectionFragment();
        return fragment;
    }


    @Override
    protected int addView() {
        return R.layout.fragment_meetups_search_location;
    }

    @Override
    public void initView() {
        super.initView();
        buildGoogleApiClient();
        setToolBar();
        setAdapter();

    }

    @Override
    public void setToolBar(){
        super.setToolBar();

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_cancel_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

    }
    public void getCurrentAddress(Location location) {
            Geocoder gcd = new Geocoder(mAct,
                    Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 1);
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String locality = addresses.get(0).getLocality();
                    String subLocality = addresses.get(0).getSubLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    if (subLocality != null) {

                        currentLocation = locality + "," + subLocality;
                    } else {

                        currentLocation = locality;
                    }
                    current_locality = locality;
                    if (mAutoCompleteAdapter != null) {
                        mAutoCompleteAdapter.setCurrentLocation(current_locality);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(mAct, "error", Toast.LENGTH_SHORT).show();
            }
    }

    public void setAdapter(){


        mAutoCompleteAdapter =  new PlacesAutoCompleteAdapter(mAct, R.layout.meetup_searchview_adapter,
                mGoogleApiClient, BOUNDS_INDIA, null, current_locality);

        mLinearLayoutManager=new LinearLayoutManager(mAct);
        meet_RecyclerView.setLayoutManager(mLinearLayoutManager);
        meet_RecyclerView.setAdapter(mAutoCompleteAdapter);
        edtSearchView.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("") && mGoogleApiClient.isConnected()) {

                    mAutoCompleteAdapter.getFilter().filter(s.toString());
                }else if(!mGoogleApiClient.isConnected()){
//                    Toast.makeText(getApplicationContext(), Constants.API_NOT_CONNECTED,Toast.LENGTH_SHORT).show();
//                    Log.e(Constants.PlacesTag,Constants.API_NOT_CONNECTED);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
        });
        meet_RecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(mAct, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position < 2) {
                            if (current == null){
                                return;
                            }
                            Toast.makeText(mAct,String.valueOf(String.valueOf(current.getLatitude()) + " " + String.valueOf(current.getLongitude())),Toast.LENGTH_SHORT).show();
                            return;
                        }
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = mAutoCompleteAdapter.getItem(position - 2);
                        final String placeId = String.valueOf(item.placeId);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if(places.getCount()==1){
                                    //Do the things here on Click.....
                                    Toast.makeText(mAct,String.valueOf(places.get(0).getLatLng()),Toast.LENGTH_SHORT).show();
                                }else {

                                }
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                    }
                })
        );

    }

    @Override
    public void meetUpCell_Clicked(View v, int adapterPosition) {

//        meetUpResults.add(meetUpItems.get(adapterPosition));
//        Toast.makeText(v.getContext()," MeetUp item clicked!!! cell position = " + String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(mAct,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mAct)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()){
            Log.v("Google API","Connecting");
            mGoogleApiClient.connect();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if(mGoogleApiClient.isConnected()){
            Log.v("Google API","Dis-Connecting");
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        current = location;
        getCurrentAddress(location);
    }
}
