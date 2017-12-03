package charles.com.milu.FavouriteEvents;

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

public class FavEventAdapter extends RecyclerView.Adapter<FavEventAdapter.ViewHolder> {

    Typeface workSans_Light;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public  TextView mEventName;
        public  TextView mEventLikes;
        public  TextView mEventranking;
        public  ImageView mEventCell;


        public ViewHolder(View itemView){

            super(itemView);

            workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");

            mEventName = (TextView) itemView.findViewById(R.id.favEvent_name);
            mEventLikes = (TextView) itemView.findViewById(R.id.favEvent_likes);
            mEventranking = (TextView) itemView.findViewById(R.id.favEvent_ranking);

            mEventName.setTypeface(workSans_Light);
            mEventLikes.setTypeface(workSans_Light);
            mEventranking.setTypeface(workSans_Light);

            mEventCell = (ImageView) itemView.findViewById(R.id.favEvent_cell);
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

        FavEventItem upComingItem = mContacts.get(position);

        TextView upComingEventName = viewHolder.mEventName;
        ImageView upComingEventCell = viewHolder.mEventCell;
        TextView upComingEventLikes = viewHolder.mEventLikes;
        TextView upComingEventranking = viewHolder.mEventranking;

//        upComingEventCell.setImageResource(upComingItem.getEventImage());
        Picasso.with(mContext).load(upComingItem.getEventImage()).into(upComingEventCell);

        upComingEventName.setText(upComingItem.getEventName());
        upComingEventLikes.setText(upComingItem.getEventlikestr());
        upComingEventranking.setText(upComingItem.getEventrankstr());
    }

    private List<FavEventItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public FavEventAdapter(Context context, List<FavEventItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.faveventcellitem, parent, false);

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
