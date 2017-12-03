package charles.com.milu.HotEvents;

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

import charles.com.milu.EventDash.EventDashAdapter;
import charles.com.milu.EventDash.EventDashItem;
import charles.com.milu.R;

/**
 * Created by dev on 9/21/17.
 */

public class HotEventAdapter extends RecyclerView.Adapter<HotEventAdapter.ViewHolder> {

    Typeface workSans_Light;
    Typeface workSans_Regular;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public  TextView hotEventName;
        public  TextView hotEventLikes;
        public  TextView hotEventranking;
        public  ImageView hotEventCell;


        public ViewHolder(View itemView){

            super(itemView);

            workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");
            workSans_Regular = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Regular.ttf");

            hotEventName = (TextView) itemView.findViewById(R.id.hotEvent_name);
            hotEventLikes = (TextView) itemView.findViewById(R.id.hotEvent_likes);
            hotEventranking = (TextView) itemView.findViewById(R.id.hotEvent_ranking);

            hotEventName.setTypeface(workSans_Regular);
            hotEventLikes.setTypeface(workSans_Light);
            hotEventranking.setTypeface(workSans_Regular);

            hotEventCell = (ImageView) itemView.findViewById(R.id.hotEvent_cell);
            hotEventCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.hotEventCell_Clicked(v, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        HotEventItem eventDashItem = mContacts.get(position);

        TextView hotEventName = viewHolder.hotEventName;
        ImageView hotEventCell = viewHolder.hotEventCell;
        TextView hotEventLikes = viewHolder.hotEventLikes;
        TextView hotEventranking = viewHolder.hotEventranking;

//        hotEventCell.setImageResource(eventDashItem.getEventImage());
        Picasso.with(mContext).load(eventDashItem.getEventImage()).into(hotEventCell);
        hotEventName.setText(eventDashItem.getEventName());
        hotEventLikes.setText(eventDashItem.getEventlikestr());
        hotEventranking.setText(eventDashItem.getEventrankstr());
    }

    private List<HotEventItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public HotEventAdapter(Context context, List<HotEventItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public HotEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.hoteventcellitem, parent, false);

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

        void hotEventCell_Clicked(View v, int adapterPosition);
    }
}
