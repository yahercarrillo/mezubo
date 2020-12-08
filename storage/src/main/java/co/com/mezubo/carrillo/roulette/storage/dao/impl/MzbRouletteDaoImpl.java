package co.com.mezubo.carrillo.roulette.storage.dao.impl;

import co.com.mezubo.carrillo.roulette.storage.dao.MzbRouletteDao;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.mapper.MzbRouletteRowMapper;
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
import java.util.*;

@Repository
public class MzbRouletteDaoImpl implements MzbRouletteDao {

    public MzbRouletteDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<MzbRoulette> findAll() {
        return template.query("select * from TBL_MZB_ROULETTE", new MzbRouletteRowMapper());
    }

    @Override
    public MzbRoulette findById(String id) {
        final String sql = "select * from TBL_MZB_ROULETTE where id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        MzbRoulette resultSet = null;
        try {
            resultSet = template.queryForObject(sql, map, new MzbRouletteRowMapper());
        } catch (DataIntegrityViolationException | IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return resultSet;
    }

    @Override
    public MzbRoulette findByCode(String code) {
        final String sql = "select * from TBL_MZB_ROULETTE where code=:code";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);

        MzbRoulette resultSet = null;
        try {
            resultSet = template.queryForObject(sql, map, new MzbRouletteRowMapper());
        } catch (DataIntegrityViolationException | IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return resultSet;
    }

    @Override
    public void insert(MzbRoulette emp) {
        final String sql = "insert into TBL_MZB_ROULETTE(id, code , description,datecreate, enable) values(:id,:code,:description,:datecreate,:enable)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("code", emp.getCode())
                .addValue("description", emp.getDescription())
                .addValue("datecreate", new Date())
                .addValue("enable", true);
        template.update(sql, param, holder);

    }

    @Override
    public void update(MzbRoulette emp) {
        final String sql = "update TBL_MZB_ROULETTE set id=:id, code=:code, description=:description,dateupdate=:dateupdate, enable=:enable  where id=:id";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id", emp.getId());
        map.put("code", emp.getCode());
        map.put("description", emp.getDescription());
        map.put("dateupdate", emp.getDateupdate());
        map.put("enable", emp.isEnable());


        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

}
