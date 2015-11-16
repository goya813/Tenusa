package usa.ten.game.tenusa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usa.ten.game.tenusa.App;

/**
 * Created by goya on 15/11/11.
 */
public class UtilAssets
{
    public static List<HashMap<String, String>> loadAssets(String fileName) {
        InputStream is = null;
        BufferedReader br = null;
        List<HashMap<String, String>> res = new ArrayList<>();

        try {
            is = App.getInstance().getAssets().open(fileName);
            br = new BufferedReader(new InputStreamReader(is));

            String str;
            while ((str = br.readLine()) != null) {
                HashMap<String, String> map = new HashMap<>();

                for (String item : str.split(",", 0)){
                    item = item.trim();
                    String key = item.split(":")[0];
                    String val = item.split(":")[1];

                    map.put(key, val);
                }

                res.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return (res);
    }
}
