/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.types;

import io.vertx.core.json.JsonArray;

/**
 *
 * @author owen
 */
public class Point extends Geography{
    
    public Point(){}
    
    public Point(double x, double y) {
        add(x).add(y);
    }
    
    @Override
    public Point fromJson(JsonArray arr) {
        add(arr.getDouble(0)).add(arr.getDouble(1));
        return this;
    }

    @Override
    public String toPostgis() {
        return "Point("+ getDouble(0)+" "+ getDouble(1)+")";
    }

    public JsonArray getGeog() {
        return this;
    }

    public void setGeog(JsonArray geog) {
        addAll(geog);
    }
}
