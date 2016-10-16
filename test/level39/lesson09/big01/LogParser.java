package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private ArrayList<ItemLog> logs = new ArrayList<>();

    public LogParser(Path logDir) {
        this.logDir = logDir;
        LoadLogs();
    }

    private void LoadLogs() {
        File[] files = logDir.toFile().listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".log");
            }
        });
        logs.clear();
        for (File file : files) {
            LoadFileLogs(file);
        }
        Collections.sort(logs, new Comparator<ItemLog>() {
            @Override
            public int compare(ItemLog o1, ItemLog o2) {
                return o1.date.compareTo(o2.date);
            }
        });
    }

    private void LoadFileLogs(File file) {
        try {
            BufferedReader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());
            String line = null;
            while ((line=reader.readLine())!=null){
                ItemLog itemLog = getItemLog(line);
                logs.add(itemLog);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private ItemLog getItemLog(String line) throws ParseException {

        String[] params  = line.split("\\t");
        String ip = params[0];
        String user  = params[1];
        Date date = getDate(params[2]);
        String[] partsOfEvent = params[3].split(" ");
        Event event = Event.valueOf(partsOfEvent[0]);
        int task = 0;
        if (event==Event.SOLVE_TASK || event==Event.DONE_TASK) {
            task = Integer.parseInt(partsOfEvent[1]);
        }
        Status status = Status.valueOf(params[4]);
        ItemLog itemLog = new ItemLog(ip, user, date, event, task, status);

        return itemLog;
    }

    private Date getDate(String param) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return dateFormat.parse(param);
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> set = getUniqueIPs(after, before);
        return set.size();
    }

    private boolean isDateAccept(Date after, Date before, Date date) {
        return (after==null || date.after(after) || date.equals(after)) &&
        (before==null || date.before(before) || date.equals(before));
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (isDateAccept(after, before, date)) set.add(itemLog.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (itemLog.user.equalsIgnoreCase(user) && isDateAccept(after, before, date)) set.add(itemLog.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (event.equals(itemLog.event) && isDateAccept(after, before, date)) set.add(itemLog.ip);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (status.equals(itemLog.status) && isDateAccept(after, before, date)) set.add(itemLog.ip);
        }
        return set;
    }

    private class ItemLog{
        String ip;
        String user;
        Date date;
        Event event;
        int task;
        Status status;

        public ItemLog(String ip, String user, Date date, Event event, int task, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.task = task;
            this.status = status;
        }

    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getAllUsers().size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (user.equals(itemLog.user) && isDateAccept(after, before, date)) set.add(itemLog.event);
        }
        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (ip.equals(itemLog.ip) && isDateAccept(after, before, date)) set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.LOGIN.equals(itemLog.event) && isDateAccept(after, before, date)) set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.DOWNLOAD_PLUGIN.equals(itemLog.event) && isDateAccept(after, before, date)) set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.WRITE_MESSAGE.equals(itemLog.event) && isDateAccept(after, before, date)) set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.SOLVE_TASK.equals(itemLog.event) && isDateAccept(after, before, date)) set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.SOLVE_TASK.equals(itemLog.event) && itemLog.task==task && isDateAccept(after, before, date)) set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.DONE_TASK.equals(itemLog.event) && isDateAccept(after, before, date)) set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.DONE_TASK.equals(itemLog.event) && itemLog.task==task && isDateAccept(after, before, date)) set.add(itemLog.user);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (user.equals(itemLog.user) && event.equals(itemLog.event) && isDateAccept(after, before, date)) set.add(itemLog.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Status.FAILED.equals(itemLog.status) && isDateAccept(after, before, date)) set.add(itemLog.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Status.ERROR.equals(itemLog.status) && isDateAccept(after, before, date)) set.add(itemLog.date);
        }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date minDate = null;

        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (user.equals(itemLog.user) && Event.LOGIN.equals(itemLog.event) && isDateAccept(after, before, date)) {
                if (minDate==null || minDate.after(itemLog.date)) minDate = itemLog.date;
            }
        }
        return minDate;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date minDate = null;
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (user.equals(itemLog.user) && Event.SOLVE_TASK.equals(itemLog.event) && task == itemLog.task && isDateAccept(after, before, date)) {
                if (minDate == null || minDate.after(itemLog.date)) minDate = itemLog.date;
            }
        }
        return minDate;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (user.equals(itemLog.user) && Event.DONE_TASK.equals(itemLog.event) && task == itemLog.task && isDateAccept(after, before, date)) {
                return itemLog.date;
            }
        }
        return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (user.equals(itemLog.user) && Event.WRITE_MESSAGE.equals(itemLog.event) && isDateAccept(after, before, date)) {
                set.add(itemLog.date);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (user.equals(itemLog.user) && Event.DOWNLOAD_PLUGIN.equals(itemLog.event) && isDateAccept(after, before, date)) {
                set.add(itemLog.date);
            }
        }
        return set;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        Set<Event> set = getAllEvents(after, before);
        return set.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (isDateAccept(after, before, date)) set.add(itemLog.event);
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (ip.equals(itemLog.ip) && isDateAccept(after, before, date)) set.add(itemLog.event);
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (user.equals(itemLog.user) && isDateAccept(after, before, date)) set.add(itemLog.event);
        }
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Status.FAILED.equals(itemLog.status) && isDateAccept(after, before, date)) set.add(itemLog.event);
        }
        return set;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Status.ERROR.equals(itemLog.status) && isDateAccept(after, before, date)) set.add(itemLog.event);
        }
        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int count = 0;
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.SOLVE_TASK.equals(itemLog.event) && task == itemLog.task && isDateAccept(after, before, date)) count++;
        }
        return count;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int count = 0;
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (Event.SOLVE_TASK.equals(itemLog.event) && task == itemLog.task && Status.OK.equals(itemLog.status) && isDateAccept(after, before, date)) count++;
        }
        return count;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer curCount = null;
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            curCount = map.get(itemLog.task);
            if (Event.SOLVE_TASK.equals(itemLog.event) && isDateAccept(after, before, date)) map.put(itemLog.task, curCount==null?1:++curCount);
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer curCount = null;
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            curCount = map.get(itemLog.task);
            if (Event.DONE_TASK.equals(itemLog.event) && isDateAccept(after, before, date)) map.put(itemLog.task, curCount==null?1:++curCount);
        }
        return map;
    }

    @Override
    public Set<Object> execute(String query) {
        if (query.equalsIgnoreCase("get ip")) return getObjectSet(getUniqueIPs(null, null));
        else if (query.equalsIgnoreCase("get user")) return getObjectSet(getAllUsers());
        else if (query.equalsIgnoreCase("get date")) return getObjectSet(getAllDates());
        else if (query.equalsIgnoreCase("get event")) return getObjectSet(getAllEvents(null, null));
        else if (query.equalsIgnoreCase("get status")) return getObjectSet(getAllStatuses());
        else {
            try {
                return getResultOfQueryByParameters(query);
            } catch (NoSuchFieldException|IllegalAccessException|ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private <E> Set<Object> getObjectSet(Set<E> sourceSet) {
        Set<Object> set = new HashSet<>();
        for (E e : sourceSet) set.add(e);
        return set;
    }

    public Set<Date> getAllDates() {
        Set<Date> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            set.add(itemLog.date);
        }
        return set;
    }

    public Set<Status> getAllStatuses() {
        Set<Status> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            set.add(itemLog.status);
        }
        return set;
    }

    private Set<Object> getResultOfQueryByParameters(String query) throws NoSuchFieldException, IllegalAccessException, ParseException {
        String[] parts = query.split("(get)|(for)|(=)|(and date between)|(and)");
        String filed1 = getFieldString(parts[1]);
        String filed2 = getFieldString(parts[2]);
        String filed3 = getFieldString(parts[3].replace("\"", ""));
        Date dateFrom = (Date)getObjectFromString(Date.class, getFieldString(parts[4].replace("\"", "")));
        Date dateTo = (Date)getObjectFromString(Date.class, getFieldString(parts[5].replace("\"", "")));


        Set<Object> set = getDataFromLog(filed1, filed2, filed3, dateFrom, dateTo);

        return set;
    }

    private String getFieldString(String part) {
        return part.trim();
    }

    private Set<Object> getDataFromLog(String filed1, String filed2, String filed3, Date dateFrom, Date dateTo) throws NoSuchFieldException, IllegalAccessException, ParseException {
        Set<Object> set = new HashSet<>();
        Field objectField1 = ItemLog.class.getDeclaredField(filed1);
        Field objectField2 = ItemLog.class.getDeclaredField(filed2);
        Object value = getObjectFromString(objectField2.getType(), filed3);
        for (ItemLog itemLog : logs) {
            if (isDateAccept(dateFrom, dateTo, itemLog.date) && objectField2.get(itemLog).equals(value)) set.add(objectField1.get(itemLog));
        }
        return set;
    }

    private Object getObjectFromString(Class<?> type, String string) throws ParseException {
        if (type == String.class) return string;
        else if (type == Date.class) return getDate(string);
        else if (type == Event.class) return Event.valueOf(string);
        else if (type == Status.class) return Status.valueOf(string);
        else return null;
    }


}
