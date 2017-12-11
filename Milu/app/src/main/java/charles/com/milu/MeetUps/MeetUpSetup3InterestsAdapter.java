package charles.com.milu.MeetUps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import charles.com.milu.CustomViews.RegularTitleTextView;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;

/**
 * Created by dev on 9/21/17.
 */

public class MeetUpSetup3InterestsAdapter extends RecyclerView.Adapter<MeetUpSetup3InterestsAdapter.ViewHolder>{

    ArrayList<MeetUpSetup3InterstsItem> selectedItmes = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder{

        public RegularTitleTextView meetUpTitle;
        public TitleTextView meetUpSubTitle;
        public ImageView meetUpCell;
        ImageView selectedItemImage;
        Boolean isselected = false;
        public ViewHolder(View itemView){

            super(itemView);


            meetUpTitle = (RegularTitleTextView) itemView.findViewById(R.id.meetUp_title);
            meetUpSubTitle = (TitleTextView) itemView.findViewById(R.id.meetUp_sub_title);

            selectedItemImage = (ImageView)itemView.findViewById(R.id.meetUp_selectImage);

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
    public void setSelectedItem(int position) {
        selectedItmes.add(mContacts.get(position));
        notifyItemChanged(position);
    }

    public void setunSelectedItem(int position) {
        selectedItmes.remove(mContacts.get(position));
        notifyItemChanged(position);
    }



    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        MeetUpSetup3InterstsItem meetupItem = mContacts.get(position);

        RegularTitleTextView meetUpTitle = viewHolder.meetUpTitle;
        TitleTextView meetUpSubTitle= viewHolder.meetUpSubTitle;
        ImageView meetUpCell = viewHolder.meetUpCell;
        if (isselected(position)){
            viewHolder.selectedItemImage.setImageResource(R.drawable.contacts_gallery_selected);
        }else{
            viewHolder.selectedItemImage.setImageResource(R.drawable.contacts_gallery_unselected2);
        }

        Picasso.with(mContext).load(meetupItem.getmMeetupImage()).into(meetUpCell);
        meetUpTitle.setText(meetupItem.getmMeetupTitle());
        meetUpSubTitle.setText(meetupItem.getmMeetupSubTitle());

    }

    private List<MeetUpSetup3InterstsItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public MeetUpSetup3InterestsAdapter(Context context, List<MeetUpSetup3InterstsItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public MeetUpSetup3InterestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.meetup_interests_cellitem, parent, false);

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
