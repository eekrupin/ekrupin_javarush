package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 25.09.2016.
 */
public class MoikrugStrategy implements Strategy{

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {

        List<Vacancy> list = new ArrayList<>();

        int page = 0;
        while (true) {
            Document doc;
            try {
                doc = getDocument(URLEncoder.encode(searchString, "UTF-8"), page++);


                Elements elements = doc.getElementsByClass("job");
                if (elements.size() == 0) break;
                for (Element element : elements) {

                    try {

                        Element elementTitle = element.getElementsByClass("title").first();

                        String salary = "";
                        Element elementCompensation = element.select(".salary").first();
                        if (elementCompensation != null)
                            salary = elementCompensation.text();

                        String city = element.select(".location").first().text();
                        String companyName = element.select(".company_name").first().text();
                        String siteName = "https://moikrug.ru/";
                        String url = "https://moikrug.ru" + elementTitle.getElementsByTag("a").attr("href");

                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(elementTitle.text());
                        vacancy.setSalary(salary);
                        vacancy.setCity(city);
                        vacancy.setCompanyName(companyName);
                        vacancy.setSiteName(siteName);
                        vacancy.setUrl(url);

                        list.add(vacancy);
                    }
                    catch (Exception e){

                    }

                }
            } catch (IOException e) {
            }
        }

        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                .referrer("http://google.ru")
                .get();
    }

}
