package charles.com.milu.Services;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import charles.com.milu.MiluApplication;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

/**
 * Created by mac on 8/16/17.
 */

public class LocationService extends Service implements LocationListener {

    private static String TAG = "Milu/" + LocationService.class.getName();
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 0.5 mile  = 804.17 meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60;        // 1 minute

    private static LocationService locationService = null;
    private LocationManager locationManager;
    private LocationChangeListener listener;

    public static LocationService getInstance() {
        if (locationService == null) {
            locationService = new LocationService();
        }

        return locationService;
    }

    public void startLocationService(final Activity mActivity) {

        Nammu.init(mActivity);
        if (!Nammu.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) && !Nammu.checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Nammu.askForPermission(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    locationManager = (LocationManager) mActivity.getSystemService(LOCATION_SERVICE);

                    boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                    if (isGPSEnabled || isNetworkEnabled) {
                        Location location = null;
                        if (isGPSEnabled) {
                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                    ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, LocationService.this);
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                Log.e(TAG, "GPS_PROVIDER");
                                MiluApplication.myLocation = location;
//                                Location loc = new Location(LocationManager.GPS_PROVIDER);
//                                loc.setLatitude(42.894286);
//                                loc.setLongitude(129.515054);
//                                DataManager_.getInstance_(mActivity).myLocation = loc;
                            }
                        }

                        if (isNetworkEnabled) {
                            if (location == null) {
                                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, LocationService.this);
                                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                if (location != null) {
                                    Log.e(TAG, "NETWORK_PROVIDER");
                                    MiluApplication.myLocation = location;
//                                    Location loc = new Location(LocationManager.GPS_PROVIDER);
//                                    loc.setLatitude(42.894286);
//                                    loc.setLongitude(129.515054);
//                                    DataManager_.getInstance_(mActivity).myLocation = loc;
                                }
                            }
                        }
                    }
                }

                @Override
                public void permissionRefused() {
                    stopLocationService();
                }
            });
        } else {
            locationManager = (LocationManager) mActivity.getSystemService(LOCATION_SERVICE);

            boolean isGPSEnabled     = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isGPSEnabled || isNetworkEnabled) {
                Location location = null;

                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, LocationService.this);
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        Log.e(TAG, "GPS_PROVIDER");
                        MiluApplication.myLocation = location;
//                        Location loc = new Location(LocationManager.GPS_PROVIDER);
//                        loc.setLatitude(42.894286);
//                        loc.setLongitude(129.515054);
//                        DataManager_.getInstance_(mActivity).myLocation = loc;
                    }
                }

                if (isNetworkEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, LocationService.this);
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            Log.e(TAG, "NETWORK_PROVIDER");
                            MiluApplication.myLocation = location;
//                            Location loc = new Location(LocationManager.GPS_PROVIDER);
//                            loc.setLatitude(42.894286);
//                            loc.setLongitude(129.515054);
//                            DataManager_.getInstance_(mActivity).myLocation = loc;
                        }
                    }
                }
            }
        }

    }

    public void stopLocationService() {
        if (locationManager != null) {
            locationManager.removeUpdates(LocationService.this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, location.getLatitude() + " : " + location.getLongitude());

        MiluApplication.myLocation = location;
        this.listener.onLocationChanged(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public interface LocationChangeListener{

        void onLocationChanged(Location location);

    }

    public void setLocationChangeListener(LocationChangeListener listener){
        this.listener = listener;
    }

}


