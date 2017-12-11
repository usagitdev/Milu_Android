package charles.com.milu.Base;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import charles.com.milu.Base.BaseFragment;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;
import charles.com.milu.Services.ConnectivityReceiver;
import charles.com.milu.Services.LocationService;
import charles.com.milu.utils.CustomImageButton;
import charles.com.milu.utils.Utilities;
import charles.com.milu.utils.logger.Log;

/**
 * Hai Nguyen - 8/27/15.
 */
public class BaseActivity extends AppCompatActivity
		implements
			View.OnClickListener, LocationService.LocationChangeListener, ConnectivityReceiver.ConnectivityReceiverListener {

	private Handler mHandler;
	private Runnable mRunnable;
	private View[] mViews = null;
	LocationChangeListener listener;
	ConnectivityReceiver connectivityReceiver;
	@Nullable
	@BindView(R.id.toolbar_btn_left)
	public
	CustomImageButton btnMenu;

	@Nullable
	@BindView(R.id.toolbar_txt_left)
	public
	TitleTextView txtLeft;

	@Nullable
	@BindView(R.id.toolbar_btn_right1)
	public
	CustomImageButton rightButton1;

	@Nullable
	@BindView(R.id.toolbar_btn_right2)
	public
	CustomImageButton rightButton2;

	@Nullable
	@BindView(R.id.fragment_container)
	public
	FrameLayout containerview;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

		connectivityReceiver = new ConnectivityReceiver();
		LocalBroadcastManager.getInstance(this).registerReceiver(connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
		LocalBroadcastManager.getInstance(this).registerReceiver(connectivityReceiver, new IntentFilter("android.location.PROVIDERS_CHANGED"));
		listener = new LocationChangeListener() {
			@Override
			public void onLocationChanged(Location location) {

			}
		};
		setToolBar();

	}
	/**
	 * Set toolbar
	 */
	@SuppressWarnings("ConstantConditions")
	public void setToolBar() {


		if (btnMenu != null) {

			btnMenu.setVisibility(View.VISIBLE);
			btnMenu.setImageResource(R.drawable.menu_button);
		}


	}


	public void startLocationService(){
		LocationService.getInstance().startLocationService(this);
		LocationService.getInstance().setLocationChangeListener(this);
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

	@Override
	public void onLocationChanged(Location location) {
		this.listener.onLocationChanged(location);
	}

	public interface FragmentRefreshListener{
		void onRefresh();
	}
	public void FragmentRefresh(){
		if(getFragmentRefreshListener() != null){
			getFragmentRefreshListener().onRefresh();
		}
	}


	@Override
	public void onGpsConnectionChanged(boolean isConnected){
		if (isConnected){
			Log.e("tag", "GPS enabled");
		}
	}

	@Override
	public void onNetworkConnectionChanged(boolean isConnected) {
		if (isConnected){
			Log.e("tag", "Network enabled");
		}
	}

	public interface LocationChangeListener{
		void onLocationChanged(Location location);
	}

	@Override protected void onResume(){
		super.onResume();
		setConnectivityListener(this);
//		startLocationService();
	}
	@Override protected void onStart(){
		super.onStart();

	}
	@Override protected void onPause(){
		super.onPause();
//		LocationService.getInstance().stopLocationService();

	}
	public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
		ConnectivityReceiver.connectivityReceiverListener = listener;
	}

//	private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
//		@Override
//		public void onGlobalLayout() {
//			int heightDiff = rootLayout.getRootView().getHeight() - rootLayout.getHeight();
//			int contentViewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
//
//			LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(BaseActivity.this);
//
//			if(heightDiff <= contentViewTop){
//				onHideKeyboard();
//
//				Intent intent = new Intent("KeyboardWillHide");
//				broadcastManager.sendBroadcast(intent);
//			} else {
//				int keyboardHeight = heightDiff - contentViewTop;
//				onShowKeyboard(keyboardHeight);
//
//				Intent intent = new Intent("KeyboardWillShow");
//				intent.putExtra("KeyboardHeight", keyboardHeight);
//				broadcastManager.sendBroadcast(intent);
//			}
//		}
//	};
//
//	private boolean keyboardListenersAttached = false;
//	private ViewGroup rootLayout;
//
//	protected void onShowKeyboard(int keyboardHeight) {}
//	protected void onHideKeyboard() {}
//
//	protected void attachKeyboardListeners() {
//		if (keyboardListenersAttached) {
//			return;
//		}
//
//		rootLayout = (ViewGroup) findViewById(R.id.fragment_container);
//		rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);
//
//		keyboardListenersAttached = true;
//	}
//
	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

}
