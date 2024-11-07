package sisosolsol.greenfire.common.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import sisosolsol.greenfire.post.model.type.PostType;

import java.sql.*;

public class PostTypeHandler implements TypeHandler<PostType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, PostType parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setObject(i, parameter.name(), Types.OTHER);
        } else {
            ps.setNull(i, Types.OTHER);
        }
    }

    @Override
    public PostType getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value != null ? PostType.valueOf(value) : null;
    }

    @Override
    public PostType getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value != null ? PostType.valueOf(value) : null;
    }

    @Override
    public PostType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value != null ? PostType.valueOf(value) : null;
    }
}
