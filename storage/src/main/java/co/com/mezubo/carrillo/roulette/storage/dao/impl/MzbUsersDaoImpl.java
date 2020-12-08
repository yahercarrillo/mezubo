package co.com.mezubo.carrillo.roulette.storage.dao.impl;

import co.com.mezubo.carrillo.roulette.storage.dao.MzbUsersDao;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbUsers;
import co.com.mezubo.carrillo.roulette.storage.mapper.MzbRouletteRowMapper;
import co.com.mezubo.carrillo.roulette.storage.mapper.MzbUsersRowMapper;
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
public class MzbUsersDaoImpl implements MzbUsersDao {

    public MzbUsersDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<MzbUsers> findAll() {
        return template.query("select * from TBL_MZB_USERS", new MzbUsersRowMapper());
    }

    @Override
    public MzbUsers findById(String id) {
        final String sql = "select * from TBL_MZB_USERS where id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        MzbUsers resultSet = null;
        try {
            resultSet = template.queryForObject(sql, map, new MzbUsersRowMapper());
        } catch (DataIntegrityViolationException | IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return resultSet;
    }

    @Override
    public MzbUsers findByNickName(String nickname) {
        final String sql = "select * from TBL_MZB_USERS where nickname=:nickname";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", nickname);
        MzbUsers resultSet = null;
        try {
            resultSet = template.queryForObject(sql, map, new MzbUsersRowMapper());
        } catch (DataIntegrityViolationException | IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return resultSet;
    }

    @Override
    public void insert(MzbUsers emp) {
        final String sql = "insert into TBL_MZB_USERS(id, nickname , description,credit, datecreate, enable) values(:id,:nickname,:description, :credit,:datecreate,:enable)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("nickname", emp.getNickname().toUpperCase())
                .addValue("description", emp.getDescription())
                .addValue("credit", emp.getCredit())
                .addValue("datecreate", new Date())
                .addValue("enable", true);
        template.update(sql, param, holder);
    }

    @Override
    public void update(MzbUsers emp) {
        final String sql = "update TBL_MZB_USERS set credit=:credit, description=:description,dateupdate=:dateupdate  where nickname=:nickname";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nickname", emp.getNickname());
        map.put("credit", emp.getCredit());
        map.put("description", emp.getDescription());
        map.put("dateupdate", new Date());

        template.execute(sql, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
