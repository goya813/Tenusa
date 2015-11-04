package usa.ten.game.tenusa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import usa.ten.game.tenusa.Beans.MatchingSelectItemBeans;
import usa.ten.game.tenusa.R;
import usa.ten.game.tenusa.status.charactor.enemy.Enemy;

/**
 * Created by goya on 15/10/27.
 */
public class MatchingSelectItemAdapter extends ArrayAdapter<Enemy>
{
    private LayoutInflater mLayoutInflater;

    public MatchingSelectItemAdapter(Context context, int textViewResourceId, List<Enemy> object)
    {
        super(context, textViewResourceId, object);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Enemy item = (Enemy)getItem(position);

        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.matching_select_item, null);
        }

        ImageView faceImg;
        faceImg = (ImageView)convertView.findViewById(R.id.face_img);
        faceImg.setImageBitmap(item.getFaceImg());

        return (convertView);
    }
}
