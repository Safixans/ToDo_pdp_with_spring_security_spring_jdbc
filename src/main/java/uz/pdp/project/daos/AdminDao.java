package uz.pdp.project.daos;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class AdminDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AdminDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean activate(Long id){
        String sql="update application set active=true where id=:id";
        var params =new MapSqlParameterSource()
                .addValue("id",id);
        jdbcTemplate.update(sql,params);
        return true;
    }

    public boolean deactivate(Long id){
        String sql="update application set active=false where id=:id";
        var params =new MapSqlParameterSource()
                .addValue("id",id);
        jdbcTemplate.update(sql,params);
        return true;
    }

}
