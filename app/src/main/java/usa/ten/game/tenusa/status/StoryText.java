package usa.ten.game.tenusa.status;

/**
 * Created by goya on 15/11/22.
 */
public class StoryText
{
    private boolean mFlagMe;
    private String mText;

    public StoryText(boolean flagMe, String text) {
        mFlagMe = flagMe;
        mText = text;
    }

    public boolean isFlagMe() {
        return mFlagMe;
    }

    public String getText() {
        return mText;
    }
}
