package usa.ten.game.tenusa.status.powerup_item;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usa.ten.game.tenusa.App;
import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.database.SqliteDAO;
import usa.ten.game.tenusa.util.UtilAssets;
import usa.ten.game.tenusa.util.UtilResource;

/**
 * Created by goya on 15/11/03.
 */
public class PowerUpItemManager
{
    private static final String CONF_FILE = "powerup_item.txt";

    private static PowerUpItemManager instance = new PowerUpItemManager();

    private SqliteDAO mSqliteDao;
    private List<PowerUpItem> mPowerUpItemList = new ArrayList<>();

    private PowerUpItemManager()
    {
        mSqliteDao = SqliteDAO.getInstance();
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

    public void soldItem(int itemId)
    {
        for (PowerUpItem item : mPowerUpItemList){
            if (item.getItemId() == itemId){
                item.sold();
            }
        }
    }

    public void save()
    {
        List<PowerUpItem> insertedItems = mSqliteDao.selectPowerUpItem();
        boolean flagInserted;

        for (PowerUpItem item : mPowerUpItemList) {
            flagInserted = false;
            for (PowerUpItem insertedItem : insertedItems) {
                if (insertedItem.getItemId() == item.getItemId()) {
                    mSqliteDao.updatePowerUpItem(item);
                    flagInserted = true;
                    break;
                }
            }
            if (flagInserted == false){
                mSqliteDao.insertPowerUpItem(item);
            }
        }
    }

    private void loadPowerUpItem()
    {
        List<HashMap<String, String>> items = UtilAssets.loadAssets(CONF_FILE);

        for (HashMap<String, String> item : items){
            int id = Integer.valueOf(item.get("id"));
            int cost = Integer.valueOf(item.get("cost"));
            String name = item.get("name");
            int passivePower = Integer.valueOf(item.get("passive_power"));
            int activePower = Integer.valueOf(item.get("active_power"));
            Bitmap img = UtilResource.loadBitmap(item.get("img_name"));

            addPowerUpItem(new PowerUpItem(id, cost, name, passivePower, activePower, img));
        }

        loadItemSoldUnits();
    }

    private void loadItemSoldUnits()
    {
        List<PowerUpItem> soldUnitsList = mSqliteDao.selectPowerUpItem();

        for (PowerUpItem soldUnits : soldUnitsList){
            for (PowerUpItem item : mPowerUpItemList){
                if (item.getItemId() == soldUnits.getItemId()){
                    item.setUnitsSold(soldUnits.getUnitsSold());
                }
            }
        }
    }
}
