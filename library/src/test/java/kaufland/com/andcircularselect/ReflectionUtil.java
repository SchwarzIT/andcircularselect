package kaufland.com.andcircularselect;

import java.lang.reflect.Field;

/**
 * Created by sbra0902 on 26.01.17.
 */

public class ReflectionUtil {

    public static <T> void fieldSet(Class clazz, Object object,  String fieldName, T value) throws NoSuchFieldException, IllegalAccessException {

        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        field.set(object, value);
    }

    public static <T> T fieldGet(Class clazz, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        return (T) field.get(object);
    }

}
