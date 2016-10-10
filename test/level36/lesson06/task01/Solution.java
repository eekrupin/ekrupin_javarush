package com.javarush.test.level36.lesson06.task01;


import java.lang.reflect.*;
import java.util.Collections;

/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }


    public static Class getExpectedClass() {

        return Collections.EMPTY_LIST.getClass();

//        Class[] classes = Collections.class.getDeclaredClasses();
//        for (Class aClass : classes) {
//            int modifier = aClass.getModifiers();
//            if (!Modifier.isPrivate(modifier)) continue;
//            if (!Modifier.isStatic(modifier)) continue;
//
//            Class[] interfaces = aClass.getInterfaces();
//            boolean hasList = interfacesHasLis(interfaces);
//            if (!hasList && aClass.getSuperclass()!=null) {
//                hasList = interfacesHasLis(aClass.getSuperclass().getInterfaces());
//            }
//            if (!hasList) continue;
//
//            boolean hasException = methodshasException(aClass);
//
//            if (!hasException) continue;
//
//            System.out.println(aClass.getSimpleName());
//
//        }
//        return null;
    }

    private static boolean methodshasException(Class aClass) {
        Method[] methods = aClass.getMethods();
        boolean methodshasException = false;

        for (Method method : methods) {
            if (!method.getName().equals("get")) continue;
            //if (method.getGenericExceptionTypes().equals("IndexOutOfBoundsException"))
            /*for (Class exceptionClass : method.getExceptionTypes()) {
                System.out.println(exceptionClass);
            }*/



        }
        return methodshasException;
    }

    private static boolean interfacesHasLis(Class[] interfaces) {
        boolean hasList = false;
            for (Class anInterface : interfaces) {
                if (anInterface.getName().contains("List")) {
                    hasList = true;
                    break;
                }
            }
        return hasList;
    }
}
