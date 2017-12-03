package charles.com.milu.MediaSelect_ResultFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaSelect_ResultFragment extends Fragment {


    public MediaSelect_ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_media_select_result, container, false);
    }
}
