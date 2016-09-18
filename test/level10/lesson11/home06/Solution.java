package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Solution
{
    public static void main(String[] args)
    {


    }

    public static class Human
    {
        public String name;
        public int age;
        public int weight;
        public int height;
        public boolean sex;
        public Human kids;

        public  Human(String name)
        {
            this.name = name;
        }
        public Human(String name, int age)
        {
            this.name = name;
            this.age = age;
        }
        public  Human(String name, int age, boolean sex)
        {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
        public  Human(String name, int age, boolean sex, Human kids)
        {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.kids = kids;
        }
        public  Human(String name, int age, int height, int weight, boolean sex, Human kids)
        {
            this.name = name;
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.sex = sex;
            this.kids = kids;
        }
        public  Human(String name, int age, Human kids)
        {
            this.name = name;
            this.age = age;
            this.kids = kids;
        }
        public  Human(String name, boolean sex)
        {
            this.name = name;
            this.sex = sex;
        }
        public  Human(String name, Human kids)
        {
            this.name = name;
            this.kids = kids;
        }
        public  Human(String name, int height, int weight)
        {
            this.name = name;
            this.height = height;
            this.weight = weight;
        }

        public  Human(String name, boolean sex, Human kids)
        {
            this.name = name;
            this.sex = sex;
            this.kids = kids;
        }
    }
}
