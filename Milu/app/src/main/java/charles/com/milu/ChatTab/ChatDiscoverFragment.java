package charles.com.milu.ChatTab;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.RegularTitleTextView;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;


/**
 * Created by mac_dev on 11/6/17.
 */

public class ChatDiscoverFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    ChatDiscoverAdapter adapter;


    public ChatDiscoverFragment getInstance() {
        return new ChatDiscoverFragment();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_chat_tab_discover;
    }

    @Override
    public void initView() {
        super.initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChatDiscoverAdapter(getContext(), (new ChatDiscoverItem()).createContactsList(8));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
    }


    public static class ChatDiscoverAdapter extends RecyclerView.Adapter<ChatDiscoverAdapter.ViewHolder> {



        public class ViewHolder extends RecyclerView.ViewHolder{

            public TitleTextView likeCount;
            public ImageView chatIcon;
            public RegularTitleTextView discoverPlace;
            public TitleTextView discoverLikePeople;
            public ImageView backgroundImageView;

            public ViewHolder(View itemView){

                super(itemView);


                likeCount = (TitleTextView) itemView.findViewById(R.id.message_count);
                discoverLikePeople = (TitleTextView) itemView.findViewById(R.id.like_count_text);
                discoverPlace = (RegularTitleTextView) itemView.findViewById(R.id.place_name);
                backgroundImageView = (ImageView) itemView.findViewById(R.id.background_image_view);
                chatIcon = (ImageView)itemView.findViewById(R.id.message_icon);
                backgroundImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, int position) {

            ChatDiscoverItem eventItem = mContacts.get(position);

            Picasso.with(mContext).load(eventItem.getBackgroundImage()).into(viewHolder.backgroundImageView);

            viewHolder.discoverPlace.setText(eventItem.getDiscoverPlace());
            viewHolder.discoverLikePeople.setText(eventItem.getDiscoverLikeText());
            viewHolder.likeCount.setText(eventItem.getDiscoverLikeCount());

        }

        private List<ChatDiscoverItem> mContacts;
        // Store the context for easy access

        private Context mContext;

        // Pass in the contact array into the constructor
        public ChatDiscoverAdapter(Context context, List<ChatDiscoverItem> contacts) {

            mContacts = contacts;

            mContext = context;
        }

        private Context getContext() {

            return mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(mContext);

            View contactView = inflater.inflate(R.layout.chat_discover_cellitem, parent, false);

            ChatDiscoverAdapter.ViewHolder viewHolder = new ChatDiscoverAdapter.ViewHolder(contactView);

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

            void mEventCell_Clicked(View v, int adapterPosition);
        }

    }

}
