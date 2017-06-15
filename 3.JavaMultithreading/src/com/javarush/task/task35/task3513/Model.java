package com.javarush.task.task35.task3513;

import java.util.*;

public class Model { //Класс, ответственный за все манипуляции производимые с игровым полем.
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    int score;      //Общий счет
    int maxTile;    //Величина максимального квадрата
    //private static final Random RANDOM = new Random(); //TODO после реализации задачи вернуть RANDOM

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    void resetGameTiles() {
        score = 0;
        maxTile = 2;
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (Tile[] tileRow : gameTiles) {
            for (int i = 0; i < FIELD_WIDTH; i++) {
                tileRow[i] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() == 0) return;
        Tile chosenTile = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
        //Tile chosenTile = emptyTiles.get(RANDOM.nextInt(emptyTiles.size())); //Выбирает случайный квадратик из множества пустых квадратиков
        //chosenTile.value = RANDOM.nextDouble() < 0.9 ? 2 : 4;                //Примерно каждый 10-й квадратик будет 4, остальные 2
        chosenTile.value = Math.random() < 0.9 ? 2 : 4;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (Tile[] tileRow : gameTiles) {
            for (Tile tile : tileRow) {
                if (tile.isEmpty()) result.add(tile);
            }
        }
        return result;
    }

    private void saveState() {
        Tile[][] temp = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int r = 0; r < FIELD_WIDTH; r++) {
            for (int c = 0; c < FIELD_WIDTH; c++) {
                temp[r][c] = new Tile(gameTiles[r][c].value);
            }
        }
        previousStates.push(temp);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (previousScores.isEmpty() || previousStates.isEmpty()) return;
        gameTiles = previousStates.pop();
        score = previousScores.pop();
    }

    public void randomMove() {
        int random = (int) (Math.random() * 4); //TODO заменить на Random в конце
        switch (random) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.add(getMoveEfficiency(this::left));
        queue.add(getMoveEfficiency(this::right));
        queue.add(getMoveEfficiency(this::up));
        queue.add(getMoveEfficiency(this::down));
        queue.peek().getMove().move();
    }

    public boolean hasBoardChanged() {
        Tile[][] lastState = previousStates.peek();

        for (int r = 0; r < lastState.length; r++) {
            for (int c = 0; c < lastState[0].length; c++) {
                if (lastState[r][c].value != gameTiles[r][c].value) return true;
            }
        }
        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        if (!hasBoardChanged()) return new MoveEfficiency(-1, 0, move);
        MoveEfficiency me = new MoveEfficiency(getEmptyTiles().size(), score, move);
        rollback();
        return me;
    }

    void left() {
        if (isSaveNeeded) saveState();
        boolean needToAdd = false;
        for (Tile[] tileRow : gameTiles) {
            if (compressTiles(tileRow) | mergeTiles(tileRow)) {
                needToAdd = true;
            }
        }
        if (needToAdd) addTile();
        isSaveNeeded = true;
    }

    void up() {
        saveState();
        rotateMatrixLeft();
        left();
        rotateMatrixRight();
    }

    void down() {
        saveState();
        rotateMatrixRight();
        left();
        rotateMatrixLeft();
    }

    void right() {
        saveState();
        rotateMatrixRight();
        rotateMatrixRight();
        left();
        rotateMatrixLeft();
        rotateMatrixLeft();
    }

    public boolean canMove() {
        return canCompress() || canMerge();
    }

    private boolean canCompress() {
        for (Tile[] tileRow : gameTiles) {
            for (Tile tile : tileRow) {
                if (tile.value == 0) return true;
            }
        }
        return false;
    }

    private boolean canMerge() {
        int rows = gameTiles.length;
        int columns = gameTiles[0].length;
        for (Tile[] tileRow : gameTiles) {
            for (int columnIndex = 1; columnIndex < columns; columnIndex++) {
                if (tileRow[columnIndex].value == tileRow[columnIndex - 1].value) return true;
            }
        }

        for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
            for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
                if (gameTiles[rowIndex][columnIndex].value == gameTiles[rowIndex - 1][columnIndex].value) return true;
            }
        }
        return false;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean compressed = false;
        for (int chosenTileIndex = 1; chosenTileIndex < tiles.length; chosenTileIndex++) {
            if (tiles[chosenTileIndex].value == 0) continue;
            for (int sideTileIndex = chosenTileIndex - 1; sideTileIndex >= 0; sideTileIndex--) {
                if (tiles[sideTileIndex].value != 0) break;
                tiles[sideTileIndex].value = tiles[sideTileIndex + 1].value;
                tiles[sideTileIndex + 1].value = 0;
                compressed = true;
            }
        }
        return compressed;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean merged = false;
        for (int chosenTileIndex = 0; chosenTileIndex < tiles.length - 1; chosenTileIndex++) {
            Tile chosenTile = tiles[chosenTileIndex];
            if (chosenTile.value == 0) continue;
            Tile sideTile = tiles[chosenTileIndex + 1];
            if (sideTile.value == 0) continue;
            if (chosenTile.value == sideTile.value) {
                chosenTile.value *= 2;
                score += chosenTile.value;
                if (chosenTile.value > maxTile) maxTile = chosenTile.value;
                sideTile.value = 0;
                compressTiles(tiles);
                merged = true;
            }
        }
        return merged;
    }

    public void rotateMatrixRight() {
        int newWidth = gameTiles.length;
        int newHeight = gameTiles[0].length;
        Tile[][] result = new Tile[newHeight][newWidth];
        for (int height = 0; height < newHeight; ++height) {
            for (int width = 0; width < newWidth; ++width) {
                int tempWidth = newWidth - width - 1;
                result[height][width] = gameTiles[tempWidth][height];
            }
        }
        gameTiles = result;
    }

    public void rotateMatrixLeft() {
        int newWidth = gameTiles.length;
        int newHeight = gameTiles[0].length;
        Tile[][] result = new Tile[newHeight][newWidth];
        for (int height = 0; height < newHeight; ++height) {
            for (int width = 0; width < newWidth; ++width) {
                int tempHeight = newHeight - height - 1;
                result[height][width] = gameTiles[width][tempHeight];
            }
        }
        gameTiles = result;
    }
}
