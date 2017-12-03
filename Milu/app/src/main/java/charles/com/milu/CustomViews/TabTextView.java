package charles.com.milu.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by mac_dev on 10/19/17.
 */

public class TabTextView extends AppCompatTextView {
    public TabTextView(Context context) {
        super(context);
        init();
    }

    public TabTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-bold.ttf");
        setTypeface(workSans_Light);
    }
}
