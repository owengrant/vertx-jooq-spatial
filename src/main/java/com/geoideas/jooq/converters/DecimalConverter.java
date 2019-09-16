/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.converters;

import java.math.BigDecimal;
import org.jooq.Converter;

/**
 *
 * @author owen
 */
public class DecimalConverter implements Converter<BigDecimal, Double> {

    @Override
    public Double from(BigDecimal arg0) {
        System.out.println("killer forst");
        System.out.println(arg0.doubleValue());
        System.out.println("killer");
        return arg0.doubleValue();
    }

    @Override
    public BigDecimal to(Double arg0) {
        System.out.println(arg0);
        return new BigDecimal(arg0);
    }

    @Override
    public Class<BigDecimal> fromType() {
        return BigDecimal.class;
    }

    @Override
    public Class<Double> toType() {
        return Double.class;
    }
    
}
