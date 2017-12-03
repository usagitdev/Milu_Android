package charles.com.milu.CalendarView;

import java.util.ArrayList;

/**
 * Created by dev on 9/22/17.
 */

public class CalendarCollectionItem {

    private int mEventImage;
    private String mEventName;
    private String mEventLikes;

    private static int lastContactId = 0;


    public CalendarCollectionItem(int EventImage,String EventName, String EventLikes) {

        mEventImage = EventImage;
        mEventName = EventName;
        mEventLikes = EventLikes;

    }

    public String getEventName() {

        return mEventName;
    }

    public int getEventImage() {

        return mEventImage;
    }

    public String getEventLikes() {

        return mEventLikes;
    }

    public static ArrayList<CalendarCollectionItem> createContactsList(int numContacts) {
        ArrayList<CalendarCollectionItem> eventItem = new ArrayList<CalendarCollectionItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new CalendarCollectionItem(1,"1","test"));
        }

        return eventItem;
    }
}
