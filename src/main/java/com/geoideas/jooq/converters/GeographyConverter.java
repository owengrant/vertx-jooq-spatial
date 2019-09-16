/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.converters;

import com.geoideas.jooq.types.Geography;
import com.geoideas.jooq.types.GeographyFactory;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.Converter;
import org.postgis.PGgeometry;

/**
 *
 * @author Anala
 */
public class GeographyConverter implements Converter<Object, Geography>{

    @Override
    public Geography from(Object databaseObject) {
        Geography geo = null;
        try {
            geo = databaseObject == null ?
                    null : new GeographyFactory().create(PGgeometry.geomFromString(databaseObject.toString()));
        } catch (SQLException ex) {
            Logger.getLogger(GeographyConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return geo;
    }

    @Override
    public Object to(Geography userObject) {
        return userObject == null ? null : userObject.toPostgis();
    }

    @Override
    public Class<Object> fromType() {
        return Object.class;
    }

    @Override
    public Class<Geography> toType() {
        return Geography.class;
    }
    
}
