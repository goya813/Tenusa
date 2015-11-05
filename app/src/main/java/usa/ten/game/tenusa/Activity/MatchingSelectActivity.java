package usa.ten.game.tenusa.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import usa.ten.game.tenusa.Adapter.MatchingSelectItemAdapter;
import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.status.charactor.enemy.Enemy;
import usa.ten.game.tenusa.status.charactor.enemy.EnemyManager;

public class MatchingSelectActivity extends Activity
{
    private EnemyManager mEnemyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_select);

        mEnemyManager = EnemyManager.getInstance();

        List<Enemy> items = loadMatchingItems();
        MatchingSelectItemAdapter adapter = new MatchingSelectItemAdapter(this, R.layout.matching_select_item, items);

        GridView personGrid = (GridView)findViewById(R.id.person_grid);
        personGrid.setAdapter(adapter);

        personGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                new AlertDialog.Builder(MatchingSelectActivity.this)
                        .setTitle("title")
                        .setMessage("message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent  = new Intent(MatchingSelectActivity.this, MatchingStoryActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    private List<Enemy> loadMatchingItems()
    {
        return (mEnemyManager.getEnemyList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_matching_select, menu);
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
