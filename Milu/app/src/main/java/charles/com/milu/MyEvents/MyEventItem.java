package charles.com.milu.MyEvents;

import java.util.ArrayList;

/**
 * Created by dev on 9/21/17.
 */

public class MyEventItem {

    private int mEventImage;
    private String mEventName;
    private String mEventlikestr;
    private String mEventrankstr;
    private boolean mFlag;

    private static int lastContactId = 0;


    public MyEventItem(int EventImage,String EventName,String Eventlikestr, String Eventrankstr, boolean Flag){

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

    public static ArrayList<MyEventItem> createContactsList(int numContacts) {
        ArrayList<MyEventItem> eventItem = new ArrayList<MyEventItem>();

        for (int i = 1; i <= numContacts; i++) {
            eventItem.add(new MyEventItem(i,"Person","1","test",true));
        }

        return eventItem;
    }

}
