package charles.com.milu.UpComingEvents;

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

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.ViewHolder> {

    Typeface workSans_Light;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView upComingEventName;
        public  TextView upComingEventLikes;
        public  TextView upComingEventranking;
        public ImageView upComingEventCell;


        public ViewHolder(View itemView){

            super(itemView);

            workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");

            upComingEventName = (TextView) itemView.findViewById(R.id.upComingEvent_name);
            upComingEventLikes = (TextView) itemView.findViewById(R.id.upComingEvent_likes);
            upComingEventranking = (TextView) itemView.findViewById(R.id.upComingEvent_ranking);

            upComingEventName.setTypeface(workSans_Light);
            upComingEventLikes.setTypeface(workSans_Light);
            upComingEventranking.setTypeface(workSans_Light);

            upComingEventCell = (ImageView) itemView.findViewById(R.id.upComingEvent_cell);
            upComingEventCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.upComingEventCell_Clicked(v, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        UpComingItem upComingItem = mContacts.get(position);

        TextView upComingEventName = viewHolder.upComingEventName;
        ImageView upComingEventCell = viewHolder.upComingEventCell;
        TextView upComingEventLikes = viewHolder.upComingEventLikes;
        TextView upComingEventranking = viewHolder.upComingEventranking;

//        upComingEventCell.setImageResource(upComingItem.getEventImage());
        Picasso.with(getContext()).load(upComingItem.getEventImage()).into(upComingEventCell);
        upComingEventName.setText(upComingItem.getEventName());
        upComingEventLikes.setText(upComingItem.getEventlikestr());
        upComingEventranking.setText(upComingItem.getEventrankstr());
    }

    private List<UpComingItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public UpComingAdapter(Context context, List<UpComingItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.upcomingcellitem, parent, false);

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

        void upComingEventCell_Clicked(View v, int adapterPosition);
    }
}
