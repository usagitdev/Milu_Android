package charles.com.milu;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import charles.com.milu.utils.SmartAsyncPolicyHolder;

/**
 * Created by mac_dev on 10/27/17.
 */

public class MiluApplication extends MultiDexApplication {

    public static boolean isCollection = false;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        SmartAsyncPolicyHolder.INSTANCE.init(getApplicationContext());
    }
}
