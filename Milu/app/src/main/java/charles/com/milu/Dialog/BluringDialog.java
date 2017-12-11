package charles.com.milu.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ms_square.etsyblur.BlurConfig;
import com.ms_square.etsyblur.BlurDialogFragment;

import charles.com.milu.CustomViews.TitleTextView;
import charles.com.milu.Login.RegistrationActivity;
import charles.com.milu.MiluApplication;
import charles.com.milu.R;
import charles.com.milu.utils.SmartAsyncPolicyHolder;

/**
 * Created by mac_dev on 10/23/17.
 */

public class BluringDialog extends BlurDialogFragment implements
        android.view.View.OnClickListener{
    public static BluringDialog newInstance() {
        BluringDialog fragment = new BluringDialog();
        fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.BlurDialogTheme);
        return fragment;
    }
    public CardView yes;
    public ImageButton logoutBtn;
    public String message;

    public TitleTextView messageView;
    public void setMessage(String message) {
        this.message = message;
    }
    // implement either onCreateView or onCreateDialog
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        messageView = (TitleTextView)v.findViewById(R.id.textView);
        yes = (CardView) v.findViewById(R.id.cdv_fb_button);
        logoutBtn = (ImageButton)v.findViewById(R.id.imv_logout);
        yes.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        messageView.setText(message);
        return v;
    }

    @NonNull
    protected BlurConfig blurConfig() {
        return new BlurConfig.Builder()
                .overlayColor(Color.argb(136, 0, 0, 0))  // semi-transparent white color
                .asyncPolicy(SmartAsyncPolicyHolder.INSTANCE.smartAsyncPolicy())
                .debug(true)
                .radius(18)
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cdv_fb_button:
                ((MiluApplication)getActivity().getApplication()).appInfo.saveLogin(true);
                dismiss();
                break;
            case R.id.imv_logout:
                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
            default:
                break;
        }

    }
}
