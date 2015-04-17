// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jsyrjala on 4/17/15.
 */
public class Util {
    public static final List<List<Character>> toBlocks(String data) {
        if(data.isEmpty()) {
            return Arrays.asList();
        }
        String[] rows = data.trim().split("\n");
        List<List<Character>> array = new ArrayList<>();
        for(String row: rows) {
            List<Character> rowArray = new ArrayList<>();
            array.add(rowArray);
            for (int col = 0; col < row.length(); col++) {
                rowArray.add(row.charAt(col));
            }
        }
        return array;
    }
}
