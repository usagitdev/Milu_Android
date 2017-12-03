package charles.com.milu.CalendarView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import charles.com.milu.R;

/**
 * Created by dev on 9/22/17.
 */

public class CalendarCollectionAdapter extends RecyclerView.Adapter<CalendarCollectionAdapter.ViewHolder>{

    Typeface workSans_Light;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mEventName;
        public TextView mEventLikes;
        public ImageView mEventCell;

        public ImageView mEventImage;


        public ViewHolder(View itemView){

            super(itemView);

            workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");

            mEventName = (TextView) itemView.findViewById(R.id.calCollectionEvent_name);
            mEventImage = (ImageView) itemView.findViewById(R.id.calCollectionEvent_Image);
            mEventLikes = (TextView) itemView.findViewById(R.id.calCollectionEvent_likes);

            ImageView blurView = (ImageView) itemView.findViewById(R.id.calCollection_BlurView);


            mEventName.setTypeface(workSans_Light);
            mEventLikes.setTypeface(workSans_Light);

            mEventCell = (ImageView) itemView.findViewById(R.id.calCollectionEvent_cell);
            mEventCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.mCalendarCollectionEventCell_Clicked(v, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        CalendarCollectionItem eventItem = mContacts.get(position);

        TextView mEventName = viewHolder.mEventName;
        ImageView mEventImage = viewHolder.mEventImage;
        TextView mEventLikes = viewHolder.mEventLikes;


        Picasso.with(mContext).load(eventItem.getEventImage()).into(mEventImage);
        mEventName.setText(eventItem.getEventName());
        mEventLikes.setText(eventItem.getEventLikes());

    }

    private List<CalendarCollectionItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public CalendarCollectionAdapter(Context context, List<CalendarCollectionItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.calendarcollectionitem_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public int getItemCount() {

        return mContacts.size();
    }

    public ItemClickListener listener;

    public void setListener(ItemClickListener listener){

        this.listener = listener;

    }

    public interface ItemClickListener {

        void mCalendarCollectionEventCell_Clicked(View v, int adapterPosition);
    }
}
