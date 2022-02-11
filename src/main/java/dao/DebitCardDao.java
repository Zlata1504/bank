package dao;

import org.example.entity.DebitCard;
import org.example.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DebitCardDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DebitCardDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public DebitCard show(){
        return jdbcTemplate.query("SELECT * FROM DebitCard",new BeanPropertyRowMapper<>
                (DebitCard.class)).stream().findAny().orElse(null);
    }
    public void save(DebitCard numberOfCard) {
        jdbcTemplate.update("INSERT INTO DebitCard VALUES(?,?,?,?) ", numberOfCard.getNumberOfCard(), numberOfCard.getBalance(),numberOfCard.getCvv(),
                numberOfCard.getExpirationDate());
    }
    public void update(String numberOfCard,DebitCard updateDebitCard) {
        jdbcTemplate.update("UPDATE DebitCard SET numberOfCard=?,cvv=?,expirationDate=?", updateDebitCard.getNumberOfCard(), updateDebitCard.getBalance(),updateDebitCard.getCvv(),
                updateDebitCard.getExpirationDate());
    }
    public void delete(String numberOfCard){
        jdbcTemplate.update("DELETE FROM DebitCard WHERE numberOfCard=?",numberOfCard);
    }
}
