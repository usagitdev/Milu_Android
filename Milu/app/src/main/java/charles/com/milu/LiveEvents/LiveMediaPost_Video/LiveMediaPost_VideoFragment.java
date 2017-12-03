package charles.com.milu.LiveEvents.LiveMediaPost_Video;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.CameraFragmentApi;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.listeners.CameraFragmentControlsAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;
import com.github.florent37.camerafragment.listeners.CameraFragmentStateListener;

import java.io.File;
import java.util.Timer;

import charles.com.milu.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMediaPost_VideoFragment extends Fragment {

    private static final int REQUEST_CAMERA_PERMISSIONS = 931;
    private static final int REQUEST_PREVIEW_CODE = 1001;
    public static final String FRAGMENT_TAG = "camera";
    ImageView videoRecordButton;
    ImageView videoSwitchButton;
    TextView videoTimer;
    Timer t;
    int secTime;
    int minTime;


   Chronometer mChronometer;


    public LiveMediaPost_VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_media_post_video, container, false);

        TextView videoTimer = (TextView) view.findViewById(R.id.liveMedia_Videotimer);
        mChronometer = (Chronometer) view.findViewById(R.id.liveMedia_Videotimer);

        return view;
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addCamera();

        setRecordButton(view);

        setCameraSwitchButton(view);

        t = new Timer();

    }

    public void setRecordButton(View view) {

        videoRecordButton = (ImageView) view.findViewById(R.id.LiveMedia_VideoRecordButton);
        videoRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.start();

                final CameraFragmentApi cameraFragment = getCameraFragment();
                if (cameraFragment != null) {
                    cameraFragment.takePhotoOrCaptureVideo(new CameraFragmentResultListener() {
                                                               @Override
                                                               public void onVideoRecorded(String filePath) {

                                                                   mChronometer.stop();
                                                                   Toast.makeText(getContext(), "onPhotoTaken " + filePath, Toast.LENGTH_SHORT).show();
                                                                   addCamera();
                                                                   mChronometer.refreshDrawableState();

                                                               }

                                                               @Override
                                                               public void onPhotoTaken(byte[] bytes, String filePath) {


                                                               }
                                                           },
                            "/storage/self/primary",
                            "photo0");
                }
            }
        });
    }

    public void setCameraSwitchButton(View view) {

        videoSwitchButton = (ImageView) view.findViewById(R.id.LiveMedia_VideoCameraSwitchButton);
        videoSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CameraFragmentApi cameraFragment = getCameraFragment();
                if (cameraFragment != null) {
                    cameraFragment.switchCameraTypeFrontBack();
                }
            }
        });
    }

    public void addCamera() {

        final Configuration.Builder builder = new Configuration.Builder();
        builder
                .setCamera(Configuration.CAMERA_FACE_REAR)
                .setFlashMode(Configuration.FLASH_MODE_AUTO)
                .setMediaAction(Configuration.MEDIA_ACTION_VIDEO);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        final CameraFragment cameraFragment = CameraFragment.newInstance(builder.build());
        getChildFragmentManager().beginTransaction()
                .replace(R.id.Videocontent, cameraFragment, FRAGMENT_TAG)
                .commit();



        if (cameraFragment != null) {
            cameraFragment.setResultListener(new CameraFragmentResultListener() {
                @Override
                public void onVideoRecorded(String filePath) {

                }

                @Override
                public void onPhotoTaken(byte[] bytes, String filePath) {

                }
            });

            cameraFragment.setStateListener(new CameraFragmentStateListener() {

                @Override
                public void onCurrentCameraBack() {

                }

                @Override
                public void onCurrentCameraFront() {

                }

                @Override
                public void onFlashAuto() {

                }

                @Override
                public void onFlashOn() {
                }

                @Override
                public void onFlashOff() {
                }

                @Override
                public void onCameraSetupForPhoto() {

                }

                @Override
                public void onCameraSetupForVideo() {

                }

                @Override
                public void shouldRotateControls(int degrees) {

                }

                @Override
                public void onRecordStateVideoReadyForRecord() {

                }

                @Override
                public void onRecordStateVideoInProgress() {

                }

                @Override
                public void onRecordStatePhoto() {

                }

                @Override
                public void onStopVideoRecord() {

                }

                @Override
                public void onStartVideoRecord(File outputFile) {

                }
            });

            cameraFragment.setControlsListener(new CameraFragmentControlsAdapter() {
                @Override
                public void lockControls() {

                    videoRecordButton.setEnabled(false);

                }

                @Override
                public void unLockControls() {

                    videoRecordButton.setEnabled(true);

                }

                @Override
                public void allowCameraSwitching(boolean allow) {

                }

                @Override
                public void allowRecord(boolean allow) {
                    videoRecordButton.setEnabled(allow);
                }

                @Override
                public void setMediaActionSwitchVisible(boolean visible) {

                }
            });


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length != 0) {
//            addCamera();
        }
    }

    private CameraFragmentApi getCameraFragment() {
        return (CameraFragmentApi) getChildFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }
}
