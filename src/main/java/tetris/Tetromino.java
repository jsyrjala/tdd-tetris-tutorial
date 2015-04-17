// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jsyrjala on 4/17/15.
 */
public class Tetromino extends Piece {

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
    public static final Tetromino T_SHAPE = new Tetromino(T_ROTATIONS, 0);
    public static final Tetromino I_SHAPE = new Tetromino(I_ROTATIONS, 0);
    public static final Tetromino O_SHAPE =  new Tetromino(O_ROTATIONS, 0);;

    private final List<Piece> blockDatas;
    private final int index;

    public Tetromino(List<Piece> blockDatas, int index) {
        super(blockDatas.get(index).toString());
        this.blockDatas = blockDatas;
        this.index = index;
    }

    @Override
    public Tetromino rotateLeft() {
        return new Tetromino(blockDatas, normalizeIndex(this.index - 1));
    }

    @Override
    public Tetromino rotateRight() {
        return new Tetromino(blockDatas, normalizeIndex(this.index + 1));
    }

    private int normalizeIndex(int i) {
        int x = i % blockDatas.size();
        if(x < 0 ) {
            return blockDatas.size() - 2 - x;
        }
        return x;
    }

}
