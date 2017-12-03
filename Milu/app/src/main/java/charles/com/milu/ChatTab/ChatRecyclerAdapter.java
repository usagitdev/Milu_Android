package charles.com.milu.ChatTab;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import charles.com.milu.R;
import charles.com.milu.models.MessageObject;


/**
 * Created by PIG18 on 1/23/2017.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    Context                     mContext;
    ArrayList<MessageObject> mChatList;
    int                         nCurrentUserId;
    String                      strUserName;
    String                      strFriendName;
    String                      strFriendAvatar;

    public ChatRecyclerAdapter(Context context, ArrayList<MessageObject> arrayChats, int userId, String uName, String fName, String nfavatar){
        mContext = context;
        nCurrentUserId = userId;
        mChatList = arrayChats;
        strUserName = uName;
        strFriendName = fName;
        strFriendAvatar = nfavatar;
    }


    @Override
    public int getItemViewType(int position){
        MessageObject message1 = mChatList.get(position);

        if(message1.nSenderId == nCurrentUserId){
            return 1;
        }else{
            return 2;
        }
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ChatViewHolder viewHolder = null;

        switch (viewType){
            case 1:
            {
                View v1 = inflater.inflate(R.layout.sender_bubble_text, parent, false);
                viewHolder = new ChatViewHolder(v1);
            }
            break;
            case 2:
            {
                View v2 = inflater.inflate(R.layout.receiver_bubble_text, parent, false);
                viewHolder = new ChatViewHolder(v2);
            }
            break;
            default:
            {
                View v1 = inflater.inflate(R.layout.sender_bubble_text, parent, false);
                viewHolder = new ChatViewHolder(v1);
            }
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        MessageObject message1 = mChatList.get(position);

        int ntype = getItemViewType(position);
        if(ntype == 1){
            holder.bindData(message1, strUserName, strFriendName, "0", ntype);
        }else if(ntype == 2){
            holder.bindData(message1, strFriendName, strUserName, strFriendAvatar, ntype);
        }
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }

    @Override
    public void onViewRecycled(ChatViewHolder holder){
        super.onViewRecycled(holder);


    }

}
