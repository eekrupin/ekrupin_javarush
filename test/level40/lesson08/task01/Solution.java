package com.javarush.test.level40.lesson08.task01;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* Отправка GET-запроса через сокет
Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
Адрес сервера и параметры для GET-запроса получи из параметра url.
Порт используй дефолтный для http.
Классы HttpURLConnection, HttpClient и т.д. не использовать.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {

            Socket socket = new Socket(url.getHost(), url.getDefaultPort());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("GET " + url.getPath() + " HTTP/1.1");
            printWriter.println("Host: " + url.getHost());
            printWriter.println("Connection: close");
            printWriter.println();
            printWriter.flush();

            InputStream inputStream = socket.getInputStream();

            //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}