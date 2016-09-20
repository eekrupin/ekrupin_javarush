package com.javarush.test.level30.lesson02.task01;

import com.javarush.test.level28.lesson10.home01.Character;

/* Осваиваем методы класса Integer
Используя метод Integer.parseInt(String, int) реализуйте логику метода convertToDecimalSystem,
который должен переводить переданную строку в десятичное число и возвращать его в виде строки.
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int redix = 10;
        String str = s;
        if (s.substring(0,2).equals("0x")){
            redix = 16;
            str = s.substring(2);
        }
        else if (s.substring(0,2).equals("0b")){
            redix = 2;
            str = s.substring(2);
        }
        else if (s.substring(0,1).equals("0")){
            redix = 8;
            str = s.substring(1);
        }

        String num = "" + Integer.parseInt(str, redix);
        return num;
    }
}
