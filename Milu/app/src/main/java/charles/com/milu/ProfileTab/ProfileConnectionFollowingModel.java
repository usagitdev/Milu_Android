package charles.com.milu.ProfileTab;


/**
 * Created by mac_dev on 10/25/17.
 */

public class ProfileConnectionFollowingModel {
    private int userAvatar;
    private String userFullName;
    private String userName;
    private boolean isFollowing;

    private int type;
    private String letter;


    public ProfileConnectionFollowingModel(String userFullName, String userName, int userAvatar, int type, String letter) {
        this.userAvatar = userAvatar;
        this.userFullName = userFullName;
        this.userName = userName;
        this.isFollowing = true;
        this.type = type;
        this.letter = letter;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public void setUserAvatar(int userAvatar) {
        this.userAvatar = userAvatar;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserAvatar() {
        return userAvatar;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public int getType() {
        return type;
    }

    public String getLetter() {
        return letter;
    }
}

