package charles.com.milu.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by mac_dev on 10/19/17.
 */

public class BoldTitleTextView extends AppCompatTextView {
    public BoldTitleTextView(Context context) {
        super(context);
        init();
    }

    public BoldTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoldTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Bold.ttf");
        setTypeface(workSans_Light);
    }
}
