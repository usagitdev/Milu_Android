package charles.com.milu.AppDataPak;

import android.content.SharedPreferences;

/**
 * Created by usd_a on 11/14/2017.
 */

public class AppInfo {
    static final String KEY_USER_LOGIN = "USER_ID";
    static final String KEY_USER_ID = "USER_ID";
    static final String KEY_PASSCODE = "USER_PASSCODE";
    static final String KEY_MEMBERUSER = "USER_MEMBER";
    static final String KEY_USERSETTING = "USER_SETTING";
    static final String KEY_SETTING_NOTI = "SETTING_NOTIF";
    static final String KEY_SETTING_SOUND = "SETTING_SOUND";

    public static final String KEY_APP_DATA = "APP_DATA";

    SharedPreferences sp;

    public AppInfo(SharedPreferences sp){
        this.sp = sp;
    }

    public boolean saveLogin(boolean b) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_USER_LOGIN, b);
        editor.apply();
        return b;
    }

    public boolean getUserLogin() {
        boolean b = sp.getBoolean(KEY_USER_LOGIN,false);
        return b;
    }
    public int saveUserId(int nUserId){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_USER_ID, nUserId);
        editor.apply();
        return nUserId;
    }

    public int getUserId(){
        int userId = sp.getInt(KEY_USER_ID, 0);
        return userId;
    }

    public String savePasscode(String strPasscode){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_PASSCODE, strPasscode);
        editor.apply();
        return strPasscode;
    }

    public String getPasscode(){
        String strPasscode = sp.getString(KEY_PASSCODE, "");
        return strPasscode;
    }

//    public void saveCurrentUser(UserInfo userinfo){
//        String jsondata = new Gson().toJson(userinfo, UserInfo.class);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString(KEY_MEMBERUSER, jsondata);
//        editor.apply();
//    }
//
//    public void saveMemberUser(MemberUser user1){
//        String jsondata = new Gson().toJson(user1, MemberUser.class);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString(KEY_MEMBERUSER, jsondata);
//        editor.apply();
//    }
//
//    public void saveUserSettings(UserSettings setting1){
//        String jsondata = new Gson().toJson(setting1, UserSettings.class);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString(KEY_USERSETTING, jsondata);
//        editor.apply();
//    }
//
//    public UserInfo getCurrentUser(){
//        String jsonData = sp.getString(KEY_MEMBERUSER, "");
//        UserInfo user1 = null;
//        if(jsonData.length() > 0) {
//            try {
//                user1 = new Gson().fromJson(jsonData, UserInfo.class);
//            } catch (Exception e) {
//
//            }
//        }
//        return user1;
//    }
//
//    public MemberUser getMemberUser(){
//        String jsonData = sp.getString(KEY_MEMBERUSER, "");
//        MemberUser user1 = null;
//        if(jsonData.length() > 0) {
//            try {
//                user1 = new Gson().fromJson(jsonData, MemberUser.class);
//            } catch (Exception e) {
//
//            }
//        }
//        return user1;
//    }
//
//    public UserSettings getUserSettings(){
//        String jsonData = sp.getString(KEY_USERSETTING, "");
//        UserSettings setting1 = null;
//        if(jsonData.length() > 0) {
//            try {
//                setting1 = new Gson().fromJson(jsonData, UserSettings.class);
//            } catch (Exception e) {
//
//            }
//        }
//        return setting1;
//    }

    public int getAppNotifSetting(){
        int nNotif = getIntWithKey(KEY_SETTING_NOTI);
        if(nNotif == -1){
            saveIntWithKey(1, KEY_SETTING_NOTI);
            return 1;
        }
        return nNotif;
    }

    public void saveAppNotifSetting(int value){
        saveIntWithKey(value, KEY_SETTING_NOTI);
    }

    public int getAppSoundSetting(){
        int nSound = getIntWithKey(KEY_SETTING_SOUND);
        if(nSound == -1){
            saveIntWithKey(1, KEY_SETTING_SOUND);
            return 1;
        }
        return nSound;
    }

    public void saveAppSoundSetting(int value){
        saveIntWithKey(value, KEY_SETTING_SOUND);
    }

    public void saveStringWithKey(String value, String key){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringWithKey(String key){
        String value = sp.getString(key, "");
        return value;
    }

    public int getIntWithKey(String key){
        int value = sp.getInt(key, -1);
        return value;
    }

    public void saveIntWithKey(int value, String key){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

}


