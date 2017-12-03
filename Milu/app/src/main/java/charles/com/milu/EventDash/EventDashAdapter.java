package charles.com.milu.EventDash;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
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
 * Created by dev on 9/20/17.
 */

public class EventDashAdapter extends RecyclerView.Adapter<EventDashAdapter.ViewHolder> {

    Typeface workSans_Light;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public  ImageView userImage;
        public  TextView userName;
        public  TextView eventString;
        public  TextView eventRanking;

        public ImageView eventCell;


        public ViewHolder(View itemView){

            super(itemView);

            workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");
            userName = (TextView) itemView.findViewById(R.id.eventDash_username);
            userImage = (ImageView) itemView.findViewById(R.id.eventDash_userImage);
            eventString = (TextView) itemView.findViewById(R.id.eventDash_String);
            eventRanking = (TextView) itemView.findViewById(R.id.eventDash_ranking);

            setFont(itemView);

            userName.setTypeface(workSans_Light);
            eventString.setTypeface(workSans_Light);
            eventRanking.setTypeface(workSans_Light);

            eventCell = (ImageView) itemView.findViewById(R.id.eventDash_cell);
            eventCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.eventDashCell_Clicked(v, getAdapterPosition());
                }
            });
        }
    }

    public void setFont(View view){

        TextView userName = (TextView) view.findViewById(R.id.eventDash_username);
            userName.setTypeface(workSans_Light);

        TextView eventString = (TextView) view.findViewById(R.id.eventDash_String);
            eventString.setTypeface(workSans_Light);

        TextView eventRanking = (TextView) view.findViewById(R.id.eventDash_ranking);
            eventRanking.setTypeface(workSans_Light);

    }

    private List<EventDashItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public EventDashAdapter(Context context, List<EventDashItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }


    private Context getContext() {

        return mContext;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.eventdashitem_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        EventDashItem eventDashItem = mContacts.get(position);

        TextView userName = viewHolder.userName;
        ImageView userImage = viewHolder.userImage;
        TextView  eventString = viewHolder.eventString;
        TextView eventRanking = viewHolder.eventRanking;

//        userImage.setImageResource(eventDashItem.getUserImage());
        Picasso.with(mContext).load(eventDashItem.getUserImage()).into(userImage);

        userName.setText(eventDashItem.getUserName());
        eventString.setText(eventDashItem.getEventString());
        eventRanking.setText(eventDashItem.getEventRanking());
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

        void eventDashCell_Clicked(View v, int adapterPosition);

//        void likeButtonClicked(View view, int position);

    }

}
