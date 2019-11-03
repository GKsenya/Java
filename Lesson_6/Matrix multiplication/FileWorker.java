package Lesson_6;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWorker {

    private String fileName;
    private int[][] matrixFirst;
    private int[][] matrixSecond;

    public FileWorker(String fileName) throws IOException {
        this.fileName = fileName;
        this.setMatrixList();
    }

    private void setMatrixList() throws IOException {

        List<String> separation = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        int colsF = separation.get(0).split(",").length;
        int separationSize = separation.size();
        int breaker = separation.indexOf(" ");
        this.matrixFirst = new int[breaker][colsF];
        this.matrixSecond = new int[separationSize - breaker - 1][colsF];

        for (int i = 0; i < breaker; i++) {
            if (separation.get(i).length() > 0) {
                String[] rowF = separation.get(i).split(",");
                int[] intLine = new int[rowF.length];
                for (int j = 0; j < rowF.length; j++) {
                    intLine[j] = Integer.valueOf(rowF[j]);
                }
                this.matrixFirst[i] = intLine;
            } else {
                break;
            }
        }

        int colsS = separation.get(breaker + 1).split(",").length;
        for (int i = breaker + 1; i < separationSize; i++) {
            if (separation.get(i).length() > 0) {
                String[] rowS = separation.get(i).split(",");
                int[] intLineS = new int[rowS.length];
                for (int j = 0; j < rowS.length; j++) {
                    intLineS[j] = Integer.valueOf(rowS[j]);
                }
                this.matrixSecond[i - breaker - 1] = intLineS;
            } else {
                break;
            }
        }
    }

    public int[][] getMatrixFirst() {
        return matrixFirst;
    }

    public int[][] getMatrixSecond() {
        return matrixSecond;
    }

    public static void writeToFile(String fileName, int[][] output) throws IOException{
        try(FileWriter fileWriter = new FileWriter(fileName)){
            for(int idx = 0; idx<output.length;idx++){
                String out = Arrays.toString(output[idx]);
                fileWriter.write(Arrays.asList(output[idx]).toString().replace("[","").replace("]",""));
            }
        }

    }
}
