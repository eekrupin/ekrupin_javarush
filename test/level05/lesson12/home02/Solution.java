package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        Man man1 = new Man("Федя", 22, "Блабла стриит 1");
        Man man2 = new Man("Петя", 13, "Блабла стриит 2");

        Man woman1 = new Man("Маша", 28, "Блабла стриит 3");
        Man woman2 = new Man("Катя", 36, "Блабла стриит 4");

        System.out.println(man1);
        System.out.println(man2);
        System.out.println(woman1);
        System.out.println(woman2);

        //выведи их на экран тут
    }

    public static class Man{
        private String name;
        private int age;
        private String address;

        Man(String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }

        Man(String name, int age){
            this.name = name;
            this.age = age;
            this.address = null;
        }
        Man(String name){
            this.name = name;
            this.age = 18;
            this.address = null;
        }
        Man(String name, String address){
            this.name = name;
            this.age = 18;
            this.address = address;
        }

        Man(){
            name = null;
            age = 18;
            address = null;
        }

        public String toString(){
            return name + " " + age + " " + address;
        }

    }

    public static class Woman{
        private String name;
        private int age;
        private String address;

        Woman(String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }

        Woman(String name, int age){
            this.name = name;
            this.age = age;
            this.address = null;
        }
        Woman(String name){
            this.name = name;
            this.age = 18;
            this.address = null;
        }
        Woman(String name, String address){
            this.name = name;
            this.age = 18;
            this.address = address;
        }

        Woman(){
            name = null;
            age = 18;
            address = null;
        }

        public String toString(){
            return name + " " + age + " " + address;
        }

    }

}
