package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.*;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by Евгений on 25.09.2016.
 */
public class HtmlView implements View {
    private final String filePath;
    private Controller controller;

    {
        filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";
    }

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private String getUpdatedFileContent(List<Vacancy> vacancies){
        String content = "";
        try {


        Document doc = getDocument();
        Element templateElement = doc.select(".template").first();

        Element patternElement  = templateElement.clone();

        patternElement.removeAttr("style");
        patternElement.removeClass("template");

        doc.select("tr[class=vacancy]").remove();

        for (Vacancy vacancy : vacancies) {
            Element vacancyElement = patternElement.clone();
            vacancyElement.getElementsByClass("city").first().text(vacancy.getCity());
            vacancyElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
            vacancyElement.getElementsByClass("salary").first().text(vacancy.getSalary());

            Element a = vacancyElement.getElementsByTag("a").first();
            a.text(vacancy.getTitle());
            a.attr("href", vacancy.getUrl());

            templateElement.before(vacancyElement.outerHtml());

        }

            content = doc.html().toString();

        }
        catch (Exception e){
            e.printStackTrace();
            content = "Some exception occurred";
        }

        return content;

    }

    protected Document getDocument() throws IOException{
        Document doc = Jsoup.parse(new File(filePath), "UTF-8");
        return doc;
    }

    private void updateFile(String s) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(s.getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        outputStream.close();
    }


    @Override
    public void setController(Controller controller) {

        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

}
