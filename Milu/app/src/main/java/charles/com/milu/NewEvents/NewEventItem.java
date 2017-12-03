package charles.com.milu.NewEvents;

import java.util.ArrayList;

/**
 * Created by dev on 9/21/17.
 */

public class NewEventItem {

    private int mEventImage;
    private String mEventName;
    private String mEventlikestr;

    private static int lastContactId = 0;


    public NewEventItem(int EventImage,String EventName,String Eventlikestr){

        mEventImage = EventImage;
        mEventName = EventName;
        mEventlikestr = Eventlikestr;

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

    public static ArrayList<NewEventItem> createContactsList(int numContacts) {
        ArrayList<NewEventItem> eventItem = new ArrayList<NewEventItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new NewEventItem(i,"Person","1"));
        }

        return eventItem;
    }

}
