package co.com.mezubo.carrillo.roulette.storage.mapper;


import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
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
public class MzbRouletteBetsRowMapper implements RowMapper<MzbRouletteBets> {

    @Override
    public MzbRouletteBets mapRow(ResultSet rs, int arg1) throws SQLException {
        MzbRouletteBets emp = new MzbRouletteBets();
        emp.setId(rs.getString("id"));
        emp.setId_roulette(rs.getString("id_roulette"));
        emp.setNumbergame(rs.getInt("numbergame"));
        emp.setColorgame(rs.getString("colorgame"));
        emp.setMoney(rs.getBigDecimal("money"));
        emp.setUsergame(rs.getString("usergame"));
        return emp;
    }


}
