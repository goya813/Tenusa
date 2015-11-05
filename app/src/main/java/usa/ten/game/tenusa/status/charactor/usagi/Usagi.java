package usa.ten.game.tenusa.status.charactor.usagi;

import java.util.HashSet;
import java.util.Set;

import usa.ten.game.tenusa.status.powerup_item.PowerUpItemManager;

/**
 * Created by goya on 15/11/01.
 */
public class Usagi
{
    private static Usagi instance = new Usagi();

    private PowerUpItemManager mPowerUpItemManager;

    private int mPoint;
    private int mPassivePower;
    private int mActivePower;
    private Set<Integer> mHasPowerUpItem = new HashSet<>();
    private int mStrongestPowerUpItemId;
    private Set<Integer> mDefeatEnemy = new HashSet<>();

    private Usagi()
    {
        mPowerUpItemManager = PowerUpItemManager.getInstance();

        mPoint = 0;
        mPassivePower = 1;
        mActivePower  = 1;
        mStrongestPowerUpItemId = -1;
    }
    public static Usagi getInstance()
    {
        return (instance);
    }

    public int getPoint() {
        return mPoint;
    }

    public void addPoint(int point) {
        mPoint += point;
    }

    public Set<Integer> getHasPowerUpItem() {
        return mHasPowerUpItem;
    }

    public void buyPowerUpItem(int powerUpItemId) {
        mHasPowerUpItem.add(powerUpItemId);

        if (powerUpItemId > mStrongestPowerUpItemId){
            setStrongestPowerUpItemId(powerUpItemId);
            mActivePower  = mPowerUpItemManager.getPowerUpItem(powerUpItemId).getActivePower();
            mPassivePower = mPowerUpItemManager.getPowerUpItem(powerUpItemId).getPassivePower();
        }
    }

    public int getPassivePower() {
        return mPassivePower;
    }

    public int getActivePower() {
        return mActivePower;
    }

    public int getStrongestPowerUpItem() {
        return mStrongestPowerUpItemId;
    }

    public void setStrongestPowerUpItemId(int strongestPowerUpItem) {
        mStrongestPowerUpItemId = strongestPowerUpItem;
    }

    public Set<Integer> getDefeatEnemy() {
        return mDefeatEnemy;
    }
    public int getDefeatEnemyNum(){
        return mDefeatEnemy.size();
    }

    public void addDefeatEnemy(int defeatEnemy) {
        mDefeatEnemy.add(defeatEnemy);
    }
}
