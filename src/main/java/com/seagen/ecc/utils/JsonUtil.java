package com.seagen.ecc.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * json的操作类
 * 
 */
public class JsonUtil {
    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OM = new ObjectMapper();

    public static final Gson gson = new Gson();

    static {
        OM.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 功能描述:传入任意一个 javabean 对象生成一个指定规格的字符串
     * 
     * @param bean
     *            bean对象
     * @return String
     */
    public static String ojbToJsonStr(Object bean) {
        String ret = null;
        try {
            ret = OM.writeValueAsString(bean);
        } catch (Exception e) {
            log.error("failed beanToJson:" + bean, e);
        }
        return ret;
    }

    /**
     * 把json字符串转为指定的对象类型
     * 
     * @param json
     *            Json字符串
     * @param cla
     *            对象类型
     * @return
     */
    public static <T> T jsonStrToObj(String json, Class<T> cla) {
        T t = null;
        try {
            t = OM.readValue(json, cla);
        } catch (Exception e) {
            log.error("failed jsonToObj:" + json, e);
        }
        return t;
    }

    public static <T> T map2Bean(Map<?, ?> map, Class<T> cla) {
        Map<Object, Object> values = new HashMap<>();
        BeanInfo bi = null;
        try {
            bi = Introspector.getBeanInfo(cla);
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            Object obj = map.get(pd.getName());
            if (obj != null) {
                values.put(pd.getName(), obj);
            }
        }
        return JsonUtil.jsonStrToObj(JsonUtil.ojbToJsonStr(values), cla);
    }

    /**
     * 把json字符串转为指定的对象类型的List
     * 
     * @param json
     *            Json字符串
     * @param cla
     *            对象类型
     * @return
     */
    public static <T> List<T> jsonStr2List(String json, Class<T> cla) {
        List<T> ret = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            ret.add(new Gson().fromJson(elem, cla));
        }
        return ret;
    }

    /**
     * 将一个Javabean对象转换层便于阅读的格式化的Json字符串
     * 
     * @param bean
     *            要转换的Javabean对象
     * @return 带换行的格式化的Json字符串
     */
    public static String ojbToJsonPrettyStr(Object bean) {
        ObjectWriter writer = OM.writer().withDefaultPrettyPrinter();
        String ret = null;
        try {
            ret = writer.writeValueAsString(bean);
        } catch (Exception e) {
            log.error("failed ojbToJsonPrettyStr:" + bean, e);
        }
        return ret;
    }

    public static Map<String, String> jsonStr2Map(String jsonStr) {
        try {
            JsonNode node = OM.readTree(jsonStr);
            Map<String, String> map = new HashMap<String, String>();
            Iterator<String> it = node.getFieldNames();
            while (it.hasNext()) {
                String key = it.next();
                map.put(key, node.get(key).asText());
            }
            return map;
        } catch (Exception e) {
            log.error("failed jsonStr2Map:" + jsonStr, e);
            return null;
        }
    }

}
