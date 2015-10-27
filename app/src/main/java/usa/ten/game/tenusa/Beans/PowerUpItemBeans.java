package usa.ten.game.tenusa.Beans;

import android.graphics.Bitmap;

/**
 * Created by goya on 15/10/26.
 */
public class PowerUpItemBeans
{
    private Bitmap mImg;
    private String mItemName;
    private int mCost;

    public Bitmap getImg() {
        return mImg;
    }
    public void setImg(Bitmap mImg) {
        this.mImg = mImg;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public int getCost() {
        return mCost;
    }

    public void setCost(int cost) {
        this.mCost = cost;
    }
}
