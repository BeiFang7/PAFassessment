package sg.edu.nus.iss.app.workshop24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.app.workshop24.model.Account;
import sg.edu.nus.iss.app.workshop24.model.Transaction;
import sg.edu.nus.iss.app.workshop24.services.FundsTransferService;

@Controller
public class FundsTransferController {

  @Autowired
  private FundsTransferService accountSvc;
  
  @GetMapping(path={"/","index.html"})
  public String getTransferPage(Model model){
    System.out.println(">>>>>>> Inside GET MAPPING");
    List<Account> accountsList = accountSvc.findAllAccounts();
    System.out.println(">>>>>>> accountsList: " + accountsList.toString());
    model.addAttribute("accounts", accountsList);
    model.addAttribute("transaction", new Transaction());
    return "index";
  }

  @PostMapping(path="/transfer", consumes="application/x-www-form-urlencoded", produces = "text/html")
  public String postTransaction(@Valid Transaction transaction, BindingResult binding, Model model, HttpSession session){
    System.out.println(">>>>>>> Inside POST MAPPING");
    if(binding.hasErrors()){
      System.out.println(">>>>>>> Binding errors: "+ binding.hasErrors());
      model.addAttribute("transaction",transaction);
      return "index";
    } else{
      model.addAttribute("transaction", transaction);
      session.invalidate();
    }
    return "success-transfer";
    
  }
}
