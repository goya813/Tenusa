package usa.ten.game.tenusa.status.charactor.enemy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

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
public class EnemyManager
{
    private static String CONF_FILE = "enemy_conf.txt";
    private static EnemyManager instance = new EnemyManager();

    private SqliteDAO mSqliteDao;
    private List<Enemy> mEnemyList = new ArrayList<>();

    private EnemyManager()
    {
        mSqliteDao = SqliteDAO.getInstance();
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

    public Enemy getEnemy(int id)
    {
        for (Enemy enemy : mEnemyList){
            if (enemy.getEnemyId() == id){
                return (enemy);
            }
        }

        return (null);
    }

    public List<Enemy> getEnemyList() {
        return mEnemyList;
    }

    public void defeatEnemy(int id)
    {
        getEnemy(id).setDefeated(true);
    }

    public void save()
    {
        List<Enemy> insertedEnemies = mSqliteDao.selectEnemy();
        boolean flagInserted;

        for (Enemy enemy : mEnemyList){
            flagInserted = false;
            for (Enemy insertedEnemy : insertedEnemies){
                if (enemy.getEnemyId() == insertedEnemy.getEnemyId()){
                    mSqliteDao.updateEnemy(enemy);
                    flagInserted = true;

                    break;
                }
            }

            if (flagInserted == false){
                mSqliteDao.insertEnemy(enemy);
            }
        }
    }

    private void loadEnemy()
    {
        List<HashMap<String, String>> items = UtilAssets.loadAssets(CONF_FILE);

        for (HashMap<String, String> item : items){
            int id = Integer.valueOf(item.get("id"));
            int hp = Integer.valueOf(item.get("hp"));
            String name = item.get("name");
            Bitmap img = UtilResource.loadBitmap(item.get("img_name"));

            addEnemy(new Enemy(id, hp, name, false, img));
        }

        loadEnemyDefeated();
    }

    private void loadEnemyDefeated()
    {
        List<Enemy> defeatDatas = mSqliteDao.selectEnemy();

        for (Enemy defeatData: defeatDatas){
            Log.v("loadEnemyDefeated", "defeated:" + defeatData.isDefeated());
            for (Enemy enemy: mEnemyList){
                if (enemy.getEnemyId() == defeatData.getEnemyId()){
                    if (defeatData.isDefeated()) {
                        defeatEnemy(enemy.getEnemyId());
                    }
                }
            }
        }
    }
}
