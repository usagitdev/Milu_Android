package charles.com.milu.HotEvents;

import java.util.ArrayList;

import charles.com.milu.EventDash.EventDashItem;

/**
 * Created by dev on 9/21/17.
 */

public class HotEventItem {

    private int mEventImage;
    private String mEventName;
    private String mEventlikestr;
    private String mEventrankstr;

    private static int lastContactId = 0;


    public HotEventItem(int EventImage,String EventName,String Eventlikestr, String Eventrankstr){

        mEventImage = EventImage;
        mEventName = EventName;
        mEventlikestr = Eventlikestr;
        mEventrankstr = Eventrankstr;

    }

    public String getEventName() {

        return mEventName;
    }

    public int getEventImage() {

        return mEventImage;
    }

    public String getEventlikestr() {

        return mEventlikestr;
    }

    public String getEventrankstr() {

        return mEventrankstr;
    }


    public static ArrayList<HotEventItem> createContactsList(int numContacts) {
        ArrayList<HotEventItem> eventItem = new ArrayList<HotEventItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new HotEventItem(i,"Person","1","test"));
        }

        return eventItem;
    }
}
