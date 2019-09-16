/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geoideas.jooq.bindings;

import com.geoideas.jooq.converters.GeographyConverter;
import com.geoideas.jooq.types.Geography;
import java.sql.SQLException;
import org.jooq.Binding;
import org.jooq.BindingGetResultSetContext;
import org.jooq.BindingGetSQLInputContext;
import org.jooq.BindingGetStatementContext;
import org.jooq.BindingRegisterContext;
import org.jooq.BindingSQLContext;
import org.jooq.BindingSetSQLOutputContext;
import org.jooq.BindingSetStatementContext;
import org.jooq.Converter;

/**
 *
 * @author owen
 */
public class GeographyBinding implements Binding<Object, Geography>{

    @Override
    public Converter<Object, Geography> converter() {
        return new GeographyConverter();
    }

    @Override
    public void sql(BindingSQLContext<Geography> ctx) throws SQLException {
        ctx.render().sql("?::geography");
    }

    @Override
    public void set(BindingSetStatementContext<Geography> ctx) throws SQLException {
        ctx.statement().setObject(ctx.index(), ctx.convert(converter()).value());
    }

    @Override
    public void get(BindingGetResultSetContext<Geography> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.resultSet().getObject(ctx.index()));
    }

    @Override
    public void get(BindingGetStatementContext<Geography> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.statement().getObject(ctx.index()));
    }

    @Override
    public void get(BindingGetSQLInputContext<Geography> ctx) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void register(BindingRegisterContext<Geography> ctx) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void set(BindingSetSQLOutputContext<Geography> ctx) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
