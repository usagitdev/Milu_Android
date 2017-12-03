package charles.com.milu.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import charles.com.milu.R;


/**
 * Created by mac_dev on 10/19/17.
 */



public class ChatTabTitleTextView extends LinearLayout {
    boolean isSelected = false;
    TextView titleView;
    View underlineview;
    String title;
    public ChatTabTitleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.chat_tab_item, this, true);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.profile_tab_style, 0, 0);

        try {
            title = a.getString(R.styleable.profile_tab_style_title);
            isSelected = a.getBoolean(R.styleable.profile_tab_style_selected, false);
        } finally {
            a.recycle();
        }

        // Throw an exception if required attributes are not set
        if (title == null) {
            throw new RuntimeException("No title provided");
        }

        init(title, isSelected);
    }

    // Setup views
    private void init(String title, boolean isSelected) {
        titleView = (TextView) findViewById(R.id.button_title);
        underlineview = findViewById(R.id.underline);

        titleView.setText(title);
        setSelect(isSelected);
    }

    public void setSelect(boolean b){
        if (b){
            Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Light.ttf");
            titleView.setTypeface(workSans_Light);
            titleView.setAlpha(1.0f);
            titleView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
            underlineview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_Milumain));
        }else{
            Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-ExtraLight.ttf");
            titleView.setTypeface(workSans_Light);
            titleView.setAlpha(0.7f);
            titleView.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            underlineview.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
        }
    }

}
