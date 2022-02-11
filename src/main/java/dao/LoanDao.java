package dao;

import org.example.entity.Loan;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LoanDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Loan> show() {
        return jdbcTemplate.query("SELECT * FROM loan", new BeanPropertyRowMapper<>(Loan.class));
    }
    public void save(Loan loan) {
        jdbcTemplate.update("INSERT INTO Loan VALUES(?,?,?,?,?) ", loan.getSum(), loan.getCreditTerm(), loan.getDateOfIssue(),
                loan.getPercent(), loan.getMonthlyPayment());
    }
    public void update(double sum,Loan updateLoan) {
        jdbcTemplate.update("UPDATE Loan SET sum=?,creditTerm=?,dateOfIssue=?,percent =?", updateLoan.getSum()
                , updateLoan.getMonthlyPayment(), updateLoan.getPercent(), updateLoan.getDateOfIssue(), updateLoan.getCreditTerm());
    }
    public void delete(double sum){
        jdbcTemplate.update("DELETE FROM Loan WHERE sum=?",sum);
    }
}