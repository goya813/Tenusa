package usa.ten.game.tenusa.status.charactor.enemy;

import android.graphics.Bitmap;

/**
 * Created by goya on 15/11/01.
 */
public class Enemy
{
    private int mEnemyId;
    private int mHp;
    private String mName;
    private boolean mDefeated;
    private Bitmap mFaceImg;


    public Enemy(int enemyId, boolean deafeated)
    {
        mEnemyId  = enemyId;
        mDefeated = deafeated;
    }

    public Enemy(int enemyId, int hp, String name, boolean defeated, Bitmap faceImg){
        mEnemyId  = enemyId;
        mHp       = hp;
        mName     = name;
        mDefeated = defeated;
        mFaceImg  = faceImg;
    }

    public int getEnemyId() {
        return mEnemyId;
    }

    public int getHp() {
        return mHp;
    }

    public String getName() {
        return mName;
    }

    public boolean isDefeated() {
        return mDefeated;
    }

    public int getDefeated()
    {
        if (isDefeated()){
            return (1);
        }

        return (0);
    }

    public void setDefeated(boolean defeated) {
        mDefeated = defeated;
    }

    public Bitmap getFaceImg() {
        return mFaceImg;
    }

    public void recycle()
    {
        mFaceImg.recycle();
    }
}
