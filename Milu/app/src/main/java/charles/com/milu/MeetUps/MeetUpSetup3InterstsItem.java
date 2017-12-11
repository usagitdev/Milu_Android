package charles.com.milu.MeetUps;

import java.util.ArrayList;


/**
 * Created by dev on 9/21/17.
 */

public class MeetUpSetup3InterstsItem {

    private int mMeetupImage;
    private String mMeetupTitle;
    private String mMeetupSubTitle;
    private static int lastContactId = 0;

    public MeetUpSetup3InterstsItem(int MeetupImage, String MeetupTitle, String MeetupSubTitle){

        mMeetupImage = MeetupImage;
        mMeetupTitle = MeetupTitle;
        mMeetupSubTitle = MeetupSubTitle;

    }

    public static ArrayList<MeetUpSetup3InterstsItem> createContactsList(int numContacts) {
        ArrayList<MeetUpSetup3InterstsItem> meetupItem = new ArrayList<MeetUpSetup3InterstsItem>();

//        for (int i = 1; i <= numContacts; i++) {
//            meetupItem.add(new MeetUpItem(i,"Person"));
//        }

        return meetupItem;
    }

    public int getmMeetupImage() {
        return mMeetupImage;
    }

    public void setmMeetupImage(int mMeetupImage) {
        this.mMeetupImage = mMeetupImage;
    }

    public String getmMeetupTitle() {
        return mMeetupTitle;
    }

    public void setmMeetupTitle(String mMeetupTitle) {
        this.mMeetupTitle = mMeetupTitle;
    }

    public String getmMeetupSubTitle() {
        return mMeetupSubTitle;
    }

    public void setmMeetupSubTitle(String mMeetupSubTitle) {
        this.mMeetupSubTitle = mMeetupSubTitle;
    }
}
