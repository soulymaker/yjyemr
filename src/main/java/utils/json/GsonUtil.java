package utils.json;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GsonUtil {

    private final static Gson gson = new Gson();

    public static <T> T getJSON(String jsonString, Type type) {
        T t = null;
        try {
            t = gson.fromJson(jsonString, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> getJsonList(String jsonString, Type type) {
        List<T> list = Lists.newArrayList();
        try {
            list = gson.fromJson(jsonString, type);
        } catch (Exception e) {
        }
        return list;
    }

    public static List<Map<String, Object>> listKeyMaps(String jsonString) {
        List<Map<String, Object>> list = Lists.newArrayList();
        try {
            list = gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
        } catch (Exception e) {
        }
        return list;
    }

    public static <T> String toJSON(T t) {
        return gson.toJson(t);
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

}
