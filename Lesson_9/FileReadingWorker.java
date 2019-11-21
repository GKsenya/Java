package Lesson_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileReadingWorker implements ResourceReadingWorker {

    private StudentsGroup[] studentsGroups;
    private String[] groupFiles;
    private int groupNumber;

    public FileReadingWorker(String[] groupFiles) throws IOException {
        this.groupFiles = groupFiles;
        this.groupNumber = groupFiles.length;
        this.studentsGroups = new StudentsGroup[groupNumber];
        this.setStudentsGroups();
    }

    private void setStudentsGroups() throws IOException {
        for(int i = 0; i < groupNumber; i ++) {
            String file = groupFiles[i];
            List<String> studentLine = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
            String group = this.getGroup(file);
            this.studentsGroups[i] = new StudentsGroup(group, studentLine);
        }
    }

    private String getGroup(String filePath){
        String group = filePath.substring(filePath.lastIndexOf("\\")+1, filePath.lastIndexOf("."));
        return group;
    }

    @Override
    public StudentsGroup[] getStudentsGroups() {
        return studentsGroups;
    }
}
