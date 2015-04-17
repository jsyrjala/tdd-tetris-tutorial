// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by jsyrjala on 4/17/15.
 */
public class UtilTest {

    @Test
    public void it_should_retunr_emptytoBlocks_should_return_empty_list() {
        Assert.assertEquals(Arrays.asList(), Util.toBlocks(""));
    }
    @Test
    public void it_should_retunr_emptytoBlocks_should_return_non_empty_list() {
        Assert.assertEquals(Arrays.asList(Arrays.asList('1','2','3'), Arrays.asList('4','5','6')), Util.toBlocks("123\n" + "456\n"));
    }
}
