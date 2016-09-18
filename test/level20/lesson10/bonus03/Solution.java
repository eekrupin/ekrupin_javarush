package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same", "npe");

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    static int[][] directions = new int[][]{
            {0,1},
            {1,1},
            {1,0},
            {1,-1},
            {0,-1},
            {-1,-1},
            {-1,0},
            {-1,1}
    };

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> detectAllWords = new ArrayList<>();

        int[][] used = new int[crossword.length][crossword[0].length];
        for (int x = 0; x < crossword[0].length; x++) {
            for (int y = 0; y < crossword.length; y++) {
                if (used[y][x]==1) continue;
                else {
                    for (String strindWord: words) {
                        if (used[y][x]==1) continue;
                        if ((int)strindWord.charAt(0)==crossword[y][x]) processing(crossword, strindWord, used, detectAllWords, x, y);
                    }
                }
            }
        }

        for (Word word : detectAllWords) {
            System.out.println(word.toString());
        }

        return detectAllWords;
    }

    private static void processing(int[][] crossword, String strindWord, int[][] used, List<Word> detectAllWords, int x, int y) {
        int startX = x;
        int startY = y;
        int maxX = crossword[0].length-1;
        int maxY = crossword.length-1;

        for (int inddir = 0; inddir<directions.length; inddir++) {
            int iterY = directions[inddir][0];
            int iterX = directions[inddir][1];
            int lenghtWord = strindWord.length();

            int[][] usingCoord = new int[lenghtWord][2];
            for (int i=0; i<lenghtWord;i++){
                int curX = startX+i*iterX;
                int curY = startY+i*iterY;
                if (curX>maxX || curY>maxY || curX<0 || curY<0) break;
                if (crossword[curY][curX]==(int)strindWord.charAt(i)){
                    usingCoord[i][0] = curY;
                    usingCoord[i][1] = curX;
                    if (i==(lenghtWord-1)){
                        Word word = new Word(strindWord);
                        word.setStartPoint(startX, startY);
                        word.setEndPoint(curX, curY);
                        detectAllWords.add(word);
                        for (int ind = 0; ind < lenghtWord; ind++) {
                            used[usingCoord[ind][0]][usingCoord[ind][1]] = 1;
                            return;
                        }
                    }
                }
                else break;
            }
        }



    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
