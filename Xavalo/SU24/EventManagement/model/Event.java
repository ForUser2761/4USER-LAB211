package model;

public class Event {
    private static int autoIncrement = 1; // Mã sự kiện tăng dần
    private int eventID; // Mã sự kiện
    private String eventName; // Tên sự kiện
    private String date; // Ngày diễn ra
    private String location; // Địa điểm
    private int numberOfAttendees; // Số người tham gia
    private int status; // Trạng thái (0: Not Available, 1: Available)

    // Constructor
    public Event(String eventName, String date, String location, int numberOfAttendees, int status) {
        this.eventID = autoIncrement++;
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.numberOfAttendees = numberOfAttendees;
        setStatus(status); // Sử dụng setter để kiểm tra giá trị
    }

    public Event(int id, String name, String date2, String location2, int numberOfAttendees2, int status2) {
        this.eventID = id;
        this.eventName = name;
        this.date = date2;
        this.location = location2;
        this.numberOfAttendees = numberOfAttendees2;
        setStatus(status2); // Sử dụng setter để kiểm tra giá trị
    }

    // Getters and Setters
    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        if (numberOfAttendees > 0) {
            this.numberOfAttendees = numberOfAttendees;
        } else {
            throw new IllegalArgumentException("Number of attendees must be greater than 0");
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        switch (status) {
            case 0:
                this.status = status;
                break;
            case 1:
                this.status = status;
                break;
            default:
                throw new IllegalArgumentException("Status must be 0 (Not Available) or 1 (Available)");
        }
    }

    public String getstat() {
        return status == 1 ? "Available" : "Not Available";
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-15s %-15s %-15s %-15s", eventID, eventName, date, location,
                numberOfAttendees, getstat());
    }

    public String StringToFile() {
        return String.format("%s|%s|%s|%s|%s|%s", eventID, eventName, date, location, numberOfAttendees, status);
    }

    public static int getAutoIncrement() {
        return autoIncrement;
    }

    public static void setAutoIncrement(int autoIncrement) {
        Event.autoIncrement = autoIncrement;
    }
}
