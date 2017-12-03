package charles.com.milu.MeetUps;

import java.util.ArrayList;


/**
 * Created by dev on 9/21/17.
 */

public class MeetUpItem {

    private int mMeetupImage;
    private String mMeetupName;
    private static int lastContactId = 0;

    public MeetUpItem(int MeetupImage,String MeetupName){

        mMeetupImage = MeetupImage;
        mMeetupName = MeetupName;

    }

    public String getMeetupName() {

        return mMeetupName;
    }

    public int getMeetupImage() {

        return mMeetupImage;
    }

    public static ArrayList<MeetUpItem> createContactsList(int numContacts) {
        ArrayList<MeetUpItem> meetupItem = new ArrayList<MeetUpItem>();

//        for (int i = 1; i <= numContacts; i++) {
//            meetupItem.add(new MeetUpItem(i,"Person"));
//        }

        return meetupItem;
    }
}
