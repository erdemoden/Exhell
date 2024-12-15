package org.woh.annotations;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;

public class AnnotationProcessor {
    private List<Class<?>> classes = new LinkedList<>();

    public static List<Class<?>> getAllClasses() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources("");
        List<File> dirs = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, ""));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + (packageName.isEmpty() ? "" : ".") + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + (packageName.isEmpty() ? "" : ".") + file.getName().substring(0, file.getName().length() - 6);
                Class<?> cls = Class.forName(className);
                Annotation[] annotations = cls.getAnnotations();
                Arrays.stream(annotations).findFirst().ifPresent(annotation -> {
                    if (annotation.annotationType().getSimpleName().equals("HellDTO")) {
                        classes.add(cls);
                    }
                });
            }
        }
        return classes;
    }
}
