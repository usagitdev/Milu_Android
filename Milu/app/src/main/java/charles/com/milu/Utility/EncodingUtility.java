package charles.com.milu.Utility;

import android.util.Base64;

/**
 * Created by PIG18 on 3/10/2017.
 */

public class EncodingUtility {
    public static String getEncodedString(String plainText){
        String encoded = plainText;
        try {
            encoded = Base64.encodeToString(encoded.getBytes(), Base64.NO_WRAP);
        }catch (Exception e){

        }
        return encoded;
    }

    public static String getDecodedString(String encodeText){
        String decoded = encodeText;
        try{
            byte[] data = Base64.decode(encodeText, Base64.NO_WRAP);
            decoded = new String(data);
        }catch (Exception e){

        }
        return decoded;
    }
}

