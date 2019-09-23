/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.types;

import io.vertx.core.json.JsonArray;
import java.util.StringJoiner;

/**
 *
 * @author owen
 */
public class LineString extends Geography{

    public LineString() {}
    
    public LineString(JsonArray geog) {
        addAll(geog);
    }
    
    @Override
    public Geography fromJson(JsonArray arr) {
        arr.stream()
           .map(d -> (JsonArray)d)
           .map(d -> new JsonArray().add(d.getDouble(0)).add(d.getDouble(1)))
           .forEach(this::add);
        return this;
    }

    @Override
    public String toPostgis() {
        var join = new StringJoiner(",");
        this.stream()
        .map(p -> (JsonArray)p)
        .map(p -> p.getDouble(0)+" "+p.getDouble(1))
        .forEach(join::add);
        return "LINESTRING("+join.toString()+")";
    }

    public JsonArray getgeog() {
        return this;
    }

    public void setgeog(JsonArray geog) {
        addAll(geog);
    }

}
