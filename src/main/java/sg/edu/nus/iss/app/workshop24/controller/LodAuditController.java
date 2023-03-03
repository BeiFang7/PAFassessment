package sg.edu.nus.iss.app.workshop24.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.app.workshop24.model.Transaction;
import sg.edu.nus.iss.app.workshop24.services.LogAuditService;

@RestController
@RequestMapping(path="/save")
public class LodAuditController {

  @Autowired
  private LogAuditService logAuditSvc;

  @PostMapping
  public ResponseEntity<String> saveTransaction(@RequestBody Transaction transaction){
    int insertCnt = logAuditSvc.save(transaction);
    if(insertCnt == 0){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .contentType(MediaType.APPLICATION_JSON)
          .body(transaction.toJSON().toString());
    }
    return ResponseEntity.status(HttpStatus.CREATED)
          .contentType(MediaType.APPLICATION_JSON)
          .body(transaction.toJSON().toString());
  }
  
}
