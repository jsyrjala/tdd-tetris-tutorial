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
    private Shape currentShape;

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


                if(isCurrentShapeAt(row, col)) {
                    s += currentShape.getType();
                } else {
                    s += getAt(row, col);
                }
            }
            s += "\n";
        }
        return s;
    }

    private boolean isCurrentShapeAt(int row, int col) {
        return currentShape != null &&
                currentShape.hasBlockAt(row, col);
    }

    public boolean hasFalling() {
        return falling;
    }


    public void drop(Shape newShape) {
        if(falling) {
            throw new IllegalStateException("already falling");
        }
        falling = true;
        currentShape = newShape;
        currentShape.init(this.columns / 2);
    }

    public void tick() {
        if(canDrop()) {
            currentShape.goDown();
        } else {
            falling = false;
            showBlock(currentShape);
        }
    }

    private boolean canDrop() {
        return !(hitsBottom() || hasBlockBelow());
    }

    private boolean hasBlockBelow() {
        return getAt(currentShape.getRow() + 1, currentShape.getCol()) != Block.EMPTY ;
    }

    private boolean hitsBottom() {
        for(int c = 0; c < columns ; c ++ ) {
            if(currentShape.hasBlockAt(this.rows -1, c)) {
                return true;
            }
        }
        return false;
    }

    private char getAt(int row, int col) {
        return board.get(row).get(col);
    }

    private void setAt(int row, int col, char value) {
        board.get(row).set(col, value);
    }

    private void showBlock(Shape shape) {
        for(int r = 0; r < rows ; r ++) {
            for (int c = 0; c < columns; c++) {
                if (shape.hasBlockAt(r, c)) {
                    setAt(r, c, shape.getType());
                }
            }
        }
    }
}
