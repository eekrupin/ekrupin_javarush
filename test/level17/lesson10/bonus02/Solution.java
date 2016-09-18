package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
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
            int block = 4;
            String[] argsBlock = new String[block];
            for (int index = 1; index<args.length; index++) {
                argsBlock[index%block==0?block-1:index%block-1] = args[index];
                if (index%block==0) updatePeople(argsBlock[0], argsBlock[1], argsBlock[2], argsBlock[3]);
             }
        }
        else if(args[0].equals("-c")){

            int block = 3;
            String[] argsBlock = new String[block];
            for (int index = 1; index<args.length; index++) {
                argsBlock[index%block==0?block-1:index%block-1] = args[index];
                if (index%block==0) addPeople(argsBlock[0], argsBlock[1], argsBlock[2]);
            }

        }
        else if(args[0].equals("-d")){
            for (int index = 1; index<args.length; index++) {
                deletePeople(args[index]);
            }
        }
        else if(args[0].equals("-i")){
            for (int index = 1; index<args.length; index++) {
                infoPeople(args[index]);
            }
        }
    }
    public synchronized static void updatePeople(String s1, String name, String s3, String s4)throws ParseException{
        int id = getID(s1);
        Sex sex = getSex(s3);
        Date db = getDB(s4);
        allPeople.get(id).setSex(sex);
        allPeople.get(id).setBirthDay(db);
        allPeople.get(id).setName(name);
    }
    public synchronized static void addPeople(String name, String s3, String s4) throws ParseException{
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
    public synchronized static void deletePeople(String s1){
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
    private static Date getDB(String s)throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(s);
    }
}
