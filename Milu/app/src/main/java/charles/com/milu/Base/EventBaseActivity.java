package charles.com.milu.Base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import charles.com.milu.Helper.CustomTouchEffect;
import charles.com.milu.Helper.LogHelper;
import ir.mirrajabi.viewfilter.core.ViewFilter;
import ir.mirrajabi.viewfilter.renderers.BlurRenderer;

/**
 * Created by mac_dev on 10/19/17.
 */

public class EventBaseActivity extends AppCompatActivity implements View.OnClickListener{
    String TAG = getClass().getName();
    public static final CustomTouchEffect TOUCH = new CustomTouchEffect();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppSettingTheme();
        //setTheme(R.style.ThemeOrange);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //setContentView(R.layout.activity_base);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

    }

    @Override
    protected void onStart(){
        super.onStart();
        LogHelper.log(TAG, "onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        LogHelper.log(TAG, "onResuem()");

    }

    @Override
    protected void onPause(){
        super.onPause();
        LogHelper.log(TAG, "onPause()");

    }

    @Override
    protected void onStop(){
        super.onStop();
        LogHelper.log(TAG, "onStop()");

    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        LogHelper.log(TAG, "startActivityForResult " + requestCode);

        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogHelper.log(TAG, "onActivityResult" +requestCode);

        super.onActivityResult(requestCode, resultCode, data);
    }

    void setAppSettingTheme(){
    }

    @Override
    public void onClick(View view) {

    }

    public View setClickListner(int resId)
    {
        View v = findViewById(resId);
        v.setOnClickListener(this);
        return v;
    }

    public View setTouchEffect(int resId)
    {
        View v = setClickListner(resId);
        v.setOnTouchListener(TOUCH);
        return v;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public void setBlurTitleBar(View view, View rootView) {
        ViewFilter.getInstance(this)
                .setRenderer(new BlurRenderer(16))
                .applyFilterOnView(view, rootView);
    }

}
