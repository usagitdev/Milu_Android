package charles.com.milu.MediaSelect_Fragment;

import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import charles.com.milu.Models.MediaObject;
import charles.com.milu.R;
import charles.com.milu.utils.CustomImageButton;
import charles.com.milu.utils.CustomTextureVideoView;

/**
 * Created by mac_dev on 12/7/17.
 */

public class MediaSelectGalleryAdapter extends RecyclerView.Adapter<MediaSelectGalleryAdapter.ViewHolder> {

    WeakReference<Context> activityWeakReference;

    ArrayList<MediaObject> mediaObjects = new ArrayList<>();
    public static final String FRAGMENT_TAG = "camera";

    ViewHolder currentVideoViewHolder;
    private RecyclerView.LayoutParams mImageViewLayoutParams;
    int numColumns = 1;

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cellImage;
        CustomTextureVideoView cellVideo;
        RelativeLayout mediaCell;
        CustomImageButton checkButton;
        CustomImageButton videoButton;
        ProgressBar imageLoaderProgressBar;
        Uri videoUrl;

        public Uri getVideoUrl() {
            return videoUrl;
        }


        public ViewHolder(View itemView) {
            super(itemView);

            mediaCell = (RelativeLayout) itemView.findViewById(R.id.media_cell);
            cellImage = (ImageView) itemView.findViewById(R.id.media_cell_image);
            cellVideo = (CustomTextureVideoView) itemView.findViewById(R.id.media_cell_video);
            checkButton = (CustomImageButton) itemView.findViewById(R.id.media_selector);
            videoButton = (CustomImageButton) itemView.findViewById(R.id.video_icon);
            imageLoaderProgressBar = (ProgressBar) itemView.findViewById(R.id.lyt_image_loader_progress_bar);
            mediaCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setSelected(getAdapterPosition());
                    listener.mediaCellClicked(v, getAdapterPosition());

                }
            });
            cellVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mp) {
                    Log.v("Video", "onPrepared" + cellVideo.getVideoPath());
                    int width = mp.getVideoWidth();
                    int height = mp.getVideoHeight();
                    cellVideo.setIsPrepared(true);
                    if (currentVideoViewHolder == ViewHolder.this) {
                        cellVideo.seekTo(0);
                        cellVideo.start();
                    }
                }
            });
            cellVideo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.v("Video", "onFocusChange" + hasFocus);
                    if (!hasFocus && currentVideoViewHolder == ViewHolder.this) {
                        stopVideo();
                    }

                }
            });
            cellVideo.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    Log.v("Video", "onInfo" + what + " " + extra);

                    return false;
                }
            });
            cellVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.v("Video", "onCompletion");

                    imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                    currentVideoViewHolder = null;
                }
            });
            videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentVideoViewHolder != null && currentVideoViewHolder != ViewHolder.this) {
                        currentVideoViewHolder.cellVideo.pause();
                        currentVideoViewHolder.videoButton.setVisibility(View.VISIBLE);
                        currentVideoViewHolder.imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                        currentVideoViewHolder = null;
                    }

                    currentVideoViewHolder = ViewHolder.this;

                    videoButton.setVisibility(View.INVISIBLE);
                    imageLoaderProgressBar.setVisibility(View.VISIBLE);
                    cellVideo.setVisibility(View.VISIBLE);
                    if (!getVideoUrl().equals(cellVideo.getVideoPath())) {
                        cellVideo.setIsPrepared(false);
//                        cellVideo.setVideoPath(getVideoUrl());
                        cellVideo.setVideoURI(getVideoUrl());
                        cellVideo.requestFocus();
                    } else {
                        if (cellVideo.isPrepared()) {
                            imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                        } else {
                            imageLoaderProgressBar.setVisibility(View.VISIBLE);
                        }
                        cellVideo.requestFocus();
                        cellVideo.seekTo(0);
                        cellVideo.start();
                    }
                }
            });
            DisplayMetrics metrics;
            metrics = mContext.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            mImageViewLayoutParams = new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, width / numColumns);
            mediaCell.setLayoutParams(mImageViewLayoutParams);
        }

        public void stopVideo() {
            Log.v("Video", "stopVideo");

            //imageView is within the visible window
            cellVideo.pause();
            videoButton.setVisibility(View.VISIBLE);
            imageLoaderProgressBar.setVisibility(View.INVISIBLE);
            currentVideoViewHolder = null;
        }

        public void onScrolled(RecyclerView recyclerView) {
            if (isViewNotVisible(videoButton, recyclerView) || isViewNotVisible(imageLoaderProgressBar, recyclerView)) {
                //imageView is within the visible window
                stopVideo();
            }
        }

        public boolean isViewNotVisible(View view, RecyclerView recyclerView) {
            Rect scrollBounds = new Rect();
            recyclerView.getHitRect(scrollBounds);
            return view.getVisibility() == View.VISIBLE && !view.getLocalVisibleRect(scrollBounds);
        }

    }

    public void setMediaObjects(ArrayList<MediaObject> objects){
        mediaObjects = objects;
        notifyDataSetChanged();
    }

    public ArrayList<MediaObject> getMediaObjects(){
        return mediaObjects;
    }


    public void setSelected(int position) {
        MediaObject mediaObject = mediaObjects.get(position);
        if (mediaObject.isSelected) {
            mediaObject.isSelected = false;
        } else {
            mediaObject.isSelected = true;
        }
        mediaObjects.set(position, mediaObject);
        notifyItemChanged(position);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }


    @Override
    public MediaSelectGalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.media_select_gallery_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MediaSelectGalleryAdapter.ViewHolder viewHolder, int position) {
            ViewHolder vh = ((ViewHolder)viewHolder);
            Uri uri = Uri.fromFile(new File(mediaObjects.get(position).path));
            if (mediaObjects.get(position).isSelected) {
                vh.checkButton.setSelected(true);
            }else{
                vh.checkButton.setSelected(false);
            }
            if (mediaObjects.get(position).type.contains("video")) {
                vh.videoButton.setVisibility(View.VISIBLE);
                vh.cellVideo.setVisibility(View.VISIBLE);
                vh.videoUrl = uri;
                vh.imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                vh.cellImage.setVisibility(View.GONE);
            }else {
                vh.videoButton.setVisibility(View.GONE);
                vh.cellVideo.setVisibility(View.GONE);
                vh.imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                vh.cellImage.setVisibility(View.VISIBLE);
                Picasso.with(mContext).load(uri).placeholder(R.drawable.empty_photo).into(vh.cellImage);
            }


    }

    private Context mContext;

    // Pass in the contact array into the constructor
    public MediaSelectGalleryAdapter(Context context, ArrayList<MediaObject> objects, int numColumnss) {

        this.activityWeakReference = new WeakReference<>(context);
        mContext = context;
        mediaObjects = objects;
        mImageViewLayoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.numColumns = numColumnss;

    }
    @Override
    public void onViewRecycled(ViewHolder holder) {
        if (holder == currentVideoViewHolder) {
            currentVideoViewHolder = null;
            holder.stopVideo();
        }
        holder.cellVideo.stopPlayback();
        super.onViewRecycled(holder);

    }

    public void onScrolled(RecyclerView recyclerView) {
        if (currentVideoViewHolder != null) {
            currentVideoViewHolder.onScrolled(recyclerView);
        }
    }

    private Context getContext() {

        return activityWeakReference.get();
    }

    @Override
    public int getItemCount() {

        return mediaObjects.size();
    }

    public MediaSelectGalleryAdapter.ItemClickListener listener;

    public void setListener(MediaSelectGalleryAdapter.ItemClickListener listener){

        this.listener = listener;

    }

    public interface ItemClickListener {


        void mediaCellClicked(View v, int adapterPosition);
    }
}
