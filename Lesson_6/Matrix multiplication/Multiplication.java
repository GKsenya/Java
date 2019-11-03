package Lesson_6;


public class Multiplication extends Thread {

    private int[][] matrixFirst;
    private int[][] matrixSecond;
    private int[][] result;
    private int mFLength;
    private int mSLength;
    private int mSHeight;

    public Multiplication(int[][] matrixFirst, int[][] matrixSecond) {
        this.matrixFirst = matrixFirst;
        this.matrixSecond = matrixSecond;
        this.mFLength = matrixFirst.length;
        this.mSLength = matrixSecond[0].length;
        this.mSHeight = matrixSecond[0].length;
        this.result = new int[mFLength][mSLength];
    }

    @Override
    public void run() {
        for (int id = 0; id < mFLength; id++) {
            for (int idx = 0; idx < mSLength; idx++) {
                int element = 0;
                for (int i = 0; i < mSHeight; i++) {
                    element += matrixFirst[id][i] * matrixSecond[i][idx];
                }
                result[id][idx] = element;
            }
        }
    }

    public int[][] getElement() {
        return result;
    }
}
