package charles.com.milu.Utility;

import android.util.Patterns;

/**
 * Created by PIG18 on 1/13/2017.
 */

public class PhoneEmailValidator {
    public static int getPhoneEmailType(String strPhoneEmail){
        strPhoneEmail = strPhoneEmail.trim();
        String strEmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        /*if(strPhoneEmail.matches(strEmailPattern)){
            return 1;
        }*/
        if(Patterns.EMAIL_ADDRESS.matcher(strPhoneEmail).matches()){
            return 1;
        }

        String strPhonePattern = "^\\+[0-9]{7,13}$";
        if(strPhoneEmail.matches(strPhonePattern)){
            return 2;
        }
        return 0;
    }
}



