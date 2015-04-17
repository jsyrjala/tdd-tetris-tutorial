// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

/**
 * Created by jsyrjala on 4/17/15.
 */
public class Block implements Shape {
    public static final char EMPTY = '.';
    public static final char BLOCK = 'X';
    private int row, col;
    private final char type;
    public Block(char type) {
        this.row = 0;
        this.col = 1;
        this.type = type;
    }

    @Override
    public int getRow() {
        return row;
    }
    @Override
    public int getCol() {
        return col;
    }
    @Override
    public void goDown() {
        this.row ++;
    }
    @Override
    public char getType() {
        return type;
    }

    @Override
    public void init(int startingCol) {
        this.col = startingCol;
    }

    @Override
    public boolean hasBlockAt(int row, int col) {
        return this.row == row && this.col == col;
    }

    @Override
    public void moveLeft() {
        col -= 1;
    }

    @Override
    public void moveRight() {
        col += 1;
    }

    @Override
    public void moveDown() {
        row += 1;
    }
}
