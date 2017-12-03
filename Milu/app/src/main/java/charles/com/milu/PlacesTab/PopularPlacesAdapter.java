package charles.com.milu.PlacesTab;

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

import charles.com.milu.MyEvents.MyEventItem;
import charles.com.milu.R;

/**
 * Created by dev on 9/21/17.
 */

public class PopularPlacesAdapter extends RecyclerView.Adapter<PopularPlacesAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder{

        public  TextView mEventName;
        public  TextView mEventLikes;
        public  TextView mEventranking;
        public  TextView mFlag;
        public  ImageView mEventCell;
        public  ImageView mRankingImg;


        public ViewHolder(View itemView){

            super(itemView);


            mEventName = (TextView) itemView.findViewById(R.id.event_name);
            mEventLikes = (TextView) itemView.findViewById(R.id.event_likes);
            mEventranking = (TextView) itemView.findViewById(R.id.populars);
            mRankingImg = (ImageView) itemView.findViewById(R.id.imv_popular);

            mEventCell = (ImageView) itemView.findViewById(R.id.back_image);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        PopularPlacesItem eventItem = mContacts.get(position);

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

    }

    private List<PopularPlacesItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public PopularPlacesAdapter(Context context, List<PopularPlacesItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.popular_places_cell, parent, false);

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