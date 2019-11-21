package Lesson_9;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerating {
    public static void main(String[] args) throws IOException {
        File file = new File("17701.csv");
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter("17701.csv", true));
        String kek = "Попов Иван Ильич 1999-05-05";
        kek.split(" ");
        writer.write(kek);
        writer.newLine();
        writer.flush();
        writer.close();
    }
}
