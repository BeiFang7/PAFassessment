package sg.edu.nus.iss.app.workshop24.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.app.workshop24.model.Account;
import sg.edu.nus.iss.app.workshop24.model.Transaction;
import sg.edu.nus.iss.app.workshop24.repositories.AccountsRepository;

//Service will wire in PO Repo and lineItem Repo
@Service
public class FundsTransferService {
  @Autowired
  private AccountsRepository accountRepo;

  public List<Account> findAllAccounts(){
    List<Account> accountsList = accountRepo.findAllAccounts();
    return accountsList;
  }

  @Transactional()
  public void transfer(String fromAccount, String toAccount, double amount, String comments){
    Transaction transaction = new Transaction();
    String transactionId = UUID.randomUUID().toString().substring(0,8);
    transaction.setTransactionId(transactionId);

    final Optional<Double> optFrom = accountRepo.getBalance(fromAccount);
    final Optional<Double> optTo = accountRepo.getBalance(toAccount);
    if(optFrom.isEmpty() || optTo.isEmpty() || (optFrom.get() < amount)){
      throw new IllegalArgumentException("Incorrect transfer amount.");
    }

    if (!(accountRepo.withdraw(fromAccount, amount) || accountRepo.deposit(toAccount, amount))){
      throw new IllegalArgumentException("Unable to perform transfer.");
    }
        
      

  }

}
