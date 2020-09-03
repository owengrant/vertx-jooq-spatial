package com.geoideas.jooq.generators;

import java.time.LocalDateTime;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.TypedElementDefinition;
import com.geoideas.jooq.types.Geography;
import io.github.jklingsporn.vertx.jooq.generate.classic.ClassicAsyncVertxGenerator;
import io.vertx.core.json.JsonObject;

public class Generator extends ClassicAsyncVertxGenerator {
    private final String DATETYPE = "java.lang.LocalDate";
     @Override
    protected boolean handleCustomTypeFromJson(TypedElementDefinition<?> column, String setter, String columnType, String javaMemberName, JavaWriter out) {
        if(isType(columnType, LocalDateTime.class)){
            out.tab(2).println("%s(json.getString(\"%s\")==null?null:LocalDateTime.parse(json.getString(\"%s\")));", setter, javaMemberName, javaMemberName);
            return true;
        }
        else if(columnType.equalsIgnoreCase(DATETYPE)){
             out.tab(2).println("%s(json.getString(\"%s\")==null?null:LocalDate.parse(json.getString(\"%s\")));", setter, javaMemberName, javaMemberName);
             return true;
         }
        else if(isType(columnType, Geography.class)){
            out.tab(2).println("%s(json.getJsonArray(\"%s\")==null?null: new Geography().fromJson(json.getJsonArray(\"%s\")));", setter, javaMemberName, javaMemberName);
            return true;
        }
        else if(isType(columnType, JsonObject.class)){
            out.tab(2).println("%s(json.getJsonObject(\"%s\")==null?null: json.getJsonObject(\"%s\"));", setter, javaMemberName, javaMemberName);
            return true;
        }
        return super.handleCustomTypeFromJson(column, setter, columnType, javaMemberName, out);
    }

    @Override
    protected boolean handleCustomTypeToJson(TypedElementDefinition<?> column, String getter, String columnType, String javaMemberName, JavaWriter out) {
        if(isType(columnType, LocalDateTime.class)){
            out.tab(2).println("json.put(\"%s\",%s()==null?null:%s().toString());", getJsonKeyName(column),getter,getter);
            return true;
        }
        else if(columnType.equalsIgnoreCase(DATETYPE)){
            out.tab(2).println("json.put(\"%s\",%s()==null?null:%s().toString());", getJsonKeyName(column),getter,getter);
            return true;
        }
        else if(isType(columnType, Geography.class)) {
            out.tab(2).println("json.put(\"%s\",%s()==null?null:%s().toJson());", getJsonKeyName(column), getter, getter);
            return true;
        }
        else if(isType(columnType, JsonObject.class)) {
            out.tab(2).println("json.put(\"%s\",%s());", getJsonKeyName(column), getter);
            return true;
        }
        return super.handleCustomTypeToJson(column, getter, columnType, javaMemberName, out);
    }
}
