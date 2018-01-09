package charles.com.milu.Base;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import net.qiujuer.genius.blur.StackBlur;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import butterknife.BindView;
import butterknife.ButterKnife;
import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.R;
import charles.com.milu.utils.CustomImageButton;
import charles.com.milu.utils.Utilities;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment
        implements
        View.OnClickListener,
        OnItemClickListener,
        OnBackPressListener {
    protected TextView mText;
    protected TextView mTime;
    protected boolean isScale = true;
    protected Bitmap mBitmap;

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

    @Nullable
    @BindView(R.id.progressBar)
    public
    AVLoadingIndicatorView progressBar;

    protected BaseActivity mAct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int viewId = addView();
        View view = inflater.inflate(viewId, container, false);

        ButterKnife.bind(this, view);
        initView();
        setToolBar();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.dismissKeyboard(mAct, view);
            }
        });
        return view;
    }
    void startAnim(){
        assert progressBar != null;
        progressBar.show();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        assert progressBar != null;
        progressBar.hide();
        // or avi.smoothToHide();
    }

    /**
     * Add fragment
     */
    protected void addFragment(BaseFragment fragment, boolean addToStack) {

        mAct.addFragment(fragment, addToStack);
    }

    /**
     * Back fragment
     *
     * @param backToHome true if wanna back to home
     */
    protected void backFragment(boolean backToHome) {

        mAct.backFragment(backToHome);
    }

    /**
     * Add view
     */
    protected int addView() {

        return 0;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mAct = (BaseActivity) context;
    }

    /**
     * Init child view
     */
    protected void initView() {

        if (btnMenu != null) {

            btnMenu.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_btn_left:

                break;
        }
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

    @Override
    public void onItemClick(Object object, int pos) {

    }

    @Override
    public void onItemClick(Object object, int pos, boolean bObject) {

    }

    @Override
    public boolean onBackPressed() {

        return new BackPressImpl(this).onBackPressed();
    }

    /**
     * add fragment
     *
     * @param fragment         Fragment
     * @param isAddToBackStack Add fragment to back stack or not
     */
    public void addChildFragment(Fragment fragment, boolean isAddToBackStack) {

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        BaseFragment currentFragment = (BaseFragment) getChildFragmentManager()
                .findFragmentById(R.id.fragment_container);
        ft.add(R.id.fragment_container, fragment, fragment.getClass().getName());
        if (isAddToBackStack) {

            ft.addToBackStack(null);
        }

        if (currentFragment != null) {

            ft.hide(currentFragment);
        } else {
            ft.show(fragment);
        }

        ft.commit();
    }

    /**
     * add fragment
     *
     * @param fragment         Fragment
     * @param isAddToBackStack Add fragment to back stack or not
     */
    public void addChildFragment(BaseFragment fragment, boolean isAddToBackStack) {

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        BaseFragment currentFragment = (BaseFragment) getChildFragmentManager()
                .findFragmentById(R.id.fragment_container);
        ft.add(R.id.fragment_container, fragment, fragment.getClass().getName());
        if (isAddToBackStack) {

            ft.addToBackStack(null);
        }

        if (currentFragment != null) {

            ft.hide(currentFragment);
        } else {
            ft.show(fragment);
        }

        ft.commit();
    }

    public void addChildFragment(BaseFragment fragment, String backStack) {

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        BaseFragment currentFragment = (BaseFragment) getChildFragmentManager()
                .findFragmentById(R.id.fragment_container);
        ft.add(R.id.fragment_container, fragment, fragment.getClass().getName());
//		if (isAddToBackStack) {

        ft.addToBackStack(backStack);
//		}

        if (currentFragment != null) {

            ft.hide(currentFragment);
        }

        ft.commit();
    }

    public void backChildFragment() {

        FragmentManager fm = getChildFragmentManager();
        if (fm.getBackStackEntryCount() <= 0) {

            return;
        }

        fm.popBackStack();
    }

    public void backChildFragment(String backStack) {

        FragmentManager fm = getChildFragmentManager();
        if (fm.getBackStackEntryCount() <= 0) {

            return;
        }

        fm.popBackStack(backStack, 0);
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public void setBitmap(Bitmap bitmap, View view) {
//        View view = getView();
        if (view != null) {
            mBitmap = Bitmap.createBitmap(view.getWidth(),
                    view.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mBitmap);
            canvas.translate(-view.getLeft(), -view.getTop());
            canvas.scale(1, 1);
            Paint paint = new Paint();
            paint.setFlags(Paint.FILTER_BITMAP_FLAG);
            canvas.drawBitmap(bitmap, 0, 0, paint);

            // Call Blur
            blur(view);
        }
    }

    protected Bitmap getScaleBitmap(Bitmap bitmap) {
        float scaleFactor = 9;
        float scale = 1f / scaleFactor;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap ret = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return ret;
    }

    protected Bitmap getNewBitmap() {
        return mBitmap.copy(mBitmap.getConfig(), true);
    }

    protected void blur(final View view) {
        if (mBitmap == null || mText == null)
            return;
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getNewBitmap();
                int radius = 20;
                if (isScale) {
                    radius = 2;
                    bitmap = getScaleBitmap(bitmap);
                }

                long startTime = System.currentTimeMillis();
                final Bitmap ret = blur(bitmap, radius);
                show(ret, System.currentTimeMillis() - startTime, view);
            }
        };
        thread.start();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    protected void show(final Bitmap bitmap, final long time, final View view) {
        if (view != null) {
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    view.setBackground(new BitmapDrawable(getResources(), bitmap));
//                    show(time);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mText = null;
        if (mBitmap != null)
            mBitmap.recycle();
        mBitmap = null;
    }

//    @Override
//    public void onClick(View v) {
//        blur();
//        isScale = !isScale;
//    }

    protected void show(long time) {
        if (mTime != null)
            mTime.setText(time + "ms");
    }

    public Bitmap blur(Bitmap bitmap, int radius) {
        return StackBlur.blur(bitmap, radius, true);

    }
    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            int heightDiff = rootLayout.getRootView().getHeight() - rootLayout.getHeight();
            int contentViewTop = mAct.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();

            LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(mAct);

            if(heightDiff <= contentViewTop){
                onHideKeyboard();

                Intent intent = new Intent("KeyboardWillHide");
                broadcastManager.sendBroadcast(intent);
            } else {
                int keyboardHeight = heightDiff - contentViewTop;
                onShowKeyboard(keyboardHeight);

                Intent intent = new Intent("KeyboardWillShow");
                intent.putExtra("KeyboardHeight", keyboardHeight);
                broadcastManager.sendBroadcast(intent);
            }
        }
    };

    private boolean keyboardListenersAttached = false;
    private ViewGroup rootLayout;

    protected void onShowKeyboard(int keyboardHeight) {}
    protected void onHideKeyboard() {}

    protected void attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return;
        }

        rootLayout = (ViewGroup) containerview;
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

        keyboardListenersAttached = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (keyboardListenersAttached) {
            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(keyboardLayoutListener);
        }
    }


}