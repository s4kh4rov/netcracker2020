package com.netcracker.contractsProject.annotations;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Configuration(packages = {"com.netcracker.contractsProject"})
public class Injector {

    /**
     * Injects dependencies into the fields of the passed object.
     *
     * @param o object in the fields of which you want to inject the dependency
     */
    public static <T> void inject(T o) throws IllegalAccessException, InstantiationException, IOException {
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
                            try {
                                throw new Exception("Not found or found more than 1 class that can be injected into the field " + field.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            field.set(o, list.get(0));
                        }
                    }
                }
            }
        }
    }
}
