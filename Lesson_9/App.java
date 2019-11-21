package Lesson_9;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException {

        if(args.length == 2&&args[0].equalsIgnoreCase("updateDB")) {

            FilesParser filesParser = new FilesParser("C:\\Users\\Gorks\\Desktop\\туц");
            String[] groupFiles = filesParser.getFiles();
            ResourceReadingWorker bob = new FileReadingWorker(groupFiles);
            StudentsGroup[] studentsGroups = bob.getStudentsGroups();
            ResourceWorker boban = new DBWorker();
            boban.saveStudentsGroups(studentsGroups);

            Student student = new Student("Попов","Иван","Ильич","1999-05-05");
            Student[] sg = new Student[1];
            sg[0] = student;
            boban.deleteStudents(sg);


            System.out.println("База данных успешно обновлена!\n");
        }

        if(args.length == 1&&args[0].equalsIgnoreCase("printAll")) {
            ResourceReadingWorker arnold = new DBReadingWorker();
            StudentsGroup[] sg = arnold.getStudentsGroups();
            for (StudentsGroup sgd : sg) {
                System.out.println(sgd);
            }
        }
    }
}
