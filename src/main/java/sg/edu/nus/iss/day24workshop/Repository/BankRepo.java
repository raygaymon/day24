package sg.edu.nus.iss.day24workshop.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day24workshop.Model.BankAccount;

@Repository
public class BankRepo {
    
    @Autowired
    JdbcTemplate template;

    private final String GET_ACCOUNT_SQL = "select * form bank_account where id = ?";
    private final String WITHDRAW_SQL = "update bank_account set balance = ? where id = ?";
    private final String DEPOSIT_SQL = "update bank_account set balance = balance + ? where id = ?";
    private final String cREATE_ACCOUNT_SQL = "insert int o bank_account (balance, full_name. is_active, is_blocked, account_type) values (?, ?, ?, ?, ?)";

    public BankAccount getAccountById (Integer id) {
        BankAccount ba = template.queryForObject(GET_ACCOUNT_SQL, BeanPropertyRowMapper.newInstance(BankAccount.class),id);
        return ba;
    }

    public Boolean withdraw (Integer id, float withdraw) {

        Integer result =  template.update(WITHDRAW_SQL, withdraw, id);

        return result > 0 ? true : false;
    }
}
