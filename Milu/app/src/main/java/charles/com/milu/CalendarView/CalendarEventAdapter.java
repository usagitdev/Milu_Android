package charles.com.milu.CalendarView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import charles.com.milu.CustomViews.RegularTitleTextView;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;

/**
 * Created by dev on 9/22/17.
 */

public class CalendarEventAdapter extends RecyclerView.Adapter<CalendarEventAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{

        public boolean mFlag;
        public TitleTextView mEventName;
        public ImageView mEventCell;
        public ImageView mOnline;
        public RelativeLayout calListCell;
        public RegularTitleTextView calTime;

        public ViewHolder(View itemView){

            super(itemView);


            mEventName = (TitleTextView) itemView.findViewById(R.id.callistcell_eventName);
            mOnline = (ImageView) itemView.findViewById(R.id.callistcell_onlineImage);
            mEventCell = (ImageView) itemView.findViewById(R.id.callistcell_eventImage);
            calTime = (RegularTitleTextView) itemView.findViewById(R.id.cal_time);

            calListCell = (RelativeLayout) itemView.findViewById(R.id.calEvent_cell);
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
        RegularTitleTextView caltime = viewHolder.calTime;
        boolean mFlag = viewHolder.mFlag;

        Picasso.with(mContext).load(eventItem.getEventImage()).into(mEventCell);

        mEventName.setText(eventItem.getEventName());
        mFlag = eventItem.getEventOnline();

        caltime.setText(eventItem.getmEventTime());
        if (eventItem.getmEventTime().equals("")) {
            caltime.setVisibility(View.GONE);
        }else{
            caltime.setVisibility(View.VISIBLE);
        }
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
