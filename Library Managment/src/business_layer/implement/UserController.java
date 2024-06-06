package business_layer.implement;

import business_layer.ControllerInterface;
import business_layer.entity.User;
import business_layer.validate.CommonRegex;
import business_layer.validate.Validate;
import data_layer.implement.UserDAO;

public class UserController implements ControllerInterface<User> {
    private final UserDAO userDao;

    public UserController() {
        userDao = new UserDAO();
    }

    @Override
    public void input() {
        while (true) {
            System.out.println("===================== Add User =====================");
            String userId = Validate.getString("Enter User ID: ", "User ID cannot be empty", CommonRegex.STRING_REGEX);
            if (userDao.getUserByID(userId) != null) {
                System.out.println("User ID already exists. Please try again.");
                continue;
            }
            String name = Validate.getString("Enter name: ", "Invalid name. Please try again.",
                    CommonRegex.STRING_REGEX);
            String dateOfBirth = Validate.getString("Enter date of birth (MM/dd/yyyy): ", "Invalid date format. Please try again.",
                    CommonRegex.DATE_REGEX);
            String phoneNumber = Validate.getString("Enter phone number: ", "Invalid phone number. Please try again.",
                    CommonRegex.PHONE_REGEX);
            String email = Validate.getString("Enter email: ", "Invalid email. Please try again.",
                    CommonRegex.EMAIL_REGEX);
            User newUser = new User(userId, name, dateOfBirth, phoneNumber, email, true);
            try {
                userDao.insert(newUser);
                System.out.println("User added successfully.");
            } catch (Exception ex) {
                System.out.println("Error adding user: " + ex.getMessage());
            }
            if (!Validate.getBoolean("Do you want to add another user? (true/false)", "Input must be true or false")) {
                break;
            }
        }
    }

    @Override
    public void showAll() {
        try {
            System.out.println("===================== All Users =====================");
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s", "User ID", "Name", "Date of Birth", "Phone Number", "Email", "Status"));
            for (User user : userDao.getListUser()) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println("Error displaying all users: " + e.getMessage());
        }
    }

    @Override
    public void delete() {
        while (true) {
            System.out.println("===================== Delete a User =====================");
            String userId = Validate.getString("Enter User ID: ", "User ID cannot be empty", CommonRegex.STRING_REGEX);
            User user = userDao.getUserByID(userId);
            if (user == null) {
                System.out.println("User not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    break;
                }
                continue;
            }
            System.out.println("User found: " + user);
            if (Validate.getBoolean("Do you want to delete this user? (true/false)", "Input must be true or false")) {
                try {
                    userDao.deleteUser(userId);
                    System.out.println("User deleted successfully.");
                } catch (Exception e) {
                    System.out.println("Error deleting user: " + e.getMessage());
                }
            }
            if (!Validate.getBoolean("Do you want to delete another user? (true/false)",
                    "Input must be true or false")) {
                break;
            }
        }
    }

    public void update() {
        while (true) {
            System.out.println("===================== Update User Information =====================");
            String userId = Validate.getString("Enter User ID: ", "User ID cannot be empty", CommonRegex.STRING_REGEX);
            User user = userDao.getUserByID(userId);
            if (user == null) {
                System.out.println("User not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    break;
                }
                continue;
            }
            System.out.println("Current User Information:");
            System.out.println(user);
            String name = Validate.getString("Enter new name (leave blank to keep existing): ",
                    "Invalid name. Please try again.", CommonRegex.STRING_REGEX);
            if (!name.isEmpty()) {
                user.setName(name);
            }
            String dateOfBirth = Validate.getString("Enter new date of birth (MM/dd/yyyy) (leave blank to keep existing): ",
                    "Invalid date format. Please try again.", CommonRegex.DATE_REGEX);
            if (!dateOfBirth.isEmpty()) {
                user.setDateOfBirth(dateOfBirth);
            }
            String phoneNumber = Validate.getString("Enter new phone number (leave blank to keep existing): ",
                    "Invalid phone number. Please try again.", CommonRegex.PHONE_REGEX);
            if (!phoneNumber.isEmpty()) {
                user.setPhoneNumber(phoneNumber);
            }
            String email = Validate.getString("Enter new email (leave blank to keep existing): ",
                    "Invalid email. Please try again.", CommonRegex.EMAIL_REGEX);
            if (!email.isEmpty()) {
                user.setEmail(email);
            }
            try {
                userDao.updateUser(user);
                System.out.println("User information updated successfully.");
            } catch (Exception ex) {
                System.out.println("Error updating user information: " + ex.getMessage());
            }
            if (!Validate.getBoolean("Do you want to update another user? (true/false)",
                    "Input must be true or false")) {
                break;
            }
        }
    }
}
