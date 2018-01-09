package charles.com.milu.LiveEvents.LiveMediaPost_Audio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.tyorikan.voicerecordingvisualizer.RecordingSampler;
import com.tyorikan.voicerecordingvisualizer.VisualizerView;

import net.robinx.lib.blur.utils.BlurUtils;
import net.robinx.lib.blur.widget.BlurDrawable;

import charles.com.milu.R;
import charles.com.milu.utils.awesomeimagepicker.models.Image;
import charles.com.milu.utils.logger.Log;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMediaPost_AudioFragment extends Fragment implements RecordingSampler.CalculateVolumeListener {

    private RelativeLayout mBlurDrawableRelativeLayout;

    public LiveMediaPost_AudioFragment() {
        // Required empty public constructor
    }
    ImageButton recordButton;
    VisualizerView visualizerView;
    RecordingSampler recordingSampler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_live_media_post_audio, container, false);
        visualizerView = (VisualizerView) view.findViewById(R.id.visualizer);
        recordButton = (ImageButton)view.findViewById(R.id.record_button);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recordingSampler.isRecording()){
                    recordingSampler.stopRecording();
                }else{
                    recordingSampler.startRecording();
                }

            }
        });

//        mBlurDrawableRelativeLayout = (RelativeLayout) view.findViewById(R.id.blur_drawable_container);
//        mBlurDrawableRelativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mBlurDrawableRelativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                BlurDrawable blurDrawable = new BlurDrawable(getActivity());
//                blurDrawable.setDrawOffset(mBlurDrawableRelativeLayout.getLeft(), mBlurDrawableRelativeLayout.getTop() + BlurUtils.getStatusBarHeight(getActivity()));
//                blurDrawable.setCornerRadius(1);
//                blurDrawable.setBlurRadius(20);
//                mBlurDrawableRelativeLayout.setBackgroundDrawable(blurDrawable);
//            }
//        });

        recordingSampler = new RecordingSampler();
        recordingSampler.setVolumeListener(this);  // for custom implements
        recordingSampler.setSamplingInterval(100); // voice sampling interval
        recordingSampler.link(visualizerView);     // link to visualizer

        return view;
    }

    @Override
    public void onPause() {
        if (recordingSampler != null)
            if (recordingSampler.isRecording()){
                recordingSampler.stopRecording();
            }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (recordingSampler != null)
            if (recordingSampler.isRecording())
                recordingSampler.release();
        super.onDestroy();
    }

    @Override
    public void onCalculateVolume(int volume) {
        Log.e("Volume", String.valueOf(volume));
    }
}
