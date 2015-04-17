// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.List;

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
    public Tetromino(List<List<Character>> blockData) {
        super(blockData);
    }
    @Override
    public Tetromino rotateLeft() {
        return (Tetromino) super.rotateLeft();
    }

    @Override
    public Tetromino rotateRight() {
        return (Tetromino) super.rotateRight();
    }

    @Override
    protected Piece createPiece(List<List<Character>> newBlocks) {
        return new Tetromino(newBlocks);
    }
}
