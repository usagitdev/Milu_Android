package charles.com.milu.LiveEvents.LiveMediaPost_Rating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.xl.ratingbar.MyRatingBar;

import charles.com.milu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMediaPost_RatingFragment extends Fragment implements MyRatingBar.OnRatingChangeListener{


    MyRatingBar ratingBar;
    EditText liveMediaPost_RatingTxt;
    public LiveMediaPost_RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_live_media_post_rating, container, false);
        liveMediaPost_RatingTxt = (EditText)view.findViewById(R.id.liveMediaPost_RatingTxt);
        ratingBar = (MyRatingBar) view.findViewById(R.id.liveMediaPost_Myratingbar);
        ratingBar.setOnRatingChangeListener(this);

        return view;
    }

    @Override
    public void onRatingChange(MyRatingBar bar, float RatingCount) {

        Toast.makeText(getContext()," RatingBar Changed" + String.valueOf(RatingCount), Toast.LENGTH_SHORT).show();

    }
}
