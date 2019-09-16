/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.types;

import io.vertx.core.json.JsonArray;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author owen
 */
public class Polygon extends Geography{
    private List<Point> geog;
    
    public Polygon() {
        this.geog = new ArrayList<>();
        add(new JsonArray());
    }
    
    public Polygon(List<Point> geog) {
        this();
        this.geog = geog;
        geog.stream().forEach(p -> getJsonArray(0).add(p.toJson()));
    }

    @Override
    public Geography fromJson(JsonArray arr) {
        arr.stream()
           .map(d -> ((JsonArray)d))
           .findFirst()
           .stream()
           .flatMap(d -> d.stream())
           .map(d -> ((JsonArray)d))
           .map(d -> new Point().fromJson(d))
           .forEach(geog::add);
        getJsonArray(0).addAll((JsonArray)arr.remove(0));
        return this;
    }

    @Override
    public JsonArray toJson() {
        return this;
    }

    @Override
    public String toPostgis() {
        var join = new StringJoiner(",");
        geog.stream()
        .map(p -> p.toJson())
        .map(p -> p.getDouble(0)+" "+p.getDouble(1))
        .forEach(join::add);
        return "POLYGON(("+join.toString()+"))";
    }
    
    
}
