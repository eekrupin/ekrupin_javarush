package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<Thread>(5);

    static {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    public static void main(String[] args) {

//        for (Thread thread:threads) {
//            thread.start();
//        }

        threads.get(3).start();
        ((Thread4)threads.get(3)).showWarning();

    }


    public static class Thread1 extends Thread{

        public void run() {while (true){
            }
        }
    }

    public static class Thread2 extends Thread{

        public void run() {
            while (true){
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("InterruptedException");
                    break;
                }
            }
        }
    }

    public static class Thread3 extends Thread{

        public void run() {
            while (true){
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Thread4 extends Thread implements Message{

        public void run() {
            while (!isInterrupted()){
            }
        }

        public void showWarning() {
            interrupt();
            try {
                join();
            } catch (Exception ignore) {/*NOP*/}
        }
    }

    public static class Thread5 extends Thread{

         public void run() {
            int sum = 0;
            String value="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                try {
                    value = reader.readLine();
                    if (value.equals("N")){
                        System.out.println(sum);
                        reader.close();
                        break;
                    }
                    else sum+=Integer.parseInt(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}


