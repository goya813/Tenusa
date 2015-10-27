package usa.ten.game.tenusa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import usa.ten.game.tenusa.Beans.PowerUpItemBeans;
import usa.ten.game.tenusa.R;

/**
 * Created by goya on 15/10/26.
 */
public class PowerUpItemAdapter extends ArrayAdapter<PowerUpItemBeans>
{
    private LayoutInflater mLayoutInflater;

    public PowerUpItemAdapter(Context context, int textViewResourceId, List<PowerUpItemBeans> object)
    {
        super(context, textViewResourceId, object);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        PowerUpItemBeans item = (PowerUpItemBeans)getItem(position);

        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.powerup_item, null);
        }

        ImageView itemImg;
        itemImg = (ImageView)convertView.findViewById(R.id.item_img);
        itemImg.setImageBitmap(item.getImg());

        TextView itemName;
        itemName = (TextView)convertView.findViewById(R.id.item_name);
        itemName.setText(item.getItemName());

        TextView itemCost;
        itemCost = (TextView)convertView.findViewById(R.id.item_cost);
        itemCost.setText("消費ぽいんと：" + item.getCost());

        return (convertView);
    }
}