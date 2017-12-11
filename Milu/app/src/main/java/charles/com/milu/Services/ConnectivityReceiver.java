package charles.com.milu.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import charles.com.milu.utils.CommonUtils;

/**
 * Created by mac_dev on 12/5/17.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

    public static ConnectivityReceiverListener connectivityReceiverListener;
    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isChanged);
        void onGpsConnectionChanged(boolean isChanged);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case "android.net.conn.CONNECTIVITY_CHANGE":
                if (connectivityReceiverListener != null) {
                    connectivityReceiverListener.onNetworkConnectionChanged(CommonUtils.isInternetConnected(context));
                }

                break;
            case "android.location.PROVIDERS_CHANGED":
                if (connectivityReceiverListener != null) {
                    connectivityReceiverListener.onGpsConnectionChanged(CommonUtils.isGpsEnable(context));
                }
                break;
            default:
                break;
        }

    }
}
