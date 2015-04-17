// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(NestedJUnit.class)
public class MovingAFallingPieceTest extends Assert {

    // Step 5: It's your turn now
    // - Remove the @Ignore annotation from this class
    // - The test names have been provided, you just need to fill in the test body
    // - Next step: RotatingAFallingPieceTest

    // TODO: a falling piece can be moved left OK
    // TODO: a falling piece can be moved right OK
    // TODO: a falling piece can be moved down OK
    // TODO: it will not move left over the board OK
    // TODO: it will not move right over the board OK
    // TODO: it will not move down over the board (will stop falling) OK
    // TODO: it cannot be moved left if another piece is in the way
    // TODO: it cannot be moved right if another piece is in the way
    // TODO: it cannot be moved down if another piece is in the way (will stop falling)

    // P.S. Take into consideration, that part of the piece's area may be empty cells.
    // Only non-empty cells should take part in the collision checks.

    public class When_a_piece_is_falling {
        private final Board board = new Board(6, 8);
        @Before
        public void dropPiece() {
            board.drop(Tetromino.T_SHAPE);
            assertEquals("" +
                    "....T...\n" +
                    "...TTT..\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }
        @Test
        public void it_can_be_moved_left() {
            board.moveShapeLeft();
            assertEquals("" +
                    "...T....\n" +
                    "..TTT...\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void it_can_be_moved_right() {
            board.moveShapeRight();
            assertEquals("" +
                    ".....T..\n" +
                    "....TTT.\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void it_can_be_moved_down() {
            board.moveShapeDown();
            assertEquals("" +
                    "........\n" +
                    "....T...\n" +
                    "...TTT..\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }
    }

    public class When_shape_hits_border {
        private final Board board = new Board(6, 8);
        @Before
        public void dropPiece() {
            board.drop(Tetromino.T_SHAPE);
            assertEquals("" +
                    "....T...\n" +
                    "...TTT..\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void it_doesnt_go_over_left_border() {
            board.moveShapeLeft();
            board.moveShapeLeft();
            board.moveShapeLeft();
            assertEquals("" +
                    ".T......\n" +
                    "TTT.....\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());

            board.moveShapeLeft();
            assertEquals("" +
                    ".T......\n" +
                    "TTT.....\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void it_doesnt_go_over_right_border() {
            board.moveShapeRight();
            board.moveShapeRight();
            board.moveShapeRight();
            assertEquals("" +
                    "......T.\n" +
                    ".....TTT\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());

            board.moveShapeRight();
            assertEquals("" +
                    "......T.\n" +
                    ".....TTT\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        @Test
        public void it_doesnt_go_over_bottom_border() {
            board.moveShapeDown();
            board.moveShapeDown();
            board.moveShapeDown();
            board.moveShapeDown();

            assertEquals("" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "....T...\n" +
                    "...TTT..\n", board.toString());

            board.moveShapeDown();
            assertEquals("" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "....T...\n" +
                    "...TTT..\n", board.toString());
        }
    }

    public class When_shape_hits_another_shape {
        private final Board board = new Board("" +
                "........\n" +
                "X......X\n" +
                "X......X\n" +
                "X......X\n" +
                "X......X\n" +
                "XXXXXXXX\n");

        @Before
        public void dropPiece() {
            board.drop(Tetromino.T_SHAPE);
            assertEquals("" +
                    "....T...\n" +
                    "X..TTT.X\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "XXXXXXXX\n", board.toString());
        }

        @Test
        public void it_doesnt_go_over_shape_on_left() {
            board.moveShapeLeft();
            board.moveShapeLeft();
            assertEquals("" +
                    "..T.....\n" +
                    "XTTT...X\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "XXXXXXXX\n", board.toString());

            board.moveShapeLeft();
            assertEquals("" +
                    "..T.....\n" +
                    "XTTT...X\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "XXXXXXXX\n", board.toString());
        }

        @Test
        public void it_doesnt_go_over_shape_on_right() {
            board.moveShapeRight();
            board.moveShapeRight();
            assertEquals("" +
                    ".....T..\n" +
                    "X...TTTX\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "XXXXXXXX\n", board.toString());

            board.moveShapeRight();
            assertEquals("" +
                    ".....T..\n" +
                    "X...TTTX\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "XXXXXXXX\n", board.toString());
        }

        @Test
        public void it_doesnt_go_over_shape_on_below() {
            board.moveShapeDown();
            board.moveShapeDown();
            board.moveShapeDown();
            assertEquals("" +
                    "........\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "X...T..X\n" +
                    "X..TTT.X\n" +
                    "XXXXXXXX\n", board.toString());

            board.moveShapeDown();
            assertEquals("" +
                    "........\n" +
                    "X......X\n" +
                    "X......X\n" +
                    "X...T..X\n" +
                    "X..TTT.X\n" +
                    "XXXXXXXX\n", board.toString());
        }
    }
}
