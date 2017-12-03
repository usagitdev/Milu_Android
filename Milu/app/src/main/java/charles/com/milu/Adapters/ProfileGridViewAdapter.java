package charles.com.milu.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import charles.com.milu.Items.ProfileGridItem;
import charles.com.milu.R;

/**
 * Created by mac_dev on 10/19/17.
 */

public class ProfileGridViewAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();


    public ProfileGridViewAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public ProfileGridViewAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ProfileGridViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public ProfileGridViewAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ProfileGridViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }

    public ProfileGridViewAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.image);
            int displaywidth = context.getResources().getDisplayMetrics().widthPixels;
            int imageWidth = (displaywidth / 6) - 1;
            GridView.LayoutParams glp = (GridView.LayoutParams) row.getLayoutParams();
            glp.width = imageWidth;
            glp.height = imageWidth;
            row.setLayoutParams(glp);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ProfileGridItem item = (ProfileGridItem) data.get(position);
        Picasso.with(context).load(item.getImageId()).into(holder.image);
        return row;
    }

    static class ViewHolder {
        ImageView image;

    }
}
