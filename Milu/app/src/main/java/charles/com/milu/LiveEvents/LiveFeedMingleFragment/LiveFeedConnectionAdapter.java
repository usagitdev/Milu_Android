package charles.com.milu.LiveEvents.LiveFeedMingleFragment;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import charles.com.milu.R;
import charles.com.milu.Utility.SquareAvatarImageView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dev on 9/25/17.
 */

public class LiveFeedConnectionAdapter extends RecyclerView.Adapter<LiveFeedConnectionAdapter.ViewHolder>{



    public class ViewHolder extends RecyclerView.ViewHolder{

        Typeface workSans_Light;
        public TextView connectionLevel;
        public TextView connectionName;
        public CircleImageView connectionImage;

        public ViewHolder(View itemView){

            super(itemView);

            workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");

            connectionLevel = (TextView) itemView.findViewById(R.id.liveMingle_ConnectionLevel);
            connectionName = (TextView) itemView.findViewById(R.id.liveMingle_ConnectionName);
            connectionImage = (CircleImageView) itemView.findViewById(R.id.liveMingle_ConnectionImage);
            connectionImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    listener.connectionItemClicked(v, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        LiveFeedConnectionItem connectionItem = mContacts.get(position);

        TextView connectionName = viewHolder.connectionName;
        TextView connectionLevel = viewHolder.connectionLevel;
        CircleImageView connectionImage = viewHolder.connectionImage;

        connectionName.setText(connectionItem.getConnectionName());
        connectionLevel.setText(connectionItem.getConnectionLevel());
        Picasso.with(mContext).load(connectionItem.getConnectionImage()).into(connectionImage);

    }

    private List<LiveFeedConnectionItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public LiveFeedConnectionAdapter(Context context, List<LiveFeedConnectionItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public LiveFeedConnectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.livemingle_connectioncell, parent, false);

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

        void connectionItemClicked(View v, int adapterPosition);
    }
}
