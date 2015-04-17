// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jsyrjala on 4/17/15.
 */
public class Tetromino extends Piece implements Shape {

    private static final Piece T_SHAPE_PIECE = new Piece("" +
            ".T.\n" +
            "TTT\n" +
            "...\n");

    private static final Piece I_SHAPE_PIECE = new Piece("" +
            ".....\n" +
            ".....\n" +
            "IIII.\n" +
            ".....\n" +
            ".....\n");

    private static final Piece O_SHAPE_PIECE = new Piece("" +
            ".OO\n" +
            ".OO\n" +
            "...\n");

    private static final List<Piece> T_ROTATIONS = Arrays.asList(
            T_SHAPE_PIECE,
            T_SHAPE_PIECE.rotateRight(),
            T_SHAPE_PIECE.rotateRight().rotateRight(),
            T_SHAPE_PIECE.rotateRight().rotateRight().rotateRight()
    );

    private static final List<Piece> I_ROTATIONS = Arrays.asList(
            I_SHAPE_PIECE,
            I_SHAPE_PIECE.rotateRight()
    );

    private static final List<Piece> O_ROTATIONS = Arrays.asList(
            O_SHAPE_PIECE
    );
    public static final Tetromino T_SHAPE = new Tetromino(T_ROTATIONS, 'T', 0);
    public static final Tetromino I_SHAPE = new Tetromino(I_ROTATIONS, 'I', 0);
    public static final Tetromino O_SHAPE = new Tetromino(O_ROTATIONS, 'O', 0);;

    private final List<Piece> rotations;
    private final int index;
    private int row, col;
    private final char type;

    public Tetromino(List<Piece> rotations, char type, int index) {
        super(rotations.get(index).toString());
        this.rotations = rotations;
        this.type = type;
        this.index = index;
    }

    @Override
    public Tetromino rotateLeft() {
        return new Tetromino(rotations, type, normalizeIndex(this.index - 1));
    }

    @Override
    public Tetromino rotateRight() {
        return new Tetromino(rotations, type, normalizeIndex(this.index + 1));
    }

    private int normalizeIndex(int i) {
        int x = i % rotations.size();
        if(x < 0 ) {
            return rotations.size() - 2 - x;
        }
        return x;
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
        this.row = 0;
    }

    @Override
    public boolean hasBlockAt(int row, int col) {
        int shapeRow = row - this.row;
        int shapeCol = col - this.col +1; // TODO miksi +1?

        if( shapeRow < 0 || shapeCol < 0) {
            return false;
        }

        if( shapeRow > blocks.size() - 1 || shapeCol > blocks.get(0).size() - 1) {
            return false;
        }
        return blocks.get(shapeRow).get(shapeCol) != Block.EMPTY;
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
