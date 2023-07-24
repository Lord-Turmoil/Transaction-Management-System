/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IdValidatorTest.java
 * Last Update: 7/24/23, 5:37 PM
 */

package tms.validator.impl;


import org.junit.Assert;
import org.junit.Test;

public class IdValidatorTest {
    @Test
    public void validateTest() {
        var validator = new IdValidator();
        var good = new String[]{
                "007517901234",
                "447018252000",
        };
        var bad = new String[]{
                "000017861234",
                "000119001526",
                "000518364235",
        };

        for (var id : good) {
            Assert.assertEquals(true, validator.check(id));
        }
        for (var id : bad) {
            Assert.assertEquals(false, validator.check(id));
        }
    }
}
