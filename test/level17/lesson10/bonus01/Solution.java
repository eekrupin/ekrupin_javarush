package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        if (args[0].equals("-u")){
            updatePeople(args[1], args[2], args[3], args[4]);
        }
        else if(args[0].equals("-c")){
            addPeople(args[1],args[2],args[3]);
        }
        else if(args[0].equals("-d")){
            deletePeople(args[1]);
        }
        else if(args[0].equals("-i")){
            infoPeople(args[1]);
        }
    }
    public static void updatePeople(String s1, String name, String s3, String s4)throws ParseException{
        int id = getID(s1);
        Sex sex = getSex(s3);
        Date db = getDB(s4);
        allPeople.get(id).setSex(sex);
        allPeople.get(id).setBirthDay(db);
        allPeople.get(id).setName(name);
    }
    public static void addPeople(String name, String s3, String s4) throws ParseException{
        Sex sex=getSex(s3);
        Date db=getDB(s4);
        if (sex==Sex.MALE){
            allPeople.add(Person.createMale(name,db));
            System.out.println(allPeople.size()-1);
        }
        if (sex==Sex.FEMALE){
            allPeople.add(Person.createFemale(name,db));
            System.out.println(allPeople.size()-1);
        }
    }
    public static void deletePeople(String s1){
        int id=getID(s1);
        allPeople.get(id).setName(null);
        allPeople.get(id).setBirthDay(null);
        allPeople.get(id).setSex(null);
    }
    public static void infoPeople(String s1){
        int id=getID(s1);
        String name=allPeople.get(id).getName();
        Sex sex=allPeople.get(id).getSex();
        String sx=null;
        if (sex==Sex.MALE){sx="м";}
        else if(sex==Sex.FEMALE) {sx="ж";}
        String dateB=null;
        if (!(allPeople.get(id).getBirthDay()==null)){
            dateB = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(allPeople.get(id).getBirthDay());
        }System.out.println(name+" "+sx+" "+dateB);
    }
    private static int getID(String s){
        return Integer.parseInt(s);
    }
    private static Sex getSex(String s)
    {   Sex sex=null;
        if ("м".equals(s)){sex=Sex.MALE;}
        else if ("ж".equals(s)){sex=Sex.FEMALE;}
        return sex;
    }
    private static Date getDB(String s)throws ParseException{
        return new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(s);
    }

  /*  public static void main(String[] args) throws ParseException {
        if (args.length==0) return;
        if (args[0].equals("-c")){
            if (getSex(args[2]).equals(Sex.MALE)) {
                allPeople.add(Person.createMale(args[1], getDay(args[3])));
                System.out.println(allPeople.size()-1);
            }
            else if (getSex(args[2]).equals(Sex.FEMALE)) {
                allPeople.add(Person.createFemale(args[1], getDay(args[3])));
                System.out.println(allPeople.size()-1);
            }

        }
        else if (args[0].equals("-u")){
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            person.setName(args[2]);
            person.setSex(getSex(args[3]));
            person.setBirthDay(getDay(args[4]));
        }
        else if (args[0].equals("-d")){
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            person.setBirthDay(null);
            person.setName(null);
            person.setSex(null);
        }
        else if (args[0].equals("-i")){
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            String bd = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(person.getBirthDay());
            String info = String.format("name %s sex %s bd %s", person.getName(), person.getSex().equals(Sex.MALE)?"м":"ж", bd);
            System.out.println(info);
        }
    }

    static Sex getSex(String s){
        Sex sex;
        if (s.equals("м"))sex = Sex.MALE;
        else sex = Sex.FEMALE;
        return sex;
    }

    static Date getDay(String s) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(s);
        return date;
    }*/

}
