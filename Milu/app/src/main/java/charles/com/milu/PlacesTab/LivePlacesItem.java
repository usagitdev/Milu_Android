package charles.com.milu.PlacesTab;

import java.util.ArrayList;

/**
 * Created by dev on 9/23/17.
 */

public class LivePlacesItem {

    private boolean mType;
    private int mEventImage;
    private String mEventName;
    private String mEventLikes;
    private String mEventNumbers;
    private String mEventRanking;

    public LivePlacesItem(boolean Type, int EventImage, String EventName, String EventLikes, String EventNumbers, String EventRanking){

        mType = Type;
        mEventImage = EventImage;
        mEventName = EventName;
        mEventLikes = EventLikes;
        mEventNumbers = EventNumbers;
        mEventRanking = EventRanking;

    }

    public boolean getEventType() {

        return mType;
    }

    public int getEventImage() {

        return mEventImage;
    }

    public String getEventName() {

        return  mEventName;

    }
    public String getmEventLikes(){

        return mEventLikes;
    }

    public String getmEventNumbers(){

        return mEventNumbers;
    }

    public String getmEventRanking(){

        return mEventRanking;
    }

    public static ArrayList<LivePlacesItem> createContactsList(int numContacts) {
        ArrayList<LivePlacesItem> meetupItem = new ArrayList<LivePlacesItem>();

        for (int i = 1; i <= numContacts; i++) {
            meetupItem.add(new LivePlacesItem(true,1,"test","test","test","test"));
        }

        return meetupItem;
    }
}
