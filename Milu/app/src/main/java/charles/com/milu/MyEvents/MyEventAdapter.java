package charles.com.milu.MyEvents;

import android.content.Context;
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
 * Created by dev on 9/21/17.
 */

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.ViewHolder> {

    Typeface workSans_Light;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public  TextView mEventName;
        public  TextView mEventLikes;
        public  TextView mEventranking;
        public  TextView mFlag;
        public  ImageView mEventCell;
        public  ImageView mRankingImg;


        public ViewHolder(View itemView){

            super(itemView);

            workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");

            mEventName = (TextView) itemView.findViewById(R.id.myEvent_name);
            mEventLikes = (TextView) itemView.findViewById(R.id.myEvent_likes);
            mEventranking = (TextView) itemView.findViewById(R.id.myEvent_ranking);
            mRankingImg = (ImageView) itemView.findViewById(R.id.myEvent_rankingimage);


            mEventName.setTypeface(workSans_Light);
            mEventLikes.setTypeface(workSans_Light);
            mEventranking.setTypeface(workSans_Light);

            mEventCell = (ImageView) itemView.findViewById(R.id.myEvent_cell);
            mEventCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.mEventCell_Clicked(v, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        MyEventItem eventItem = mContacts.get(position);

        TextView mEventName = viewHolder.mEventName;
        ImageView mEventCell = viewHolder.mEventCell;
        TextView mEventLikes = viewHolder.mEventLikes;
        TextView mEventranking = viewHolder.mEventranking;
        TextView mFlag = viewHolder.mFlag;

        ImageView mRankingImage = viewHolder.mRankingImg;

//        mEventCell.setImageResource(eventItem.getEventImage());
        Picasso.with(mContext).load(eventItem.getEventImage()).into(mEventCell);
        mEventName.setText(eventItem.getEventName());
        mEventLikes.setText(eventItem.getEventlikestr());
        mEventranking.setText(eventItem.getEventrankstr());


        boolean rankingFlag = eventItem.getFlag();
        if (rankingFlag){

            mRankingImage.setImageResource(R.drawable.event_list_upcoming_bell_icon);

        }else {

            mRankingImage.setImageResource(R.drawable.events_rating_star);

        }
    }

    private List<MyEventItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public MyEventAdapter(Context context, List<MyEventItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.myevents_cellitem, parent, false);

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