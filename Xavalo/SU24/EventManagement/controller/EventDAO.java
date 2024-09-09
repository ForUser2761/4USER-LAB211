package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import util.Constant;

public class EventDAO {
    List<Event> eventList = new ArrayList<Event>();

    /**
     * add event to list
     */
    public void addEvent(Event event) {
        // add equipment to list
        eventList.add(event);
        // writeToFile();
        writeToFile();
    }

    /**
     * Write data to file
     */
    private void writeToFile() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(Constant.EVENT_FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Event event : eventList) {
                bufferedWriter.write(event.StringToFile());
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Load data from file
     */
    public void loadDataFromFile() {
        eventList.clear();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(Constant.EVENT_FILE_NAME);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String date = data[2].trim();
                String location = data[3].trim();
                int numberOfAttendees = Integer.parseInt(data[4].trim());
                int status = Integer.parseInt(data[5].trim());
                Event event = new Event(id, name, date, location, numberOfAttendees, status);
                eventList.add(event);
                Event.setAutoIncrement(++id);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Search event by id
     */
    public Event searchEvent(int id) {
        loadDataFromFile();
        for (Event event : eventList) {
            if (event.getEventID() == id) {
                return event;
            }
        }
        return null;
    }

    /**
     * Search event by location
     */
    public List<Event> searchEventByLocation(String location) {
        loadDataFromFile();
        List<Event> result = new ArrayList<Event>();
        for (Event event : eventList) {
            if (event.getLocation().toUpperCase().contains(location.toUpperCase())) {
                result.add(event);
            }
        }
        return result;
    }

    /**
     * Delete event
     */
    public void deleteEvent(int id) {
        loadDataFromFile();
        Event event = searchEvent(id);
        if (event == null) {
            throw new IllegalArgumentException("Event not found");
        }
        eventList.remove(event);
        writeToFile();
    }

    /*
     * Update event
     */
    public void updateEvent(Event event) {
        writeToFile();
    }

    /**
     * Get list of event
     */
    public List<Event> getEventList() {
        loadDataFromFile();
        return eventList;
    }

}
