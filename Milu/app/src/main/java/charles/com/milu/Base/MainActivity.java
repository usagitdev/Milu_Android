package charles.com.milu.Base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.jaeger.library.StatusBarUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import charles.com.milu.Adapters.BaseActivity;
import charles.com.milu.R;
import charles.com.milu.utils.Utilities;

/**
 * Hai Nguyen - 11/2/15.
 */
public class MainActivity extends BaseActivity implements OnItemClickListener {


	@Override
	protected int addView() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
//		setStatusBarTranslucent(true);

		// Set current user
		if (savedInstanceState == null) {

			addFragment(MainFragment.getInstance(), false);
		}

		printKeyHash(this);
//		viewBottom.setVisibility(View.INVISIBLE);
	}
	@Override
	protected void setStatusBar() {
		StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {

		}
	}
	public static String printKeyHash(Activity context) {
		PackageInfo packageInfo;
		String key = null;
		try {
			//getting application package name, as defined in manifest
			String packageName = context.getApplicationContext().getPackageName();

			//Retriving package info
			packageInfo = context.getPackageManager().getPackageInfo(packageName,
					PackageManager.GET_SIGNATURES);

			Log.d("Package Name=", context.getApplicationContext().getPackageName());

			for (Signature signature : packageInfo.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				key = new String(Base64.encode(md.digest(), 0));

				// String key = new String(Base64.encodeBytes(md.digest()));
				Log.d("Key Hash=", key);
			}
		} catch (PackageManager.NameNotFoundException e1) {
			Log.e("Name not found", e1.toString());
		}
		catch (NoSuchAlgorithmException e) {
			Log.e("No such an algorithm", e.toString());
		} catch (Exception e) {
			Log.e("Exception", e.toString());
		}

		return key;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Toggle drawer
	 */
	@Override
	public void onItemClick(Object object, int pos) {

	}

	@Override
	public void onItemClick(Object object, int pos, boolean bObject) {

	}

	@Override
	public void onBackPressed() {

		BaseFragment fragment = (BaseFragment) getSupportFragmentManager()
				.findFragmentById(R.id.activity_container);
		if (fragment == null || !fragment.onBackPressed()) {

			super.onBackPressed();
		}
	}
	protected void setStatusBarTranslucent(boolean makeTranslucent) {
//		StatusBarUtil.setTranslucentForDrawerLayout(MainActivity.this, drawer, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);

//		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
//
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
	}
	public interface IOnFocusListenable {
		public void onWindowFocusChanged(boolean hasfocus);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}

}
