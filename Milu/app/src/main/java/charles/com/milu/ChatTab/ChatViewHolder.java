package charles.com.milu.ChatTab;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.Helper.LogHelper;
import charles.com.milu.R;
import charles.com.milu.Utility.SquareImageView;
import charles.com.milu.Models.MessageObject;
import charles.com.milu.Models.UserModel;
import charles.com.milu.utils.PicaImageLoader;

/**
 * Created by mac on 2017-12-01.
 */
public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    Context mContext;
    MessageObject message;

    ImageView mImageUserPhoto;
    TitleTextView        mTextMsgText;
    ImageView       mImageMsgPhoto;
    ImageView       mImageMsgVideo;
    RelativeLayout chatImageView;
    RelativeLayout chatVideoView;

    MediaPlayer mMediaPlayer = null;
    private Handler mUpdateProgressHandler = new Handler();
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            mUpdateProgressHandler.postDelayed(this, 200);
        }
    };

    boolean bIncoming = false;


    public ChatViewHolder(View itemView) {
        super(itemView);

        mContext = itemView.getContext();
        mImageUserPhoto = (SquareImageView)itemView.findViewById(R.id.user_avatar);
        mTextMsgText = (TitleTextView)itemView.findViewById(R.id.txt_message);
        mImageMsgPhoto = (RoundedImageView)itemView.findViewById(R.id.chat_message_image);
        mImageMsgVideo = (RoundedImageView)itemView.findViewById(R.id.chat_message_video);
        chatImageView = (RelativeLayout)itemView.findViewById(R.id.image_view);
        chatVideoView = (RelativeLayout)itemView.findViewById(R.id.video_view);

    }

    public void bindData(MessageObject message1, UserModel receiver){ //1 or 2
        message = message1;
        LogHelper.log("ChatViewHolder", "bind data message text = " + message1.strMsgText + " media = " + message1.strMsgMedia);

        mImageUserPhoto.setImageResource(R.drawable.user01);
        if(message1.nSenderId == 1){
            PicaImageLoader.loadImageWithPicasso(mContext, mImageUserPhoto, receiver.nPhoto, R.drawable.user01, 0, true, 100, 100);
        }

        if (message.nMsgMediaType == 0){
            mTextMsgText.setText(message.strMsgText);
            chatImageView.setVisibility(View.GONE);
            chatVideoView.setVisibility(View.GONE);
        }else {
            if (message.strMsgMedia.contains("http")) {
                PicaImageLoader.loadImageWithPicasso(mContext, mImageMsgPhoto, message.strMsgMedia, R.drawable.empty_photo);
            }else{
                Uri uri = Uri.fromFile(new File(message.strMsgMedia));
                Picasso.with(mContext).load(uri).placeholder(R.drawable.empty_photo).into(mImageMsgPhoto);
            }

            mTextMsgText.setVisibility(View.GONE);
            chatVideoView.setVisibility(View.GONE);
        }
    }



    public void removeMediaUpdater(){
        mUpdateProgressHandler.removeCallbacks(mUpdateTimeTask);
    }

    public void updateProgressBar() {
        mUpdateProgressHandler.postDelayed(mUpdateTimeTask, 100);
    }

    @Override
    public void onClick(View v) {
    }


}

