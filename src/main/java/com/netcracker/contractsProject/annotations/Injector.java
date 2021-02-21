package com.netcracker.contractsProject.annotations;

import com.google.common.reflect.ClassPath;
import com.netcracker.contractsProject.exceptions.InjectException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Configuration(packages = {"com.netcracker.contractsProject"})
public class Injector {
    private static final Logger LOGGER = Logger.getRootLogger();

    /**
     * Injects dependencies into the fields of the passed object.
     *
     * @param o object in the fields of which you want to inject the dependency
     * @throws InjectException An exception is thrown if not found or found more than 1 class, which can be entered in the field
     */
    public static <T> void inject(T o) throws InjectException {
        try {
            String[] packages = Injector.class.getAnnotation(Configuration.class).packages();
            ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
            for (String pkg : packages) {
                Set<ClassPath.ClassInfo> allClasses = classPath.getTopLevelClassesRecursive(pkg);
                for (Field field : o.getClass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(MyInject.class)) {
                        Class cls = field.getAnnotation(MyInject.class).clazz();
                        field.setAccessible(true);
                        List<Object> list = new ArrayList<>();
                        for (ClassPath.ClassInfo ci : allClasses) {
                            if (cls.isAssignableFrom(ci.load()) && !ci.load().isInterface()) {
                                list.add(ci.load().newInstance());
                            }
                        }
                        if (Collection.class.isAssignableFrom(field.getType())) {
                            field.set(o, list);
                        } else {
                            if (list.size() != 1) {
                                throw new InjectException("Not found or found more than 1 class that can be injected into the field " + field.toString());
                            } else {
                                field.set(o, list.get(0));
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException | IOException | InstantiationException e) {
            LOGGER.error(e.getMessage());
            throw new InjectException(e);
        }

    }
}
