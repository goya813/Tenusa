package usa.ten.game.tenusa.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.reflect.Field;

import usa.ten.game.tenusa.App;
import usa.ten.game.tenusa.R;

/**
 * Created by goya on 15/11/11.
 */
public class UtilResource
{
    public static Bitmap loadBitmap(String imgName)
    {
        R.mipmap rDrawable = new R.mipmap();
        Field field;
        int resId = -1;

        try {
            field = rDrawable.getClass().getField(imgName);
            resId = field.getInt(rDrawable);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Bitmap img = BitmapFactory.decodeResource(App.getInstance().getResources(), resId);

        return (img);
    }
}
