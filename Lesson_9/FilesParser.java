package Lesson_9;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesParser{
    private String path;
    private String[] files;

    public FilesParser(String path) {
        this.path = path;
        this.files = this.setGroupFiles();
    }
    private String[] setGroupFiles(){
        File[] filesInFolder = new File(path).listFiles();
        int size = filesInFolder.length;
        List<String> filesCSV = new ArrayList<>();
        for(int i = 0; i < size; i ++){
            File file = filesInFolder[i];
            if(file.getName().contains(".csv")) {
                filesCSV.add(file.getPath());
            }
        }
        return filesCSV.toArray(new String[filesCSV.size()]);
    }

    public String[] getFiles() {
        return this.files;
    }
}
