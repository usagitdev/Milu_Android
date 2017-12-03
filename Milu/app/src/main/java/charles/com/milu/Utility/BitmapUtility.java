package charles.com.milu.Utility;

/**
 * Created by PIG18 on 1/25/2017.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import charles.com.milu.R;

public class BitmapUtility {
    public static final int AVATAR_WIDTH = 128;
    public static final int AVATAR_HEIGHT = 128;
    public static final int ID_WIDTH = 480;
    public static final int ID_HEIGHT = 640;

    public static final int PHOTO_WIDTH = 1000;
    public static final int PHOTO_HEIGHT = 1000;


    public static final String TAG = BitmapUtility.class.getName();

    static Drawable DEFAULT_AVATAR;

    public static Drawable getDefaultAvatar(Context c) {
        if (DEFAULT_AVATAR == null) {
            Resources r = c.getResources();
            Bitmap source = BitmapFactory.decodeResource(r, R.drawable.ic_avatar_0);
            Bitmap circular = getCircularBitmap(source);
            DEFAULT_AVATAR = new BitmapDrawable(r, circular);
            source.recycle();
        }
        return DEFAULT_AVATAR;
    }

    public static Bitmap getBitmap(int resId, Resources r, int reqWidth, int reqHeight) {
        BitmapFactory.Options boundsOptions = new BitmapFactory.Options();
        boundsOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(r, resId, boundsOptions);

        BitmapFactory.Options newOptions = new BitmapFactory.Options();
        newOptions.inSampleSize = calculateInSampleSize(boundsOptions, reqWidth, reqHeight);
        return BitmapFactory.decodeResource(r, resId, newOptions);
    }


    public static Bitmap getBitmapFromFile(Uri uri, int reqWidth, int reqHeight) {
        BitmapFactory.Options boundsOptions = new BitmapFactory.Options();
        boundsOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(uri.getPath(), boundsOptions);

        BitmapFactory.Options newOptions = new BitmapFactory.Options();
        newOptions.inSampleSize = calculateInSampleSize(boundsOptions, reqWidth, reqHeight);
        return BitmapFactory.decodeFile(uri.getPath(), newOptions);
    }

    public static Bitmap getPhotoTaken(Uri uri) {
        return getBitmapFromFile(uri, AVATAR_WIDTH, AVATAR_HEIGHT);
    }

    public static Bitmap getBitmapFromUri(Uri uri) {
        return getBitmapFromFile(uri, PHOTO_WIDTH, PHOTO_HEIGHT);
    }

    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = Math.max(height / reqHeight, width / reqWidth);
        int inWidthSampleSize = width / reqWidth;
        int inHeightSampleSize = height / reqHeight;
        if (inWidthSampleSize * reqWidth < width) {
            inWidthSampleSize++;
        }
        if (inHeightSampleSize * reqHeight < height) {
            inHeightSampleSize++;
        }

        return Math.max(inWidthSampleSize, inHeightSampleSize);
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    public static String createString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return "data:image/jpeg:base64," + Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);
    }

    public static void cropBitmap(File idFile) {
        Bitmap idBitmap = getBitmapFromFile(Uri.fromFile(idFile), 640, 960);
        int idWidth = idBitmap.getWidth();
        int idHeight = idBitmap.getHeight();

        int cropWidth = idWidth * 7 / 8;
        int cropHeight = idHeight * 4 / 12;
        int cropX = idWidth / 16;
        int cropY = idHeight * 3 / 12;
        float ratio = Math.min(640.0f / cropWidth, 410.0f / cropHeight);
        Rect srcRect = new Rect(cropX, cropY, cropX + cropWidth, cropY + cropHeight);
        Rect dstRect = new Rect(0, 0, Math.round(ratio * cropWidth), Math.round(ratio * cropHeight));

        Bitmap cropBitmap = Bitmap.createBitmap(dstRect.width(), dstRect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(cropBitmap);

        Log.d(TAG, String.format("%d, %d, %d, %d", srcRect.left, srcRect.top, srcRect.width(), srcRect.height()));
        Log.d(TAG, String.format("%d, %d, %d, %d", dstRect.left, dstRect.top, dstRect.width(), dstRect.height()));

        canvas.drawBitmap(idBitmap, srcRect, dstRect, null);
        try {
            FileOutputStream fos = new FileOutputStream(idFile);
            cropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cropBitmap.recycle();
        idBitmap.recycle();
    }


    public static void saveToFile(Bitmap btmap, Uri uri, int max_size){
        int stream_length = max_size;
        int compressQ = 110;

        while (stream_length >= max_size) {
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            compressQ -= 10;
            Log.d(TAG, "Quality: " + compressQ);
            btmap.compress(Bitmap.CompressFormat.JPEG, compressQ, bmpStream);
            byte[] bmpPicByteArray = bmpStream.toByteArray();
            stream_length = bmpPicByteArray.length;
            Log.d(TAG, "Size: " + stream_length);
        }

        try {
            FileOutputStream bmpFile = new FileOutputStream(uri.getPath());
            btmap.compress(Bitmap.CompressFormat.JPEG, compressQ, bmpFile);
            bmpFile.flush();
            bmpFile.close();
        } catch (Exception e) {
            Log.e(TAG, "Error on saving file");
        }
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        if(width < maxSize && height < maxSize){
            return image;
        }

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


}

