package com.javarush.test.level22.lesson13.task03;

import java.util.HashMap;
import java.util.Map;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {

/*    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println();
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
        System.out.println(checkTelNumber("65(543)66-655-55"));


    }*/

    public static void main(String[] args)
    {
        Map<String, Boolean> tests = new HashMap();
        tests.put("38xx501A34567", false);
        tests.put("3805012345", true);
        tests.put("+380501234567", true);
        tests.put("++380501234567", false);
        tests.put("+38(0501234567", false);
        tests.put("+38)050(1234567", false);
        tests.put("+38(050)1234567", true);
        tests.put("+38(05)1234567", false);
        tests.put("(3)80501234567", false);
        tests.put("(380)501234567", false);
        tests.put("380-50123-45", true);
        tests.put("(380)501-234567", false);
        tests.put("(38-0)501234567", false);
        tests.put("380-(501)234567", false);
        tests.put("380(50-1)234567", false);
        tests.put("380(501)(23)4567", false);
        tests.put("+38050123(456)7", true);
        tests.put("+38050123(456)76", false);
        tests.put("+380501234(567)", false);
        tests.put("3-805-0123-45", false);
        tests.put("-3805-012345", false);
        tests.put("3805-012345-", false);
        tests.put("+380501234567", true);
        tests.put("+38(050)1234567", true);
        tests.put("+38(05)01234567", false);
        tests.put("+38(0501)234567", false);
        tests.put("+38050123-45-67", true);
        tests.put("050123-4567", true);
        tests.put("+38)050(1234567", false);
        tests.put("+38(050)1-23-45-6-7", false);
        tests.put("050ххх4567", false);
        tests.put("050123456", false);
        tests.put("+38-(050)1234567", false);
        tests.put("+38((050)1234567", false);
        tests.put("+5(0--5)1234567", false);
        tests.put("7-4-4123689-5", false);
        tests.put("1-23456789-0", true);
        tests.put("+38051202(345)7", true);
        tests.put("+38051202(345)-7", true);
        tests.put("+-313450531202", true);
        tests.put("+313450--531202", false);
        tests.put("", false);
        tests.put("(050)34(125)6-7", false);
        tests.put("+38)050(1234567", false);
        tests.put("+3+8(050)1234567", false);
        tests.put("+313450531202-", false);
        tests.put("(380)(050)3567", false);
        tests.put("050123456", false);
        tests.put("+38(050)1-23-45-6-7", false);
        tests.put("+38050123-45-67", true);
        tests.put("(345)0512027", true);
        tests.put("+38050123-45-6789", false);
        tests.put("050123-45678", false);
        tests.put("+38)050(1234567", false);
        tests.put("050123456", false);
        tests.put("+38-(050)1234567", false);
        tests.put("(380)(050)3567", false);
        tests.put("+38((050)1234567", false);
        tests.put("+5(0--5)1234567", false);
        tests.put("7-4-4123689-5", false);
        tests.put("+(012)--123456789", false);
        tests.put("7-4-4123689-5", false);
        tests.put("-38-(050)34567", false);
        tests.put("+38(050)1-23-45-6-7", false);
        tests.put("(050)34125)67", false);
        tests.put("050123456", false);
        tests.put("050123-4567", true);
        tests.put("+38((050)1234567", false);
        tests.put("-3805012345-67", true);
        tests.put("-12345678910", false);
        tests.put("+38(050)1234567-", false);
        tests.put("+38050(123)(456)7", false);
        tests.put("050С…С…С…4567", false);
        tests.put("0-50123-4567", true);
        tests.put("+38(050)12-34-567", true);
        tests.put(null, false);
        tests.put("", false);
        tests.put("+380501234567", true);
        tests.put("+3805012345q67", false);
        tests.put("+3805012345 67", false);
        tests.put("+3805012345.67", false);
        tests.put("+3805012345,67", false);
        tests.put("1-23456789-0", true);
        tests.put("1-23(456)789-0", false);
        tests.put("1-234567(89-0)", false);
        tests.put("1-2345678(9-0)", false);
        tests.put("(1-2)3456789-0", false);
        tests.put("+38051202(345)-7", true);
        tests.put("(345)0512027", true);
        tests.put("+-313450531202", true);
        tests.put("+-313450531202-", false);
        tests.put("+380501212334567", false);
        tests.put("+3805012asd34567", false);
        tests.put("+38(050)1234567", true);
        tests.put("+38(150)1234567", true);
        tests.put("+3+8(050)1234567", false);
        tests.put("+38(050)12-34-567", true);
        tests.put("+38(050)12-34567", true);
        tests.put("+38(050)112-34567", false);
        tests.put("+38(050)12-34(5)67", false);
        tests.put("+3(8)(050)12-34567", false);
        tests.put("+38050123-45-67", true);
        tests.put("+38050123-45-6789", false);
        tests.put("050123-4567", true);
        tests.put("050123-45678", false);
        tests.put("+38)050(1234567", false);
        tests.put("+38(050)1-23-45-6-7", false);
        tests.put("050ххх4567", false);
        tests.put("050123456", false);
        tests.put("(0)501234567", false);
        tests.put("+38-(050)1234567", false);
        tests.put("38-(050)34567", false);
        tests.put("-38-(050)34567", false);
        tests.put("38-(050)34567-", false);
        tests.put("38(050)3(45)67", false);
        tests.put("(380)(050)3567", false);
        tests.put("+38(380)(050)3567", false);
        tests.put("8(380)(050)367", false);
        tests.put("8(380)4(050)67", false);
        tests.put("+38((050)1234567", false);
        tests.put("+5(0--5)1234567", false);
        tests.put("7-4-4123689-5", false);
        tests.put("+(012)123456789", true);
        tests.put("+(012)1-2345678-9", true);
        tests.put("+(012)-12345678-9", true);
        tests.put("+(012)-1-23456789", true);
        tests.put("+(012)1234567", false);
        tests.put("+(01-2)123456789", false);
        tests.put("+(012)12345678--9", false);
        tests.put("+(012)--123456789", false);
        tests.put("+38(050)-1234567", true);
        tests.put("+38050(123)(456)7", false);
        tests.put("051202(345)-7", true);
        tests.put("+38051202(345)7", true);
        tests.put("+380501234(567)", false);
        tests.put("+38050123425-1", true);
        tests.put("+380501234251", true);



        for (Map.Entry<String, Boolean> pair: tests.entrySet()){
            String key = pair.getKey();
            boolean value = pair.getValue();
            if (value != checkTelNumber(key))
                System.out.printf("Test fail: %s\t\t\t- %b (must be %b)\n", key, checkTelNumber(key), value);
            else
                System.out.printf("Test OK: %s\t\t\t- %b (must be %b)\n", key, checkTelNumber(key), value);
        }


    }

    public static boolean checkTelNumber(String telNumber) {

        if (telNumber==null || telNumber.isEmpty()) return false;

        //+12d = ^\+(?!(?:.*-){3})(?!.*--)(?=[^()]*\([\d]{3}\)[^()]*\d$|[^()]*\d$)(?!.*-.*[()])(?:[()-]*\d){12}[()-]*
        //10d = (?!(?:.*-){3})(?!.*--)(?=[^()]*\([\d]{3}\)[^()]*\d$|[^()]*\d$)(?!.*-.*[()])(?:[()-]*\d){10}[()-]*
        boolean result = telNumber.matches("(^\\+(?!(?:.*-){3})(?!.*--)(?=[^()]*\\([\\d]{3}\\)[^()]*\\d$|[^()]*\\d$)(?!.*-.*[()])(?:[()-]*\\d){12}[()-]*)|((?!^-)(?!(?:.*-){3})(?!.*--)(?=[^()]*\\([\\d]{3}\\)[^()]*\\d$|[^()]*\\d$)(?!.*-.*[()])(?:[()-]*\\d){10}[()-]*)");

        //с исправлением -3805012345-67


        return result;
    }
}
