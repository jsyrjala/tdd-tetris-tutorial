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
    public final char type;
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
}
