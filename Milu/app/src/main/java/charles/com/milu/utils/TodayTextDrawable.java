package charles.com.milu.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by mac_dev on 11/21/17.
 */

public class TodayTextDrawable extends Drawable {

    private final String text;
    private final Paint paint;
    private final Bitmap bitmap;

    public TodayTextDrawable(String text, Bitmap bitmap) {

        this.text = text;
        this.bitmap = bitmap;

        this.paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(12f);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setShadowLayer(6f, 0, 0, Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void draw(Canvas canvas) {

//        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawText(text, 0, 0, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}