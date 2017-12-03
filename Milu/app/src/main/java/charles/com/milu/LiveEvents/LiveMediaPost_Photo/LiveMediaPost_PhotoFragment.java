package charles.com.milu.LiveEvents.LiveMediaPost_Photo;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.CameraFragmentApi;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.listeners.CameraFragmentControlsAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;
import com.github.florent37.camerafragment.listeners.CameraFragmentStateListener;

import java.io.File;

import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMediaPost_PhotoFragment extends Fragment {


    private static final int REQUEST_CAMERA_PERMISSIONS = 931;
    private static final int REQUEST_PREVIEW_CODE = 1001;
    public static final String FRAGMENT_TAG = "camera";
    ImageView recordButton;
    ImageView switchButton;


    public LiveMediaPost_PhotoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_media_post_photo, container, false);

        return view;
    }

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addCamera();

        setRecordButton(view);

        setCameraSwitchButton(view);

    }

    public void setRecordButton(View view) {

        recordButton = (ImageView) view.findViewById(R.id.LiveMedia_PhotoRecordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CameraFragmentApi cameraFragment = getCameraFragment();
                if (cameraFragment != null) {
                    cameraFragment.takePhotoOrCaptureVideo(new CameraFragmentResultListener() {
                                                               @Override
                                                               public void onVideoRecorded(String filePath) {

                                                               }

                                                               @Override
                                                               public void onPhotoTaken(byte[] bytes, String filePath) {

                                                                   Toast.makeText(getContext(), "onPhotoTaken " + filePath, Toast.LENGTH_SHORT).show();
                                                                   addCamera();
                                                               }
                                                           },
                            "/storage/self/primary",
                            "photo0");
                }
            }
        });
    }

    public void setCameraSwitchButton(View view) {

        switchButton = (ImageView) view.findViewById(R.id.LiveMedia_CameraSwitchButton);
        switchButton.setOnClickListener(new View.OnClickListener() {
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
                .setFlashMode(Configuration.FLASH_MODE_ON)
                .setMediaAction(Configuration.MEDIA_ACTION_PHOTO);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final CameraFragment cameraFragment = CameraFragment.newInstance(builder.build());
        getChildFragmentManager().beginTransaction()
                .replace(R.id.content, cameraFragment, FRAGMENT_TAG)
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

                    recordButton.setEnabled(false);

                }

                @Override
                public void unLockControls() {

                    recordButton.setEnabled(true);

                }

                @Override
                public void allowCameraSwitching(boolean allow) {

                }

                @Override
                public void allowRecord(boolean allow) {
                    recordButton.setEnabled(allow);
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
            addCamera();
        }
    }

    private CameraFragmentApi getCameraFragment() {
        return (CameraFragmentApi) getChildFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }
}
