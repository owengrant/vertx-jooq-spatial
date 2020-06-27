/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.bindings;

import io.github.jklingsporn.vertx.jooq.shared.postgres.JSONToJsonObjectBinding;
import io.github.jklingsporn.vertx.jooq.shared.postgres.JSONToJsonObjectConverter;
import io.vertx.core.json.JsonObject;
import org.jooq.Converter;
import org.jooq.JSON;

/**
 *
 * @author owen
 */
public class JsonBinding extends JSONToJsonObjectBinding {

    @Override
    public Converter<JSON, JsonObject> converter() {
        return JSONToJsonObjectConverter.getInstance();
    }
    
    
    
}
