// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int rows;
    private final int columns;
    private boolean falling;

    private List<List<Character>> board;
    private Block currentBlock;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new ArrayList<>(this.rows);
        for(int r = 0 ; r < this.rows ; r++) {
            List<Character> colList = new ArrayList<>(this.columns);
            for(int c = 0; c < this.columns; c++) {
                colList.add(Block.EMPTY);
            }
            board.add(colList);
        }
    }

    public String toString() {
        String s = "";
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {

                if(currentBlock != null && row == currentBlock.row && col == currentBlock.col) {
                    s += currentBlock.type;
                } else {
                    s += getAt(row, col);
                }
            }
            s += "\n";
        }
        return s;
    }

    public boolean hasFalling() {
        return falling;
    }


    public void drop(Block newBlock) {
        if(falling) {
            throw new IllegalStateException("already falling");
        }
        falling = true;
        currentBlock = newBlock;
    }

    public void tick() {
        if(canDrop()) {
            currentBlock.row++;
        } else {
            falling = false;
            showBlock(currentBlock);
        }
    }

    private boolean canDrop() {
        return getAt(currentBlock.row, currentBlock.col) != Block.EMPTY || currentBlock.row < this.rows -1;
    }

    private char getAt(int row, int col) {
        return board.get(row).get(col);
    }

    private void setAt(int row, int col, char value) {
        board.get(row).set(col, value);
    }

    private void showBlock(Block block) {
        setAt(block.row, block.col, block.type);
    }
}
