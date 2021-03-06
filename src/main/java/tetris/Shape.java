// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

/**
 * Created by jsyrjala on 4/17/15.
 */
public interface Shape {

    int getRow();
    int getCol();
    void goDown();
    char getType();

    void init(int startingCol);

    boolean hasBlockAt(int row, int col);

    void moveLeft();

    void moveRight();

    void moveDown();
}
