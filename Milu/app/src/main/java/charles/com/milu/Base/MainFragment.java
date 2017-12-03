package charles.com.milu.Base;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import charles.com.milu.Adapters.ViewPagerAdapter;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;
import charles.com.milu.utils.DisablePagingViewPager;
import charles.com.milu.utils.TodayTextDrawable;

/**
 * Hai Nguyen - 7/30/16.
 */
public class MainFragment extends BaseFragment {

	@BindView(R.id.main_view_pager)
	DisablePagingViewPager viewPager;

	@BindView(R.id.main_view_tab_layout)
	TabLayout tabLayout;

	TextView todayTextView;

	private ViewPagerAdapter mAdapter;

	public static MainFragment getInstance() {

		return new MainFragment();
	}

	@Override
	protected int addView() {
		return R.layout.fragment_main;
	}

	@SuppressLint("DefaultLocale")
	@Override
	protected void initView() {
		super.initView();

		String[] titles = getResources().getStringArray(R.array.tab_titles);
		TypedArray tabDrawables = getResources().obtainTypedArray(
				R.array.tab_drawables);
		mAdapter = new ViewPagerAdapter(getChildFragmentManager(), titles);
		viewPager.setAdapter(mAdapter);
		tabLayout.setupWithViewPager(viewPager);
		viewPager.setOffscreenPageLimit(5);

		// Set tab view
		LayoutInflater inflater = LayoutInflater.from(mAct);
		for (int i = 0; i < tabLayout.getTabCount(); i++) {

			@SuppressLint("InflateParams")
			View view = inflater.inflate(R.layout.item_tab, null);
			ImageView imageView = ButterKnife.findById(view, R.id.item_tab_imv);
			TitleTextView textView = ButterKnife.findById(view, R.id.item_tab_text);
			if (i == 1) {
				Calendar calendar = Calendar.getInstance();
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				textView.setText(String.format("%d", day));
				todayTextView = textView;

			}else{
				textView.setText("");

			}
			imageView.setImageResource(tabDrawables.getResourceId(i, R.mipmap.ic_launcher));
			TabLayout.Tab tab = tabLayout.getTabAt(i);
			if (tab != null) {

				tab.setCustomView(view);
			}
		}

		tabDrawables.recycle();
		tabLayout
				.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
					@Override
					public void onTabSelected(TabLayout.Tab tab) {
						if (tab.getPosition() == 0) {

							BaseFragment fragment = mAdapter
									.getRegisteredFragment(0);
							fragment.onResume();
							fragment.backChildFragment();
						}
						if (tab.getPosition() == 1) {
							Calendar calendar = Calendar.getInstance();
							int day = calendar.get(Calendar.DAY_OF_MONTH);
							if (todayTextView != null){
								todayTextView.setText(String.format("%d", day));
								todayTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.color_Onyx));
							}
						}
					}

					@Override
					public void onTabUnselected(TabLayout.Tab tab) {
						if (tab.getPosition() == 1) {
							Calendar calendar = Calendar.getInstance();
							int day = calendar.get(Calendar.DAY_OF_MONTH);
							if (todayTextView != null) {
								todayTextView.setText(String.format("%d", day));
								todayTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.color_Milumain));
							}
						}

					}

					@Override
					public void onTabReselected(TabLayout.Tab tab) {

						if (tab.getPosition() == 0) {

							BaseFragment fragment = mAdapter
									.getRegisteredFragment(0);
							fragment.onResume();
							fragment.backChildFragment();
						}
					}
				});
	}

	@Override
	public boolean onBackPressed() {

		OnBackPressListener fragment = mAdapter.getRegisteredFragment(viewPager
				.getCurrentItem());
		return fragment != null && fragment.onBackPressed();
	}
}
