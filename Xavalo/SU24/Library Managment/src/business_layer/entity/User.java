package business_layer.entity;

public class User {
    private String userId;
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private boolean activeUser;

    public User() {
    }

    public User(String userId, String name, String dateOfBirth, String phoneNumber, String email, boolean activeUser) {
        this.userId = userId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activeUser = activeUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s %-15s", userId, name, dateOfBirth, phoneNumber, email, activeUser ? "Active" : "Inactive");
    }

    public String getUserProperties() {
        return userId + " | " + name + " | " + dateOfBirth + " | " + phoneNumber + " | " + email + " | " + activeUser;
    }
}
