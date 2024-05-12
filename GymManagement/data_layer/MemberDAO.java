package data_layer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import business_layer.entity.Member;
import business_layer.validate.Constant;

public class MemberDAO {
    private static List<Member> memberList = new ArrayList<>();

    public MemberDAO() {
        try {
            loadDataFromFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads data from a file and populates the memberList with Member objects.
     */
    private void loadDataFromFile() {
        memberList.clear();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(Constant.MEMBER_FILE_NAME);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");
                Member member = new Member(data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        Integer.parseInt(data[5].trim()));
                memberList.add(member);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
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
     * Returns the list of members.
     * 
     * @return the list of members
     * 
     */
    public List<Member> getMemberList() {
        loadDataFromFile();
        return memberList;
    }

    /**
     * Adds a member to the list of members.
     * 
     * @param member: the member to add
     */
    public void addMember(Member member) {
        memberList.add(member);
        writeToFile();
    }

    /**
     * Updates a member in the list of members.
     */
    private void writeToFile() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(Constant.MEMBER_FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Member member : memberList) {
                bufferedWriter.write(member.StringToFile());
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

    public void deleteMember(String id) {
        //TODO: giai thich cho sinh vien - 4user.net
        Member member = memberList.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
        if (member != null) {
            memberList.remove(member);
            writeToFile();
        } else {
            throw new IllegalArgumentException("Member not found");
        }
    }

    /**
     * Get member by id
     * @param id id of member
     * @return  member
     */
    public Member getMemberById(String id) {
        return memberList.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateMember(Member member) {
        writeToFile();
    }

}
