package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.DateQuery;
import com.javarush.test.level39.lesson09.big01.query.EventQuery;
import com.javarush.test.level39.lesson09.big01.query.IPQuery;
import com.javarush.test.level39.lesson09.big01.query.UserQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date  = dateFormat.parse(params[2]);
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
        Set<Event> set = new HashSet<>();
        for (ItemLog itemLog : logs) {
            Date date = itemLog.date;
            if (isDateAccept(after, before, date)) set.add(itemLog.event);
        }
        return set.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return null;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return null;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return null;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return null;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return null;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return 0;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return 0;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return null;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return null;
    }
}
