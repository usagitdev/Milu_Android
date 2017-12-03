package charles.com.milu.LiveEvents.LiveMediaPost_Audio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import net.robinx.lib.blur.utils.BlurUtils;
import net.robinx.lib.blur.widget.BlurDrawable;

import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMediaPost_AudioFragment extends Fragment {

    private RelativeLayout mBlurDrawableRelativeLayout;

    public LiveMediaPost_AudioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_live_media_post_audio, container, false);


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

        return view;
    }
}
