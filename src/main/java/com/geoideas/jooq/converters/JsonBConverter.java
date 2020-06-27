/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.converters;

import io.github.jklingsporn.vertx.jooq.shared.postgres.JSONBToJsonObjectConverter;
import io.github.jklingsporn.vertx.jooq.shared.postgres.PgConverter;
import io.vertx.core.json.JsonObject;
import org.jooq.Converter;
import org.jooq.JSONB;
import org.jooq.impl.IdentityConverter;

/**
 *
 * @author owen
 */
public class JsonBConverter  implements PgConverter<JsonObject,JSONB, JsonObject> {

    private static final IdentityConverter<JsonObject> identityConverter = new IdentityConverter<>(JsonObject.class);

    private static JSONBToJsonObjectConverter INSTANCE;
    public static JSONBToJsonObjectConverter getInstance() {
        return INSTANCE == null ? INSTANCE = new JSONBToJsonObjectConverter() : INSTANCE;
    }

    @Override
    public JsonObject from(JSONB t) {
        return t == null || t.data() == null ? null : new JsonObject(t.data());
    }

    @Override
    public JSONB to(JsonObject u) {
        return u == null ? null : JSONB.valueOf(u.encode());
    }

    @Override
    public Class<JSONB> fromType() {
        return JSONB.class;
    }

    @Override
    public Class<JsonObject> toType() {
        return JsonObject.class;
    }

    @Override
    public Converter<JsonObject, JsonObject> pgConverter() {
        return identityConverter;
    }
}