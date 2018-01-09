package charles.com.milu.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import charles.com.milu.R;

/**
 * Created by mac_dev on 10/23/17.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public CardView yes;
    public ImageButton logoutBtn;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_dialog);
        yes = (CardView) findViewById(R.id.cdv_fb_button);
        logoutBtn = (ImageButton)findViewById(R.id.imv_logout);
        yes.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cdv_fb_button:
                break;
            case R.id.imv_logout:
                break;
            default:
                break;
        }
        dismiss();
    }
}
