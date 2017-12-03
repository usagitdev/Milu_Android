package charles.com.milu.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 2017-12-01.
 */

public class MessageObject {
    @SerializedName("msg_id")
    public int     nMsgId;
    @SerializedName("msg_sender")
    public int     nSenderId;
    @SerializedName("msg_receiver")
    public int     nReceiverId;
    @SerializedName("msg_text")
    public String  strMsgText;
    @SerializedName("msg_media")
    public String  strMsgMedia;
    @SerializedName("msg_media_type")
    public int     nMsgMediaType;
    @SerializedName("msg_time")
    public String  strMsgTime;
    @SerializedName("msg_read")
    public int     nMsgRead;
    @SerializedName("msg_thread")
    public String  strMsgThread;

    public int     nRetracted;

    public MessageObject(){
        nMsgId = nSenderId = nReceiverId = nMsgMediaType = nMsgRead = 0;
        strMsgText = strMsgMedia = strMsgTime = strMsgThread = "";
        nRetracted = 0;
    }
}
