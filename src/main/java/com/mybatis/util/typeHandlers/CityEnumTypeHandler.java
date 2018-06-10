package com.mybatis.util.typeHandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.mybatis.common.user.enumObject.CityEnum;

public class CityEnumTypeHandler extends BaseTypeHandler<CityEnum> {
    @Override
    public CityEnum getNullableResult(ResultSet rSet, String columnName)
            throws SQLException {
        return CityEnum.getCityByName(rSet.getString(columnName));
    }

    @Override
    public CityEnum getNullableResult(ResultSet rSet, int columnIndex)
            throws SQLException {
        return CityEnum.getCityByName(rSet.getString(columnIndex));
    }

    @Override
    public CityEnum getNullableResult(CallableStatement cStatement, int columnIndex)
            throws SQLException {
        return CityEnum.getCityByName(cStatement.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement pStatement, int index,
            CityEnum citytest, JdbcType jdbcType) throws SQLException {
        pStatement.setString(index, citytest.getName());
    }
}