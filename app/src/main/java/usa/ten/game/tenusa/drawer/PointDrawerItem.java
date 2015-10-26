package usa.ten.game.tenusa.drawer;

/**
 * Created by goya on 15/10/24.
 */
public class PointDrawerItem
{
    private int mX, mY;
    private String mText;
    private int mDrawnCount;

    public PointDrawerItem(int x, int y, String text)
    {
        mX = x;
        mY = y;
        mText = text;
        mDrawnCount = 0;
    }

    public void countUp()
    {
        mDrawnCount++;
    }

    public int getX() {

        return mX;
    }

    public int getY() {
        return mY;
    }

    public String getText() {
        return mText;
    }

    public int getDrawnCount()
    {
        return (mDrawnCount);
    }
}
