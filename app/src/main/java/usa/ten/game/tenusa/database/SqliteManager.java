package usa.ten.game.tenusa.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import usa.ten.game.tenusa.App;

/**
 * Created by goya on 15/11/05.
 */

public class SqliteManager
{
    private static final String DB = "tenusa.db";
    private static final int DB_VERSION = 1;

    private static SqliteManager instance = new SqliteManager();

    private SQLiteDatabase mDb;
    private MySqLiteOpenHelper mSqliteOpenHelper;

    private SqliteManager()
    {
        mSqliteOpenHelper = new MySqLiteOpenHelper(App.getInstance().getApplicationContext());
    }
    public static SqliteManager getInstance()
    {
        return (instance);
    }

    public boolean insert(String tableName, String[] key, Object[] value)
    {
        SQLiteDatabase db = mSqliteOpenHelper.getWritableDatabase();
        String sql = "INSERT INTO " + tableName + " (";

        for (int i = 0; i < key.length - 1; i++){
            sql += key[i] + ", ";
        }
        sql += key[key.length - 1] + ") ";

        sql += "VALUES(";
        for (int i = 0; i < value.length - 1; i++){
            sql += "?, ";
        }
        sql += "?);";

        db.execSQL(sql, value);

        return (true);
    }

    public List<String[]> select(String tableName)
    {
        List<String[]> res = new ArrayList<String[]>();
        int rawCount;
        SQLiteDatabase db = mSqliteOpenHelper.getReadableDatabase();
        String sql = "SELECT * from " + tableName + ";";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        rawCount = cursor.getCount();
        for (int i = 0; i < rawCount; i++){
            int colCount = cursor.getColumnCount();
            String[] data = new String[colCount];

            for (int j = 0; j < colCount; j++){
                data[j] = cursor.getString(j);
            }

            res.add(data);
            cursor.moveToNext();
        }

        return (res);
    }

    public void exec(String sql)
    {
        SQLiteDatabase db = mSqliteOpenHelper.getWritableDatabase();
        db.execSQL(sql);
    }

    private static class MySqLiteOpenHelper extends SQLiteOpenHelper
    {
        public MySqLiteOpenHelper(Context c)
        {
            super(c, DB, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String sql = "create table usagi (" +
                         "point Integer" +
                         ");";
            db.execSQL(sql);

            sql = "insert into usagi values(0);";
            db.execSQL(sql);

            sql =   "create table powerup_items(" +
                    "id Integer primary key," +
                    "unitssold Integer" +
                    ");";
            db.execSQL(sql);

            sql =   "create table enemies(" +
                    "id Integer primary key," +
                    "defeated Integer" +
                    ");";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1)
        {
            dropTable(db);
            onCreate(db);
        }

        private void dropTable(SQLiteDatabase db)
        {
            String sql = "drop table usagi";
            db.execSQL(sql);

            sql = "drop table powerup_item";
            db.execSQL(sql);

            sql = "drop table enemy";
            db.execSQL(sql);
        }
    }
}
