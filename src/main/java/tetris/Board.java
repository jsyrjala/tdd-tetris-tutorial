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
        this.board = new ArrayList<>(this.rows);
        for(int r = 0 ; r < this.rows ; r++) {
            List<Character> colList = new ArrayList<>(this.columns);
            for(int c = 0; c < this.columns; c++) {
                colList.add(Block.EMPTY);
            }
            this.board.add(colList);
        }
    }

    public Board(String boardData) {
        this.board = Util.toBlocks(boardData);
        this.rows = board.size();
        this.columns = board.get(0).size();
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
            addShapeToBoard(currentShape);
        }
    }

    private boolean canDrop() {
        return !(hitsBottom() || collidesWithBlocksAtOffset(+1, 0));
    }

    private boolean collidesWithBlocksAtOffset(int rowOffset, int colOffset) {
        for(int r = 0; r < rows ; r ++) {
            for (int c = 0; c < columns; c++) {
                if(currentShape.hasBlockAt(r, c) &&
                        ( ((r + rowOffset) > rows -1 ) || (c + colOffset < 0) ||  (c + colOffset > columns -1) ||
                                reservedAt(rowOffset, colOffset, r, c))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean reservedAt(int rowOffset, int colOffset, int r, int c) {
        return getAt(r + rowOffset, c + colOffset) != Block.EMPTY;
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

    private void addShapeToBoard(Shape shape) {
        for(int r = 0; r < rows ; r ++) {
            for (int c = 0; c < columns; c++) {
                if (shape.hasBlockAt(r, c)) {
                    setAt(r, c, shape.getType());
                }
            }
        }
    }

    public void moveShapeLeft() {
        if(!collidesWithBlocksAtOffset(0, -1)) {
            currentShape.moveLeft();
        }
    }

    public void moveShapeRight() {
        if(!collidesWithBlocksAtOffset(0, +1)) {
            currentShape.moveRight();
        }
    }

    public void moveShapeDown() {
        if(!collidesWithBlocksAtOffset(+1, 0)) {
            currentShape.moveDown();
        }
    }
}
