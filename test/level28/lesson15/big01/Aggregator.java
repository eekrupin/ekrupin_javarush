package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;
import com.javarush.test.level28.lesson15.big01.view.View;


public class Aggregator {
    public static void main(String[] args) {
        Provider[] providers = {new Provider(new HHStrategy()), new Provider(new MoikrugStrategy())};
        View view = new HtmlView();
        Model model = new Model(view, providers);
        Controller controller = new Controller(model);
        view.setController(controller);
        ((HtmlView)view).userCitySelectEmulationMethod();

    }
}
