package com.javarush.test.level23.lesson04.task01;

/* Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {

        Solution[] sol = new Solution[2];
        sol[0] = new Solution();
        sol[0].innerClasses[0] = sol[0].new InnerClass();
        sol[0].innerClasses[1] = sol[0].new InnerClass();

        sol[1] = new Solution();
        sol[1].innerClasses[0] = sol[1].new InnerClass();
        sol[1].innerClasses[1] = sol[1].new InnerClass();

        return sol;
    }
}
