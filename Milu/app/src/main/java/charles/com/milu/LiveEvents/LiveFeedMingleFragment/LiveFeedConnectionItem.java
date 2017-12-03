package charles.com.milu.LiveEvents.LiveFeedMingleFragment;

import java.util.ArrayList;

/**
 * Created by dev on 9/25/17.
 */

public class LiveFeedConnectionItem {

    private int mConnectionImage;
    private String mConnectionName;
    private String mConnectionLevel;


    public LiveFeedConnectionItem(int ConnectionImage,String ConnectionName, String ConnectionLevel){

        mConnectionImage = ConnectionImage;
        mConnectionName = ConnectionName;
        mConnectionLevel = ConnectionLevel;

    }

    public String getConnectionName() {

        return mConnectionName;
    }

    public int getConnectionImage() {

        return mConnectionImage;
    }

    public  String getConnectionLevel(){

        return mConnectionLevel;
    }

    public static ArrayList<LiveFeedConnectionItem> createContactsList(int numContacts) {
        ArrayList<LiveFeedConnectionItem> connectionItem = new ArrayList<LiveFeedConnectionItem>();

        for (int i = 1; i <= numContacts; i++) {
            connectionItem.add(new LiveFeedConnectionItem(i,"Person","test"));
        }

        return connectionItem;
    }
}
