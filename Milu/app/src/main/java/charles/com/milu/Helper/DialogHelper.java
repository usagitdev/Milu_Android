package charles.com.milu.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Chris on 1/9/2017.
 */

public class DialogHelper {
    public static void showServerConnectError(Context context) {
        showServerConnectError(context, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    public static void showServerConnectError(Context context, DialogInterface.OnClickListener listener) {
        showErrorAlertDialog("Server connection error, try again...", context, listener);
    }

    public static void showAlertDialog(String title, String msg, Context context, DialogInterface.OnClickListener listener) {
        showAlertDialog(title, msg, context, "OK", listener);
    }

    public static void showErrorAlertDialog(String msg, Context context) {
        showAlertDialog("", msg, context, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    public static void showErrorAlertDialog(String msg, Context context, DialogInterface.OnClickListener listener) {
        showAlertDialog("error", msg, context, "OK", listener);
    }

    public static void showAlertDialog(String title, String msg, Context context, String positiveLabel, DialogInterface.OnClickListener listener) {
        if(context == null){
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing() || ((Activity) context).isDestroyed()) {
                return;
            }
        }
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(positiveLabel, listener)
                .show();
    }

}


