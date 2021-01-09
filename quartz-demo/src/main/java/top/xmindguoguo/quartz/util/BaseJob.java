package top.xmindguoguo.quartz.util;

import org.quartz.Job;

import java.lang.annotation.Annotation;

public abstract class BaseJob implements Job {

    public static String getscheduledId(Class<?> classObject) {
        ScheduledName scheduled = classObject.getAnnotation(ScheduledName.class);
        return scheduled.id();
    }

    public Annotation getClassAnnotation(Class<?> scannerClass, Class<? extends Annotation> allowInjectClass) {

        if (!scannerClass.isAnnotationPresent(allowInjectClass)) {
            return null;
        }

        return scannerClass.getAnnotation(allowInjectClass);
    }

}
