package charles.com.milu.LiveEvents.LiveFeedPostFragment;

import java.util.ArrayList;

/**
 * Created by dev on 9/25/17.
 */

public class LiveMediaPostItem {

    private int mfeedType;
    private String muserName;
    private int muserImage;
    private String mupvoteCount;
    private int mfeedImage;
    private int mfeedDescription;
    private int mfeedProperty;

    public LiveMediaPostItem(int feedType,String name,int userImage, String upvoteCount, int feedIamge, int feedDescription, int feedProperty){

        mfeedType = feedType;
        muserName = name;
        muserImage = userImage;
        mupvoteCount = upvoteCount;
        mfeedImage = feedIamge;
        mfeedDescription = feedDescription;
        mfeedProperty = feedProperty;
    }

    public int getFeedType() {

        return mfeedType;
    }

    public String getName() {

        return muserName;
    }

    public int getuserImage() {

        return muserImage;
    }

    public String getupVote() {

        return mupvoteCount;
    }

    public int getfeedImage() {

        return mfeedImage;
    }

    public int getDescription() {

        return mfeedDescription;
    }

    public int getProperty() {

        return mfeedProperty;
    }

    private static int lastContactId = 0;

    public static ArrayList<LiveMediaPostItem> createContactsList(int numContacts) {
        ArrayList<LiveMediaPostItem> contacts = new ArrayList<LiveMediaPostItem>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new LiveMediaPostItem(i,"Person",1,"test",1,1,1));
        }

        return contacts;
    }
}
