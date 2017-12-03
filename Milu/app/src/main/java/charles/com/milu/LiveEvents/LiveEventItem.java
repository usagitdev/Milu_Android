package charles.com.milu.LiveEvents;

import java.util.ArrayList;

/**
 * Created by dev on 9/23/17.
 */

public class LiveEventItem {

    private boolean mType;
    private int mEventImage;
    private String mEventName;
    private String mEventLikes;
    private String mEventNumbers;
    private String mEventRanking;

    public LiveEventItem(boolean Type, int EventImage, String EventName,String EventLikes, String EventNumbers, String EventRanking){

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

    public static ArrayList<LiveEventItem> createContactsList(int numContacts) {
        ArrayList<LiveEventItem> meetupItem = new ArrayList<LiveEventItem>();

        for (int i = 1; i <= numContacts; i++) {
            meetupItem.add(new LiveEventItem(true,1,"test","test","test","test"));
        }

        return meetupItem;
    }
}
