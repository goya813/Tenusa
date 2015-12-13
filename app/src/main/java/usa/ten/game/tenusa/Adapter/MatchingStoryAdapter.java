package usa.ten.game.tenusa.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.status.StoryText;

/**
 * Created by goya on 15/12/06.
 */
public class MatchingStoryAdapter extends ArrayAdapter<StoryText>
{
    private LayoutInflater mLayoutInflater;

    public MatchingStoryAdapter(Context context, int textViewResourceId, List<StoryText> object)
    {
        super(context, textViewResourceId, object);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        StoryText item = (StoryText)getItem(position);

        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.matching_story_item, null);
        }

        TextView talkText = (TextView)convertView.findViewById(R.id.talk_text);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)talkText.getLayoutParams();
        talkText.setText(item.getText());

        Log.v("MatchingStoryAdapter", "flag me:" + item.isFlagMe());
        if (item.isFlagMe()){
            Log.v("MatchingStoryAdapter", "me");
            lp.gravity = Gravity.RIGHT;
            talkText.setBackgroundResource(R.mipmap.mine_ballloon);
        }
        else {
            Log.v("MatchingStoryAdapter", "enemy");
            lp.gravity = Gravity.LEFT;
            talkText.setBackgroundResource(R.mipmap.enemy_ballloon);
        }

        talkText.setLayoutParams(lp);

        return (convertView);
    }
}
