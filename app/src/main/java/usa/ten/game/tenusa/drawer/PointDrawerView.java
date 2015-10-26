package usa.ten.game.tenusa.drawer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by goya on 15/10/24.
 */
public class PointDrawerView extends View
{
    private static final int MAX_DRAW_COUNT = 5;

    private int mViewWidth, mViewHeight;
    private List<PointDrawerItem> mDrawItemList;

    private Paint mPaint;
    private Random mRand;

    public PointDrawerView(Context context)
    {
        super(context);
    }

    public PointDrawerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        mDrawItemList = new ArrayList<PointDrawerItem>();

        mRand = new Random();
    }

    public PointDrawerView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        mDrawItemList = new ArrayList<PointDrawerItem>();

        mRand = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        List<PointDrawerItem> stub = new ArrayList<PointDrawerItem>();
//        canvas.drawColor(Color.BLUE);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(40);

        for (PointDrawerItem item : mDrawItemList){
            canvas.drawText(item.getText(), item.getX(), item.getY(), mPaint);
//            canvas.drawText("11111111111111", 30, 350, mPaint);

            item.countUp();
            if (item.getDrawnCount() == MAX_DRAW_COUNT){
                stub.add(item);
            }
        }

        mDrawItemList.removeAll(stub);
    }

    public void addPoint(int point)
    {
        int x, y;

        x = mRand.nextInt(mViewWidth);
        y = mRand.nextInt(mViewHeight);

        mDrawItemList.add(new PointDrawerItem(x, y, String.valueOf(point)));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);

        mViewWidth  = getWidth();
        mViewHeight = getHeight();
    }
}

