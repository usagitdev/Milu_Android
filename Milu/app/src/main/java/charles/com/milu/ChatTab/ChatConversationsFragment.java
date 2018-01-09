package charles.com.milu.ChatTab;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.RegularTitleTextView;
import charles.com.milu.R;


/**
 * Created by mac_dev on 11/6/17.
 */

public class ChatConversationsFragment extends BaseFragment implements ChatConversationAdapter.ItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    ChatConversationAdapter adapter;


    public ChatConversationsFragment getInstance() {
        return new ChatConversationsFragment();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_chat_tab_chat;
    }

    @Override
    public void initView() {
        super.initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChatConversationAdapter(getContext(), (new ChatConversationItem()).createContactsList(8));
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void conversation_Clicked(View v, int adapterPosition) {
        addFragment(ChatRoomFragment.getInstance(), true);
    }



}
class ChatConversationAdapter extends RecyclerView.Adapter<ChatConversationAdapter.ViewHolder> {



    public class ViewHolder extends RecyclerView.ViewHolder{

        public RegularTitleTextView userName;
        public RegularTitleTextView timestamp;
        public ImageView userAvatar;
        public ImageView imvNotitype;
        public CardView userBadge;
        public CardView userOnlineIndicator;
        public RegularTitleTextView userMessage;
        public RegularTitleTextView userBadgeNumber;
        public BlurImageView backgroundImageView;
        RelativeLayout conversationView;

        public ViewHolder(final View itemView){

            super(itemView);


            userName = (RegularTitleTextView) itemView.findViewById(R.id.user_name);
            timestamp = (RegularTitleTextView) itemView.findViewById(R.id.chat_time);
            userBadgeNumber = (RegularTitleTextView) itemView.findViewById(R.id.txt_badge);
            userMessage = (RegularTitleTextView) itemView.findViewById(R.id.txt_message);

            backgroundImageView = (BlurImageView) itemView.findViewById(R.id.blurred_background_image_view);
            userAvatar = (ImageView)itemView.findViewById(R.id.user_image);
            imvNotitype = (ImageView)itemView.findViewById(R.id.imv_match_image);
            userBadge = (CardView)itemView.findViewById(R.id.user_badge);
            userOnlineIndicator = (CardView)itemView.findViewById(R.id.icon_online);
            backgroundImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.conversation_Clicked(itemView, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        ChatConversationItem eventItem = mContacts.get(position);

        Picasso.with(mContext).load(eventItem.getBackgroundImage()).into(viewHolder.backgroundImageView, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.backgroundImageView.setBlur(6);
            }
            @Override
            public void onError() {

            }
        });
        Picasso.with(mContext).load(eventItem.getUserImage()).into(viewHolder.userAvatar);

        viewHolder.userBadge.setVisibility(eventItem.getUserBadge() == "0" ? View.GONE : View.VISIBLE);
        viewHolder.userOnlineIndicator.setVisibility(eventItem.isOnline() ? View.VISIBLE: View.GONE);
        viewHolder.userMessage.setText(eventItem.getUserMessage());
        viewHolder.userBadgeNumber.setText(eventItem.getUserBadge());
        viewHolder.userName.setText(eventItem.getUserName());
        viewHolder.timestamp.setText(eventItem.getTimeStamp());
        viewHolder.imvNotitype.setImageResource(eventItem.getChatNotiType());

    }

    private List<ChatConversationItem> mContacts;
    // Store the context for easy access

    private Context mContext;

    // Pass in the contact array into the constructor
    public ChatConversationAdapter(Context context, List<ChatConversationItem> contacts) {

        mContacts = contacts;

        mContext = context;
    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.chat_conversation_cellitem, parent, false);

        ChatConversationAdapter.ViewHolder viewHolder = new ChatConversationAdapter.ViewHolder(contactView);

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

        void conversation_Clicked(View v, int adapterPosition);
    }


}
