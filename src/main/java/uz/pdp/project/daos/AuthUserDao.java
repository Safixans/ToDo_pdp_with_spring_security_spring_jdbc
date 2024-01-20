package uz.pdp.project.daos;

import lombok.NonNull;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.project.domains.AuthUser;

import java.util.List;
import java.util.Optional;

@Component
public class AuthUserDao {
private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthUserDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long save(AuthUser user) {
        String sql="insert into application (username,password) values(:username,:password)";
        var params = new MapSqlParameterSource()
                .addValue("username",user.getUsername())
                .addValue("password",user.getPassword());

        var keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
        return (Long) keyHolder.getKeys().get("id");
    }

    public List<AuthUser> getAllUsers() {
        var sql = "select * from application order by id";
        var mapper = BeanPropertyRowMapper.newInstance(AuthUser.class);
        return namedParameterJdbcTemplate.query(sql, mapper);
    }


    public Optional<AuthUser> findByUsername(@NonNull String username) {
        var sql = "select * from application t where t.username = :username and active = true";
        var paramSource = new MapSqlParameterSource().addValue("username", username);
        var rowMapper = BeanPropertyRowMapper.newInstance(AuthUser.class);
        try {
            var authUser = namedParameterJdbcTemplate.queryForObject(sql, paramSource, rowMapper);
            return Optional.of(authUser);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
