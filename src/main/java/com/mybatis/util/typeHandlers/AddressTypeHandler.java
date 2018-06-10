package com.mybatis.util.typeHandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import com.mybatis.common.user.entity.Address;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class AddressTypeHandler extends BaseTypeHandler<Address> {
  @Override
  public Address getNullableResult(ResultSet rSet, String columnName) throws SQLException {
    return new Address(rSet.getString(columnName));
  }

  @Override
  public Address getNullableResult(ResultSet rSet, int columnIndex) throws SQLException {
    return new Address(rSet.getString(columnIndex));
  }

  @Override
  public Address getNullableResult(CallableStatement cStatement, int columnIndex)
      throws SQLException {
    return new Address(cStatement.getString(columnIndex));
  }

  @Override
  public void setNonNullParameter(PreparedStatement pStatement, int index, Address address,
      JdbcType jdbcType) throws SQLException {
    pStatement.setString(index, address.toString());
  }
}
