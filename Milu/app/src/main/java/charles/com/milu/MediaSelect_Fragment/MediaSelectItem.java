package charles.com.milu.MediaSelect_Fragment;

import android.graphics.Bitmap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import charles.com.milu.MeetUps.MeetUpItem;

/**
 * Created by dev on 9/28/17.
 */

public class MediaSelectItem {

    private String mImage;
    private boolean selected;

    public MediaSelectItem(String Image){

        mImage = Image;
        selected = false;

    }

    public String getImage() {

        return mImage;
    }

    public static ArrayList<MediaSelectItem> createContactsList(int numContacts) {
        ArrayList<MediaSelectItem> mItem = new ArrayList<MediaSelectItem>();
        String urlString = "https://drive.google.com/open?id=0B0ALcwcUwZaFcllWWnY1cjVlYzA";

            for (int i = 1; i <= numContacts; i++) {
                mItem.add(new MediaSelectItem("test"));
            }

        return mItem;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

}
