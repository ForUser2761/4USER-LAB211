package business_layer;

import java.util.ArrayList;
import java.util.List;

import business_layer.entity.Member;
import business_layer.validate.Constant;
import business_layer.validate.Validate;
import data_layer.MemberDAO;

public class MemberUI {
    MemberDAO  memberDAO = new MemberDAO();

    /**
     * Input member information
     */
    public void input() {
        //input member
        //id, name, address, email, phoneNumber, membershipType
        String id = Validate.getString("Enter id: ", "Id must be a string, in format Mxxxxxx", Constant.MEMBER_ID);
        String name = Validate.getString("Enter name: ", "Name must be a string", Constant.STRING_REGEX);
        String address = Validate.getString("Enter address: ", "Address must be a string", Constant.STRING_REGEX);
        String email = Validate.getString("Enter email: ", "Email must be in format", Constant.EMAIL_REGEX);
        String phoneNumber = Validate.getString("Enter phone number: ", "Phone number must be 10 digits", Constant.PHONE_REGEX);        
        int membershipType = Validate.getInteger("1. Standard\n2.Premium\n3.VIP\nEnter membership type:\n" + //
                        " ", "Membership type must be a number", 1, 3);        
        //create member object
        Member member = new Member(id, name, address, email, phoneNumber, membershipType);

        //add member to database
        try {
            memberDAO.addMember(member);
            System.out.println("Member added successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Sort and print the list of members ascending by name
     */
    public void sortAscendingByName() {
        //get list of members
        List<Member> memberList = memberDAO.getMemberList();
        //check list empty
        if (memberList.isEmpty()) {
            System.out.println("No member to sort");
            return;
        }
        //clone member list
        List<Member> cloneList = new ArrayList<>(memberList);
        //sort list of members by name
        cloneList.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
        //display format
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s ", "Id", "Name", "Address", "Email", "Phone", "Membership"));
        //print list of members
        cloneList.forEach(System.out::println);
    }

    /**
     * Delete member by id
     */
    public void deleteMember() {
        //get list of members
        List<Member> memberList = memberDAO.getMemberList();
        //check list empty
        if (memberList.isEmpty()) {
            System.out.println("No member to delete");
            return;
        }
        //display format
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s ", "Id", "Name", "Address", "Email", "Phone", "Membership"));
        //print list of members
        memberList.forEach(System.out::println);
        //input id to delete
        String id = Validate.getString("Enter id to delete: ", "Id must be a string, in format Mxxxxxx", Constant.MEMBER_ID);
        //delete member
        try {
            memberDAO.deleteMember(id);
            System.out.println("Member deleted successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void viewAndUpdate() {
        //get list of members
        List<Member> memberList = memberDAO.getMemberList();
        //check list empty
        if (memberList.isEmpty()) {
            System.out.println("No member to update");
            return;
        }
        //display format
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s ", "Id", "Name", "Address", "Email", "Phone", "Membership"));
        //print list of members
        memberList.forEach(System.out::println);
        //input id to update
        String id = Validate.getString("Enter id to update: ", "Id must be a string, in format Mxxxxxx", Constant.MEMBER_ID);
        //update member
        Member member = memberDAO.getMemberById(id);
        if (member == null) {
            System.out.println("Member not found");
            return;
        }
        //update member to database
        //input member
        //id, name, address, email, phoneNumber, membershipType
        String name = Validate.getString("Enter name: ", "Name must be a string", Constant.STRING_REGEX);
        String address = Validate.getString("Enter address: ", "Address must be a string", Constant.STRING_REGEX);
        String email = Validate.getString("Enter email: ", "Email must be in format", Constant.EMAIL_REGEX);
        String phoneNumber = Validate.getString("Enter phone number: ", "Phone number must be 10 digits", Constant.PHONE_REGEX);
        String membershipType = Validate.getString("1. Standard\n2.Premium\n3.VIP\nEnter membership type:\n" + //
                        " ", "Membership type must be a number","(1|2|3|^$)");
        //update member object
        if (name != null && !name.isEmpty()) {
            member.setName(name);
        }
        if (address != null && !address.isEmpty()) {
            member.setAddress(address);
        }
        if (email != null && !email.isEmpty()) {
            member.setEmail(email);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            member.setPhoneNumber(phoneNumber);
        }
        if (membershipType != null && !membershipType.isEmpty()) {
            member.setMembershipType(Integer.parseInt(membershipType));
        }
        //update member to database
        try {
            memberDAO.updateMember(member);
            System.out.println("Member updated successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }                        

    }
    
}
