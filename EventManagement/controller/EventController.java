package controller;

import java.util.List;

import model.Event;
import util.Validate;

public class EventController {
    EventDAO eventDAO = new EventDAO();

    /**
     * Input event
     */
    public void inputEvent() {
        // input event
        // input name, date, location, number of attendees, status
        String name = Validate.getString("Enter event name: ",
                "e event name is at least five characters long and does not contain spaces",
                "[a-zA-Z0-9]{5,}");
        String date = Validate.getDate("Enter event date (yyyy-MM-dd): ", "Invalid date format");
        String location = Validate.getString("Enter event location: ", "Location must be string",
                ".+");
        int numberOfAttendees = Validate.getInteger("Enter number of attendees: ",
                "Number of attendees must be greater than 0", 1, Integer.MAX_VALUE);
        int status = Validate.getInteger("Enter status (0: Not Available, 1: Available): ", "Status must be 0 or 1", 0,
                1);

        // create event
        Event event = new Event(name, date, location, numberOfAttendees, status);
        // add event to list
        try {
            eventDAO.addEvent(event);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Search event
     */
    public void searchEvent() {
        while (true) {

            // input event id
            int id = Validate.getInteger("Enter event id: ", "Event id must be a number", 1, Integer.MAX_VALUE);
            // search event by id
            Event event = eventDAO.searchEvent(id);
            // check event exists
            if (event == null) {
                System.out.println("Event not found");
            } else {
                System.out.println("Exist event !!");
                System.out.println(event);
            }
            //ask continue
            if (!Validate.isContinue()) {
                break;
            }
        }

    }

    /**
     * Search event by location
     */
    public void searchEventByLocation() {
        while (true) {
            // input location
            String location = Validate.getString("Enter event location: ", "Location must be string", ".+");
            // search event by location
            List<Event> eventList = eventDAO.searchEventByLocation(location);
            // check event exists
            if (eventList.isEmpty()) {
                System.out.println("Event not found");
            } else {
                System.out.println("Exist event !!");
                eventList.forEach(System.out::println);
            }
            //ask continue
            if (!Validate.isContinue()) {
                break;
            }
        }
    }

    /**
     * Delete event
     */
    public void deleteEvent() {
        //enter event id
        int id = Validate.getInteger("Enter event id: ", "Event id must be a number", 1, Integer.MAX_VALUE);
        //delete event
        try {
            eventDAO.deleteEvent(id);
            System.out.println("Event deleted successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Update event
     */
    public void updateEvent() {
        //enter event id
        int id = Validate.getInteger("Enter event id: ", "Event id must be a number", 1, Integer.MAX_VALUE);
        //search event by id
        Event event = eventDAO.searchEvent(id);
        //check event null
        if (event == null) {
            System.out.println("Event not found");
            return;
        }
        //display event
        System.out.println(event);
        //input new event
        //name, date, location, number of attendees, status
        String name = Validate.getString("Enter event name: ",
                "e event name is at least five characters long and does not contain spaces",
                "([a-zA-Z0-9]{5,}|\\s+)");
        String date = Validate.getDate("Enter event date (yyyy-MM-dd): ", "Invalid date format", "((\\d{4}-\\d{2}-\\d{2})|\\s+)");
        String location = Validate.getString("Enter event location: ", "Location must be string",
                "(.+|\\s+)");
        String numberOfAttendees = Validate.getString("Enter number of attendees: ",
                "Number of attendees must be greater than 0", "(\\d+|\\s+)");
        String status = Validate.getString("Enter status (0: Not Available, 1: Available): ", "Status must be 0 or 1", "(0|1|\\s+)");
        //update event
        if (name != null || !name.isEmpty()) {
            event.setEventName(name);
        }
        if (date != null || !date.isEmpty()) {
            event.setDate(date);
        }
        if (location != null || !location.isEmpty()) {
            event.setLocation(location);
        }
        if (numberOfAttendees != null || !numberOfAttendees.isEmpty()) {
            event.setNumberOfAttendees(Integer.parseInt(numberOfAttendees));
        }
        if (status != null || !status.isEmpty()) {
            event.setStatus(Integer.parseInt(status));
        }
        //update event to database
        try {
            eventDAO.updateEvent(event);
            System.out.println("Event updated successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Print event list
     */
    public void printEventList() {
        List<Event> eventList = eventDAO.getEventList();
        if (eventList.isEmpty()) {
            System.out.println("No event to view and update");
            return;
        }
        System.out.format("%-10s %-15s %-15s %-15s %-15s %-15s\n", "ID", "Name", "Date", "Location", "Number of Attendees",
                "Status");
        eventList.forEach(System.out::println);
    }

}
