package charles.com.milu.Models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mac_dev on 12/7/17.
 */

public class MediaObject implements Parcelable {
    public long id;
    public String type;
    public String path;
    public Uri uripath;
    public boolean isSelected;

    public MediaObject(long id, String type, String path, boolean isSelected) {
        this.id = id;
        this.type = type;
        this.path = path;
        this.isSelected = isSelected;
    }

    public void setUripath(Uri uri) {
        this.uripath = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(type);
        dest.writeString(path);
    }

    public static final Creator<MediaObject> CREATOR = new Creator<MediaObject>() {
        @Override
        public MediaObject createFromParcel(Parcel source) {
            return new MediaObject(source);
        }

        @Override
        public MediaObject[] newArray(int size) {
            return new MediaObject[size];
        }
    };

    private MediaObject(Parcel in) {
        id = in.readLong();
        type = in.readString();
        path = in.readString();
    }
}
