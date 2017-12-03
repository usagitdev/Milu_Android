package charles.com.milu.CalendarView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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

public class CalendarEventAdapter extends RecyclerView.Adapter<CalendarEventAdapter.ViewHolder>{


    Typeface workSans_Light;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public boolean mFlag;
        public  TextView mEventName;
        public ImageView mEventCell;
        public ImageView mOnline;
        public ImageView calListCell;

        public ViewHolder(View itemView){

            super(itemView);

            workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");

            mEventName = (TextView) itemView.findViewById(R.id.callistcell_eventName);
            mOnline = (ImageView) itemView.findViewById(R.id.callistcell_onlineImage);
            mEventCell = (ImageView) itemView.findViewById(R.id.callistcell_eventImage);


            mEventName.setTypeface(workSans_Light);

            calListCell = (ImageView) itemView.findViewById(R.id.calEvent_cell);
            calListCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.mEventCell_Clicked(v, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        CalendarEventItem eventItem = mContacts.get(position);

        TextView mEventName = viewHolder.mEventName;
        ImageView mEventCell = viewHolder.mEventCell;
        ImageView mOnline = viewHolder.mOnline;
        boolean mFlag = viewHolder.mFlag;

        Picasso.with(mContext).load(eventItem.getEventImage()).into(mEventCell);

        mEventName.setText(eventItem.getEventName());
        mFlag = eventItem.getEventOnline();

        if (mFlag){

            Picasso.with(mContext).load(R.drawable.registration_background).into(mOnline);

        }else {

        }


    }

    private List<CalendarEventItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public CalendarEventAdapter(Context context, List<CalendarEventItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.calendarlistitem_layout, parent, false);

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

        void mEventCell_Clicked(View v, int adapterPosition);
    }

}
