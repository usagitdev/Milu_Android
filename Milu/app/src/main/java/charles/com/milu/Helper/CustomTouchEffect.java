package charles.com.milu.Helper;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Chris on 1/9/2017.
 */

public class CustomTouchEffect implements View.OnTouchListener{

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            view.setAlpha(0.7f);
        }
        else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL)
        {
            view.setAlpha(1f);
        }
        return false;
    }
}
