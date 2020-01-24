package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Class<? extends Resume> resumeClass = resume.getClass();
        //https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
        Field field = resumeClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");

        Method method = resumeClass.getMethod("toString");
        Object result = method.invoke(resume);
        System.out.println(result);
    }
}