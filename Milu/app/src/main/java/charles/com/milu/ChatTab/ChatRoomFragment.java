package charles.com.milu.ChatTab;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.CameraFragmentApi;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.listeners.CameraFragmentControlsAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentStateAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentVideoRecordTextAdapter;
import com.google.android.cameraview.CameraView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.Models.MediaObject;
import charles.com.milu.Models.MessageObject;
import charles.com.milu.Models.UserModel;
import charles.com.milu.R;
import charles.com.milu.utils.CustomImageButton;
import charles.com.milu.utils.logger.Images;

import static charles.com.milu.Helper.ForegroundHelper.TAG;


/**
 * Created by mac_dev on 11/6/17.
 */

public class ChatRoomFragment extends BaseFragment implements View.OnClickListener, GalleryAdapter.ItemClickListener{

    @BindView(R.id.chat_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_chat_message)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.toolbar_btn_left)
    CustomImageButton backButton;

    @BindView(R.id.toolbar_btn_right2)
    CustomImageButton infoButton;

    @BindView(R.id.avatars_view)
    RelativeLayout avatarViews;

    @BindView(R.id.receiver_name)
    TitleTextView txtReceiverName;

    @BindView(R.id.recyclerView_group)
    RecyclerView recyclerGroupAvatarView;

    @BindView(R.id.bottom_bar)
    LinearLayout bottomBar;

    @BindView(R.id.bottom_type_bar)
    RelativeLayout bottomTypeBar;

    @BindView(R.id.gallery_view)
    LinearLayout galleryView;

    @BindView(R.id.media_button)
    CustomImageButton mediaButton;

    @BindView(R.id.type_text_view)
    EditText typeTextView;

    @BindView(R.id.send_message_button)
    CustomImageButton sendMessageButton;

    @BindView(R.id.gallery_toolbar)
    LinearLayout galleryToolBar;

    @BindView(R.id.right_arrow_button)
    ImageButton rightArrowButton;

    @BindView(R.id.add_image_camera_button)
    CustomImageButton addImageCameraButton;

    @BindView(R.id.add_image_gallery_button)
    CustomImageButton addImageGalleryButton;

    @BindView(R.id.image_gallery)
    RecyclerView recyclerGalleryView;
//    @BindView(R.id.cameraview)
//    FrameLayout cameraLayout;

    @BindView(R.id.googleCamera)
    CameraView cameraView;
    private Handler mBackgroundHandler;
    private static final int REQUEST_CAMERA_PERMISSION = 1;

    private static final String FRAGMENT_DIALOG = "dialog";


    TabLayout tabLayout = null;
    ArrayList<UserModel> receivers = new ArrayList<>();
    ArrayList<MessageObject> messageObjects = new ArrayList<>();
    ChatRecyclerAdapter adapter;
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    private boolean granted = false;

    ArrayList<MediaObject> mediaObjectArrayList = new ArrayList<>();

    GalleryAdapter galleryAdapter;
    public static ChatRoomFragment getInstance() {
        return new ChatRoomFragment();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_chat_room;
    }

    @Override
    public void initView() {
        super.initView();
        UserModel receiver = new UserModel();
        receiver.nFirstName = "aaa";
        receiver.nLastName = "bbb";
        receiver.nId = 1;
        receiver.nPhoto = Images.imageThumbUrls[51];
        receivers.add(receiver);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChatRecyclerAdapter(getContext(), messageObjects, receivers);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setToolBar();
        hideKeyboard();
        galleryView.setVisibility(View.GONE);

        rightArrowButton.setOnClickListener(this);
        if (cameraView != null) {
            cameraView.addCallback(mCallback);
        }

        getAllMedias();

    }


    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    granted = true;
//                    addCamera();
                } else {
                    Toast.makeText(mAct, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void setToolBar() {
        backButton.setImageResource(R.drawable.nav_bar_back_icon);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFragment(false);
            }
        });
        infoButton.setOnClickListener(this);
        addImageCameraButton.setOnClickListener(this);
        addImageGalleryButton.setOnClickListener(this);
        mediaButton.setOnClickListener(this);
        sendMessageButton.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadMessage();
            }
        });

        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(), "WorkSans-Light.ttf");
        typeTextView.setTypeface(workSans_Light);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_btn_left:
                backFragment(false);
                break;
            case R.id.toolbar_btn_right1:
                break;
            case R.id.toolbar_btn_right2:
                break;

            case R.id.send_message_button:
                functionSendMessage();
                break;
            case R.id.media_button:
                functionShowBottomBar();
                break;

            case R.id.right_arrow_button:
                galleryToolBar.setVisibility(View.VISIBLE);
                rightArrowButton.setVisibility(View.GONE);
                break;
        }
    }


    public void functionSendMessage() {
        if (galleryView.getVisibility() == View.VISIBLE) {
            for (MediaObject object: galleryAdapter.getMediaObjects()) {
                if (object.isSelected) {
                    MessageObject messageObject = new MessageObject();
                    messageObject.nMsgMediaType = 1;
                    messageObject.strMsgMedia = object.path;
                    messageObject.nSenderId = 0;
                    messageObject.nReceiverId = 1;

                    messageObjects.add(messageObject);
                    MessageObject messageObject1 = new MessageObject();
                    messageObject1.nMsgMediaType = 1;
                    messageObject1.strMsgMedia = object.path;
                    messageObject1.nSenderId = 1;
                    messageObject1.nReceiverId = 0;

                    messageObjects.add(messageObject1);
                    adapter.notifyItemInserted(adapter.getItemCount() - 1);
                    typeTextView.setText("");

                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                }
            }
        }else{
            String txtMessage = typeTextView.getText().toString().trim();
            if (txtMessage.isEmpty()) {
                return;
            }
            MessageObject messageObject = new MessageObject();
            messageObject.nMsgMediaType = 0;
            messageObject.strMsgText = txtMessage;
            messageObject.nSenderId = 0;
            messageObject.nReceiverId = 1;

            messageObjects.add(messageObject);
            MessageObject messageObject1 = new MessageObject();
            messageObject1.nMsgMediaType = 0;
            messageObject1.strMsgText = txtMessage;
            messageObject1.nSenderId = 1;
            messageObject1.nReceiverId = 0;

            messageObjects.add(messageObject1);
            adapter.notifyItemInserted(adapter.getItemCount() - 1);
            typeTextView.setText("");

            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        }

    }

    public void reloadMessage() {
        refreshLayout.setRefreshing(false);
    }

    public void functionShowBottomBar() {
        hideKeyboard();

        if (galleryView.getVisibility() != View.VISIBLE) {
            galleryView.setVisibility(View.VISIBLE);
            galleryAdapter.setMediaObjects(mediaObjectArrayList);
        } else {
            galleryView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = mAct.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = mAct.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(mAct, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            cameraView.start();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(mAct,
                Manifest.permission.CAMERA)) {
            ConfirmationDialogFragment
                    .newInstance(R.string.add,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION,
                            R.string.cast_notification_connected_message)
                    .show(mAct.getSupportFragmentManager(), FRAGMENT_DIALOG);
        } else {
            ActivityCompat.requestPermissions(mAct, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBackgroundHandler != null) {
            mBackgroundHandler.getLooper().quitSafely();
            mBackgroundHandler = null;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        cameraView.stop();
    }

    public void getAllMedias() {
        String[] projection = {
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.TITLE
        };

        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

        Uri queryUri = MediaStore.Files.getContentUri("external");

        CursorLoader cursorLoader = new CursorLoader(
                mAct,
                queryUri,
                projection,
                selection,
                null, // Selection args (none).
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
        );

        Cursor cursor = cursorLoader.loadInBackground();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex(projection[1])) != null) {
                long id = cursor.getLong(cursor.getColumnIndex(projection[0]));
                String type = cursor.getString(cursor.getColumnIndex(projection[4]));
                String path = cursor.getString(cursor.getColumnIndex(projection[1]));

                mediaObjectArrayList.add(new MediaObject(id,type , path,false));
            }
            cursor.moveToNext();
        }
        cursor.close();

        galleryAdapter = new GalleryAdapter(getContext(), mediaObjectArrayList);


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
        recyclerGalleryView.setLayoutManager(staggeredGridLayoutManager);

        recyclerGalleryView.setAdapter(galleryAdapter);
        galleryAdapter.setListener(this);
        recyclerGalleryView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_IDLE) {
                    galleryAdapter.onScrolled(recyclerView);
                }
            }

        });


    }

    @OnClick(R.id.front_back_camera_switcher)
    public void onSwitchCameraClicked() {
        if (cameraView != null){
            if (cameraView.getFacing() == CameraView.FACING_FRONT){
                cameraView.setFacing(CameraView.FACING_BACK);
            }else {
                cameraView.setFacing(CameraView.FACING_FRONT);
            }
        }
    }

    @OnClick(R.id.record_button)
    public void onRecordButtonClicked() {
        if (cameraView != null){
            cameraView.takePicture();
        }
    }


    @Override
    public void mediaCellClicked(View v, int adapterPosition) {
        galleryAdapter.setSelected(adapterPosition);

    }

    public static class ConfirmationDialogFragment extends DialogFragment {

        private static final String ARG_MESSAGE = "message";
        private static final String ARG_PERMISSIONS = "permissions";
        private static final String ARG_REQUEST_CODE = "request_code";
        private static final String ARG_NOT_GRANTED_MESSAGE = "not_granted_message";

        public static ConfirmationDialogFragment newInstance(@StringRes int message,
                                                             String[] permissions, int requestCode, @StringRes int notGrantedMessage) {
            ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_MESSAGE, message);
            args.putStringArray(ARG_PERMISSIONS, permissions);
            args.putInt(ARG_REQUEST_CODE, requestCode);
            args.putInt(ARG_NOT_GRANTED_MESSAGE, notGrantedMessage);
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Bundle args = getArguments();
            return new AlertDialog.Builder(getActivity())
                    .setMessage(args.getInt(ARG_MESSAGE))
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String[] permissions = args.getStringArray(ARG_PERMISSIONS);
                                    if (permissions == null) {
                                        throw new IllegalArgumentException();
                                    }
                                    ActivityCompat.requestPermissions(getActivity(),
                                            permissions, args.getInt(ARG_REQUEST_CODE));
                                }
                            })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getActivity(),
                                            args.getInt(ARG_NOT_GRANTED_MESSAGE),
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                    .create();
        }

    }
    private Handler getBackgroundHandler() {
        if (mBackgroundHandler == null) {
            HandlerThread thread = new HandlerThread("background");
            thread.start();
            mBackgroundHandler = new Handler(thread.getLooper());
        }
        return mBackgroundHandler;
    }
    private CameraView.Callback mCallback
            = new CameraView.Callback() {

        @Override
        public void onCameraOpened(CameraView cameraView) {
            Log.d(TAG, "onCameraOpened");
        }

        @Override
        public void onCameraClosed(CameraView cameraView) {
            Log.d(TAG, "onCameraClosed");
        }

        @Override
        public void onPictureTaken(CameraView cameraView, final byte[] data) {
            Log.d(TAG, "onPictureTaken " + data.length);
            Toast.makeText(cameraView.getContext(), "taken", Toast.LENGTH_SHORT)
                    .show();
            getBackgroundHandler().post(new Runnable() {
                @Override
                public void run() {
                    File file = new File(mAct.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                            "picture.jpg");
                    OutputStream os = null;
                    try {
                        os = new FileOutputStream(file);
                        os.write(data);
                        os.close();
                    } catch (IOException e) {
                        Log.w(TAG, "Cannot write to " + file, e);
                    } finally {
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                // Ignore
                            }
                        }
                    }
                }
            });
        }

    };

    @Override
    protected void onShowKeyboard(int height){
        galleryView.setVisibility(View.GONE);
    }

}
