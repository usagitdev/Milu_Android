package charles.com.milu.Login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dev on 9/18/17.
 */

public class LoginViewPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public LoginViewPageAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new Registration1_Fragment());
        fragments.add(new Registration2_Fragment());
        fragments.add(new Registration3_Fragment());
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
