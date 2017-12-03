package charles.com.milu.MediaSelect_Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import charles.com.milu.MediaSelect_Fragment.MediaSelect_Fragment;
import charles.com.milu.MediaSelect_ResultFragment.MediaSelect_ResultFragment;

/**
 * Created by dev on 9/28/17.
 */

public class MediaSelectAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public MediaSelectAdapter(FragmentManager fm, MediaSelect_Fragment ms_fragment, MediaSelect_ResultFragment mr_fragment){
        super(fm);

        fragments.add(ms_fragment);
        fragments.add(mr_fragment);

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
