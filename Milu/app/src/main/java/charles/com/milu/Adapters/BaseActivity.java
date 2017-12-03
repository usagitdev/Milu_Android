package charles.com.milu.Adapters;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;
import charles.com.milu.utils.CustomImageButton;
import charles.com.milu.utils.Utilities;

/**
 * Hai Nguyen - 8/27/15.
 */
public class BaseActivity extends AppCompatActivity
		implements
			View.OnClickListener {

	private Handler mHandler;
	private Runnable mRunnable;
	private View[] mViews = null;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

////		getWindow().setSoftInputMode(
////				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
////		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
////		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//		if (makeTranslucent) {
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//		} else {
//			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//		}
//// create our manager instance after the content view is set
//		SystemBarTintManager tintManager = new SystemBarTintManager(this);
//// enable status bar tint
//		tintManager.setStatusBarTintEnabled(true);
//// enable navigation bar tint
//		tintManager.setNavigationBarTintEnabled(true);
//// set the transparent color of the status bar, 20% darker
//		tintManager.setTintColor(Color.parseColor("#20000000"));

		//Firebase Log Event
		// UI
		int layoutId = addView();
		setContentView(layoutId);
		ButterKnife.bind(this);
//		CustomPreferences.init(this);
		initView(savedInstanceState);
		getSupportFragmentManager().addOnBackStackChangedListener(
				new FragmentManager.OnBackStackChangedListener() {
					public void onBackStackChanged() {

						FragmentManager manager = getSupportFragmentManager();
						if (manager != null) {

							BaseFragment fragment = (BaseFragment) manager
									.findFragmentById(R.id.activity_container);
							fragment.setToolBar();
						}
					}
				});
	}
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		setStatusBar();
	}

	protected void setStatusBar() {
		StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}
	/**
	 * Add layout view for activity
	 *
	 * @return Layout view id
	 */
	protected int addView() {

		return 0;
	}

	protected void initView(@Nullable Bundle savedInstanceState) {

		mHandler = new Handler();
		mRunnable = new Runnable() {
			@Override
			public void run() {

				if (mViews != null) {

					for (View view : mViews) {

						view.setVisibility(View.VISIBLE);
					}
				}

			}
		};
	}

	@Override
	public void onClick(View view) {

	}

	/**
	 * add fragment
	 *
	 * @param fragment
	 *            Fragment
	 * @param isAddToBackStack
	 *            Add fragment to back stack or not
	 */
	public void addFragment(BaseFragment fragment, boolean isAddToBackStack) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		BaseFragment currentFragment = (BaseFragment) getSupportFragmentManager()
				.findFragmentById(R.id.activity_container);
		ft.add(R.id.activity_container, fragment, fragment.getClass().getName());
		if (isAddToBackStack) {

			ft.addToBackStack(null);
		}

		if (currentFragment != null) {

			ft.hide(currentFragment);
		}

		View current = getCurrentFocus();
		if (current != null) {

			Utilities.dismissKeyboard(this, current);
		}

		ft.commit();
	}

	/**
	 * Back to previous fragment
	 */
	public void backFragment(boolean backToHome) {

		FragmentManager fm = getSupportFragmentManager();
		if (fm.getBackStackEntryCount() <= 0) {

			finish();
			return;
		}

		if (backToHome) {

			fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		} else {

			fm.popBackStack();
		}
	}

	/*
	 * Show error
	 */
	public void showError(String text, View... views) {

		mViews = views;

		if (mViews != null) {

			for (View view : mViews) {

				view.setVisibility(View.INVISIBLE);
			}
		}

		mHandler.postDelayed(mRunnable, 3500);
	}


	public FragmentRefreshListener getFragmentRefreshListener(){
		return fragmentRefreshListener;
	}
	public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener){
		this.fragmentRefreshListener = fragmentRefreshListener;
	}
	private FragmentRefreshListener fragmentRefreshListener;
	public interface FragmentRefreshListener{
		void onRefresh();
	}
	public void FragmentRefresh(){
		if(getFragmentRefreshListener() != null){
			getFragmentRefreshListener().onRefresh();
		}
	}

}
