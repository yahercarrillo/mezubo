package co.com.mezubo.carrillo.roulette.storage.mapper;


import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper de resultados de un ResultSet
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
public class MzbRouletteRowMapper implements RowMapper<MzbRoulette> {

    @Override
    public MzbRoulette mapRow(ResultSet rs, int arg1) throws SQLException {
        MzbRoulette emp = new MzbRoulette();
        emp.setId(rs.getString("id"));
        emp.setCode(rs.getString("code"));
        emp.setDescription(rs.getString("description"));
        emp.setDatecreate(rs.getDate("datecreate"));
        emp.setDateupdate(rs.getDate("dateupdate"));
        emp.setEnable(rs.getBoolean("enable"));
        return emp;
    }


}
