package charles.com.milu.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import charles.com.milu.Base.BaseFragment;
import charles.com.milu.HomeScreen.TabBar_chatting;
import charles.com.milu.HomeScreen.TabBar_livefeed;
import charles.com.milu.HomeScreen.TabBar_places;
import charles.com.milu.HomeScreen.TabBar_userProfile;
import charles.com.milu.HomeScreen.Tabbar_eventdash;

/**
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

	private String[] mTitles;
	SparseArray<BaseFragment> mFragments;

	/**
	 * Create pager adapter
	 *
	 */
	public ViewPagerAdapter(FragmentManager fm, String[] titles) {
		super(fm);
		this.mTitles = titles;
		this.mFragments = new SparseArray<>();
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {

			case 0 :

				return TabBar_livefeed.getInstance();
			case 1 :

				return Tabbar_eventdash.getInstance();
            case 2 :

                return TabBar_chatting.getInstance();
            case 3 :

                return TabBar_places.getInstance();
            case 4 :

                return TabBar_userProfile.getInstance();
			default :

				return TabBar_livefeed.getInstance();
		}
	}

	@Override
	public int getCount() {
		return mTitles.length;
	}

	@Override
	public CharSequence getPageTitle(final int position) {
		return mTitles[position];
	}

	/**
	 * On each Fragment instantiation we are saving the reference of that
	 * Fragment in a Map It will help us to retrieve the Fragment by position
	 *
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		BaseFragment fragment = (BaseFragment) super.instantiateItem(container,
				position);
		mFragments.put(position, fragment);
		return fragment;
	}

	/**
	 * Remove the saved reference from our Map on the Fragment destroy
	 *
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		mFragments.remove(position);
		super.destroyItem(container, position, object);
	}

	/**
	 * Get the Fragment by position
	 *
	 */
	public BaseFragment getRegisteredFragment(int position) {
		return mFragments.get(position);
	}
}
