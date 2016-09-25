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
 * Created by ekrupin on 23.09.2016.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {

        List<Vacancy> list = new ArrayList<>();

        int page = 0;
        while (true) {
            Document doc;
            try {
                doc = getDocument(URLEncoder.encode(searchString, "UTF-8"), page++);


                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break;
                for (Element element : elements) {


                    Element elementTitle = element.select("[data-qa=vacancy-serp__vacancy-title]").first();

                    String salary = "";
                    Element elementCompensation = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    if (elementCompensation != null)
                        salary = elementCompensation.text();

                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();
                    String companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();
                    String siteName = "http://hh.ua/";
                    String url = elementTitle.attr("href");

                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(elementTitle.text());
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);

                    list.add(vacancy);

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
