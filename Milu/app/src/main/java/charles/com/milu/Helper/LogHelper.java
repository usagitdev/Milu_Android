package charles.com.milu.Helper;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Chris on 1/9/2017.
 */

public class LogHelper {

    public static boolean       debug_mode = true;
    public static String        debug_name = "my_debug";

    public static void log(String category, String descriptoin){
        if(debug_mode == true){
            Log.d(debug_name, category + " " + descriptoin);
        }else{

        }
    }

    public static void log(String descriptoin){
        if(debug_mode == true){
            Log.d(debug_name, descriptoin);
        }else{

        }
    }

    public static void toast(Context context, String description){
        if(debug_mode == true) {
            Toast.makeText(context, description, Toast.LENGTH_SHORT).show();
        }else{

        }
    }

    public static void alert(Context context, String description){
        if(debug_mode == true) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(description).setPositiveButton("OK", null)
                    .show();
        }else {

        }
    }
}

