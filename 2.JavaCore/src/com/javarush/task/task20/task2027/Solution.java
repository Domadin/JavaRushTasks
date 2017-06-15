package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}

        };
        System.out.println(detectAllWords(crossword, "jr", "rr", "oe", "mm", "vo", "jrgs", "vorg","aaa"));
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        List<Word> wordsList = new ArrayList<>();
        int above, below;           // rows considered above and below row number r
        int left, right;            // columns considered left and right of column c
        int width = crossword[0].length;    //Количество столбцов columns
        int height = crossword.length;      //Количество строк (рядов rows)
        boolean interrupted, found = false;

        for (String word : words) {
            byte[] data = word.getBytes();

            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    found = false;

                    if (crossword[r][c] == data[0]) {
                        System.out.println("Нашел 1 букву " + r + c);
                        int startRow = r;
                        int startColumn = c;

                        Word newWord = new Word(word);
                        newWord.setStartPoint(c, r);
                        if (data.length > 1) {

                            above = r - 1;
                            interrupted = false;
                            if (above > -1 && crossword[above][c] == data[1]) {
                                if (data.length > 2) {
                                    for (int i = 2; i < data.length; i++) {
                                        above -= 1;
                                        if (above > -1 && crossword[above][c] == data[i]) {
                                        } else {
                                            interrupted = true;
                                            break;
                                        }
                                    }
                                    if (!interrupted) {
                                        Word temp = new Word(word);
                                        temp.setEndPoint(c, above);
                                        temp.setStartPoint(startColumn, startRow);
                                        wordsList.add(temp);

                                        found = true;
                                    }
                                } else {
                                    Word temp = new Word(word);
                                    temp.setEndPoint(c, above);
                                    temp.setStartPoint(startColumn, startRow);
                                    wordsList.add(temp);
                                    found = true;
                                }

                            }
                            below = r + 1;
                            interrupted = false;
                            if (below < height && crossword[below][c] == data[1]) {

                                if (data.length > 2) {
                                    for (int i = 2; i < data.length; i++) {
                                        below += 1;
                                        if (below < height && crossword[below][c] == data[i]) {
                                        } else {
                                            interrupted = true;
                                            break;
                                        }
                                    }
                                    if (!interrupted) {
                                        Word temp = new Word(word);
                                        temp.setEndPoint(c, below);
                                        temp.setStartPoint(startColumn, startRow);
                                        wordsList.add(temp);

                                        found = true;
                                    }
                                } else {
                                    Word temp = new Word(word);
                                    temp.setEndPoint(c, below);
                                    temp.setStartPoint(startColumn, startRow);
                                    wordsList.add(temp);
                                    found = true;
                                }

                            }
                            left = c - 1;
                            interrupted = false;
                            if (left > -1 && crossword[r][left] == data[1]) {

                                if (data.length > 2) {
                                    for (int i = 2; i < data.length; i++) {
                                        left -= 1;
                                        if (left > -1 && crossword[r][left] == data[i]) {
                                        } else {
                                            interrupted = true;
                                            break;
                                        }
                                    }
                                    if (!interrupted) {
                                        Word temp = new Word(word);
                                        temp.setEndPoint(left, r);
                                        temp.setStartPoint(startColumn, startRow);
                                        wordsList.add(temp);

                                        found = true;
                                    }
                                } else {
                                    Word temp = new Word(word);
                                    temp.setEndPoint(left, r);
                                    temp.setStartPoint(startColumn, startRow);
                                    wordsList.add(temp);
                                    found = true;
                                }

                            }
                            right = c + 1;
                            interrupted = false;
                            if (right < width && crossword[r][right] == data[1]) {

                                if (data.length > 2) {
                                    for (int i = 2; i < data.length; i++) {
                                        right += 1;
                                        if (right < width && crossword[r][right] == data[i]) {
                                        } else {
                                            interrupted = true;
                                            break;
                                        }
                                    }
                                    if (!interrupted) {
                                        Word temp = new Word(word);
                                        temp.setEndPoint(right, r);
                                        temp.setStartPoint(startColumn, startRow);
                                        wordsList.add(temp);
                                        found = true;
                                    }
                                } else {
                                    Word temp = new Word(word);
                                    temp.setEndPoint(right, r);
                                    temp.setStartPoint(startColumn, startRow);
                                    wordsList.add(temp);
                                    found = true;
                                }

                            }
                            below = r + 1;
                            left = c - 1;
                            interrupted = false;
                            if (left > -1 && below < height && crossword[below][left] == data[1]) {

                                if (data.length > 2) {
                                    for (int i = 2; i < data.length; i++) {
                                        left -= 1;
                                        below += 1;
                                        if (left > -1 && below < height && crossword[below][left] == data[i]) {
                                        } else {
                                            interrupted = true;
                                            break;
                                        }
                                    }
                                    if (!interrupted) {
                                        Word temp = new Word(word);
                                        temp.setEndPoint(left, below);
                                        temp.setStartPoint(startColumn, startRow);
                                        wordsList.add(temp);

                                        found = true;
                                    }
                                } else {
                                    Word temp = new Word(word);
                                    temp.setEndPoint(left, below);
                                    temp.setStartPoint(startColumn, startRow);
                                    wordsList.add(temp);
                                    found = true;
                                }

                            }
                            below = r + 1;
                            right = c + 1;
                            interrupted = false;
                            if (right < width && below < height && crossword[below][right] == data[1]) {

                                if (data.length > 2) {
                                    for (int i = 2; i < data.length; i++) {
                                        right += 1;
                                        below += 1;
                                        if (right < width && below < height && crossword[below][right] == data[i]) {
                                        } else {
                                            interrupted = true;
                                            break;
                                        }
                                    }
                                    if (!interrupted) {
                                        Word temp = new Word(word);
                                        temp.setEndPoint(right, below);
                                        temp.setStartPoint(startColumn, startRow);
                                        wordsList.add(temp);
                                        found = true;
                                    }
                                } else {
                                    Word temp = new Word(word);
                                    temp.setEndPoint(right, below);
                                    temp.setStartPoint(startColumn, startRow);
                                    wordsList.add(temp);
                                    found = true;
                                }

                            }
                            above = r - 1;
                            left = c - 1;
                            interrupted = false;
                            if (left > -1 && above > -1 && crossword[above][left] == data[1]) {

                                if (data.length > 2) {
                                    for (int i = 2; i < data.length; i++) {
                                        left -= 1;
                                        above -= 1;
                                        if (left > -1 && above > -1 && crossword[above][left] == data[i]) {
                                        } else {
                                            interrupted = true;
                                            break;
                                        }
                                    }
                                    if (!interrupted) {

                                        Word temp = new Word(word);
                                        temp.setEndPoint(left, above);
                                        temp.setStartPoint(startColumn, startRow);
                                        wordsList.add(temp);
                                        found = true;
                                    }
                                } else {
                                    Word temp = new Word(word);
                                    temp.setEndPoint(left, above);
                                    temp.setStartPoint(startColumn, startRow);
                                    wordsList.add(temp);
                                    found = true;
                                }

                            }
                            above = r - 1;
                            right = c + 1;
                            interrupted = false;
                            if (right < width && above > -1 && crossword[above][right] == data[1]) {

                                if (data.length > 2) {
                                    for (int i = 2; i < data.length; i++) {
                                        right += 1;
                                        above -= 1;
                                        if (right < width && above > -1 && crossword[above][right] == data[i]) {
                                        } else {
                                            interrupted = true;
                                            break;
                                        }
                                    }
                                    if (!interrupted) {
                                        Word temp = new Word(word);
                                        temp.setEndPoint(right, above);
                                        temp.setStartPoint(startColumn, startRow);
                                        wordsList.add(temp);

                                        found = true;
                                    }
                                } else {
                                    Word temp = new Word(word);
                                    temp.setEndPoint(right, above);
                                    temp.setStartPoint(startColumn, startRow);
                                    wordsList.add(temp);
                                    found = true;
                                }
                            }
                        }
                    }
                }
            }
            if (!found) {
                Word newWord = new Word(word);
                wordsList.add(newWord);

            }
        }
        return wordsList;
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
