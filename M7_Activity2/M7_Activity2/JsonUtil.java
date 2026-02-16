package M7_Activity2;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    private JsonUtil() {}

    public static String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}