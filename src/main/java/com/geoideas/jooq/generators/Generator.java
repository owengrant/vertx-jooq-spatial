package com.geoideas.jooq.generators;

import io.github.jklingsporn.vertx.jooq.generate.classic.ClassicAsyncVertxGenerator;
import java.time.LocalDateTime;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.TypedElementDefinition;
import com.geoideas.jooq.types.Geography;
import java.math.BigDecimal;

public class Generator extends ClassicAsyncVertxGenerator {
     @Override
    protected boolean handleCustomTypeFromJson(TypedElementDefinition<?> column, String setter, String columnType, String javaMemberName, JavaWriter out) {
        if(isType(columnType, LocalDateTime.class)){
            out.tab(2).println("%s(json.getString(\"%s\")==null?null:LocalDateTime.parse(json.getString(\"%s\")));", setter, javaMemberName, javaMemberName);
            return true;
        }
        else if(isType(columnType, Geography.class)){
            out.tab(2).println("%s(json.getJsonArray(\"%s\")==null?null: new Geography().fromJson(json.getJsonArray(\"%s\")));", setter, javaMemberName, javaMemberName);
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
        else if(isType(columnType, Geography.class)){
            out.tab(2).println("json.put(\"%s\",%s()==null?null:%s().toJson());", getJsonKeyName(column),getter,getter);
            return true;
        }
        return super.handleCustomTypeToJson(column, getter, columnType, javaMemberName, out);
    }
}
