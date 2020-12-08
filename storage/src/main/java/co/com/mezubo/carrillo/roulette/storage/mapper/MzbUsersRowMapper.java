package co.com.mezubo.carrillo.roulette.storage.mapper;


import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbUsers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper de resultados de un ResultSet
 * para usuarios
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
public class MzbUsersRowMapper implements RowMapper<MzbUsers> {

    @Override
    public MzbUsers mapRow(ResultSet rs, int arg1) throws SQLException {
        MzbUsers emp = new MzbUsers();
        emp.setId(rs.getString("id"));
        emp.setNickname(rs.getString("nickname"));
        emp.setDescription(rs.getString("description"));
        emp.setCredit(rs.getBigDecimal("credit"));
        emp.setDatecreate(rs.getDate("datecreate"));
        emp.setDateupdate(rs.getDate("dateupdate"));
        emp.setEnable(rs.getBoolean("enable"));
        return emp;
    }


}
