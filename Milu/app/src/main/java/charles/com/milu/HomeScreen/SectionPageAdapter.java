package charles.com.milu.HomeScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import charles.com.milu.Base.BaseFragment;

/**
 * Created by dev on 9/19/17.
 */

public class SectionPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new TabBar_livefeed());
        fragments.add(new BaseFragment());
        fragments.add(new TabBar_chatting());
        fragments.add(new TabBar_places());
        fragments.add(new TabBar_userProfile());
//        fragments.add(new ImageGridFragment());
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
