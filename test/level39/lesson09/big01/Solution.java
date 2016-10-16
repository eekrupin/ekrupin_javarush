package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\Java\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));
        /*System.out.println("getNumberOfUniqueIPs: " + logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println("getUniqueIPs: " + logParser.getUniqueIPs(null, null));
        System.out.println("getIPsForUser: " + logParser.getIPsForUser("Amigo", null, new Date()));*/
        /*System.out.println("getIPsForEvent: " + logParser.getIPsForEvent(Event.DONE_TASK, null, new Date()));
        System.out.println("getIPsForStatus: " + logParser.getIPsForStatus(Status.ERROR, null, new Date()));*/

       /* System.out.println("task 4:");
        System.out.println(logParser.getNumberOfAllEvents(null, null));
        System.out.println(logParser.getAllEvents(null, null));
        System.out.println(logParser.getEventsForIP("192.168.100.2", null, null));
        System.out.println(logParser.getEventsForUser("Vasya Pupkin", null, null));
        System.out.println(logParser.getFailedEvents(null, null));
        System.out.println(logParser.getErrorEvents(null, null));
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18, null, null));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(18, null, null));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));
        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, null));*/

        /*System.out.println("task 5:");
        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get date"));
        System.out.println(logParser.execute("get event"));
        System.out.println(logParser.execute("get status"));*/

        //System.out.println("task 6:");
        //System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
        //System.out.println(logParser.execute("get status for date = \"30.01.2014 12:56:22\""));

        //System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin\""));
        //System.out.println(logParser.execute("get user for event = \"DONE_TASK\""));
        //System.out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));

//        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between\n" +
//                "\"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));

        System.out.println("task 7:");
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        //get ip for user = "Eduard Petrovich Morozko" and date between "11.12.2013 0:00:00" and "03.01.2014 23:59:59"

    }
}
