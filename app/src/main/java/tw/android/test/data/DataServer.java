package tw.android.test.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hermes.hsieh on 2017/9/17.
 */

public class DataServer {
    public static List<String> getStringDataList(int startCount, int count) {
        List<String> list = new ArrayList<>();
        for (int i = startCount; i < startCount + count; i++) {
            list.add("[ " + i + " ] StringList Data");
        }
        return list;
    }

    public static List<String> getStringDataList(int count) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add("[ " + i + " ] StringList Data");
        }
        return list;
    }
}
