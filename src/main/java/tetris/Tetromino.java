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
    public static final Tetromino I_SHAPE = new IShape("" +
            ".....\n" +
            ".....\n" +
            "IIII.\n" +
            ".....\n" +
            ".....\n");

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

    private static class IShape extends Tetromino {
        private int angle = 0;
        public IShape(String blockData) {
            super(blockData);
        }

        public IShape(List<List<Character>> blockData) {
            super(blockData);
        }

        @Override
        public Tetromino rotateLeft() {
            angle += 1;
            if(angle % 2 == 1) {
                return super.rotateLeft();
            } else {
                return super.rotateRight();
            }
        }

        @Override
        public Tetromino rotateRight() {
            angle -= 1;
            if(angle % 2 == 1) {
                return super.rotateLeft();
            } else {
                return super.rotateRight();
            }
        }
    }
}
