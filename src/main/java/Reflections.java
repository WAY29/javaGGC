import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class Reflections {
    public static void setFieldValue(final Object obj, final String fieldName, final Object value) throws Exception {
        final Field field = getField(obj.getClass(), fieldName);
        field.set(obj, value);
    }

    public static Object getFieldValue(final Object obj, final String fieldName) throws Exception {
        final Field field = getField(obj.getClass(), fieldName);
        return field.get(obj);
    }

    public static List<Method> getClassMethods(final Class clazz) throws Exception {
        List<Method> methodList = Arrays.asList(clazz.getMethods());
        List<Method> filteredMethodList =
                methodList.stream().
                filter((Method m) -> m.getParameterTypes().length > 0).
                collect(Collectors.toList());

        Collections.sort(filteredMethodList,new Comparator<Method>() {
            public int compare(Method o1,
                               Method o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return filteredMethodList;
    }

    public static Field getField(final Class<?> clazz, final String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException ex) {
            if (clazz.getSuperclass() != null)
                field = getField(clazz.getSuperclass(), fieldName);
        }
        return field;
    }
}
