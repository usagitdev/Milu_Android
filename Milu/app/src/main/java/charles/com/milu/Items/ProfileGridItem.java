package charles.com.milu.Items;

import android.graphics.Bitmap;

/**
 * Created by mac_dev on 10/19/17.
 */

public class ProfileGridItem {
    private Bitmap image;
    private String imageId;

    public ProfileGridItem(Bitmap image) {
        super();
        this.image = image;
    }

    public ProfileGridItem(String imageId) {
        super();
        this.imageId = imageId;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }


}
