package charles.com.milu.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by mac_dev on 10/19/17.
 */

public class RegularTitleTextView extends AppCompatTextView {
    public RegularTitleTextView(Context context) {
        super(context);
        init();
    }

    public RegularTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegularTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Regular.ttf");
        setTypeface(workSans_Light);
    }
}
