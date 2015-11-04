package usa.ten.game.tenusa.status.powerup_item;

import android.graphics.Bitmap;

/**
 * Created by goya on 15/11/01.
 */
public class PowerUpItem
{
    private int mItemId;
    private int mCost;
    private String mName;
    private int mPassivePower;
    private int mActivePower;
    private int mUnitsSold;
    private Bitmap mItemImg;

    public PowerUpItem(int itemId, int cost, String name, int passivePower, int activePower, Bitmap itemImg)
    {
        mItemId       = itemId;
        mCost         = cost;
        mName         = name;
        mPassivePower = passivePower;
        mActivePower  = activePower;
        mItemImg      = itemImg;
    }

    public int getItemId() {
        return mItemId;
    }

    public int getCost() {
        return mCost;
    }

    public String getName() {
        return mName;
    }

    public Bitmap getItemImg() {
        return mItemImg;
    }

    public void recycle()
    {
        mItemImg.recycle();
    }
}
