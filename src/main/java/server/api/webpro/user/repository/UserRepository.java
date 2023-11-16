package server.api.webpro.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import server.api.webpro.user.dto.UserResponse;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository  { //extends JpaRepository<User,Long>
    private final JdbcTemplate jdbcTemplate;

    public void save(String name, int age){
        String sql = "INSERT INTO user(name,age) VALUES(?,?)";
        jdbcTemplate.update(sql, name, age);
    }
    public List<UserResponse> get(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new UserResponse(
                rs.getLong("id"), rs.getString("name"),
                rs.getInt("age")));
    }
    public void update(String name,Long id){
        String sql = "update user set name=? where id=?";
        jdbcTemplate.update(sql,name, id);
    }
    public void delete(String name){
        String sql = "delete from user where name=?";
        jdbcTemplate.update(sql,name);
    }
    public boolean isUserNotExistById(Long id){
        String sql = "select * from user where id=?";
        return jdbcTemplate.query(sql,(rs,rownum)->0,id).isEmpty();
    }

    public boolean isUserNotExistByName(String name){
        String sql = "select * from user where name=?";
        return jdbcTemplate.query(sql,(rs,rownum)->0,name).isEmpty();
    }

}
