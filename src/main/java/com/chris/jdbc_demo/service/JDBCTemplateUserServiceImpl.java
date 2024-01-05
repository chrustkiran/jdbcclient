package com.chris.jdbc_demo.service;

import com.chris.jdbc_demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class JDBCTemplateUserServiceImpl implements UserService{
    private final JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = (res, rowNum) -> User.builder().
            id(res.getString("id"))
            .firstName(res.getString("first_name"))
            .lastName(res.getString("last_name")).build();
    @Override
    public Collection<User> findAll() {
        String query = "select * from t_user";
        User.builder().build();
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public void create(User user) {
        String query = "insert into t_user values(?,?,?)";
        int success = jdbcTemplate.update(query, user.getId(),
                user.getFirstName(), user.getLastName());
        if (success == 1) log.info("user created, with id ", user.getId());
    }

    @Override
    public void update(User user, String id) {
        String query = "update t_user set first_name=? and last_name=? where id=?";
        int update = jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), id);
        if (update == 1) log.info("user updated with user id " + id);
    }

    @Override
    public void delete(String id) {
        String query = "delete from t_user where id=?";
        int delete = jdbcTemplate.update(query, id);
        if (delete == 1) log.info("user was deleted with user id " + id);
    }
}
