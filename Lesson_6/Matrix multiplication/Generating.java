package Lesson_6;

import java.io.FileWriter;
import java.io.IOException;

public class Generating {
    public Generating() throws IOException {
        try (FileWriter fileWriter = new FileWriter("Matrix.txt")) {
            for (int i = 1; i < 2999; i++) {
                for (int j = 1; j < 3000; j++) {
                    fileWriter.write(String.valueOf((int) (Math.random() * 10 + 1)) + ',');
                }
                fileWriter.write("\n");
            }
            fileWriter.write(" \n");

            for (int i = 1; i < 3000; i++) {
                for (int j = 1; j < 2999; j++) {
                    fileWriter.write(String.valueOf((int) (Math.random() * 10 + 1)) + ',');
                }
                fileWriter.write((int) (Math.random() * 10 + 1) + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Generating n = new Generating();
    }
}
