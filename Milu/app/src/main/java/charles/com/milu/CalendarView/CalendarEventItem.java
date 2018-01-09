package charles.com.milu.CalendarView;

import java.util.ArrayList;

/**
 * Created by dev on 9/22/17.
 */

public class CalendarEventItem {

    private boolean mFlage;
    private int mEventImage;
    private String mEventName;
    private String mEventTime;

//    private static int lastContactId = 0;


    public CalendarEventItem(boolean Flag, int EventImage,String EventName, String eventTime){

        mFlage = Flag;
        mEventImage = EventImage;
        mEventName = EventName;
        mEventTime = eventTime;

    }

    public String getEventName() {

        return mEventName;
    }

    public int getEventImage() {

        return mEventImage;
    }

    public boolean getEventOnline() {

        return mFlage;
    }

    public static ArrayList<CalendarEventItem> createContactsList(int numContacts) {
        ArrayList<CalendarEventItem> eventItem = new ArrayList<CalendarEventItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new CalendarEventItem(true,1,"1", "5:25PM"));
        }

        return eventItem;
    }

    public String getmEventTime() {
        return mEventTime;
    }

    public void setmEventTime(String mEventTime) {
        this.mEventTime = mEventTime;
    }
}
