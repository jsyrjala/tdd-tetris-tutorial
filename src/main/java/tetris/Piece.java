// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsyrjala on 4/17/15.
 */
public class Piece {
    protected List<List<Character>> blocks;

    public Piece(String blockData) {
        this.blocks = Util.toBlocks(blockData);
    }

    public Piece(List<List<Character>> blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for(List<Character> rowArray: blocks) {
            for(char c : rowArray){
                buf.append(c);
            }
            buf.append('\n');
        }
        return buf.toString();
    }

    public Piece rotateRight() {
        int rows = blocks.size();
        int cols = blocks.get(0).size();

        List<List<Character>> newBlocks = createArray(rows);
        for(int r = 0; r < rows; r ++) {
            for(int c = 0; c < cols; c ++) {
                char value = blocks.get(rows - r - 1).get(c);
                newBlocks.get(c).set(r, value);
            }
        }
        return createPiece(newBlocks);
    }

    public Piece rotateLeft() {
        return rotateRight().rotateRight().rotateRight();
    }

    protected Piece createPiece(List<List<Character>> newBlocks) {
        return new Piece(newBlocks);
    }
    private List<List<Character>> createArray(int size) {
        List<List<Character>> array = new ArrayList<>();
        for(int r = 0; r < size; r ++) {
            List<Character> rowArray = new ArrayList<>();
            array.add(rowArray);
            for(int c = 0; c < size; c ++) {
                rowArray.add('?');
            }
        }
        return array;
    }
}
