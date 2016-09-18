package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
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

            JavaRush javaRush = new JavaRush();

            User u1 = new User();
            u1.setMale(true);
            u1.setFirstName("Вася");
            u1.setCountry(User.Country.RUSSIA);
            u1.setBirthDate(new Date());
            javaRush.users.add(u1);

            User u2 = new User();
            u2.setMale(true);
            u2.setFirstName("Петя");
            u2.setCountry(User.Country.OTHER);
            u2.setBirthDate(new Date());
            javaRush.users.add(u2);

            javaRush.save(outputStream);
            outputStream.flush();
            outputStream.close();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            if (loadedObject.users.get(0).getCountry().equals(u1.getCountry()) &&
                    loadedObject.users.get(0).getFirstName().equals(u1.getFirstName()) &&
                loadedObject.users.get(1).getCountry().equals(u2.getCountry()) &&
                    loadedObject.users.get(1).getFirstName().equals(u2.getFirstName())
                    ){
                System.out.println("Ok");

            }
            else System.out.println("False");

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush{
        public List<User> users = new ArrayList<>();


        public void save(OutputStream outputStream) throws Exception {
            PrintWriter print = new PrintWriter(outputStream);
            print.println(users.size());
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                print.println("<User>");
                print.println(user.getFirstName());
                print.println(user.getLastName());
                print.println(user.getBirthDate().getTime());
                print.println(user.isMale());
                print.println(user.getCountry());
            }
            print.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            User user;
            while (reader.ready()){
                int size = Integer.parseInt(reader.readLine());
                for (int row = 1; row <= size; row++) {
                    String line = reader.readLine();
                    if (line.equals("<User>")) {
                        user = new User();
                        users.add(user);

                        user.setFirstName(reader.readLine());
                        user.setLastName(reader.readLine());
                        user.setBirthDate(new Date(Long.parseLong(reader.readLine())));
                        user.setMale(Boolean.parseBoolean(reader.readLine()));
                        user.setCountry(User.Country.valueOf(reader.readLine()));
                    }
                }
            }
        }

    }
}
