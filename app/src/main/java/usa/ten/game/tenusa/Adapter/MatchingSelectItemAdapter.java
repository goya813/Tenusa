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

/**
 * Created by goya on 15/10/27.
 */
public class MatchingSelectItemAdapter extends ArrayAdapter<MatchingSelectItemBeans>
{
    private LayoutInflater mLayoutInflater;

    public MatchingSelectItemAdapter(Context context, int textViewResourceId, List<MatchingSelectItemBeans> object)
    {
        super(context, textViewResourceId, object);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MatchingSelectItemBeans item = (MatchingSelectItemBeans)getItem(position);

        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.matching_select_item, null);
        }

        ImageView faceImg;
        faceImg = (ImageView)convertView.findViewById(R.id.face_img);
        faceImg.setImageBitmap(item.getFaceImg());

        return (convertView);
    }
}
