package charles.com.milu.Utility;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by PIG18 on 3/8/2017.
 */

public class SquareAvatarImageView extends ImageView {

    public SquareAvatarImageView(Context context) {
        super(context);
    }

    public SquareAvatarImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareAvatarImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
