package sg.edu.nus.iss.day24workshop.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day24workshop.Exception.BANotFoundException;
import sg.edu.nus.iss.day24workshop.Model.BankAccount;

@Repository
public class BankRepo {
    
    @Autowired
    JdbcTemplate template;

    private final String GET_ACCOUNT_SQL = "select * from bank_account where id = ?";
    private final String WITHDRAW_SQL = "update bank_account set balance = ? where id = ?";
    private final String DEPOSIT_SQL = "update bank_account set balance = balance + ? where id = ?";
    private final String cREATE_ACCOUNT_SQL = "insert into bank_account (balance, full_name. is_active, is_blocked, account_type) values (?, ?, ?, ?, ?)";

    public BankAccount getAccountById (Integer id) {
        
        List<BankAccount> bas = template.query(GET_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(BankAccount.class),id);

        if (bas.isEmpty()){
            throw new BANotFoundException("ew");
        } else {
            return bas.get(0);
        }
    }

    public Boolean withdraw (Integer id, float withdraw) {

        Integer result =  template.update(WITHDRAW_SQL, withdraw, id);

        return result > 0 ? true : false;
    }

    public Boolean deposit (Integer id, float deposit) {
        
        return template.update(DEPOSIT_SQL, deposit, id) > 0 ? true : false;
    }

    public Boolean createAccount (BankAccount ba){

        return template.update(cREATE_ACCOUNT_SQL, ba.getBalance(), ba.getFullName(), ba.getIsActive(), ba.getIsBlocked(), ba.getAccountType()) > 0 ? true : false;
    }

}
