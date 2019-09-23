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
public class Geography extends JsonArray{
    
    public Geography fromJson(JsonArray arr) {
        if(arr.isEmpty()) return new Geography();
        Geography geog = null;
        var v1 = arr.getValue(0);
        if(v1 instanceof JsonArray) {
            var v2 = ((JsonArray) v1).getValue(0);
            if(v2 instanceof JsonArray) {
                geog = new Polygon().fromJson(arr);
            }
            else {
                geog = new LineString().fromJson(arr);
            }
        }
        else {
            geog = new Point().fromJson(arr);
        }
        return geog;
    }
    
    public JsonArray toJson() {
        return new JsonArray().addAll(this);
    }
    
    public String toPostgis() {
        return "";
    }
    
    @Override
    public String toString() {
        return toPostgis();
    }
    
    @Override
    public JsonArray copy(){
        return this;
    }
}
