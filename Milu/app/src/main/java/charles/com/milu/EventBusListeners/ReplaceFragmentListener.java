package charles.com.milu.EventBusListeners;

import android.support.v4.app.Fragment;

/**
 * Created by dev on 9/22/17.
 */

public class ReplaceFragmentListener {

    public ReplaceFragmentListener (Fragment fragment, boolean action) {
        this.fragment = fragment;
        this.action = action;
    }

    private Fragment fragment;

    public boolean isAction() {
        return action;
    }

    private boolean action;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

}
