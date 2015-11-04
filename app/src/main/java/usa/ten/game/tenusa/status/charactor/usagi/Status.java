package usa.ten.game.tenusa.status.charactor.usagi;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by goya on 15/11/01.
 */
public class Status
{
    private static Status instance = new Status();

    private int mPoint;
    private int mPassivePower;
    private int mActivePower;
    private Set<Integer> mHasPowerUpItem = new HashSet<>();
    private int mStrongestPowerUpItemId;
    private Set<Integer> mDefeatEnemy = new HashSet<>();

    private Status()
    {
        mPoint = 0;
        mPassivePower = 0;
        mActivePower  = 0;
        mStrongestPowerUpItemId = -1;
    }
    public static Status getInstance()
    {
        return (instance);
    }

    public int getPoint() {
        return mPoint;
    }

    public void setPoint(int point) {
        mPoint = point;
    }

    public Set<Integer> getHasPowerUpItem() {
        return mHasPowerUpItem;
    }

    public void buyPowerUpItem(int powerUpItem) {
        mHasPowerUpItem.add(powerUpItem);

        if (powerUpItem > mStrongestPowerUpItemId){
            setStrongestPowerUpItem(powerUpItem);
        }
    }

    public int getStrongestPowerUpItem() {
        return mStrongestPowerUpItemId;
    }

    public void setStrongestPowerUpItem(int strongestPowerUpItem) {
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
