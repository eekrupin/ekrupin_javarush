package com.javarush.test.level28.lesson15.big01.model;


import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 25.09.2016.
 */
public class Model {
    View view;
    Provider[] providers;

    public Model(View view, Provider[] providers) throws IllegalArgumentException{

        if (view == null || providers.length==0) throw new IllegalArgumentException();

        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city){
        List<Vacancy> allVacancies = new ArrayList<>();
        for (Provider provider : providers) {
            List<Vacancy> vacancies = provider.getJavaVacancies(city);
            allVacancies.addAll(allVacancies.size(), vacancies);
        }
        view.update(allVacancies);
    }

}
