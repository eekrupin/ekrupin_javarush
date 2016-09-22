package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int part = (int)(Math.random()*3)+1;
        for (int i = 1; i <=8 ; i++) {
            int num=0;
            switch (part){
                case 1: num = (int)(Math.random()*(58-48))+48; break;
                case 2: num = (int)(Math.random()*(91-65))+65; break;
                case 3: num = (int)(Math.random()*(123-97))+97; break;
            }
            part++;
            part = part==4? 1: part;
            stream.write(num);
        }
        return stream;
    }
}
