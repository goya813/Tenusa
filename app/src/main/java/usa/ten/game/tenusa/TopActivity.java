package usa.ten.game.tenusa;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import usa.ten.game.tenusa.drawer.PointDrawerItem;
import usa.ten.game.tenusa.drawer.PointDrawerView;


public class TopActivity extends Activity {

    private Handler mDrawerViewHander = new Handler();
    private Handler mRoutinePointHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        final PointDrawerView pointDrawerview = (PointDrawerView)findViewById(R.id.drawPointView);
        mDrawerViewHander.postDelayed(new Runnable() {
            @Override
            public void run() {
                pointDrawerview.invalidate();

                mDrawerViewHander.postDelayed(this, 50);
            }
        }, 50);
        mRoutinePointHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pointDrawerview.addPoint(1);

                mDrawerViewHander.postDelayed(this, 1000);
            }
        }, 1000);

        final ImageButton usagi = (ImageButton)findViewById(R.id.usagiButton);
        usagi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pointDrawerview.addPoint(2);
            }
        });
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
