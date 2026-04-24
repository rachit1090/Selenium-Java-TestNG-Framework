package utility;

import java.util.Map;

public class TestDataHelper {
    private Map<String, Object> data;

    public TestDataHelper(Map<String, Object> data) {
        this.data = data;
    }

    public String getString(String key) {
        return (String) data.getOrDefault(key, "");
    }

    public int getInt(String key) {
        return (int) data.getOrDefault(key, 0);
    }
}
