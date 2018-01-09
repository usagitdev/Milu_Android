package charles.com.milu.MediaSelect_Fragment;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.Models.MediaObject;
import charles.com.milu.Models.Pusher;
import charles.com.milu.R;
import charles.com.milu.utils.AppConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaSelect_Fragment extends BaseFragment implements MediaSelectGalleryAdapter.ItemClickListener {


    private ProgressDialog myProgressDialog = null;

    @BindView(R.id.mediaSelect_RecyclerView)
    RecyclerView mediaSelectRecyclerView;

    ArrayList<MediaObject> mediaObjectArrayList = new ArrayList<>();
    MediaSelectGalleryAdapter galleryAdapter;

    public static MediaSelect_Fragment getInstance() {
        return new MediaSelect_Fragment();
    }

    @Override
    protected int addView() {
        return R.layout.fragment_media_select;
    }

    @Override
    public void initView() {
        super.initView();
        getAllMedias();
    }
//    public void getImageUrl_setAdapter(View view){
//
//        mediaItems = MediaSelectItem.createContactsList(0);
//
//        cc = getActivity().getContentResolver().query(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,
//                null);
//        if (cc != null) {
//
//            myProgressDialog = new ProgressDialog(getActivity());
//            myProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            myProgressDialog.setMessage(getResources().getString(R.string.hello_blank_fragment));
//            //myProgressDialog.setIcon(R.drawable.blind);
//            myProgressDialog.show();
//
//            new Thread() {
//                public void run() {
//                    try {
//                        cc.moveToFirst();
//                        mUrls = new Uri[cc.getCount()];
//                        strUrls = new String[cc.getCount()];
//                        mNames = new String[cc.getCount()];
//                        for (int i = 0; i < cc.getCount(); i++) {
//                            cc.moveToPosition(i);
//                            mUrls[i] = Uri.parse(cc.getString(1));
//                            strUrls[i] = cc.getString(1);
//                            String itemTest = mUrls[i].getPath();
//                            MediaSelectItem item = new MediaSelectItem(itemTest);
//                            mNames[i] = cc.getString(3);
//
//                            mediaItems.add(i,item);
//                        }
//
//                    } catch (Exception e) {
//                    }
//                    myProgressDialog.dismiss();
//                }
//            }.start();
//        }
//
//        mediaSelectRecyclerView = (RecyclerView) view.findViewById(R.id.mediaSelect_RecyclerView);
//
//        mediaSelectAdapter = new MediaSelectAdapter(getContext(), mediaItems);
//        mediaSelectRecyclerView.setAdapter(mediaSelectAdapter);
//        mediaSelectAdapter.setListener(this);
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
//        mediaSelectRecyclerView.setLayoutManager(gridLayoutManager);
//        mediaSelectRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 1, true));
//
//        mediaSelectAdapter.getSelectedItems();
//
//    }
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

    }


    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }
    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
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

        galleryAdapter = new MediaSelectGalleryAdapter(getContext(), mediaObjectArrayList, 3);


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        mediaSelectRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mediaSelectRecyclerView.setAdapter(galleryAdapter);
        galleryAdapter.setListener(this);
        mediaSelectRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    @Override
    public void mediaCellClicked(View v, int adapterPosition) {
//        galleryAdapter.setSelected(adapterPosition);
        Toast.makeText(v.getContext()," Media item clicked!!!",Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new Pusher(AppConstants.SELECT_MEDIA_SELECT_ITEM, getSelectedItems()));
    }

    public ArrayList<MediaObject> getSelectedItems() {
        ArrayList<MediaObject> arrayList = new ArrayList<>();
        for (MediaObject object : galleryAdapter.getMediaObjects()) {
            if (object.isSelected) {
                arrayList.add(object);
            }
        }
        return arrayList;
    }
}
