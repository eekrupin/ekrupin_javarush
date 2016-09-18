package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int i1 = Integer.parseInt(reader.readLine());
        int i2 = Integer.parseInt(reader.readLine());

        ArrayList<Integer> dividers1 = getdividers(i1);
        ArrayList<Integer> dividers2 = getdividers(i2);

        showNOD(dividers1, dividers2);

    }

    public static ArrayList<Integer> getdividers(int value) {
        ArrayList<Integer> dividers = new ArrayList<>();

        dividers.add(1);
        int rest = new Integer(value);
        while (rest>1){
            for (int i = 2; i <=rest ; i++) {
                if (rest/(i*1.0)==rest/i) {
                    rest = rest/i;
                    dividers.add(i);
                    break;
                }
            }
        }

        return dividers;

    }

    public static void showNOD(ArrayList<Integer> dividers1, ArrayList<Integer> dividers2){
        ArrayList<Integer> first = dividers1.size() > dividers2.size() ? dividers1 : dividers2;
        ArrayList<Integer> second = dividers1.size() > dividers2.size() ? dividers2 : dividers1;

        ArrayList<Integer> total = new ArrayList<>();

        for (int divid: first) {
            for (int ind = 0;ind < second.size(); ind++) {
                if (second.get(ind) == divid) {
                    total.add(divid);
                    second.remove(ind);
                    break;
                }
            }
        }

        int NOD = 1;
        for (int value:total) {
            NOD = NOD * value;
        }

        System.out.println(NOD);

    }

}
