package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = File.createTempFile("E:\\Java\\your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            if (ivanov.name.equals(somePerson.name) && ivanov.assets.get(0).getName().equals(somePerson.assets.get(0).getName()) &&
                    ivanov.assets.get(1).getName().equals(somePerson.assets.get(1).getName())) System.out.println("OK");
            else System.out.println("False");
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter print = new PrintWriter(outputStream);
            print.println("<Human>");
            print.println(name);
            print.println(assets.size());
            for (Asset asset: assets) {
                print.println(asset.getName());
                print.println(asset.getPrice());
            }
            print.println("</Human>");
            print.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Human human;
            while (reader.ready()){
                String line = reader.readLine();
                if (line.equals("<Human>")) {
                    name = reader.readLine();
                    int sizeAssets = Integer.parseInt(reader.readLine());
                    for (int row = 1; row <= sizeAssets; row++) {
                        Asset asset = new Asset(reader.readLine());
                        asset.setPrice(Double.parseDouble(reader.readLine()));
                        assets.add(asset);
                    }      
                    if (reader.readLine().equals("</Human>")){
                        break;
                    }
                }
            }

        }
    }
}
