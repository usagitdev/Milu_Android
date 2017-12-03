package charles.com.milu.UpComingEvents;

import java.util.ArrayList;



/**
 * Created by dev on 9/21/17.
 */

public class UpComingItem  {

    private int mEventImage;
    private String mEventName;
    private String mEventlikestr;
    private String mEventrankstr;

    private static int lastContactId = 0;


    public UpComingItem(int EventImage,String EventName,String Eventlikestr, String Eventrankstr){

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


    public static ArrayList<UpComingItem> createContactsList(int numContacts) {
        ArrayList<UpComingItem> eventItem = new ArrayList<UpComingItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new UpComingItem(i,"Person","1","test"));
        }

        return eventItem;
    }
}
