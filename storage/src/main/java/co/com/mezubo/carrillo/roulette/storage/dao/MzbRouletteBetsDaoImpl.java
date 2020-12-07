package co.com.mezubo.carrillo.roulette.storage.dao;

import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
import co.com.mezubo.carrillo.roulette.storage.mapper.MzbRouletteBetsRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Servicio de apuestas
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
@Repository
public class MzbRouletteBetsDaoImpl implements MzbRouletteBetsDao {

    public MzbRouletteBetsDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<MzbRouletteBets> findAll() {
        return template.query("select * from TBL_MZB_ROULETTE_BETS", new MzbRouletteBetsRowMapper());
    }

    @Override
    public MzbRouletteBets findById(String id) {
        final String sql = "select * from TBL_MZB_ROULETTE_BETS where id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        MzbRouletteBets resultSet = null;
        try {
            resultSet = template.queryForObject(sql, map, new MzbRouletteBetsRowMapper());
        } catch (DataIntegrityViolationException | IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return resultSet;
    }

    @Override
    public MzbRouletteBets findByNumberGame(int numberGame) {
        final String sql = "select * from TBL_MZB_ROULETTE_BETS where numbergame=:numbergame";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("numbergame", numberGame);

        MzbRouletteBets resultSet = null;
        try {
            resultSet = template.queryForObject(sql, map, new MzbRouletteBetsRowMapper());
        } catch (DataIntegrityViolationException | IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return resultSet;
    }

    @Override
    public MzbRouletteBets findByUserGame(String usergame) {
        final String sql = "select * from TBL_MZB_ROULETTE_BETS where usergame=:usergame";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("usergame", usergame);

        MzbRouletteBets resultSet = null;
        try {
            resultSet = template.queryForObject(sql, map, new MzbRouletteBetsRowMapper());
        } catch (DataIntegrityViolationException | IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return resultSet;
    }

    @Override
    public List<MzbRouletteBets> findByUsersByColorGame(String id_roulette, String colorgame) {
        final String sql = "select * from TBL_MZB_ROULETTE_BETS where id_roulette=:id_roulette and colorgame=:colorgame";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id_roulette", id_roulette);
        map.put("colorgame", colorgame);

        List<MzbRouletteBets> resultSet = null;
        try {
            resultSet = template.query(sql, map, new MzbRouletteBetsRowMapper());
        } catch (DataIntegrityViolationException | IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return resultSet;
    }

    @Override
    public void insert(MzbRouletteBets emp) {
        final String sql = "insert into TBL_MZB_ROULETTE_BETS(id, id_roulette , numbergame,colorgame, money,usergame) values(:id,:id_roulette,:numbergame,:colorgame,:money,:usergame)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("id_roulette", emp.getId_roulette())
                .addValue("numbergame", emp.getNumbergame())
                .addValue("colorgame", emp.getColorgame())
                .addValue("money", emp.getMoney())
                .addValue("usergame", emp.getUsergame());
        template.update(sql, param, holder);

    }

    @Override
    public void update(MzbRouletteBets emp) {

        final String sql = "update TBL_MZB_ROULETTE_BETS set id_roulette=:id_roulette, numbergame=:numbergame, colorgame=:colorgame,money=:money, usergame=:usergame  where id=:id";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id", emp.getId());
        map.put("id_roulette", emp.getId_roulette());
        map.put("numbergame", emp.getNumbergame());
        map.put("colorgame", emp.getColorgame());
        map.put("money", emp.getMoney());
        map.put("usergame", emp.getUsergame());


        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

}
