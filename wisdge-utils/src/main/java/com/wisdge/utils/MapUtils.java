package com.wisdge.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class MapUtils {

    /**
     * 使用key/value创建一个Map对象
     * @param key
     * @param value
     * @return
     */
    public static Map<String, Object> make(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    /**
     * 使用BeanUtils工具类 将Map转为Bean
     *
     * @param clazz 创建对象的Class类声明
     * @param map Map对象
     * @param <T>
     * @return
     */
    public static <T> T toBean(Class<T> clazz, Map map) throws Exception {
        T bean = clazz.newInstance();
        DateTimeConverter dtConverter = new DateTimeConverter();
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        convertUtilsBean.deregister(Date.class);
        convertUtilsBean.register(dtConverter, Date.class);
        BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
        beanUtilsBean.populate(bean, map);
        return bean;
    }

    /**
     * 将Map转为Bean
     *
     * @param clazz 创建对象的Class类声明
     * @param map Map对象
     * @param <T>
     * @return
     * @throws IntrospectionException    获取类属性异常
     * @throws IllegalAccessException    创建Bean对象异常
     * @throws InstantiationException    创建Bean对象异常
     * @throws InvocationTargetException 对象转换异常
     */
    public static <T> T toBeanInfo(Class<T> clazz, Map map) throws IntrospectionException, IllegalAccessException, InstantiationException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz); // 获取类属性
        T bean = clazz.newInstance(); // 创建 JavaBean 对象
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);
                if (value == null)
                    continue;
                try {
                    Class<?> classTypes[] = descriptor.getWriteMethod().getParameterTypes();
                    if (classTypes[0].equals(Date.class)) {
                        if (value instanceof String)
                            value = DateUtils.parse((String) value);
                    }
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(bean, args);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return bean;
    }

    /**
     * 将Bean对象转为Map
     *
     * @param bean
     * @return Map
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Map toMap(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public static <T> T get(Map map, String key, T defaultValue) {
        if (map.containsKey(key)) {
            return (T) map.get(key);
        }
        return defaultValue;
    }

    public static String getString(Map map, String key, String defaultValue) {
        if (map.containsKey(key)) {
            return (String) map.get(key);
        }
        return defaultValue;
    }

    public static int getInteger(Map map, String key, int defaultValue) {
        if (map.containsKey(key)) {
            return (int) map.get(key);
        }
        return defaultValue;
    }

    public static float getFloat(Map map, String key, float defaultValue) {
        if (map.containsKey(key)) {
            return (float) map.get(key);
        }
        return defaultValue;
    }

    public static long getLong(Map map, String key, long defaultValue) {
        if (map.containsKey(key)) {
            return (long) map.get(key);
        }
        return defaultValue;
    }

    public static boolean getBoolean(Map map, String key, boolean defaultValue) {
        if (map.containsKey(key)) {
            return (boolean) map.get(key);
        }
        return defaultValue;
    }
}

//日期转换器
@Slf4j
class DateTimeConverter implements Converter {
    private static final String DATE = "yyyy-MM-dd";
    private static final String DATETIME = "yyyy-MM-dd HH:mm";
    private static final String DATETIME_WITH_SECONDS = "yyyy-MM-dd HH:mm:ss";
    private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

    @Override
    public Object convert(Class type, Object value) {
        // TODO Auto-generated method stub
        return toDate(type, value);
    }

    public static Object toDate(Class type, Object value) {
        if (value == null || "".equals(value))
            return null;
        if (value instanceof String) {
            String dateValue = value.toString().trim();
            int length = dateValue.length();
            if (type.equals(Date.class)) {
                try {
                    DateFormat formatter = null;
                    if (length <= 10) {
                        formatter = new SimpleDateFormat(DATE, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                    if (length <= 16) {
                        formatter = new SimpleDateFormat(DATETIME, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                    if (length <= 19) {
                        formatter = new SimpleDateFormat(DATETIME_WITH_SECONDS, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                    if (length <= 23) {
                        formatter = new SimpleDateFormat(TIMESTAMP, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return value;
    }
}
