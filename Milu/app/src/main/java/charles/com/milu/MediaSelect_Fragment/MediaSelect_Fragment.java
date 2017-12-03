package charles.com.milu.MediaSelect_Fragment;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import charles.com.milu.MeetUps.GridSpacingItemDecoration;
import charles.com.milu.MeetUps.MeetUpItem;
import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaSelect_Fragment extends Fragment implements MediaSelectAdapter.ItemClickListener {


    ArrayList<MediaSelectItem> mediaItems;
    ArrayList<Boolean> selectedBoollist =  new ArrayList<Boolean>();
    ArrayList<Uri> selectedlist =  new ArrayList<>();
    private Uri[] mUrls = null;
    private String[] strUrls = null;
    private String[] mNames = null;
    private GridView gridview = null;
    private Cursor cc = null;
    private Button btnMoreInfo = null;
    private ProgressDialog myProgressDialog = null;
    private RecyclerView mediaSelectRecyclerView;

    private MediaSelectAdapter mediaSelectAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_media_select, container, false);

        if (checkPermissionREAD_EXTERNAL_STORAGE(getContext())){
            getImageUrl_setAdapter(view);
        }else{

        }

        return view;
    }

    public void getImageUrl_setAdapter(View view){

        mediaItems = MediaSelectItem.createContactsList(0);

        cc = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,
                null);
        if (cc != null) {

            myProgressDialog = new ProgressDialog(getActivity());
            myProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            myProgressDialog.setMessage(getResources().getString(R.string.hello_blank_fragment));
            //myProgressDialog.setIcon(R.drawable.blind);
            myProgressDialog.show();

            new Thread() {
                public void run() {
                    try {
                        cc.moveToFirst();
                        mUrls = new Uri[cc.getCount()];
                        strUrls = new String[cc.getCount()];
                        mNames = new String[cc.getCount()];
                        for (int i = 0; i < cc.getCount(); i++) {
                            cc.moveToPosition(i);
                            mUrls[i] = Uri.parse(cc.getString(1));
                            strUrls[i] = cc.getString(1);
                            String itemTest = mUrls[i].getPath();
                            MediaSelectItem item = new MediaSelectItem(itemTest);
                            mNames[i] = cc.getString(3);

                            mediaItems.add(i,item);
                        }

                    } catch (Exception e) {
                    }
                    myProgressDialog.dismiss();
                }
            }.start();
        }

        mediaSelectRecyclerView = (RecyclerView) view.findViewById(R.id.mediaSelect_RecyclerView);

        mediaSelectAdapter = new MediaSelectAdapter(getContext(), mediaItems);
        mediaSelectRecyclerView.setAdapter(mediaSelectAdapter);
        mediaSelectAdapter.setListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mediaSelectRecyclerView.setLayoutManager(gridLayoutManager);
        mediaSelectRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 1, true));

        mediaSelectAdapter.getSelectedItems();

    }
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

    }

    public List<MediaSelectItem> getSelectedItems() {
        if (mediaSelectAdapter == null)
            return null;
        return mediaSelectAdapter.getSelectedItems();
    }

    @Override
    public void mediaSelectedClicked(View v, int adapterPosition) {
        if (selectedlist.size() == 0)
            selectedlist.add(mUrls[adapterPosition]);


        Toast.makeText(v.getContext()," Media item clicked!!!",Toast.LENGTH_SHORT).show();

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

}
