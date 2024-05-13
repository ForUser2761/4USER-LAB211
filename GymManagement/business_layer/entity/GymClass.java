package business_layer.entity;

import java.util.HashMap;
import java.util.Map;

public class GymClass {
    private String classId;
    private String name;
    private String schedule;
    private int capacity;
    private Map<String, Member> members; // Using HashMap to store members by their ID
    private Map<String, Equipment> equipmentMap; // Using HashMap to store equipment by their ID

    public GymClass(String classId, String name, String schedule, int capacity) {
        this.classId = classId;
        this.name = name;
        this.schedule = schedule;
        this.capacity = capacity;
        this.members = new HashMap<>();
        this.equipmentMap = new HashMap<>();
    }

    // Getters and setters
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public Map<String, Equipment> getEquipmentMap() {
        return equipmentMap;
    }

    public void addMember(Member member) {
        if (!members.containsKey(member.getId()) && members.size() < capacity) {
            members.put(member.getId(), member);
        }
    }

    public void addEquipment(Equipment equipment) {
        equipmentMap.putIfAbsent(equipment.getEquipmentId(), equipment);
    }

    public void addMember(String memberIds) {
        String[] ids = memberIds.split(",");
        for (String id : ids) {
            Member member = new Member();
            member.setId(id);
            addMember(member);
        }
    }

    public void addEquipment(String equipmentIds) {
        String[] ids = equipmentIds.split(",");
        for (String id : ids) {
            Equipment equipment = new Equipment();
            equipment.setEquipmentId(id);
            addEquipment(equipment);
        }
    }

    public void editEquipment(String equipmentIds) {
        equipmentMap.clear();
        addEquipment(equipmentIds);
    }

    public void editMember(String memberIds) {
        members.clear();
        addMember(memberIds);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s", classId, name, schedule, capacity);
    }

    public String stringToFile() {
        StringBuilder sbMemberShip = new StringBuilder();
        //loop through the members and append their ID to the StringBuilder,
        //but not adding the last comma
        members.forEach((k, v) -> sbMemberShip.append(k).append(","));
        sbMemberShip.deleteCharAt(sbMemberShip.length() - 1);

        StringBuilder sbEquipment = new StringBuilder();
        //loop through the equipment and append their ID to the StringBuilder,
        //but not adding the last comma
        equipmentMap.forEach((k, v) -> sbEquipment.append(k).append(","));
        sbEquipment.deleteCharAt(sbEquipment.length() - 1);
        return String.format("%s|%s|%s|%s|%s|%s", classId, name,
                schedule, capacity, sbMemberShip.toString(), sbEquipment.toString());
    }
}
