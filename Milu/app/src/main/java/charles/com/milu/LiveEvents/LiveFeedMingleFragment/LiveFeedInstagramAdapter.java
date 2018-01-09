package charles.com.milu.LiveEvents.LiveFeedMingleFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

import charles.com.milu.R;

/**
 * Created by dev on 9/25/17.
 */

public class LiveFeedInstagramAdapter extends RecyclerView.Adapter<LiveFeedInstagramAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{


        public ImageView insImage;
        public LinearLayout instamoreView;

        public ViewHolder(View itemView){

            super(itemView);

            insImage = (ImageView) itemView.findViewById(R.id.liveMingle_InstagramCell);
            instamoreView = (LinearLayout) itemView.findViewById(R.id.instagram_more_view);
            instamoreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.instagframItemClicked(v, getAdapterPosition());

                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        LiveFeedInstagramItem insItem = mContacts.get(position);

        ImageView insImage = viewHolder.insImage;
        if (insItem.getInsImage() == 0) {
            viewHolder.insImage.setVisibility(View.INVISIBLE);
            viewHolder.instamoreView.setVisibility(View.VISIBLE);
        }else{
            viewHolder.insImage.setVisibility(View.VISIBLE);
            viewHolder.instamoreView.setVisibility(View.INVISIBLE);
            Picasso.with(mContext).load(insItem.getInsImage()).into(insImage);
        }

    }

    private List<LiveFeedInstagramItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public LiveFeedInstagramAdapter(Context context, List<LiveFeedInstagramItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.livemingle_instagramcell, parent, false);

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

        void instagframItemClicked(View v, int adapterPosition);
    }
}
