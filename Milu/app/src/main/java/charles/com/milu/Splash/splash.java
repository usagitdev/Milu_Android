package charles.com.milu.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;

import charles.com.milu.Login.RegistrationActivity;
import charles.com.milu.R;

public class splash extends AppCompatActivity {

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        setStatusBar();



        Thread myThread = new Thread() {

            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

    public void setStatusBar(){

//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.init();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mImmersionBar != null)
//            mImmersionBar.destroy();
    }
}
