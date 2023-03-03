package sg.edu.nus.iss.app.workshop24.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.workshop24.model.Account;

@Repository
public class AccountsRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final String SQL_FIND_ALL_ACCOUNTS = "select * from accounts";

  private final String SQL_FIND_ACCOUNT_BY_ID = "select * from accounts where account_id like ?";

  private final String SQL_GET_BALANCE_BY_ID = "select balance from accounts where account_id= ?";

  private final String SQL_WITHDRAW = "update accounts set balance = balance - ? where account_id like ?";

  private final String SQL_DEPOSIT = "update accounts set balance = balance + ? where account_id like ?";


  public List<Account> findAllAccounts(){
    List<Account> accountsList = new ArrayList<>();
    accountsList = jdbcTemplate.query(SQL_FIND_ALL_ACCOUNTS,BeanPropertyRowMapper.newInstance(Account.class));
    return accountsList;
  }

  public boolean withdraw (String accountId, double amount){
    final int rowCount=jdbcTemplate.update(SQL_WITHDRAW,amount,accountId);
    return rowCount>0;
  }

  public boolean deposit (String accountId, double amount){
    final int rowCount = jdbcTemplate.update(SQL_DEPOSIT,amount,accountId);
    return rowCount>0;
  }

  public Optional<Double> getBalance(String accountId){
    final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BALANCE_BY_ID,accountId);
    return Optional.ofNullable(rs.next()?rs.getDouble("bala;nce"):null);
  }

}
