package usa.ten.game.tenusa.status.charactor.enemy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import usa.ten.game.tenusa.App;
import usa.ten.game.tenusa.R;

/**
 * Created by goya on 15/11/03.
 */
public class EnemyManager
{
    private static EnemyManager instance = new EnemyManager();

    private List<Enemy> mEnemyList = new ArrayList<>();

    private EnemyManager()
    {
        loadEnemy();
    }
    public static EnemyManager getInstance()
    {
        return (instance);
    }

    private void addEnemy(Enemy enemy)
    {
        mEnemyList.add(enemy);
    }

    public Enemy getEnemy(int index)
    {
        return mEnemyList.get(index);
    }

    public List<Enemy> getEnemyList() {
        return mEnemyList;
    }

    private void loadEnemy()
    {
        Bitmap img;
        img = BitmapFactory.decodeResource(App.getInstance().getResources(), R.mipmap.ic_launcher);
        addEnemy(new Enemy(0, 10, "atsushi", false, img));
        addEnemy(new Enemy(1, 10, "atsushi", false, img));
        addEnemy(new Enemy(2, 10, "atsushi", false, img));
        addEnemy(new Enemy(3, 10, "atsushi", false, img));
    }
}
