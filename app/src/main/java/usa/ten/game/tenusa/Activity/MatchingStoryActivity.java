package usa.ten.game.tenusa.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.status.StoryText;
import usa.ten.game.tenusa.status.charactor.enemy.Enemy;
import usa.ten.game.tenusa.status.charactor.enemy.EnemyManager;
import usa.ten.game.tenusa.util.UtilAssets;

public class MatchingStoryActivity extends Activity
{
    private static String TEXT_ME    = "me";
    private static String TEXT_ENEMY = "enemy";
    private static String BASE_CONF_NAME = "story_conf";
    private static String EXTENSION = "txt";

    private EnemyManager mEnemyManager;

    private List<StoryText> mStoryTexts = new ArrayList<>();
    private int mStoryStage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_story);

        mEnemyManager = EnemyManager.getInstance();
        int enemyId = getIntent().getIntExtra("enemyId", -1);
        final Enemy enemy = mEnemyManager.getEnemy(enemyId);

        final List<String> texts = loadStoryText(enemyId);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, new ArrayList<String>());
        adapter.add(texts.get(0));

        ListView storyList = (ListView)findViewById(R.id.story_list);
        storyList.setAdapter(adapter);

        Button nextButton = (Button)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStoryStage++;

                if (mStoryStage < texts.size()) {
                    adapter.add(texts.get(mStoryStage));
                }
                else {
                    Intent intent = new Intent(MatchingStoryActivity.this, MatchingBattleActivity.class);
                    intent.putExtra("enemyId", enemy.getEnemyId());

                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private List<String> loadStoryText(int enemyId)
    {
        String confFile = BASE_CONF_NAME + enemyId + "." + EXTENSION;
        List<HashMap<String, String>> items = UtilAssets.loadAssets(confFile);
        List<String> texts = new ArrayList<>();

        for (HashMap<String, String> item : items){
            String text = "";
            boolean flagMe;

            if (item.containsKey(TEXT_ME)){
                text = item.get(TEXT_ME);
                flagMe = true;
            }
            else {
                text = item.get(TEXT_ENEMY);
                flagMe = false;
            }

            Log.v("loadStoryText", "load");
            if (text == null){
                Log.v("loadStoryText", "null");
            }
            Log.v("loadStoryText", text);
            mStoryTexts.add(new StoryText(flagMe, text));

            if (flagMe){
                texts.add("私:" + text);
            }
            else {
                texts.add("敵:" + text);
            }
        }

        return (texts);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_matching_story, menu);
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
