package ru.spbau.eshcherbin.homework.cw2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Class that provides Dependency Injection functionality
 */
public class Injector {
    private static Set<Class<?>> currentClasses = new HashSet<>();
    private static Map<Class<?>, Object> classToInstance = new HashMap<>();
    private static List<Class<?>> implementationClasses = new ArrayList<>();

    /**
     * Create and initialize object of `rootClassName` class using classes from
     * `implementationClassNames` for concrete dependencies.
     */
    public static Object initialize(String rootClassName, List<String> implementationClassNames) throws Exception {
        currentClasses.clear();
        classToInstance.clear();
        implementationClasses.clear();
        for (String name : implementationClassNames) {
            implementationClasses.add(Class.forName(name));
        }
        Class<?> rootClass = Class.forName(rootClassName);
        if (!implementationClassNames.contains(rootClassName)) {
            implementationClasses.add(rootClass);
        }
        return getInstance(rootClass);
    }

    /**
     * Provides an instance for `clazz`
     * @return desired instance
     */
    private static Object getInstance(Class<?> clazz) throws InjectionCycleException, IllegalAccessException, InvocationTargetException, InstantiationException, ImplementationNotFoundException, AmbiguousImplementationException {
        if (currentClasses.contains(clazz)) {
            throw new InjectionCycleException();
        }
        if (classToInstance.containsKey(clazz)) {
            return classToInstance.get(clazz);
        }
        currentClasses.add(clazz);
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> type = parameterTypes[i];
            Class<?> implementationType = null;
            for (Class<?> candidate : implementationClasses) {
                if (type.isAssignableFrom(candidate)) {
                    if (implementationType == null) {
                        implementationType = candidate;
                    } else {
                        throw new AmbiguousImplementationException();
                    }
                }
            }
            if (implementationType == null) {
                throw new ImplementationNotFoundException();
            } else {
                parameters[i] = getInstance(implementationType);
            }
        }
        currentClasses.remove(clazz);
        Object instance = constructor.newInstance(parameters);
        classToInstance.put(clazz, instance);
        return instance;
    }
}
