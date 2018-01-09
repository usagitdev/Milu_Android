package charles.com.milu;

import android.content.Context;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import charles.com.milu.AppDataPak.AppInfo;
import charles.com.milu.utils.SmartAsyncPolicyHolder;

/**
 * Created by mac_dev on 10/27/17.
 */

public class MiluApplication extends MultiDexApplication {

    public static boolean isCollection = false;
    public static Location myLocation;
    static MiluApplication mInstance;

    public AppInfo appInfo;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        myLocation = new Location();
        SmartAsyncPolicyHolder.INSTANCE.init(getApplicationContext());
        appInfo = new AppInfo(PreferenceManager.getDefaultSharedPreferences(this));

    }

    public static synchronized MiluApplication getInstance() {
        return mInstance;
    }
    public void setmInstance(MiluApplication mInstance) {
        MiluApplication.mInstance = mInstance;
    }

}
