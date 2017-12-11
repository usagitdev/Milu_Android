package charles.com.milu.Models;

import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abderrahim El imame on 04/05/2016.
 * Email : abderrahim.elimame@gmail.com
 */
public class Pusher {
    private String action;
    private View view;
    private String data;
    private String title;
    private boolean bool;
//    private ContactsModel contactsModel;
    private List<MessageObject> messagesModelList;
    private List<MediaObject> mediaObjects;
    private JSONObject jsonObject;
    private MessageObject messagesModel;
    private int callId;
//    private ConversationsModel conversationsModel;
    private int groupID;
    private int statusID;
    private int conversationId;
//    private NotificationsModel notificationsModel;

    private int messageId;

    public Pusher(String action) {
        this.action = action;
    }

    public Pusher(String action, String data) {
        this.action = action;
        this.data = data;
    }

    public Pusher(String action, String data, String title) {
        this.action = action;
        this.data = data;
        this.title = title;
    }


    public Pusher(String action, String data, Boolean bool) {
        this.action = action;
        this.data = data;
        this.bool = bool;
    }

    public Pusher(String action, List<MessageObject> messagesModelList) {
        this.action = action;
        this.messagesModelList = messagesModelList;
    }

    public Pusher(String action, ArrayList<MediaObject> mediaObjects) {
        this.action = action;
        this.mediaObjects = mediaObjects;
    }

//    public Pusher(String action, ContactsModel contactsModel) {
//        this.action = action;
//        this.contactsModel = contactsModel;
//    }


//    public Pusher(String action, NotificationsModel notificationsModel) {
//        this.action = action;
//        this.notificationsModel = notificationsModel;
//    }

    public Pusher(String action, JSONObject jsonObject) {
        this.action = action;
        this.jsonObject = jsonObject;
    }

    public Pusher(String action, MessageObject messagesModel) {
        this.action = action;
        this.messagesModel = messagesModel;
    }

//    public Pusher(String action, ConversationsModel conversationsModel) {
//        this.action = action;
//        this.conversationsModel = conversationsModel;
//    }

    public Pusher(String action, int groupID, MessageObject messagesModel) {
        this.action = action;
        this.messagesModel = messagesModel;
        this.groupID = groupID;
    }

    public Pusher(String itemIsActivated, View view) {
        this.action = itemIsActivated;
        this.view = view;
    }


    public Pusher(String action, int ID) {
        this.action = action;
        this.messageId = ID;
        this.groupID = ID;
        this.conversationId = ID;
        this.callId = ID;
        this.statusID = ID;
    }


    public Pusher(String action, int groupID, int conversationId) {
        this.action = action;
        this.conversationId = conversationId;
        this.groupID = groupID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }


    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

//    public NotificationsModel getNotificationsModel() {
//        return notificationsModel;
//    }
//
//    public void setNotificationsModel(NotificationsModel notificationsModel) {
//        this.notificationsModel = notificationsModel;
//    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }


    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }


    public MessageObject getMessagesModel() {
        return messagesModel;
    }

    public void setMessagesModel(MessageObject messagesModel) {
        this.messagesModel = messagesModel;
    }


//    public ConversationsModel getConversationsModel() {
//        return conversationsModel;
//    }
//
//    public void setConversationsModel(ConversationsModel conversationsModel) {
//        this.conversationsModel = conversationsModel;
//    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    public List<MessageObject> getMessagesModelList() {
        return messagesModelList;
    }

    public void setMessagesModelList(List<MessageObject> messagesModelList) {
        this.messagesModelList = messagesModelList;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getAction() {
        return action;
    }


//    public ContactsModel getContactsModel() {
//        return contactsModel;
//    }
//
//    public void setContactsModel(ContactsModel contactsModel) {
//        this.contactsModel = contactsModel;
//    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<MediaObject> getMediaObjects() {
        return mediaObjects;
    }

    public void setMediaObjects(List<MediaObject> mediaObjects) {
        this.mediaObjects = mediaObjects;
    }
}
