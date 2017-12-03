package charles.com.milu.ProfileTab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.MiluApplication;
import charles.com.milu.R;
import charles.com.milu.Utility.CircleImageView;
import charles.com.milu.utils.CustomClickTextView;

/**
 * Created by mac_dev on 10/25/17.
 */

public class ProfileConnectionsFollowingRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SectionIndexer {
    public static final int TYPE_CELL = 0;
    public static final int TYPE_SECTION = 1;

    private List<ProfileConnectionFollowingModel> followingModelList;
    private Context context;
    private int mItemHeight = 0;
    private RecyclerView.LayoutParams mImageViewLayoutParams;

    private boolean isCollection;
    public ProfileConnectionsFollowingRecyclerViewAdapter(@NonNull List<ProfileConnectionFollowingModel> followingModelList, @NonNull Context context, @Nullable boolean b) {
        this.followingModelList = followingModelList;
        this.context = context;
        this.isCollection = b;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (isCollection) {
            View view = LayoutInflater.from(context).inflate(R.layout.profile_connections_follwoing_cell1, parent, false);
            final CollectionViewHolder viewHolder = new CollectionViewHolder(view);
            if (viewHolder.collectionCell.getLayoutParams().height != mItemHeight) {
                DisplayMetrics metrics = new DisplayMetrics();
                metrics = context.getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                mImageViewLayoutParams = new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, width / 3);
                viewHolder.collectionCell.setLayoutParams(mImageViewLayoutParams);
            }

            viewHolder.collectionCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        viewHolder.imvSelect.setImageResource(R.drawable.contacts_gallery_selected);
                        listener.onRowClick(v, position);
                    }
                }
            });

            return viewHolder;
        }else{
            switch (viewType) {
                case TYPE_CELL: {
                    View view = LayoutInflater.from(context).inflate(R.layout.profile_connections_follwoing_cell, parent, false);
                    final FollowingViewHolder viewHolder = new FollowingViewHolder(view);

                    viewHolder.btnUnfollow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = viewHolder.getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                viewHolder.useravatar.setImageResource(R.drawable.contacts_list_selected1);
                                listener.onRowClick(v, position);
                            }
                        }
                    });

                    return viewHolder;
                }
                case TYPE_SECTION: {
                    View view = LayoutInflater.from(context).inflate(R.layout.profile_connections_following_section, parent, false);
                    return new LetterViewHolder(view);
                }
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProfileConnectionFollowingModel profileConnectionFollowingModel = followingModelList.get(position);
        if (profileConnectionFollowingModel != null) {

            if (isCollection){

                ((CollectionViewHolder) holder).bindTo(profileConnectionFollowingModel, context);
            }else{
                switch (getItemViewType(position)) {
                    case TYPE_CELL: {
                        String model = profileConnectionFollowingModel.getUserFullName();
                        if (model != null) {
                            boolean b = true;
                            if (position < followingModelList.size() - 1) {
                                if (followingModelList.get(position + 1).getType() == TYPE_SECTION && position < followingModelList.size()) {
                                    b = false;
                                }
                            }
                            if (position == followingModelList.size() - 1) {
                                b = false;
                            }
                            ((FollowingViewHolder) holder).bindTo(profileConnectionFollowingModel, context, b);
                        }
                        break;
                    }
                    case TYPE_SECTION: {
                        ((LetterViewHolder) holder).bindTo(profileConnectionFollowingModel.getLetter());
                        break;
                    }
                }
            }
        }
    }

    public void setItemHeight(int height) {
        if (height == mItemHeight) {
            return;
        }
        mItemHeight = height;
        mImageViewLayoutParams =
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (followingModelList != null) ? followingModelList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        ProfileConnectionFollowingModel model = followingModelList.get(position);
        if (model != null) {
            return model.getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public Object[] getSections() {
        List<String> sectionList = new ArrayList<>();

        if (isCollection) {
            for (ProfileConnectionFollowingModel model : followingModelList) {
                if (model.getLetter() != null) {
                    sectionList.add(model.getLetter());
                }
            }
        }else{
            for (ProfileConnectionFollowingModel model : followingModelList) {
                if (model.getType() == TYPE_SECTION) {
                    sectionList.add(model.getLetter());
                }
            }
        }

        return sectionList.toArray();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0, size = followingModelList.size(); i < size; i++) {
            ProfileConnectionFollowingModel profileConnectionFollowingModel = followingModelList.get(i);
            if (MiluApplication.isCollection) {
                if (profileConnectionFollowingModel.getLetter() != null) {
                    String sortStr = profileConnectionFollowingModel.getLetter();
                    char firstChar = sortStr.toUpperCase().charAt(0);
                    if (firstChar == sectionIndex) {
                        return i;
                    }
                }

            }else{
                if (profileConnectionFollowingModel.getType() == TYPE_SECTION) {
                    String sortStr = profileConnectionFollowingModel.getLetter();
                    char firstChar = sortStr.toUpperCase().charAt(0);
                    if (firstChar == sectionIndex) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        int realSize = getItemCount();
        if (position >= realSize) {
            position = realSize - 1;
        }

        ProfileConnectionFollowingModel profileConnectionFollowingModel = followingModelList.get(position);
        Object[] sectionArray = getSections();
        String letter = "";
        if (isCollection){
            String model = profileConnectionFollowingModel.getUserFullName();
            if (model != null) {
                letter = model.substring(0, 1);
            }
            for (int i = 0; i < sectionArray.length; i++) {
                if (sectionArray[i].toString().equals(letter)) {
                    return i;
                }
            }
        }else{
            switch (profileConnectionFollowingModel.getType()) {
                case TYPE_CELL: {
                    String model = profileConnectionFollowingModel.getUserFullName();
                    if (model != null) {
                        letter = model.substring(0, 1);
                    }
                    break;
                }
                case TYPE_SECTION: {
                    letter = profileConnectionFollowingModel.getLetter();
                    break;
                }
            }

            for (int i = 0; i < sectionArray.length; i++) {
                if (sectionArray[i].toString().equals(letter)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static class FollowingViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserFullName;
        TextView txtUserName;
        CircleImageView useravatar;
        CustomClickTextView btnUnfollow;
        View underline;

        public FollowingViewHolder(View itemView) {
            super(itemView);
            txtUserFullName = (TextView) itemView.findViewById(R.id.user_full_name);
            txtUserName = (TextView) itemView.findViewById(R.id.user_name);
            useravatar = (CircleImageView) itemView.findViewById(R.id.user_image);
            btnUnfollow = (CustomClickTextView) itemView.findViewById(R.id.btn_unfollowing);
            underline = (View) itemView.findViewById(R.id.underline);
        }

        public void bindTo(@NonNull ProfileConnectionFollowingModel model, @NonNull Context context, boolean b) {
            txtUserFullName.setText(model.getUserFullName().toLowerCase());
            txtUserName.setText(model.getUserName().toLowerCase());
            Picasso.with(context).load(model.getUserAvatar()).into(useravatar);
            if (b) {
                underline.setVisibility(View.VISIBLE);
            }else{
                underline.setVisibility(View.GONE);
            }
//            textViewCountry.setCompoundDrawablesWithIntrinsicBounds(FlagKit.drawableWithFlag(context, model.getCountryCode().toLowerCase()), null, null, null);
        }
    }

    public static class CollectionViewHolder extends RecyclerView.ViewHolder {

        TitleTextView txtUserFullName;
        ImageView useravatar;
        ImageView imvSelect;
        TitleTextView txtLetter;
        RelativeLayout collectionCell;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            txtUserFullName = (TitleTextView) itemView.findViewById(R.id.txt_username);
            txtLetter = (TitleTextView) itemView.findViewById(R.id.txt_letter);
            useravatar = (ImageView) itemView.findViewById(R.id.user_image);
            imvSelect = (ImageView) itemView.findViewById(R.id.imv_select);
            collectionCell = (RelativeLayout) itemView.findViewById(R.id.collection_cell);
        }

        public void bindTo(@NonNull ProfileConnectionFollowingModel model, @NonNull Context context) {
            txtUserFullName.setText(model.getUserFullName().toLowerCase());
            if (model.getLetter() != null) {
                txtLetter.setText(model.getLetter());
                txtLetter.setVisibility(View.VISIBLE);
            }else{
                txtLetter.setVisibility(View.GONE);
            }
            Picasso.with(context).load(model.getUserAvatar()).into(useravatar);
            imvSelect.setImageResource(R.drawable.contacts_gallery_unselected2);
        }
    }

    public static class LetterViewHolder extends RecyclerView.ViewHolder {

        TitleTextView textViewLetter;

        public LetterViewHolder(View itemView) {
            super(itemView);
            textViewLetter = (TitleTextView) itemView.findViewById(R.id.section_title);
        }

        public void bindTo(@NonNull String letter) {
            textViewLetter.setText(letter);
        }
    }

    public RowClickListener listener;

    public void setListener(RowClickListener listener) {
        this.listener = listener;
    }

    public interface RowClickListener {
        void onRowClick(View view, int position);
    }
}