package com.stevenw.compute;

import com.stevenw.AQS.AqsDemo;

import javax.xml.bind.Element;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author stevenw
 * @date 2019/7/19
 * <p>
 * 各类运算工具
 * <p>
 * <p>double直接进行运算，会存在精度问题，需要使用{@link BigDecimal}
 * 参考<a>https://blog.csdn.net/jdsjlzx/article/details/47978753</a>
 */
public class ComputeUtils {
    /**
     * 计算double相加
     *
     * @param augend
     * @param addend
     * @return
     */
    public static double sum(double augend, double addend) {
        BigDecimal a = new BigDecimal(Double.toString(augend));
        BigDecimal b = new BigDecimal(Double.toString(addend));
        return a.add(b).doubleValue();
    }

    /**
     * 计算double相减
     *
     * @param subtracted
     * @param bubtraction
     * @return
     */
    public static double sub(double subtracted, double bubtraction) {
        BigDecimal a = new BigDecimal(Double.toString(subtracted));
        BigDecimal b = new BigDecimal(Double.toString(bubtraction));
        return a.subtract(b).doubleValue();
    }

    /**
     * 计算double相乘
     *
     * @param multiplicand
     * @param multiplier
     * @return
     */
    public static double multiply(double multiplicand, double multiplier) {
        BigDecimal a = new BigDecimal(Double.toString(multiplicand));
        BigDecimal b = new BigDecimal(Double.toString(multiplier));
        return a.multiply(b).doubleValue();
    }

    /**
     * 计算double相除
     *
     * <p>需要指定精度和舍入法
     * @param divided
     * @param divisor
     * @return
     */
    public static double divide(double divided, double divisor, int scale,int roundingMode){
        BigDecimal a = new BigDecimal(Double.toString(divided));
        BigDecimal b = new BigDecimal(Double.toString(divisor));
        return a.divide(b,scale,roundingMode).doubleValue();
    }

    /**
     * 计算double相除
     *
     * <p>需要指定舍入法
     * @param divided
     * @param divisor
     * @param roundingMode
     * @return
     */
    public static double divide(double divided, double divisor, int roundingMode){
        BigDecimal a = new BigDecimal(Double.toString(divided));
        BigDecimal b = new BigDecimal(Double.toString(divisor));
        return a.divide(b,roundingMode).doubleValue();
    }



    /**
     * double数据取精度
     *
     * @param num1
     * @return
     */
    public static double round(double num1, int scale, int roundingMode) {
        BigDecimal bigDecimal = new BigDecimal(num1);
        bigDecimal = bigDecimal.setScale(scale, roundingMode);
        double reslut = bigDecimal.doubleValue();
        return reslut;
    }


        public static void main(String[] args) {

        }


}
