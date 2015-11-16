package usa.ten.game.tenusa.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.database.SqliteDAO;
import usa.ten.game.tenusa.drawer.PointDrawerItem;
import usa.ten.game.tenusa.drawer.PointDrawerView;
import usa.ten.game.tenusa.status.charactor.enemy.EnemyManager;
import usa.ten.game.tenusa.status.charactor.usagi.Usagi;
import usa.ten.game.tenusa.status.powerup_item.PowerUpItemManager;


public class TopActivity extends Activity
{
    private Usagi mUsagi;
    private EnemyManager mEnemyManager;
    private PowerUpItemManager mPowerUpManager;

    private Handler mDrawerViewHander = new Handler();
    private Handler mRoutinePointHandler = new Handler();
    private SqliteDAO mSqliteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        mSqliteDao = SqliteDAO.getInstance();

        mUsagi = Usagi.getInstance();
        mEnemyManager = EnemyManager.getInstance();
        mPowerUpManager = PowerUpItemManager.getInstance();


        final TextView usagiStatusText = (TextView)findViewById(R.id.usagi_status);

        final PointDrawerView pointDrawerview = (PointDrawerView)findViewById(R.id.drawPointView);
        mDrawerViewHander.postDelayed(new Runnable() {
            @Override
            public void run() {
                pointDrawerview.invalidate();
                usagiStatusText.setText("普通のうさぎ  " + mUsagi.getPoint() + "pt");

                mDrawerViewHander.postDelayed(this, 50);
            }
        }, 50);
        mRoutinePointHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mUsagi.addPoint(mUsagi.getPassivePower());
                pointDrawerview.addPoint(mUsagi.getPassivePower());

                mDrawerViewHander.postDelayed(this, 1000);
            }
        }, 1000);

        final ImageButton usagi = (ImageButton)findViewById(R.id.usagiButton);
        usagi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mUsagi.addPoint(mUsagi.getActivePower());
                pointDrawerview.addPoint(mUsagi.getActivePower());
            }
        });

        final Button powerUp = (Button)findViewById(R.id.powerup);
        powerUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(TopActivity.this, PowerUpActivity.class);
                startActivity(intent);
            }
        });

        final Button matching = (Button)findViewById(R.id.matching);
        matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopActivity.this, MatchingSelectActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        mUsagi.save();
//        mEnemyManager.save();
        mPowerUpManager.save();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        mDrawerViewHander.removeCallbacksAndMessages(null);
        mRoutinePointHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
