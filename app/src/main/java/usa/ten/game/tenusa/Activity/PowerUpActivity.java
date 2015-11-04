package usa.ten.game.tenusa.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import usa.ten.game.tenusa.Adapter.PowerUpItemAdapter;
import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.status.powerup_item.PowerUpItem;
import usa.ten.game.tenusa.status.powerup_item.PowerUpItemManager;

public class PowerUpActivity extends Activity
{
    private PowerUpItemManager mPowerUpItemManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_up);

        mPowerUpItemManager = PowerUpItemManager.getInstance();
        List<PowerUpItem> items = loadPowerUpItem();

        PowerUpItemAdapter adapter = new PowerUpItemAdapter(this, 0, items);

        ListView itemListView = (ListView)findViewById(R.id.powerup_item_list);
        itemListView.setAdapter(adapter);

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                new AlertDialog.Builder(PowerUpActivity.this)
                        .setTitle("title")
                        .setMessage("message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    private List<PowerUpItem> loadPowerUpItem()
    {
        return (mPowerUpItemManager.getPowerUpItemList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_power_up, menu);
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
