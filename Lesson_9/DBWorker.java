package Lesson_9;

import java.sql.*;

public class DBWorker implements ResourceWorker {

    private static String url = "jdbc:mysql://remotemysql.com:3306/uGTs1iK5gj";
    private static String username = "uGTs1iK5gj";
    private static String password = "x1pWjkeSTI";

    @Override
    public void saveStudentsGroups(StudentsGroup[] studentsGroups) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            try {
                Statement stmt = con.createStatement();
                for(StudentsGroup studentsGroup : studentsGroups) {
                    int groupID = this.saveGroup(studentsGroup.getGroup(), stmt);
                    Student[] students = studentsGroup.getStudents();
                    for(Student student : students){
                        ResultSet rs = stmt.executeQuery("SELECT * FROM `Student` WHERE `first_name` = '" + student.getFirstName()
                                + "' AND `second_name` = '" + student.getSecondName() + "'  AND `last_name` = '" + student.getLastName()
                                + "' AND `group_id` = " + groupID );
                        if(!rs.next()) {
                            String request = "INSERT INTO Student(group_id, first_name, second_name, last_name, birthday_date) VALUES (" +
                                    +groupID
                                    + ",'" + student.getFirstName()
                                    + "','" + student.getSecondName()
                                    + "','" + student.getLastName()
                                    + "','" + student.getBirthDay() + "')";
                            stmt.execute(request);
                        }
                    }
                }
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudents(Student[] students) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            try {
                Statement stmt = con.createStatement();
                for(Student student: students) {
                    ResultSet rs = stmt.executeQuery("SELECT group_id FROM `Student` WHERE `first_name` = '" + student.getFirstName()
                            + "' AND `second_name` = '" + student.getSecondName() + "'  AND `last_name` = '" + student.getLastName() + "'");
                    rs.next();
                    int group_id = rs.getInt(1);
                    rs.close();
                    String request = "DELETE FROM `Student` WHERE `first_name` = '" + student.getFirstName()
                            + "' AND `second_name` = '" + student.getSecondName() + "'  AND `last_name` = '" + student.getLastName() + "'";
                    stmt.execute(request);
                    ResultSet rs1 = stmt.executeQuery("SELECT count(*) FROM `Student` WHERE `group_id` = " + group_id);
                    System.out.println(rs1.next());
                    System.out.println(rs1.getInt(1));
                    if(rs1.getInt(1) == 0){
                        String request1 = "DELETE FROM `Groups` WHERE `id` = " + group_id;
                        stmt.execute(request1);
                    }
                }
                stmt.close();
            } finally {
            con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int saveGroup(String group, Statement stmt){
        int group_id = 0;
        try {
            ResultSet rs = stmt.executeQuery("SELECT `id` FROM `Groups` WHERE `group_name` = '" + group + "'");
            if(!rs.first()){
                String request = "INSERT INTO `Groups`(`group_name`) VALUES ('" + group + "')";
                stmt.execute(request);
            }
            rs = stmt.executeQuery("SELECT `id` FROM `Groups` WHERE `group_name` = '" + group + "'");
            rs.next();
            group_id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group_id;
    }

}
