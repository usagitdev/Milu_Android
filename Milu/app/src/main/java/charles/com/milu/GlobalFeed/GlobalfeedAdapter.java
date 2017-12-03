package charles.com.milu.GlobalFeed;

/**
 * Created by dev on 9/19/17.
 */


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.eightbitlab.supportrenderscriptblur.SupportRenderScriptBlur;
import com.jgabrielfreitas.core.BlurImageView;
import com.ms_square.etsyblur.BlurringView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.xl.ratingbar.MyRatingBar;

import java.util.List;

import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;
import charles.com.milu.utils.logger.Images;


public class GlobalfeedAdapter extends RecyclerView.Adapter<GlobalfeedAdapter.ViewHolder>{

//    private ImageFetcher imageFetcher = null;

    public class ViewHolder extends RecyclerView.ViewHolder{

        private  ImageView userImage;
        private  TitleTextView userName;
        private  TitleTextView upvoteCount;
//        private  BlurringView feedImage;
        private  ImageView feedImage, feedImage2, feedImage3, feedImage4, feedImage5;
        private  TitleTextView feedDescription;
        private  TitleTextView feedProperty;
        private  ImageView likeButton;
        private  ImageView shareButton;
        private  ImageView moreButton;
        private  ImageView chatButton;
        private  ImageView upvoteButton1;
        private  ImageView upvoteButton2;
        private MyRatingBar ratingBar;
        private TitleTextView txtRating;
        RelativeLayout worldFeedView;

        public ViewHolder(View itemView){

            super(itemView);

            userName = (TitleTextView) itemView.findViewById(R.id.globalfeed_userName);
            userImage = (ImageView) itemView.findViewById(R.id.globalfeed_userImage);
            upvoteCount = (TitleTextView) itemView.findViewById(R.id.globalfeed_upvoteCount);
            feedImage = (ImageView) itemView.findViewById(R.id.globalfeed_feedImage);
            feedImage2 = (ImageView) itemView.findViewById(R.id.globalfeed_feedImage2);
            feedImage3= (ImageView) itemView.findViewById(R.id.globalfeed_feedImage3);
            feedImage4= (ImageView) itemView.findViewById(R.id.globalfeed_feedImage4);
            feedImage5 = (ImageView) itemView.findViewById(R.id.globalfeed_feedImage5);
            feedDescription = (TitleTextView) itemView.findViewById(R.id.globalfeed_description);
            feedProperty = (TitleTextView) itemView.findViewById(R.id.txt_posttime);
            ratingBar = (MyRatingBar) itemView.findViewById(R.id.ratingView);
            txtRating = (TitleTextView) itemView.findViewById(R.id.txt_rating_score);


            worldFeedView = (RelativeLayout) itemView.findViewById(R.id.world_feedview);
            upvoteButton2 = (ImageView) itemView.findViewById(R.id.globalfeed_upboteButton2);


            likeButton = (ImageView) itemView.findViewById(R.id.globlafeed_likeButton);
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.likeButtonClicked(v, getAdapterPosition());
                }
            });

            shareButton = (ImageView) itemView.findViewById(R.id.globlafeed_shareButton);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.shareButtuonClicked(v, getAdapterPosition());
                }
            });

            chatButton = (ImageView) itemView.findViewById(R.id.globlafeed_chatButton);
            chatButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.chatButtonClicked(v, getAdapterPosition());
                }
            });
            moreButton = (ImageView) itemView.findViewById(R.id.globlafeed_moreButton);
            moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.moreButtonClicked(v, getAdapterPosition());
                }
            });

            upvoteButton1 = (ImageView) itemView.findViewById(R.id.globalfeed_upboteButton1);
            upvoteButton1.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    listener.upvoteButton1Clicked(v, getAdapterPosition());
                }
            });

            upvoteButton2 = (ImageView) itemView.findViewById(R.id.globalfeed_upboteButton2);
            upvoteButton2.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View v) {
                    listener.upvoteButton2Clicked(v, getAdapterPosition());
                }
            });

        }
    }

    private List<GlobalFeedItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public GlobalfeedAdapter(Context context, List<GlobalFeedItem> contacts) {

        mContacts = contacts;

        mContext = context;
//        ImageCache.ImageCacheParams cacheParams =
//                new ImageCache.ImageCacheParams(mContext, "thumb");
//        imageFetcher = new ImageFetcher(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size1));
//        imageFetcher.setLoadingImage(R.drawable.empty_photo);
    }


    private Context getContext() {

        return mContext;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView;
        ViewHolder viewHolder;
        switch (viewType) {
            case 0:
                contactView = inflater.inflate(R.layout.globalfeeditem_layout0, parent, false);

                viewHolder = new ViewHolder(contactView);

                return viewHolder;
            case 1:
                contactView = inflater.inflate(R.layout.globalfeeditem_layout1, parent, false);

                viewHolder = new ViewHolder(contactView);

                return viewHolder;
            case 2:
                contactView = inflater.inflate(R.layout.globalfeeditem_layout2, parent, false);

                viewHolder = new ViewHolder(contactView);

                return viewHolder;
            case 3:
                contactView = inflater.inflate(R.layout.globalfeeditem_layout3, parent, false);

                viewHolder = new ViewHolder(contactView);

                return viewHolder;
            case 4:
                contactView = inflater.inflate(R.layout.globalfeeditem_layout4, parent, false);

                viewHolder = new ViewHolder(contactView);

                return viewHolder;
            case 5:
                contactView = inflater.inflate(R.layout.globalfeeditem_layout5, parent, false);

                viewHolder = new ViewHolder(contactView);

                return viewHolder;
            case 6:
                contactView = inflater.inflate(R.layout.globalfeeditem_layout6, parent, false);

                viewHolder = new ViewHolder(contactView);

                return viewHolder;
            case 7:
                contactView = inflater.inflate(R.layout.globalfeeditem_layout7, parent, false);

                viewHolder = new ViewHolder(contactView);

                return viewHolder;
        }
        contactView = inflater.inflate(R.layout.globalfeeditem_layout0, parent, false);

        viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        GlobalFeedItem contact = mContacts.get(position);

        TextView textView = viewHolder.userName;
        ImageView userImage = viewHolder.userImage;
        TextView upvoteCount = viewHolder.upvoteCount;
        ImageView feedImage = viewHolder.feedImage;
        TextView feedDescription = viewHolder.feedDescription;
        TextView feedProperty = viewHolder.feedProperty;
        TextView txtratingView = viewHolder.txtRating;
        MyRatingBar ratingBar = viewHolder.ratingBar;

        textView.setText(contact.getName());
        upvoteCount.setText(contact.getupVote());
        Picasso picasso = Picasso.with(mContext);
        picasso.load(contact.getuserImage()).placeholder(R.drawable.empty_photo).into(userImage);
        feedDescription.setText(contact.getDescription());
//        feedProperty.setText(contact.getProperty());
        picasso.load(Images.imageUrls[position]).into( feedImage);
        switch (mContacts.get(position).getFeedType()) {
            case 0:

                break;
            case 1:

                break;
            case 2:
                picasso.load(Images.imageUrls[11]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage2);
                break;
            case 3:
                picasso.load(Images.imageUrls[12]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage2);
                picasso.load(Images.imageUrls[13]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage3);
                break;
            case 4:
                picasso.load(Images.imageUrls[14]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage2);
                picasso.load(Images.imageUrls[15]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage3);
                picasso.load(Images.imageUrls[16]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage4);
                break;
            case 5:
                picasso.load(Images.imageUrls[17]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage2);
                picasso.load(Images.imageUrls[18]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage3);
                picasso.load(Images.imageUrls[19]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage4);
                picasso.load(Images.imageUrls[10]).placeholder(R.drawable.empty_photo).into(viewHolder.feedImage5);
                break;
            case 6:

                break;
            case 7:

                break;
            default:

                break;
        }

//        viewHolder.topBlurView.blurredView(viewHolder.feedImage);
//        viewHolder.bottomBlurView.blurredView(viewHolder.feedImage);

    }

    @Override
    public int getItemCount() {

        return mContacts.size();
    }

    @Override
    public int getItemViewType(int position) {

        return mContacts.get(position).getFeedType();
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

    }
}


