package charles.com.milu.LiveEvents.LiveFeedPostFragment;

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
 * Created by dev on 9/25/17.
 */

public class LiveFeedDispAdapter extends RecyclerView.Adapter<LiveFeedDispAdapter.ViewHolder>{

public class ViewHolder extends RecyclerView.ViewHolder{

    public ImageView userImage;
    public  TextView userName;
    public  TextView upvoteCount;
    public  ImageView feedImage;
    public  TextView feedDescription;
    public  TextView feedProperty;
    public  ImageView likeButton;
    public  ImageView shareButton;
    public  ImageView moreButton;
    public  ImageView chatButton;
    public  ImageView upvoteButton1;
    public  ImageView upvoteButton2;

    public  ImageView mediaCell;

    public ViewHolder(View itemView){

        super(itemView);

        userName = (TextView) itemView.findViewById(R.id.livemediapost_userName);
        userImage = (ImageView) itemView.findViewById(R.id.livemediapost_userImage);
        upvoteCount = (TextView) itemView.findViewById(R.id.livemediapost_upvoteCount);
        feedImage = (ImageView) itemView.findViewById(R.id.livemediapost_feedImage);
        feedDescription = (TextView) itemView.findViewById(R.id.livemediapost_description);
        feedProperty = (TextView) itemView.findViewById(R.id.livemediapost_property);

        upvoteButton2 = (ImageView) itemView.findViewById(R.id.livemediapost_upboteButton2);

        Typeface workSans_Light = Typeface.createFromAsset(mContext.getAssets(),"WorkSans-Light.ttf");
        userName.setTypeface(workSans_Light);
        upvoteCount.setTypeface(workSans_Light);
        feedDescription.setTypeface(workSans_Light);
        feedProperty.setTypeface(workSans_Light);

        likeButton = (ImageView) itemView.findViewById(R.id.livemediapost_likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.likeButtonClicked(v, getAdapterPosition());
            }
        });

        shareButton = (ImageView) itemView.findViewById(R.id.livemediapost_shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.shareButtuonClicked(v, getAdapterPosition());
            }
        });

        chatButton = (ImageView) itemView.findViewById(R.id.livemediapost_chatButton);
        chatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.chatButtonClicked(v, getAdapterPosition());
            }
        });
        moreButton = (ImageView) itemView.findViewById(R.id.livemediapost_moreButton);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.moreButtonClicked(v, getAdapterPosition());
            }
        });

        upvoteButton1 = (ImageView) itemView.findViewById(R.id.livemediapost_upboteButton1);
        upvoteButton1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listener.upvoteButton1Clicked(v, getAdapterPosition());
            }
        });

        upvoteButton2 = (ImageView) itemView.findViewById(R.id.livemediapost_upboteButton2);
        upvoteButton2.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                listener.upvoteButton2Clicked(v, getAdapterPosition());
            }
        });

        mediaCell = (ImageView) itemView.findViewById(R.id.livemediapost_feedImage);
        mediaCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.liveMediaPostCell_clicked(v, getAdapterPosition());
            }
        });

    }
}

    private List<LiveMediaPostItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public LiveFeedDispAdapter(Context context, List<LiveMediaPostItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }


    private Context getContext() {

        return mContext;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.livemediapost_itemlayout, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        LiveMediaPostItem contact = mContacts.get(position);

        TextView textView = viewHolder.userName;
        ImageView userImage = viewHolder.userImage;
        TextView upvoteCount = viewHolder.upvoteCount;
        ImageView feedImage = viewHolder.feedImage;
        TextView feedDescription = viewHolder.feedDescription;
        TextView feedProperty = viewHolder.feedProperty;

        textView.setText(contact.getName());
        upvoteCount.setText(contact.getupVote());
//        userImage.setImageResource(contact.getuserImage());
        Picasso.with(mContext).load(contact.getuserImage()).into(userImage);
//        feedImage.setImageResource(contact.getfeedImage());
        Picasso.with(mContext).load(contact.getfeedImage()).into(feedImage);
        feedDescription.setText(contact.getDescription());
        feedProperty.setText(contact.getProperty());
    }

    @Override
    public int getItemCount() {

        return mContacts.size();
    }

    public ItemClickListener listener;

    public void setListener(ItemClickListener listener){
        this.listener = listener;
    }

public interface ItemClickListener{

    void likeButtonClicked(View view, int position);
    void shareButtuonClicked(View view, int position);
    void moreButtonClicked(View view, int position);
    void chatButtonClicked(View view, int positon);
    void upvoteButton1Clicked(View view, int positon);
    void upvoteButton2Clicked(View view, int positon);
    void liveMediaPostCell_clicked(View v, int adapterPosition);
}
}
