package Lesson_6;

import com.sun.jdi.IntegerValue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, IncorrectInputData, InterruptedException {

        FileWorker newFile = new FileWorker("Matrix.txt");
        int[][] matrixFirst = newFile.getMatrixFirst();
        int[][] matrixSecond = newFile.getMatrixSecond();
        System.out.println("Считались данные");
        if (matrixFirst[0].length != matrixSecond.length) {
            throw new IncorrectInputData("Количество столбцов в матрице не совпадает с количестром элементов в векторе - перемножение невозможно");
        }

        //-----------------------Однопоточный метод--------------------------//

        long begin = System.currentTimeMillis();
        SimpleMultiplication simpleMultiplication = new SimpleMultiplication(matrixFirst, matrixSecond);
        int[][] firstResult = simpleMultiplication.getResult();
        FileWorker.writeToFile("SimpleMultiplication.txt", firstResult);
        long out = System.currentTimeMillis() - begin;
        System.out.println("Результат работы однопоточной программы: затрачено времени: " + out);


        //-----------------------Многопоточный метод--------------------------//

        int threadNumber;
        for(int idxkk = 1; idxkk<5; idxkk++) {
            threadNumber = idxkk;

            int interval = (int) Math.ceil(matrixFirst.length / threadNumber);
            Multiplication[] threads = new Multiplication[threadNumber];
            int[][] secondResult = new int[matrixFirst.length][matrixSecond[0].length];
            int[][] subMatrixFirst;
            long rebegin = System.currentTimeMillis();
            for (int i = 0; i < threadNumber; i++) {
                if (i != threadNumber - 1) {
                    subMatrixFirst = Arrays.copyOfRange(matrixFirst, i * interval, (i + 1) * interval);

                } else {
                    subMatrixFirst = Arrays.copyOfRange(matrixFirst, i * interval, matrixFirst.length);
                }
                Multiplication bob = new Multiplication(subMatrixFirst, matrixSecond);
                bob.start();
                threads[i] = bob;
            }

            for (int idx = 0; idx < threadNumber; idx++) {
                threads[idx].join();
                int[][] secondPartResult = threads[idx].getElement();
                int secondPartResultLength = secondPartResult.length;
                if (idx < threadNumber - 1) {
                    for (int i = 0; i < secondPartResultLength; i++) {
                        secondResult[i + idx * interval] = secondPartResult[i];
                    }
                } else {
                    int ending = matrixFirst.length - (threadNumber - 1) * interval;
                    for (int i = 0; i < ending; i++) {
                        secondResult[i + idx * interval] = secondPartResult[i];
                    }
                }
            }

            long reout = System.currentTimeMillis() - rebegin;

            System.out.println("Результат работы многопоточной программы: затрачено времени: " + reout + "; " + idxkk +" потоков/однопоточная программа = " + (out*100 - reout*100)/out + "%");
        }
//        FileWorker.writeToFile("Multiplication.txt", secondResult);
//        Generating g = new Generating();

    }
}
