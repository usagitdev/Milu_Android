package charles.com.milu.LiveEvents.LiveFeedMain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import charles.com.milu.LiveEvents.LiveFeedChatFragment.LiveFeedChatFragment;
import charles.com.milu.LiveEvents.LiveFeedMingleFragment.LiveFeedMingleFragment;
import charles.com.milu.LiveEvents.LiveFeedPostFragment.LiveFeedDispFragment;

/**
 * Created by dev on 9/24/17.
 */

public class LiveFeedPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public LiveFeedPageAdapter(FragmentManager fm){
        super(fm);

        fragments.add(new LiveFeedDispFragment());
        fragments.add(new LiveFeedMingleFragment());
        fragments.add(new LiveFeedChatFragment());
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
