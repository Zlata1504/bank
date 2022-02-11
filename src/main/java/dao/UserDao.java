package dao;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public void save(User user){
        jdbcTemplate.update("INSERT INTO User VALUES(1,?,?,?) ",user.getFirstName(),user.getLastName(),user.getEmail(),
                user.getRegistrationDate());
    }
    public void update(String email,User updateUser){
        jdbcTemplate.update("UPDATE User SET firstName=?,lastName=?,email=?,registrationDate=?",updateUser.getFirstName()
        ,updateUser.getLastName(),updateUser.getEmail(),updateUser.getRegistrationDate());
    }
    public void delete(String email){
        jdbcTemplate.update("DELETE FROM User WHERE email=?",email);
    }
}
