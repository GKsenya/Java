package Lesson_6;

public class SimpleMultiplication {

    private int[][] matrixFirst;
    private int[][] matrixSecond;
    private int[][] result;
    private int mFHight;
    private int mSHight;
    private int mSLength;

    public SimpleMultiplication(int[][] matrixFirst, int[][] matrixSecond) {
        this.matrixFirst = matrixFirst;
        this.matrixSecond = matrixSecond;
        this.mFHight = matrixFirst.length;
        this.mSLength = matrixSecond[0].length;
        this.mSHight = matrixSecond.length;

        this.result = new int[mFHight][mSHight];
    }

    public int[][] getResult() throws IncorrectInputData {
        for (int id = 0; id < mFHight; id++) {
            for (int idx = 0; idx < mSLength; idx++) {
                int element = 0;

                 for (int i = 0; i < mSHight; i++) {
                    element += matrixFirst[id][i] * matrixSecond[i][idx];
//                    System.out.println(element);
                }
                result[id][idx] = element;
            }
        }
        return result;
    }
}
