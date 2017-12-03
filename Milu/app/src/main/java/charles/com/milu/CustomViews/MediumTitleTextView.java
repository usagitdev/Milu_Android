package charles.com.milu.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by mac_dev on 10/19/17.
 */

public class MediumTitleTextView extends AppCompatTextView {
    public MediumTitleTextView(Context context) {
        super(context);
        init();
    }

    public MediumTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MediumTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Medium.ttf");
        setTypeface(workSans_Light);
    }
}
