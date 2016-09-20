package com.javarush.test.level30.lesson02.home01;

/* Конвертер систем счислений
Реализуйте логику метода convertNumberToOtherNumerationSystem, который должен переводить число number.getDigit()
из одной системы счисления(numerationSystem) в другую (expectedNumerationSystem)
бросьте NumberFormatException, если переданное число некорректно, например, число "120" с системой счисления 2
Валидация для - number.getDigit() - целое не отрицательное
Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {

        int numSys = number.getNumerationSystem().getNumerationSystemIntValue();
        String digit = number.getDigit();
        int numSysTo = expectedNumerationSystem.getNumerationSystemIntValue();

        ValidateDigit(digit, numSys);

        int digDec = 0;
        for (int i=0; i<digit.length(); i++){
            String strDig = "" + digit.charAt(digit.length()-1-i);
            int dig;
           if (strDig.equals("A")) dig=10;
           else if (strDig.equals("B")) dig=11;
           else if (strDig.equals("C")) dig=12;
           else if (strDig.equals("D")) dig=13;
           else if (strDig.equals("E")) dig=14;
           else if (strDig.equals("F")) dig=15;
           else dig = Integer.parseInt(strDig);

            digDec+=dig*(int)Math.pow(numSys, i);
        }
        String strDec = String.valueOf(digDec);

        StringBuilder sb = new StringBuilder();
        int rest = Integer.parseInt(strDec);
        while (rest>0){
            int restOfDiv = rest%numSysTo;
            switch (restOfDiv){
                case 10: sb.append("A"); break;
                case 11: sb.append("B"); break;
                case 12: sb.append("C"); break;
                case 13: sb.append("D"); break;
                case 14: sb.append("E"); break;
                case 15: sb.append("F"); break;
                default: sb.append(restOfDiv);
            }
            rest = (rest-restOfDiv)/numSysTo;
        }

        Number resNumber = new Number(expectedNumerationSystem, sb.reverse().toString());

        return resNumber;
    }

    private static void ValidateDigit(String digit, int numSys) {

        if (!digit.matches("[0-9,A-F]*")) throw new NumberFormatException();

        digit = digit.toUpperCase();
        for (int i = 0; i <digit.length() ; i++) {
            Character ch = digit.charAt(i);
            int num;
            if (ch == 'A') num = 10;
            else if (ch == 'B') num = 11;
            else if (ch == 'C') num = 12;
            else if (ch == 'D') num = 13;
            else if (ch == 'E') num = 14;
            else if (ch == 'F') num = 15;
            else num = Integer.parseInt(String.valueOf(ch));
            if (num>=numSys) throw new NumberFormatException();
        }

    }
}
