package com.chris.jdbc_demo.service;

import com.chris.jdbc_demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class JDBCClientUserServiceImpl implements UserService {
    private final JdbcClient jdbcClient;

    //if we need more controls, it's same as JDBC Template
    RowMapper<User> userRowMapper = (res, rn) -> User.builder().id(res.getString("id"))
            .firstName(res.getString("first_name")).lastName(res.getString("last_name")).build();
    @Override
    public Collection<User> findAll() {
        String sql = "select * from t_user";
        return jdbcClient.sql(sql).query(User.class).list();

        //if we need more controls
        //return jdbcClient.sql("select * from t_user").query(userRowMapper).list();
    }

    @Override
    public void create(User user) {
        String sql = "insert into t_user values(?,?,?)";
        int success = jdbcClient.sql(sql).params(user.getId(), user.getFirstName(), user.getLastName()).update();
        if (success == 1) log.info("user created, with id " + user.getId());
    }

    @Override
    public void update(User user, String id) {
        String sql = "update t_user set first_name=? and last_name=? where id=?";
        int update = jdbcClient.sql(sql).params(user.getFirstName(), user.getLastName(), id).update();
        if (update == 1) log.info("user updated with user id " + id);
    }

    @Override
    public void delete(String id) {
        String query = "delete from t_user where id=?";
        int delete = jdbcClient.sql(id).param(id).update();
        if (delete == 1) log.info("user was deleted with user id " + id);
    }
}
