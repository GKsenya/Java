package Lesson_9;

import java.util.Arrays;
import java.util.List;

public class StudentsGroup {

    private String group;
    private Student[] students;
    private int studentsNumber;

    public StudentsGroup(String group, List<String> students) {
        this.group = group;
        this.studentsNumber = students.size();
        this.students = new Student[studentsNumber];
        this.setStudents(students);
    }

    private void setStudents(List<String> students){
        for(int i = 0; i < this.studentsNumber; i++){
            String[] studInfo = students.get(i).split(" ");
            String firstName = studInfo[0];
            String secondName = studInfo[1];
            String lastName = studInfo[2];
            String birthday = studInfo[3];
            this.students[i] = new Student(firstName, secondName, lastName, birthday);

        }
    }

    public String getGroup() {
        return this.group;
    }

    public Student[] getStudents(){
        return this.students;
    }

    @Override
    public String toString() {
        String stud = "";
        for(Student student: students){
            stud += student.toString() + "\n";
        }
        return "Группа " + group + "\n" + stud;
    }
}
