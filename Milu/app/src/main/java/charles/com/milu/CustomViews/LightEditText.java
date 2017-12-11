package charles.com.milu.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by mac_dev on 10/19/17.
 */

public class LightEditText extends AppCompatEditText {
    public LightEditText(Context context) {
        super(context);
        init();
    }

    public LightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface workSans_Light = Typeface.createFromAsset(getContext().getAssets(),"WorkSans-Light.ttf");
        setTypeface(workSans_Light);
    }
}
