package usa.ten.game.tenusa.status.powerup_item;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import usa.ten.game.tenusa.App;
import usa.ten.game.tenusa.R;

/**
 * Created by goya on 15/11/03.
 */
public class PowerUpItemManager
{
    private static PowerUpItemManager instance = new PowerUpItemManager();

    private List<PowerUpItem> mPowerUpItemList = new ArrayList<>();

    private PowerUpItemManager()
    {
        loadPowerUpItem();
    }
    public static PowerUpItemManager getInstance()
    {
        return (instance);
    }

    private void addPowerUpItem(PowerUpItem item)
    {
        mPowerUpItemList.add(item);
    }

    public PowerUpItem getPowerUpItem(int itemId)
    {
        for (PowerUpItem item : mPowerUpItemList){
            if (item.getItemId() == itemId){
                return (item);
            }
        }

        return (null);
    }

    public List<PowerUpItem> getPowerUpItemList() {
        return mPowerUpItemList;
    }

    private void loadPowerUpItem()
    {
        Bitmap img;
        img = BitmapFactory.decodeResource(App.getInstance().getResources(), R.mipmap.ic_launcher);
        addPowerUpItem(new PowerUpItem(0, 10, "hinokinobou", 3, 3, img));
        addPowerUpItem(new PowerUpItem(1, 30, "konbou", 5, 5, img));
        addPowerUpItem(new PowerUpItem(2, 50, "tetsunoken", 7, 7, img));
        addPowerUpItem(new PowerUpItem(3, 70, "katana", 10, 10, img));
    }
}
