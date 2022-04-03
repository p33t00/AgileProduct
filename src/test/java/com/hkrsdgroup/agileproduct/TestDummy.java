package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.Dummy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDummy {
    @Test
    public void testIncBy2() {
        Dummy d = new Dummy();
        var res = d.incBy2(2);
        assertEquals(2, res);
    }
}
