package com.stevenw.compute;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author stevenw
 * @date 2019/7/19
 */
@SpringBootTest
public class ComputeTest {
    @Test
    public void testDoubleCompute(){
        //RoundingMode.HALF_EVEN 银行家舍入法，4舍6如，5后有数进位，5后有数，5前一位奇进偶舍
        double temp =  ComputeUtils.round(1.000015,5, BigDecimal.ROUND_HALF_EVEN);
        System.err.println(temp);
        double temp1 =  ComputeUtils.divide(1.1,1.3,4,BigDecimal.ROUND_HALF_UP);
        System.err.println(temp1);
    }
}
