package charles.com.milu.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 2017-12-01.
 */

public class UserModel {
    @SerializedName("user_id")
    public int     nId;
    @SerializedName("user_first_name")
    public String     nFirstName;
    @SerializedName("user_last_name")
    public String     nLastName;
    @SerializedName("user_photo")
    public String     nPhoto;


    public UserModel(){
        nId = 0;
        nFirstName = nLastName = nPhoto = "";
    }
}
