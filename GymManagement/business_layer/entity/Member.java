package business_layer.entity;

public class Member {
    // Các thuộc tính của thành viên
    private String id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private int membershipType;  // Loại hội viên, ví dụ: "Standard", "Premium", v.v.

    // Constructor để khởi tạo đối tượng Member mới
    public Member(String id, String name, String address, String email, String phoneNumber, int membershipType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.membershipType = membershipType;
    }

    // Getters và Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(int membershipType) {
        this.membershipType = membershipType;
    }

    public String getMemberShipString() {
        switch (membershipType) {
            case 1:
                return "Standard";
            case 2:
                return "Premium";
            case 3:
                return "VIP";
            default:
                return "Unknown";
        }
    }

    // Phương thức để hiển thị thông tin của Member
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s %-15s ", id, name, address, email, phoneNumber, getMemberShipString());
    }

    public String StringToFile() {
        return String.format("%s|%s|%s|%s|%s|%s", id, name, address, email, phoneNumber, membershipType);

    }
}

