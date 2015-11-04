package usa.ten.game.tenusa;

import android.app.Application;

/**
 * Created by goya on 15/11/03.
 */
public class App extends Application
{
    private static App instance = new App();

    public App()
    {
        instance = this;
    }
    public static App getInstance()
    {
        return (instance);
    }
}
