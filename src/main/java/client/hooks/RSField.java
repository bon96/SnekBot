package client.hooks;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * BonBom
 * Date: 3.11.2017
 * Time: 18.02
 */

public class RSField extends RSFieldInfo {
    private HookLoader hookLoader;

    RSField(HookLoader hookLoader, String realClassName, String realFieldName, String className, String fieldName, long multiplier) {
        super(realClassName, realFieldName, className, fieldName, multiplier);
        this.hookLoader = hookLoader;
    }

    RSField(HookLoader hookLoader, String realClassName, String realFieldName, String className, String fieldName, int multiplier) {
        super(realClassName, realFieldName, className, fieldName, multiplier);
        this.hookLoader = hookLoader;
    }

    RSField(HookLoader hookLoader, String realClassName, String realFieldName, String className, String fieldName, double multiplier) {
        super(realClassName, realFieldName, className, fieldName, multiplier);
        this.hookLoader = hookLoader;
    }


    public Object getValue() {
        return getValue(null);
    }

    public Object getValue(Object object) {
        try {
            Field f = hookLoader.classLoader.loadClass(super.classname).getDeclaredField(super.fieldName);
            f.setAccessible(true);
            Object o = f.get(object);
            if (!equals(multiplier, 0)) {
                return getRealValue((Number) o, multiplier);
            }
            return o;
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("all")
    public <N extends Number> N getRealValue(N value, N multiplier) {
            Class<? extends Number> cl = value.getClass();
            if (cl == Integer.class) {
                return (N) Integer.valueOf(value.intValue() * multiplier.intValue());
            }
            if (cl == Long.class) {
                return (N) Long.valueOf(value.longValue() * multiplier.longValue());
            }
            throw new UnsupportedOperationException("unknown class: " + cl);
    }

    public <N extends Number> boolean equals(N value1, N value2) {
            Class<? extends Number> cl = value1.getClass();
            if (cl == Integer.class) {
                return value1.intValue() == value2.intValue();
            }
            if (cl == Long.class) {
                return value1.longValue() == value2.longValue();
            }
            if (cl == Double.class) {
                return value1.doubleValue() == value2.doubleValue();
            }
            throw new UnsupportedOperationException("unknown class: " + cl);
    }
}
