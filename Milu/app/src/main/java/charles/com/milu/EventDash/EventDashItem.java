package charles.com.milu.EventDash;

import java.util.ArrayList;

import charles.com.milu.GlobalFeed.GlobalFeedItem;

/**
 * Created by dev on 9/20/17.
 */

public class EventDashItem {

    private int mUserImage;
    private String mUserName;
    private String mEventString;
    private String mEventRanking;

    private static int lastContactId = 0;


    public EventDashItem(int UserImage,String UserName,String EventString, String EventRanking){

        mUserImage = UserImage;
        mUserName = UserName;
        mEventString = EventString;
        mEventRanking = EventRanking;

    }


    public String getUserName() {

        return mUserName;
    }

    public int getUserImage() {

        return mUserImage;
    }

    public String getEventString() {

        return mEventString;
    }

    public String getEventRanking() {

        return mEventRanking;
    }


    public static ArrayList<EventDashItem> createContactsList(int numContacts) {
        ArrayList<EventDashItem> eventItem = new ArrayList<EventDashItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new EventDashItem(i,"Person","1","test"));
        }

        return eventItem;
    }
}
