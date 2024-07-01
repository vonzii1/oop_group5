/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.sf.jasperreports.engine.data;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JRResultSetDataSource implements JRDataSource {
    private ResultSet resultSet;
    private boolean hasNext;

    public JRResultSetDataSource(ResultSet rs) {
        this.resultSet = rs;
        try {
            this.hasNext = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            this.hasNext = false;
        }
    }

    @Override
    public boolean next() throws JRException {
        boolean currentHasNext = hasNext;
        try {
            if (hasNext) {
                hasNext = resultSet.next();
            }
        } catch (SQLException e) {
            throw new JRException(e);
        }
        return currentHasNext;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        try {
            return resultSet.getObject(jrField.getName());
        } catch (SQLException e) {
            throw new JRException(e);
        }
    }
}
