package com.javarush.test.level20.lesson10.bonus02;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {

        int RectangleCount = 0;

        int length = a.length;
        byte[][] used = new byte[length][length];

        for (int i=0; i<length;i++){
            for (int j=0; j<length;j++){
                if (used[i][j]==1) continue;
                if (a[i][j]==1) {
                    processRectangle(a, used, length, i, j);
                    RectangleCount++;
                }
                else used[i][j]=1;
            }
        }

        return RectangleCount;
    }

    private static void processRectangle(byte[][] a, byte[][] used, int length, int i, int j) {
        int endI = i;
        int endJ = j;
        for (int ii=i; ii<length;ii++){
            if (a[ii][j]==1) endI=ii;
            if (a[ii][j]==0) {
                endI=ii;
                break;
            }
        }

        for (int jj=j; jj<length;jj++){
            if (a[i][jj]==1) endJ=jj;
            if (a[i][jj]==0) {
                endJ=jj;
                break;
            }
        }

        for (int ii=i; ii<=endI;ii++){
            for (int jj=j; jj<=endJ;jj++){
                used[ii][jj]=1;
            }
        }

    }
}
