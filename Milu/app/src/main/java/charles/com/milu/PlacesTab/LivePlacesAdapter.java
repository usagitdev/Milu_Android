package charles.com.milu.PlacesTab;

import android.content.Context;
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

import java.util.List;

import charles.com.milu.R;
import charles.com.milu.Utility.SquareAvatarImageView;
import charles.com.milu.utils.CommonUtils;

/**
 * Created by dev on 9/23/17.
 */

public class LivePlacesAdapter extends RecyclerView.Adapter<LivePlacesAdapter.ViewHolder>{

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

        SquareAvatarImageView squareAvatarImageView;

        public RelativeLayout liveEventCell_Layout, eventString_Layout;

        public ViewHolder(View itemView) {
            super(itemView);

            liveEventCell_Layout = (RelativeLayout) itemView.findViewById(R.id.liveEvent_Celllayout);
            eventString_Layout = (RelativeLayout) itemView.findViewById(R.id.liveEvent_Eventcount);

            eventImage = (ImageView) itemView.findViewById(R.id.liveEvent_Image);
            eventName = (TextView) itemView.findViewById(R.id.liveEvent_Eventname);
            eventLikes = (TextView) itemView.findViewById(R.id.liveEvent_Eventlikes);
            eventNumbers = (TextView) itemView.findViewById(R.id.liveEvent_Count);
            eventRanking = (TextView) itemView.findViewById(R.id.liveEvent_ranking);
            eventNumStr = (TextView) itemView.findViewById(R.id.liveEvent_NumStr);
            animator = (ViewAnimator) itemView.findViewById(R.id.animator);

            squareAvatarImageView = (SquareAvatarImageView) itemView.findViewById(R.id.squre_image);
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
    public LivePlacesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.liveplace_large_cell, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


        LivePlacesItem eventItem = mContacts.get(position);

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

//        mEventImage.setImageResource(eventItem.getEventImage());
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
        int screenWidth = mContext.getResources().getDisplayMetrics().heightPixels;


        int height = screenWidth / 4;
        viewHolder.liveEventCell_Layout.getLayoutParams().height = height;
        if (eventType){

            viewHolder.eventString_Layout.setVisibility(View.GONE);
            int width = (int) CommonUtils.convertDpToPixel(320, mContext);
            viewHolder.liveEventCell_Layout.getLayoutParams().width = width;

        }else {
            viewHolder.eventString_Layout.setVisibility(View.VISIBLE);
            viewHolder.squareAvatarImageView.getLayoutParams().height = height;
            int width = (int) CommonUtils.convertDpToPixel(430, mContext);
            viewHolder.liveEventCell_Layout.getLayoutParams().width = width;

        }

    }

    private List<LivePlacesItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public LivePlacesAdapter(Context context, List<LivePlacesItem> contacts) {

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
