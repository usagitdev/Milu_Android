package charles.com.milu.PlacesTab;

import java.util.ArrayList;

/**
 * Created by dev on 9/21/17.
 */

public class NearbyPlacesItem {

    private int mEventImage;
    private String mEventName;
    private String mEventlikestr;
    private String mEventrankstr;
    private boolean mFlag;

    private static int lastContactId = 0;


    public NearbyPlacesItem(int EventImage, String EventName, String Eventlikestr, String Eventrankstr, boolean Flag){

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

    public static ArrayList<NearbyPlacesItem> createContactsList(int numContacts) {
        ArrayList<NearbyPlacesItem> eventItem = new ArrayList<NearbyPlacesItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new NearbyPlacesItem(i,"Person","1","test",true));
        }

        return eventItem;
    }

}
