package charles.com.milu.PlacesTab;

import java.util.ArrayList;

/**
 * Created by dev on 9/21/17.
 */

public class PopularPlacesItem {

    private int mEventImage;
    private String mEventName;
    private String mEventlikestr;
    private String mEventrankstr;
    private boolean mFlag;

    private static int lastContactId = 0;


    public PopularPlacesItem(int EventImage, String EventName, String Eventlikestr, String Eventrankstr, boolean Flag){

        mEventImage = EventImage;
        mEventName = EventName;
        mEventlikestr = Eventlikestr;
        mEventrankstr = Eventrankstr;
        mFlag = Flag;

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

    public boolean getFlag(){

        return mFlag;
    }

    public static ArrayList<PopularPlacesItem> createContactsList(int numContacts) {
        ArrayList<PopularPlacesItem> eventItem = new ArrayList<PopularPlacesItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new PopularPlacesItem(i,"Person","1","test",true));
        }

        return eventItem;
    }

}
