package charles.com.milu.ChatTab;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.Helper.LogHelper;
import charles.com.milu.R;
import charles.com.milu.Utility.SquareImageView;
import charles.com.milu.models.MessageObject;
import charles.com.milu.utils.PicaImageLoader;

/**
 * Created by mac on 2017-12-01.
 */
public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    Context mContext;
    MessageObject message;

    ImageView mImageUserPhoto;
    TextView        mTextMsgText;
    ImageView       mImageMsgPhoto;

    MediaPlayer mMediaPlayer = null;
    private Handler mUpdateProgressHandler = new Handler();
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            updateProgressBarOnce();
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
    }

    public void bindData(MessageObject message1, String strName, String friendName, String strAvatar, int inComing){ //1 or 2
        message = message1;
        LogHelper.log("ChatViewHolder", "bind data message text = " + message1.strMsgText + " media = " + message1.strMsgMedia);

        mImageUserPhoto.setImageResource(R.drawable.user01);
        if(message1.nSenderId == 1){
            PicaImageLoader.loadImageWithPicasso(mContext, mImageUserPhoto, strAvatar, R.drawable.user01, 0, true, 100, 100);
        }



    }

    void functionClickAudioAction(){
        if(mMediaPlayer == null) return;
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(mp != mMediaPlayer) return;
                mMediaPlayer.seekTo(0);
                mUpdateProgressHandler.removeCallbacks(mUpdateTimeTask);
                updateProgressBarOnce();
            }
        });

        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mUpdateProgressHandler.removeCallbacks(mUpdateTimeTask);
            updateProgressBarOnce();
        }
        else {
            mMediaPlayer.start();
            updateProgressBar();
        }
    }

    private void updateProgressBarOnce() {
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

