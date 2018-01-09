package charles.com.milu.MeetUps;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.BoolRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import charles.com.milu.HotEvents.HotEventAdapter;
import charles.com.milu.HotEvents.HotEventItem;
import charles.com.milu.R;

/**
 * Created by dev on 9/21/17.
 */

public class MeetUpAdapter extends RecyclerView.Adapter<MeetUpAdapter.ViewHolder>{


    ArrayList<MeetUpItem> selectedItmes = new ArrayList<>();
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView meetUpName;
        public ImageView meetUpCell;
        ImageView selectedItemImage;
        Boolean isselected = false;
        public ViewHolder(View itemView){

            super(itemView);


            meetUpName = (TextView) itemView.findViewById(R.id.meetUp_title);

            selectedItemImage = (ImageView)itemView.findViewById(R.id.meetUp_list_selector);

            meetUpCell = (ImageView) itemView.findViewById(R.id.meetUp_cell);
            meetUpCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.meetUpCell_Clicked(v, getAdapterPosition());

                }
            });
        }
    }

    public boolean isselected(int position){
        return selectedItmes.contains(mContacts.get(position));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        MeetUpItem meetupItem = mContacts.get(position);
        if (isselected(position)){
            viewHolder.selectedItemImage.setVisibility(View.VISIBLE);
        }else{
            viewHolder.selectedItemImage.setVisibility(View.GONE);
        }

        TextView meetUpName = viewHolder.meetUpName;
        ImageView meetUpCell = viewHolder.meetUpCell;

        Picasso.with(mContext).load(meetupItem.getMeetupImage()).into(meetUpCell);
        meetUpName.setText(meetupItem.getMeetupName());

    }

    public void setSelectedItem(int position) {
        selectedItmes.add(mContacts.get(position));
        notifyItemChanged(position);
    }

    public void setunSelectedItem(int position) {
        selectedItmes.remove(mContacts.get(position));
        notifyItemChanged(position);
    }

    private List<MeetUpItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public MeetUpAdapter(Context context, List<MeetUpItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public MeetUpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.meetup_setup1_cell, parent, false);

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

        void meetUpCell_Clicked(View v, int adapterPosition);

    }
}
