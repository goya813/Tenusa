package usa.ten.game.tenusa.database;

import java.util.ArrayList;
import java.util.List;

import usa.ten.game.tenusa.status.charactor.enemy.Enemy;
import usa.ten.game.tenusa.status.powerup_item.PowerUpItem;

/**
 * Created by goya on 15/11/10.
 */
public class SqliteDAO
{
    private static SqliteDAO instance = new SqliteDAO();
    private SqliteManager mSqliteManager;

    private SqliteDAO()
    {
        mSqliteManager = SqliteManager.getInstance();
    }
    public static SqliteDAO getInstance()
    {
        return (instance);
    }

    public int selectPoint()
    {
        List<String[]> datas = mSqliteManager.select("usagi");

        return (Integer.valueOf(datas.get(0)[0]));
    }

    public void updatePoint(int point)
    {
        String sql = String.format("UPDATE usagi SET point = %d;", point);

        mSqliteManager.exec(sql);
    }

    public List<Enemy> selectEnemy()
    {
        List<String[]> items = mSqliteManager.select("enemies");
        List<Enemy> res = new ArrayList<>();

        for (String[] item : items){
            int id           = Integer.valueOf(item[0]);
            boolean defeated = false;
            if (Integer.valueOf(item[1]) == 1){
                defeated = true;
            }

            Enemy enemy  = new Enemy(id, defeated);

            res.add(enemy);
        }

        return (res);
    }

    public void insertEnemy(Enemy enemy)
    {
        String[] keys = {"id", "defeated"};
        Object[] vals = {enemy.getEnemyId(), enemy.getDefeated()};

        mSqliteManager.insert("enemies", keys, vals);
    }

    public void updateEnemy(Enemy enemy)
    {
        String sql = String.format("UPDATE enemies SET defeated = %d where id = %d;", enemy.getDefeated(), enemy.getEnemyId());

        mSqliteManager.exec(sql);
    }

    public List<PowerUpItem> selectPowerUpItem()
    {
        List<String[]> items = mSqliteManager.select("powerup_items");
        List<PowerUpItem> res = new ArrayList<PowerUpItem>();

        for (String[] item : items){
            int id        = Integer.valueOf(item[0]);
            int unitsSold = Integer.valueOf(item[1]);
            PowerUpItem powerupItem = new PowerUpItem(id, unitsSold);

            res.add(powerupItem);
        }

        return (res);
    }

    public void insertPowerUpItem(PowerUpItem item)
    {
        String[] keys = {"id", "unitssold"};
        Object[] vals = {item.getItemId(), item.getUnitsSold()};

        mSqliteManager.insert("powerup_items", keys, vals);
    }

    public void updatePowerUpItem(PowerUpItem powerupItem)
    {
        String sql = String.format("UPDATE powerup_items SET unitssold = %d where id = %d;", powerupItem.getUnitsSold(), powerupItem.getItemId());

        mSqliteManager.exec(sql);
    }
}
