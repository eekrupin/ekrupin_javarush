package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\Java\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));
        /*System.out.println("getNumberOfUniqueIPs: " + logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println("getUniqueIPs: " + logParser.getUniqueIPs(null, null));
        System.out.println("getIPsForUser: " + logParser.getIPsForUser("Amigo", null, new Date()));*/
        System.out.println("getIPsForEvent: " + logParser.getIPsForEvent(Event.DONE_TASK, null, new Date()));
        System.out.println("getIPsForStatus: " + logParser.getIPsForStatus(Status.ERROR, null, new Date()));
    }
}
