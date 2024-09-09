package ui;

import java.util.List;

import business_layer.entity.Equipment;
import business_layer.entity.GymClass;
import business_layer.entity.Member;
import business_layer.validate.Constant;
import business_layer.validate.Validate;
import data_layer.EquipmentDAO;
import data_layer.GymClassDAO;
import data_layer.MemberDAO;

public class GymClassUI {

    public void input() {
        // input gym class
        // classId, name, schedule, capacity
        String classId = Validate.getString("Enter class ID: ", "Class ID must not be empty", Constant.CLASS_ID);
        String name = Validate.getString("Enter class name: ", "Class name must not be empty", Constant.STRING_REGEX);
        String schedule = Validate.getString("Enter class schedule: ", "Class schedule must not be empty",
                Constant.STRING_REGEX);
        int capacity = Validate.getInteger("Enter class capacity: ", "Class capacity must be a number", 1, 100);
        // get list of members
        List<Member> memberList = new MemberDAO().getMemberList();
        // display format
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s ", "Id", "Name", "Address", "Email",
                "Phone", "Membership"));
        // print list of members
        memberList.forEach(System.out::println);
        String memberIds;
        while (true) {
            // input member id seperated by ,
            memberIds = Validate.getString("Enter member IDs seperated by ,: ", "Member IDs must not be empty",
                    Constant.STRING_REGEX);
            // check member id exist
            boolean isMemberIdExist = checkMemberIdExist(memberIds, memberList);
            if (isMemberIdExist == false) {
                System.out.println("Please enter member IDs again, make sure all member IDs exist");
            } else {
                break;
            }
        }
        // get list of equipments
        List<Equipment> equipmentList = new EquipmentDAO().getEquipmentList();
        // display format
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s ", "Id", "Name", "Type", "Status", "Note"));
        // print list of equipments
        equipmentList.forEach(System.out::println);
        String equipmentIds;
        while (true) {
            // input equipment id seperated by ,
            equipmentIds = Validate.getString("Enter equipment IDs seperated by ,: ", "Equipment IDs must not be empty",
                    Constant.STRING_REGEX);
            boolean isEquipmentIdExist = checkEquipmentIdExist(equipmentIds, equipmentList);
            if (isEquipmentIdExist == false) {
                System.out.println("Please enter equipment IDs again, make sure all equipment IDs exist");
            } else {
                break;
            }                    
        }
        // create gym class object
        GymClass gymClass = new GymClass(classId, name, schedule, capacity);
        gymClass.addMember(memberIds);
        gymClass.addEquipment(equipmentIds);
        // add gym class to database
        try {
            new GymClassDAO().addGymClass(gymClass);
            System.out.println("Gym class added successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // display message

    }

    /**
     * Check if equipment ID exist
     * @param equipmentIds
     * @param equipmentList
     * @return true if all equipment IDs exist, false otherwise
     */
    private boolean checkEquipmentIdExist(String equipmentIds, List<Equipment> equipmentList) {
        String[] ids = equipmentIds.split(",");
        for (String id : ids) {
            boolean isExist = equipmentList.stream().anyMatch(e -> e.getEquipmentId().equals(id));
            if (!isExist) {
                System.out.println("Equipment ID " + id + " does not exist");
                return false;
            }
        }
        return true;
    }

    /**
     * Check if member ID exist in the list of members 
     * @param memberIds
     * @param memberList
     * @return true if all member IDs exist, false otherwise
     */
    private boolean checkMemberIdExist(String memberIds, List<Member> memberList) {
        String[] ids = memberIds.split(",");
        for (String id : ids) {
            boolean isExist = memberList.stream().anyMatch(m -> m.getId().equals(id));
            if (!isExist) {
                System.out.println("Member ID " + id + " does not exist");
                return false;
            }
        }
        return true;
    }

    public void view() {
        // get list of gym classes
        List<GymClass> gymClassList = new GymClassDAO().getGymClassList();
        // check list empty
        if (gymClassList.isEmpty()) {
            System.out.println("No gym class to view");
            return;
        }
        // display format
        System.out.format("%-15s %-15s %-15s %-15s\n", "Class ID", "Name", "Schedule", "Capacity");
        // print list of gym classes
        gymClassList.forEach(System.out::println);

    }

    public void update() {
        // get list of gym classes
        List<GymClass> gymClassList = new GymClassDAO().getGymClassList();
        // check list empty
        if (gymClassList.isEmpty()) {
            System.out.println("No gym class to update");
            return;
            
        }
        // display format
        System.out.format("%-15s %-15s %-15s %-15s\n", "Class ID", "Name", "Schedule", "Capacity");
        // print list of gym classes
        gymClassList.forEach(System.out::println);
        // input class ID to update
        String classId = Validate.getString("Enter class ID to update: ", "Wrong", Constant.CLASS_ID);
        // get gym class by class ID
        GymClass gymClass = gymClassList.stream().filter(g -> g.getClassId().equals(classId)).findFirst().orElse(null);
        // check gym class exist
        if (gymClass == null) {
            System.out.println("Gym class ID " + classId + " does not exist");
            return;
        }
        // input new schedule
        String schedule = Validate.getString("Enter new schedule: ", "Schedule must not be empty", Constant.STRING_REGEX);
        //change member
        // get list of members
        List<Member> memberList = new MemberDAO().getMemberList();
        // display format
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s ", "Id", "Name", "Address", "Email",
                "Phone", "Membership"));
        // print list of members
        memberList.forEach(System.out::println);
        String memberIds;
        while (true) {
            // input member id seperated by ,
            memberIds = Validate.getString("Enter member IDs seperated by ,: ", "Member IDs must not be empty",
                    Constant.STRING_REGEX);
            // check member id exist
            boolean isMemberIdExist = checkMemberIdExist(memberIds, memberList);
            if (isMemberIdExist == false) {
                System.out.println("Please enter member IDs again, make sure all member IDs exist");
            } else {
                break;
            }
        }
        //change equipment
        // get list of equipments
        List<Equipment> equipmentList = new EquipmentDAO().getEquipmentList();
        // display format
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s ", "Id", "Name", "Type", "Status", "Note"));
        // print list of equipments
        equipmentList.forEach(System.out::println);
        String equipmentIds;
        while (true) {
            // input equipment id seperated by ,
            equipmentIds = Validate.getString("Enter equipment IDs seperated by ,: ", "Equipment IDs must not be empty",
                    Constant.STRING_REGEX);
            boolean isEquipmentIdExist = checkEquipmentIdExist(equipmentIds, equipmentList);
            if (isEquipmentIdExist == false) {
                System.out.println("Please enter equipment IDs again, make sure all equipment IDs exist");
            } else {
                break;
            }                    
        }
        if (equipmentIds != null || !equipmentIds.isEmpty()) {
            gymClass.editEquipment(equipmentIds);
        }
        if (memberIds != null || !memberIds.isEmpty()) {
            gymClass.editMember(memberIds);
        }
        if (schedule != null || !schedule.isEmpty()) {
            gymClass.setSchedule(schedule);
        }
        // update gym class
        try {
            new GymClassDAO().updateGymClass(gymClass);
            System.out.println("Gym class updated successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete() {
        // get list of gym classes
        List<GymClass> gymClassList = new GymClassDAO().getGymClassList();
        // check list empty
        if (gymClassList.isEmpty()) {
            System.out.println("No gym class to delete");
            return;
        }
        // display format
        System.out.format("%-15s %-15s %-15s %-15s\n", "Class ID", "Name", "Schedule", "Capacity");
        // print list of gym classes
        gymClassList.forEach(System.out::println);
        // input class ID to delete
        String classId = Validate.getString("Enter class ID to delete: ", "Wrong", Constant.CLASS_ID);
        // get gym class by class ID
        GymClass gymClass = gymClassList.stream().filter(g -> g.getClassId().equals(classId)).findFirst().orElse(null);
        // check gym class exist
        if (gymClass == null) {
            System.out.println("Gym class ID " + classId + " does not exist");
            return;
        }
        // delete gym class
        try {
            new GymClassDAO().deleteGymClass(gymClass);
            System.out.println("Gym class deleted successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
