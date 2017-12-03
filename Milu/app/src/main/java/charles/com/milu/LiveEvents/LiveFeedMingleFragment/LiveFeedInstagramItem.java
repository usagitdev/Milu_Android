package charles.com.milu.LiveEvents.LiveFeedMingleFragment;

import java.util.ArrayList;

/**
 * Created by dev on 9/25/17.
 */

public class LiveFeedInstagramItem {

    private int mInsImage;


    public LiveFeedInstagramItem(int InsImage){

        mInsImage = InsImage;

    }


    public int getInsImage() {

        return mInsImage;
    }

    public static ArrayList<LiveFeedInstagramItem> createContactsList(int numContacts) {
        ArrayList<LiveFeedInstagramItem> insItem = new ArrayList<LiveFeedInstagramItem>();

        for (int i = 1; i <= numContacts; i++) {
            insItem.add(new LiveFeedInstagramItem(i));
        }

        return insItem;
    }
}
