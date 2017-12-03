package charles.com.milu.LiveEvents.LiveMediaPost;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import charles.com.milu.LiveEvents.LiveMediaPost_Audio.LiveMediaPost_AudioFragment;
import charles.com.milu.LiveEvents.LiveMediaPost_Critique.LiveMediaPost_CritiqueFragment;
import charles.com.milu.LiveEvents.LiveMediaPost_Photo.LiveMediaPost_PhotoFragment;
import charles.com.milu.LiveEvents.LiveMediaPost_Rating.LiveMediaPost_RatingFragment;
import charles.com.milu.LiveEvents.LiveMediaPost_Video.LiveMediaPost_VideoFragment;

/**
 * Created by dev on 9/27/17.
 */

public class LiveMediaPostAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public LiveMediaPostAdapter(FragmentManager fm){
        super(fm);

        fragments.add(new LiveMediaPost_PhotoFragment());
        fragments.add(new LiveMediaPost_AudioFragment());
        fragments.add(new LiveMediaPost_CritiqueFragment());
        fragments.add(new LiveMediaPost_RatingFragment());
        fragments.add(new LiveMediaPost_VideoFragment());

    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}