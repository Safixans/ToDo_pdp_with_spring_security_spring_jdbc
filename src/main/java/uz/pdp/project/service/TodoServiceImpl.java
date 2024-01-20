package uz.pdp.project.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import uz.pdp.project.model.Todo;

import java.util.List;
import java.util.Map;

@Service
public class TodoServiceImpl implements TodoService {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public TodoServiceImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public List<Todo> getAllTodo() {
        String sql = "select * from application;";
        var rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);
        return  jdbcTemplate.query(sql, rowMapper);

    }

    @Override
    public Todo getById(int id) {

        String sql = "select * from application t where t.id=?";
        var rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);


    }

    @Override
    public void addTodo(Todo todo) {
//        String sql="insert into users(title,priority) values(?,?)";

        var params = new BeanPropertySqlParameterSource(Todo.class);
        KeyHolder keyHolder = simpleJdbcInsert
                .withTableName("application")
                .usingGeneratedKeyColumns("id")
                .usingColumns("title", "priority")
                .executeAndReturnKeyHolder(params);
        Map<String, Object> keys = keyHolder.getKeys();

        keys.forEach((k, v) -> System.out.println(k + "::" + v));

    }

    @Override
    public void update(int id, Todo todo) {
        String sql = "Update application set title=? , priority=? where id=?";
        jdbcTemplate.update(sql, todo.getTitle(), todo.getPriority(),id);
    }

    @Override
    public void delete(int id) {
        String sql="delete *  from application where id =? ";
        jdbcTemplate.update(sql,id);
    }


}
