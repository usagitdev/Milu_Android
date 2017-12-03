package charles.com.milu.Fragments;


import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jgabrielfreitas.core.BlurImageView;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import butterknife.BindView;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;
import charles.com.milu.utils.CustomClickTextView;
import charles.com.milu.utils.Utilities;
import charles.com.milu.utils.awesomeimagepicker.activities.AlbumSelectActivity;
import charles.com.milu.utils.awesomeimagepicker.helpers.ConstantsCustomGallery;
import charles.com.milu.utils.awesomeimagepicker.models.Image;
import charles.com.milu.utils.logger.Log;
import co.lujun.androidtagview.TagContainerLayout;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends BaseFragment {
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 12005;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 12006;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 12007;
    public static final int MIC_PERMISSION_REQUEST_CODE = 12008;
    final String TAG = getClass().getName();

    public static final int REQUEST_TAKE_PICTURE = 10001;
    public static final int REQUEST_PICK_GALLERY = 10002;

    private static final String HOUR = "hour";
    private static final String MINUTE = "minute";


    @BindView(R.id.blurred_background_image_view)
    BlurImageView blurImageView;

    @BindView(R.id.add_header_photo_button)
    CustomClickTextView btnAddHeaderPhoto;

    @BindView(R.id.imv_header_photo)
    ImageView imvEventHeaderPhoto;

    @BindView(R.id.imv_no_image)
    ImageView imvNoHeaderPhoto;

    @BindView(R.id.edt_title)
    EditText edtEventTitle;

    @BindView(R.id.edt_address)
    EditText edtEventAddress;

    @BindView(R.id.edt_ticket_url)
    EditText edtEventTicketTarget;

    @BindView(R.id.switch_all_day)
    SwitchCompat switchAllDay;

    @BindView(R.id.txt_start_time)
    TitleTextView txtEventStartTime;

    @BindView(R.id.txt_duration)
    TitleTextView txtEventDuration;

    @BindView(R.id.interest_tags_container)
    TagContainerLayout interest_tags_container;

    @BindView(R.id.switch_private)
    SwitchCompat switchPrivate;

    @BindView(R.id.switch_allow_guest_allow)
    SwitchCompat switchAllowGuest;

    @BindView(R.id.switch_mingle)
    SwitchCompat switchMingle;

    @BindView(R.id.switch_networking)
    SwitchCompat switchNetworking;

    @BindView(R.id.create_event_button)
    CustomClickTextView btnCreateEvent;

    @BindView(R.id.view_select_duration)
    NestedScrollView viewSelectDuration;

    @BindView(R.id.start_date_button)
    RelativeLayout btnStartDate;

    @BindView(R.id.end_date_button)
    RelativeLayout btnEndDate;

    @BindView(R.id.start_title)
    TitleTextView txtStartTitle;

    @BindView(R.id.end_title)
    TitleTextView txtEndTitle;

    @BindView(R.id.line_start)
    View lineStart;

    @BindView(R.id.line_end)
    View lineEnd;

    @BindView(R.id.calendarView)
    MaterialCalendarView calendarView;

    @BindView(R.id.timepicker)
    TimePicker timePicker;

//    @BindView(R.id.ampm)
//    NumberPicker pickerAMPM;

    @BindView(R.id.cancel_button)
    CustomClickTextView cancelButton;

    @BindView(R.id.done_button)
    CustomClickTextView doneButton;


    public static CreateEventFragment getInstance() {
        // Required empty public constructor
        CreateEventFragment fragment = new CreateEventFragment();
        return fragment;
    }

    @Override
    protected int addView() {
        return R.layout.fragment_create_event;
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar();
        setButtons();
    }

    @Override
    public void setToolBar(){

        assert txtLeft != null;
        txtLeft.setText("create event");

        assert btnMenu != null;
        btnMenu.setImageResource(R.drawable.nav_bar_back_icon);
        btnMenu.setVisibility(View.VISIBLE);
        btnMenu.setOnClickListener(this);

        assert  rightButton2 != null;
        rightButton2.setImageResource(R.drawable.nav_bar_check_mark_icon);
        rightButton2.setVisibility(View.VISIBLE);
        rightButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:
                mAct.onBackPressed();
                break;
            case R.id.toolbar_btn_right2:
                mAct.onBackPressed();
                break;
            case R.id.add_header_photo_button:
                functionPickFromGalleryIntent();
                break;
            case R.id.txt_duration:
                functionSelectDuration();
                break;
            case R.id.cancel_button:
                hideSelectDuration();
                break;
            case R.id.done_button:
                hideSelectDuration();
                break;
            case R.id.start_date_button:
                changeTab(0);
                break;
            case R.id.end_date_button:
                changeTab(1);
                break;
        }
    }

    public void changeTab(int index) {
        if (index == 0) {
            txtStartTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
            txtEndTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.color_DimGray));
            lineStart.setVisibility(View.VISIBLE);
            lineEnd.setVisibility(View.GONE);
        }else{
            txtEndTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
            txtStartTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.color_DimGray));
            lineEnd.setVisibility(View.VISIBLE);
            lineStart.setVisibility(View.GONE);
        }
    }

    public void setButtons() {

        btnAddHeaderPhoto.setOnClickListener(this);
        btnCreateEvent.setOnClickListener(this);
        txtEventDuration.setOnClickListener(this);

        cancelButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
        btnStartDate.setOnClickListener(this);
        btnEndDate.setOnClickListener(this);

    }

    public void functionSelectDuration(){
        viewSelectDuration.setVisibility(View.VISIBLE);
    }

    public void hideSelectDuration() {
        viewSelectDuration.setVisibility(View.GONE);
    }

    private void functionPickFromGalleryIntent() {
        Log.e("gallery", "Start Gallery");

        Intent intent = new Intent(mAct, AlbumSelectActivity.class);
        intent.putExtra("limit", 1); // set limit for image selection
        startActivityForResult(intent, REQUEST_PICK_GALLERY);
    }

    private void functionTakePictureIntent() {

//        new MaterialCamera(this)
//                .stillShot()
//                .primaryColor(0x333333)
//                .start(REQUEST_TAKE_PICTURE);

    }

    // permissions
    void functionRequestPermissionsForCamera(){
        if(!checkPermissionForReadStorage()) {
            requestPermissionForReadStorage();
        }
        else
        {
            if (!checkPermissionForCamera()) {
                requestPermissionForCamera();
            } else {
                if (!checkPermissionForExternalStorage()) {
                    requestPermissionForExternalStorage();
                } else {

                }
            }
        }
    }

    public boolean checkPermissionForExternalStorage(){
        int result = ContextCompat.checkSelfPermission(mAct, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForCamera(){
        int result = ContextCompat.checkSelfPermission(mAct, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForReadStorage(){
        int result = ContextCompat.checkSelfPermission(mAct, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public void requestPermissionForReadStorage()
    {
        ActivityCompat.requestPermissions(mAct, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
    }

    public void requestPermissionForExternalStorage(){
        ActivityCompat.requestPermissions(mAct,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
    }

    public void requestPermissionForCamera(){
        ActivityCompat.requestPermissions(mAct,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                functionRequestPermissionsForCamera();
            } else {
                Utilities.showAlertDialog(mAct, "","You denied read storage permission, and cannot read photo file.", "Ok", "", null, null, true);
            }
        }else if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                functionTakePictureIntent();
            } else {
                Utilities.showAlertDialog(mAct, "","You denied access camera permission, and cannot take a photo.", "Ok", "", null, null, true);
            }
        }else if(requestCode == EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                functionTakePictureIntent();

            } else {
                Utilities.showAlertDialog(mAct, "","You denied write storage permission, and cannot take a photo.", "Ok", "", null, null, true);
            }
        }else if(requestCode == MIC_PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //functionOpenAudioFragment();
//                mRequest = REQUEST_AUDIO_GRANTED;

            } else {
                Utilities.showAlertDialog(mAct, "","You denied access microphone permission, and cannot record voice.", "Ok", "", null, null, true);
            }
        }else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mAct, "You may now place a call", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mAct, "This application needs permission to use your microphone to function properly.", Toast
                    .LENGTH_LONG).show();
        }
    }
    private void callCropIntent(Uri imageUri) {

        Picasso.with(mAct).load(imageUri).into(imvEventHeaderPhoto);
        imvNoHeaderPhoto.setVisibility(View.GONE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_TAKE_PICTURE:
                if (resultCode == RESULT_OK) {
                    //callCropIntent(requestedAvatarUri);

                    String imgPath = data.getDataString();
                    Uri imgUri = Uri.parse(imgPath);
                    callCropIntent(imgUri);

                }
                break;
            case REQUEST_PICK_GALLERY:
                if (resultCode == RESULT_OK && data != null) {

                    ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);
                    Uri imgUri = null;
                    for(int i = 0; i < images.size(); i++){
                        imgUri = Uri.fromFile(new File(images.get(i).path));
                    }
                    if(imgUri != null) callCropIntent(imgUri);

                }
                break;
//            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
//                CropImage.ActivityResult result = CropImage.getActivityResult(data);
//                if (resultCode == RESULT_OK) {
//                    Uri avatarUri = result.getUri();
//                    mPhotoUri = avatarUri;
//
//                    Bitmap btmap = BitmapUtility.getBitmapFromFile(mPhotoUri, 800, 1000);
//                    BitmapUtility.saveToFile(btmap, mPhotoUri, 1500 * 1000);
//
//                    if(mPhotoUri != null){
//                        functionOpenPhotoFragment();
//                    }
//                }
//                break;
        }

    }


}
