package usa.ten.game.tenusa.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.status.charactor.enemy.Enemy;
import usa.ten.game.tenusa.status.charactor.enemy.EnemyManager;

public class MatchingBattleActivity extends Activity
{
    private static String BASE_DESCRIPTION = "タッチしまくれ！ HP:";

    private TextView mDescription;
    private EnemyManager mEnemyManager;

    private int mEnemyId;
    private int mEnemyHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_battle);

        mDescription = (TextView)findViewById(R.id.description);

        mEnemyManager = EnemyManager.getInstance();
        mEnemyId = getIntent().getIntExtra("enemyId", -1);
        Enemy enemy= mEnemyManager.getEnemy(mEnemyId);

        mEnemyHp = enemy.getHp();
        mDescription.setText(BASE_DESCRIPTION + mEnemyHp);

        setClickListener();
    }

    private void setClickListener()
    {
        final ImageView usagiImg = (ImageView)findViewById(R.id.battle_usagi_img);
        usagiImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mEnemyHp--;
                        if (mEnemyHp == 0){
                            finishBattle();
                        }

                        usagiImg.setImageResource(R.mipmap.usagi_attack);
                        mDescription.setText(BASE_DESCRIPTION + mEnemyHp);
                        break;

                    case MotionEvent.ACTION_UP:
                        usagiImg.setImageResource(R.mipmap.usagi);
                        break;
                }

                return true;
            }
        });
    }

    private void finishBattle()
    {
        mEnemyManager.defeatEnemy(mEnemyId);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_matching_battle, menu);
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
