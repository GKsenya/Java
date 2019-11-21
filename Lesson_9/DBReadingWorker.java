package Lesson_9;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReadingWorker implements ResourceReadingWorker {
    private StudentsGroup[] studentsGroup;
    private static String url = "jdbc:mysql://remotemysql.com:3306/uGTs1iK5gj";
    private static String username = "uGTs1iK5gj";
    private static String password = "x1pWjkeSTI";
    private List<String> ids = new ArrayList<>();
    private int groupNumber;

    public DBReadingWorker() {
        this.setStudentsGroup();
    }

    private void setStudentsGroup() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            try {
                Statement stmt = con.createStatement();
                setIDs(stmt);
                this.groupNumber = this.ids.size();
                studentsGroup = new StudentsGroup[groupNumber];
                for(int i = 0; i< groupNumber; i++){
                    List<String> students = new ArrayList<>();
                    ResultSet rs = stmt.executeQuery("SELECT `Student`.`first_name`, `Student`.`second_name`, " +
                            "`Student`.`last_name`, `Student`.`birthday_date` FROM `Student` INNER JOIN `Groups` ON " +
                            "`Student`.`group_id` = (SELECT `Groups`.`id` WHERE `Groups`.`group_name` = '" + this.ids.get(i) + "')");
                    while (rs.next()) {
                        students.add(rs.getString("first_name") + " "  + rs.getString("second_name")
                                + " "  + rs.getString("last_name") + " " + rs.getString("birthday_date"));
                    }
                    rs.close();
                    studentsGroup[i] = new StudentsGroup(this.ids.get(i), students);
                }

                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setIDs(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM `Groups`");
        while (rs.next()) {
            this.ids.add(rs.getString("group_name"));
        }

    }

    @Override
    public StudentsGroup[] getStudentsGroups() {
        return studentsGroup;
    }
}
