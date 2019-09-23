/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.types;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.vertx.core.json.JsonArray;
import org.postgis.Geometry;

/**
 *
 * @author owen
 */
public class GeographyFactory {
    
    public enum Type {POINT, LINESTRING, POLYGON};
    
    public Geography createFromJson(JsonArray arr) {
        Geography geog = null;
        var v1 = arr.getValue(0);
        if(v1 instanceof JsonArray) {
            var v2 = ((JsonArray) v1).getValue(0);
            if(v2 instanceof JsonArray) {
                var v3 = ((JsonArray) v1).getValue(0);
                if(v3 instanceof JsonArray) {
                    geog = new Polygon().fromJson(arr);
                }
                else if((v1 instanceof Integer || v1 instanceof Double) && arr.size() == 2)
                    geog = new LineString().fromJson(arr);       
            }
        }
        else if((v1 instanceof Integer || v1 instanceof Double) && arr.size() == 2)
            geog = new Point().fromJson(arr);
        return geog;
    }
    
    public Geography create(Geometry geo) {
        Geography geog = null;
        switch(type(geo)) {
            case POINT: 
                geog = toPoint(geo);
                break;
            case LINESTRING:
                geog = toLineString(geo);
                break;
            case POLYGON:
                geog = toPolygon(geo);
                break;
            default:
                geog = null;
        }
        return geog;
    }
    
    private Type type(Geometry geo) {
        var geoString = geo.toString();
        var type = geoString.contains(";") ? geoString.split(";")[1] : geoString;
        return Type.valueOf(type.substring(0, type.indexOf("(")));
    }
    
    private Point toPoint(Geometry geo) {
        var p = (org.postgis.Point)geo;
        return new Point(p.x,p.y);
    }
    
    private LineString toLineString(Geometry geo) {
        var line = (org.postgis.LineString) geo;
        var points = Stream.of(line.getPoints())
            .map(this::toPoint)
            .map(Point::getGeog)
            .collect(Collectors.toList());
        return new LineString(new JsonArray(points));
    }
    
    private Polygon toPolygon(Geometry geo) {
        var line = (org.postgis.Polygon) geo;
        var points = Stream.of(line.getRing(0).getPoints())
            .map(this::toPoint)
            .collect(Collectors.toList());
        return new Polygon(points);
    }
    
    private double toDouble(String coor) {
        return Double.valueOf(coor.trim());
    }
}
