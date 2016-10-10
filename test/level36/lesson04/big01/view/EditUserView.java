package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.Util;
import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.dao.UserDao;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

/**
 * Created by ekrupin on 10.10.2016.
 */
public class EditUserView implements View{

    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        System.out.println("User to be edited:");
        System.out.println("\t" + modelData.getActiveUser());
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventUserDeleted(long id) {
        controller.onUserDelete(id);
    }

    public void fireEventUserChanged(String name, long id, int level){
        controller.onUserChange(name, id, level);
    }

}
