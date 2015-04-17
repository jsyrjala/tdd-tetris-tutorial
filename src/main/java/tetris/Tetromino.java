// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

/**
 * Created by jsyrjala on 4/17/15.
 */
public class Tetromino extends Piece {
    public static final Tetromino T_SHAPE = new Tetromino("" +
            ".T.\n" +
            "TTT\n" +
            "...\n"
    );

    public Tetromino(String blockData) {
        super(blockData);
    }
}
