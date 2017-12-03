package charles.com.milu.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import charles.com.milu.Helper.LogHelper;
import charles.com.milu.R;

/**
 * Created by mac on 2017-12-01.
 */

public class PicaImageLoader {

    public static void loadImageWithPicasso(Context context, ImageView iv, String url)
    {
        loadImageWithPicasso(context, iv, url, R.drawable.empty_photo);
    }

    public static void loadImageWithPicasso(Context context, ImageView iv, String url, int placeholder)
    {
        loadImageWithPicasso(context, iv, url, placeholder, placeholder);
    }

    public static void loadImageWithPicasso(Context context, ImageView iv, String url, int placeholder, int error_holder)
    {
        loadImageWithPicasso(context, iv, url, placeholder, error_holder, false, -1, -1);
    }

    public static void loadImageWithPicasso(Context context, ImageView iv, String url, int placeholder, int error, boolean centerCrop, int width, int height)
    {
        try {
            RequestCreator creator = Picasso.with(context).load(url);

            if (placeholder > 0)
                creator.placeholder(placeholder);

            if (error > 0)
                creator.error(error);

            if (centerCrop)
                creator.centerCrop();

            if (width > 0)
                creator.resize(width, height);

//		creator.into(new ImageTarget(iv));
            creator.into(iv);
        }catch (Exception e){
            LogHelper.log("Picasso Loader", "error : " + e.toString());
        }
    }

}
