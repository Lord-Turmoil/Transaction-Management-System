/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: IdValidatorTest.java
 * Last Update: 7/24/23, 5:37 PM
 */

package tms.validator.impl;


import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {
    @Test
    public void idValidatorTest() {
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

    @Test
    public void passwordValidatorTest() {
        var validator = new PasswordValidator();
        var good = new String[]{
                "a256@buq",
                "hp1o_sdf",
                "Z568a9$73",
        };
        var bad = new String[]{
                "896qou",
                "a*afjsd12",
                "123456a_",
        };

        for (var password : good) {
            Assert.assertEquals(true, validator.check(password));
        }
        for (var password : bad) {
            Assert.assertEquals(false, validator.check(password));
        }
    }
}
