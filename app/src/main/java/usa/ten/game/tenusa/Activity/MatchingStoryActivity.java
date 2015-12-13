package usa.ten.game.tenusa.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usa.ten.game.tenusa.Adapter.MatchingStoryAdapter;
import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.status.StoryText;
import usa.ten.game.tenusa.status.charactor.enemy.Enemy;
import usa.ten.game.tenusa.status.charactor.enemy.EnemyManager;
import usa.ten.game.tenusa.util.UtilAssets;

public class MatchingStoryActivity extends Activity
{
    private static String KEY_ME    = "me";
    private static String KEY_ENEMY = "enemy";
    private static String BASE_CONF_NAME = "story_conf";
    private static String EXTENSION = "txt";

    private EnemyManager mEnemyManager;

    private int mStoryStage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_story);

        mEnemyManager = EnemyManager.getInstance();
        int enemyId = getIntent().getIntExtra("enemyId", -1);
        final Enemy enemy = mEnemyManager.getEnemy(enemyId);

        final List<StoryText> texts = loadStoryText(enemyId);
        final MatchingStoryAdapter adapter = new MatchingStoryAdapter(this, R.layout.matching_story_item, new ArrayList<StoryText>());
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

    private List<StoryText> loadStoryText(int enemyId)
    {
        String confFile = BASE_CONF_NAME + enemyId + "." + EXTENSION;
        List<HashMap<String, String>> items = UtilAssets.loadAssets(confFile);
        List<String> texts = new ArrayList<>();
        List<StoryText> storyTexts = new ArrayList<>();

        for (HashMap<String, String> item : items){
            String text = "";
            boolean flagMe;

            if (item.containsKey(KEY_ME)){
                text = item.get(KEY_ME);
                flagMe = true;
            }
            else {
                text = item.get(KEY_ENEMY);
                flagMe = false;
            }

            storyTexts.add(new StoryText(flagMe, text));
        }

        return (storyTexts);
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
