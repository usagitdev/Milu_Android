package charles.com.milu.LiveEvents;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.lucode.hackware.selfdependlayout.SelfDependLayout;

import java.util.List;

import charles.com.milu.R;

/**
 * Created by dev on 9/23/17.
 */

public class LiveEventAdapter extends RecyclerView.Adapter<LiveEventAdapter.ViewHolder>{

    Typeface workSans_Light;
    boolean eventType;


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView eventImage;
        RelativeLayout eventCell;
        TextView eventName;
        TextView eventNumbers;
        TextView eventLikes;
        TextView eventRanking;
        TextView eventNumStr;
        ViewAnimator animator;

        public SelfDependLayout  eventString_Layout;
        public RelativeLayout liveEventCell_Layout;
        public RelativeLayout liveEventCell_imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            liveEventCell_Layout = (RelativeLayout) itemView.findViewById(R.id.liveEvent_Celllayout);
            eventString_Layout = (SelfDependLayout) itemView.findViewById(R.id.liveEvent_Eventcount);
            liveEventCell_imageView = (RelativeLayout) itemView.findViewById(R.id.liveEvent_ImageView);

            eventImage = (ImageView) itemView.findViewById(R.id.liveEvent_Image);
            eventName = (TextView) itemView.findViewById(R.id.liveEvent_Eventname);
            eventLikes = (TextView) itemView.findViewById(R.id.liveEvent_Eventlikes);
            eventNumbers = (TextView) itemView.findViewById(R.id.liveEvent_Count);
            eventRanking = (TextView) itemView.findViewById(R.id.liveEvent_ranking);
            eventNumStr = (TextView) itemView.findViewById(R.id.liveEvent_NumStr);
            animator = (ViewAnimator) itemView.findViewById(R.id.animator);


            eventCell = (RelativeLayout) itemView.findViewById(R.id.card_view);
            eventCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.eventCell_Clicked(v, getAdapterPosition());

                }
            });
        }
    }

    @Override
    public LiveEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.liveplace_large_cell, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


        LiveEventItem eventItem = mContacts.get(position);

        eventType = eventItem.getEventType();

        TextView mEventName = viewHolder.eventName;
        ImageView mEventImage = viewHolder.eventImage;
        TextView mEventLikes = viewHolder.eventLikes;
        TextView mEvnetNumbers = viewHolder.eventNumbers;
        TextView mEventRanking = viewHolder.eventRanking;
        final ViewAnimator animator = viewHolder.animator;

        mEventName.setText(eventItem.getEventName());
        mEventLikes.setText(eventItem.getmEventLikes());
        mEvnetNumbers.setText(eventItem.getmEventNumbers());
        mEventRanking.setText(eventItem.getmEventRanking());

        mEventImage.setImageResource(eventItem.getEventImage());
        animator.setDisplayedChild(1);
        Picasso.with(mContext).load(eventItem.getEventImage()).into(mEventImage, new Callback() {
            @Override
            public void onSuccess() {
                animator.setDisplayedChild(0);
            }

            @Override
            public void onError() {

            }
        });
        int screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = mContext.getResources().getDisplayMetrics().heightPixels;


        int height = screenHeight / 4;
        viewHolder.liveEventCell_Layout.getLayoutParams().height = height;
        if (!eventType){

            viewHolder.eventString_Layout.setVisibility(View.GONE);
            int width = screenWidth;
            viewHolder.liveEventCell_imageView.getLayoutParams().width = (int) (width * 0.8 );

        }else {
            viewHolder.eventString_Layout.setVisibility(View.VISIBLE);
            int width = screenWidth;
            viewHolder.liveEventCell_imageView.getLayoutParams().width = (int) (width * 0.8 );

        }

    }

    private List<LiveEventItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public LiveEventAdapter(Context context, List<LiveEventItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
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


    void eventCell_Clicked(View v, int adapterPosition);
}
}
