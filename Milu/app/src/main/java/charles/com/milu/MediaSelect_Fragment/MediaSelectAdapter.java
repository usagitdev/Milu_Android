package charles.com.milu.MediaSelect_Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import charles.com.milu.R;

/**
 * Created by dev on 9/28/17.
 */

public class MediaSelectAdapter extends RecyclerView.Adapter<MediaSelectAdapter.ViewHolder>{

    private String[] strUrls = null;

    private List<MediaSelectItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder{


        public ImageView mediaImage;
        public ImageView imageCEll;
        ImageView selectedItemImage;
        Boolean isselected = false;

        public ViewHolder(View itemView){

            super(itemView);

            selectedItemImage = (ImageView)itemView.findViewById(R.id.mediaSelect_selectImage);

            if (isselected){

                selectedItemImage.setImageResource(R.drawable.contacts_gallery_unselected2);

            }

            mediaImage = (ImageView) itemView.findViewById(R.id.mediaSelect_Image);

            imageCEll = (ImageView) itemView.findViewById(R.id.mediaSelect_cell);

        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        final MediaSelectItem mItem = mContacts.get(position);

        ImageView mediaCell = viewHolder.mediaImage;

        mediaCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItem.isSelected()) {
                    viewHolder.selectedItemImage.setImageResource(R.drawable.contacts_gallery_unselected2);

                    mItem.setSelected(false);
                }

                else {
                    viewHolder.selectedItemImage.setImageResource(R.drawable.contacts_gallery_selected);
                    mItem.setSelected(true);
                }

            }
        });

        String imageUrlStr = mItem.getImage();

        Bitmap bmp = decodeURI(imageUrlStr);

//        mediaCell.setImageBitmap(bmp);
        Picasso.with(mContext).load(imageUrlStr).into(mediaCell);

    }

    public Bitmap decodeURI(String filePath){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Only scale if we need to
        // (16384 buffer for img processing)
        Boolean scaleByHeight = Math.abs(options.outHeight - 100) >= Math.abs(options.outWidth - 100);
        if(options.outHeight * options.outWidth * 2 >= 16384){
            // Load, scaling to smallest power of 2 that'll get it <= desired dimensions
            double sampleSize = scaleByHeight
                    ? options.outHeight / 100
                    : options.outWidth / 100;
            options.inSampleSize =
                    (int)Math.pow(2d, Math.floor(
                            Math.log(sampleSize)/Math.log(2d)));
        }
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[512];
        Bitmap output = BitmapFactory.decodeFile(filePath, options);

        return output;
    }

    // Pass in the contact array into the constructor
    public MediaSelectAdapter(Context context, List<MediaSelectItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public MediaSelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.media_select_item_cell, parent, false);

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

    public List<MediaSelectItem> getItems() {
        return mContacts;
    }

    public List<MediaSelectItem> getSelectedItems() {
        List<MediaSelectItem> selectedItems = new ArrayList<>();
        for (int i = 0; i < mContacts.size(); i ++) {
            MediaSelectItem item = mContacts.get(i);
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }

        return selectedItems;
    }

    public interface ItemClickListener {

        void mediaSelectedClicked(View v, int adapterPosition);
    }
}
