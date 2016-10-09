package com.javarush.test.level35.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals("C://pathToClasses/");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {

        Set<Animal> result = new HashSet<>();

        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";

        File dir = new File(pathToAnimals);
        String[] modules = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".class");
            }
        });


        for (String stringModule : modules) {
            try {
                final String finalPathToAnimals = pathToAnimals;
                ClassLoader classLoader = new ClassLoader() {
                    @Override
                    protected Class<?> findClass(String name) throws ClassNotFoundException {
                        try {
                            byte[] b = Files.readAllBytes(Paths.get(finalPathToAnimals + name + ".class"));
                            return defineClass(null, b, 0, b.length);
                        } catch (IOException e) {
                            return super.findClass(name);
                        }
                    }
                };
                String nameModule = stringModule.substring(0, stringModule.length() - 6);
                Class clazz = classLoader.loadClass(nameModule);

                boolean hasInteface = false;
                Class[] interfaces = clazz.getInterfaces();
                for (Class anInterface : interfaces) {
                    if (Animal.class.equals(anInterface)) {
                        hasInteface = true;
                        break;
                    }
                }

                if (!hasInteface) continue;

                boolean hasConstructor = false;
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor constructor : constructors) {
                    if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterTypes().length == 0) {
                        hasConstructor = true;
                        break;
                    }
                }

                if (!hasConstructor) continue;

                result.add((Animal) clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
